package com.capinfo.framework.web.controller.login;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.common.util.LogUtil;
import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.service.*;
import com.capinfo.framework.web.vo.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.druid.DruidPlugin;
import jfinal.model.MProject;
import jfinal.model.MonitoringCityData;
import jfinal.model.MonitoringProjectData;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.*;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {

    @Resource
    private UserService userService;
    @Resource
    private UserRoleReleService userRoleReleService;
    @Resource
    private RoleMenuReleService roleMenuReleService;


    private DruidPlugin dp;
    private ActiveRecordPlugin arp;
    private String dsName = Calendar.getInstance().getTimeInMillis() + "";

    @Value("#{c['alert.pm25.orange']}")
    private String pm25AlertOrange;
    @Value("#{c['alert.pm25.red']}")
    private String pm25AlertRed;
    @Value("#{c['alert.pm10.orange']}")
    private String pm10AlertOrange;
    @Value("#{c['alert.pm10.red']}")
    private String pm10AlertRed;
    @Value("#{c['alert.dust.orange']}")
    private String dustAlertOrange;
    @Value("#{c['alert.dust.red']}")
    private String dustAlertRed;
    @Value("#{c['alert.noise.orange']}")
    private String noiseAlertOrange;
    @Value("#{c['alert.noise.red']}")
    private String noiseAlertRed;
    @Value("#{c['jdbc.url']}")
    private String dburl;
    @Value("#{c['jdbc.username']}")
    private String dbuser;
    @Value("#{c['jdbc.password']}")
    private String dbpass;

    private void createNew() {
        dp = new DruidPlugin(dburl, dbuser, dbpass);
        dsName = Calendar.getInstance().getTimeInMillis() + "";
        arp = new ActiveRecordPlugin(dsName, dp);
        arp.addMapping("ibs_web_data", MonitoringCityData.class);
        arp.addMapping("ibs_monitoring_project", MProject.class);
        arp.addMapping("ibs_monitoring_project_data", MonitoringProjectData.class);
    }

    private boolean needReConnect(DruidPlugin dp) {
        boolean reConnect = false;
        if (dp == null) {
            return true;
        }
        if (dp.getDataSource() == null) {
            return true;
        }
        try {
            if (dp.getDataSource().getConnection().isClosed()) {
                return true;
            }
        } catch (SQLException e) {
            return true;
        }
        return reConnect;
    }

    //@InitBinder
    public void init() {
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        dp = (DruidPlugin) webApplicationContext.getServletContext().getAttribute("JFinalPlugin");
        if (dp != null) {
            arp = (ActiveRecordPlugin) webApplicationContext.getServletContext().getAttribute("JFinalIndex");
            arp.stop();
            dp.stop();
            if (needReConnect(dp)) {
                createNew();
            }
            dp.start();
            arp.start();
            webApplicationContext.getServletContext().setAttribute("JFinalPlugin", dp);
            webApplicationContext.getServletContext().setAttribute("JFinalIndex", arp);
        } else {
            dp = new DruidPlugin(dburl, dbuser, dbpass);
            arp = new ActiveRecordPlugin(dsName, dp);
            arp.addMapping("ibs_web_data", MonitoringCityData.class);
            arp.addMapping("ibs_monitoring_project", MProject.class);
            arp.addMapping("ibs_monitoring_project_data", MonitoringProjectData.class);
            dp.start();
            arp.start();
            webApplicationContext.getServletContext().setAttribute("JFinalPlugin", dp);
            webApplicationContext.getServletContext().setAttribute("JFinalIndex", arp);
        }
    }

    @RequestMapping(value = "/index", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginPage(Model model) throws Exception {
        return "login";
    }

    private String getCityCode(String str, String city) {
        return getCityCode(str, city, "weaid");
    }

    private String getCityCode(String str, String city, String field) {
        if (str.indexOf(city) > 0) {
            String myJson1 = str.substring(0, str.indexOf(city));
            String myJson2 = str.substring(str.indexOf(city));
            String myJsonStr = myJson1.substring(myJson1.lastIndexOf("{")) + myJson2.substring(0, myJson2.indexOf("}") + 1);
            LogUtil.printLog("info", "JsonStr:" + myJsonStr);
            JSONObject myJson = JSONObject.fromObject(myJsonStr);
            return myJson.getString(field);
        }
        return null;
    }

    private int parseInt(String num) {
        int result = 0;
        try {
            result = Integer.parseInt(num);
        } catch (Exception e) {
        }
        return result;
    }

    @RequestMapping(value = "/login", method = {RequestMethod.POST, RequestMethod.GET})
    public String loginTo(Model model, HttpSession session, UserQueryBean queryBean, String valiCode) throws Exception {
        init();
//		String sessionCode = ValidationCodeHelper.getValidationCodeFromSession(session);
//		if (valiCode == null || !valiCode.equals(sessionCode)) {
//			model.addAttribute("msg", "验证码输入错误");
//			return "login";
//		}
        Object object = userService.login(queryBean);
        if (object instanceof User) {
            User loginUser = (User) object;
            session.setAttribute("userid", loginUser.getId());
            session.setAttribute("username", loginUser.getUserName());
            session.setAttribute("usertype", loginUser.getUserType());
            String province = loginUser.getProvince();
            model.addAttribute("province", province);
            String city = loginUser.getCity();
            model.addAttribute("city", city);
            String district = loginUser.getDistrict();
            model.addAttribute("district", district);
            String code = "1";
            String cityId = "";
            String districtId = "";
            String realID = "101010100";
            URL ccUrl = LoginController.class.getResource("/CityCode.json");
            String ccJson = "{}";
            try {
                ccJson = FileUtils.readFileToString(FileUtils.toFile(ccUrl), "UTF8");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            if (city != null) {
                code = getCityCode(ccJson, city);
                cityId = getCityCode(ccJson, city, "cityid");
                if (cityId != null)
                    if (cityId.length() > 0) {
                        realID = cityId;
                        //LogUtil.printLog("INFO","C to realID:"+realID);
                    }
            }
            if (district != null) {
                code = getCityCode(ccJson, district);
                districtId = getCityCode(ccJson, district, "cityid");
                if (districtId != null)
                    if (districtId.length() > 0) {
                        realID = districtId;
                        //LogUtil.printLog("INFO","D to realID:"+realID);
                    }
            }
            //获取天气数据
            //String weatherAPI = "http://api.k780.com:88/?app=weather.today&weaid=" + code + "&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
            long timeTicket=Calendar.getInstance().getTimeInMillis();
            String weatherAPI="http://d1.weather.com.cn/dingzhi/" + realID + ".html?_=" + timeTicket;
            String aqiAPI = "http://d1.weather.com.cn/aqi_all/" + realID + ".html?_=" + timeTicket;
            try {
                LogUtil.printLog("INFO","获取天气数据:"+weatherAPI);
                String body = Jsoup.connect(weatherAPI).header("Referer", "http://www.weather.com.cn/textFC/hb.shtml").ignoreContentType(true).execute().body();
                String weatherResult=body.substring(body.indexOf("={")+1,body.indexOf("};")+1);
                JSONObject jsonObject = JSONObject.fromObject(weatherResult);
                model.addAttribute("weather", jsonObject);
                LogUtil.printLog("INFO","获取AQI数据:"+aqiAPI);
                String aqiStr = Jsoup.connect(aqiAPI).header("Referer", "http://www.weather.com.cn/air/").ignoreContentType(true).execute().body();
                if (aqiStr != null) {
                    aqiStr = aqiStr.substring(aqiStr.indexOf("{"), aqiStr.lastIndexOf("}") + 1);
                }
                //LogUtil.printLog("INFO","AirResult:"+aqiStr);
                JSONObject aqi = JSONObject.fromObject(aqiStr);
                JSONArray datas = aqi.getJSONArray("data");
                //LogUtil.printLog("INFO","DatasResult:"+datas.size());
                JSONObject effective = null;
                for (Object data : datas) {
                    JSONObject value = JSONObject.fromObject(data);
                    if (value.get("t1").toString().length() > 0) {
                        effective = value;
                        //LogUtil.printLog("INFO","Result:"+effective);
                    }
                }
                model.addAttribute("air", effective);
            } catch (Exception e) {
                LogUtil.printLog("error", "采集空气质量数据时出错:" + e.getMessage());
            }
//            MonitoringCityData cityData = MonitoringCityData.dao.findFirst("select * from ibs_web_data where code='150100' order by timestamp desc");
//            if (cityData != null) {
//                model.addAttribute("cityData", cityData);
//            }
            //获取用户摘要
            String sql_additon = "";
            if (province != null) {
                sql_additon += " and province='" + province + "'";
            }
            if (city != null) {
                sql_additon += " and city='" + city + "'";
            }
            if (district != null) {
                sql_additon += " and district='" + district + "'";
            }
            String sql_project = "select count(*) from ibs_monitoring_project where 1=1 " + sql_additon;
            String sql_device = "select count(*) from ibs_monitoring_device where project_id in(select id from ibs_monitoring_project where 1=1 " + sql_additon + ")";
            String sql_device_online = "select count(*) from ibs_monitoring_device where dev_status=1 and project_id in(select id from ibs_monitoring_project where 1=1 " + sql_additon + ")";
            String sql_device_offline = "select count(*) from ibs_monitoring_device where dev_status=0 and project_id in(select id from ibs_monitoring_project where 1=1 " + sql_additon + ")";
            String sql_alarm = "select  count(*) from (SELECT	(SELECT g.id FROM ibs_monitoring_device_group g WHERE g.project_id=p.id limit 1) group_id 		" +
                    ",(SELECT alarm.result FROM ibs_alarm alarm,ibs_monitoring_device device 		" +
                    "where alarm.project_id = p.id 		" +
                    "and device.device_group_id = alarm.device_group_id 		" +
                    "and device.dev_status = 1 		and alarm.result = 0 		" +
                    "ORDER BY alarm.create_time DESC limit 1) alarm_result,p.*  " +
                    "FROM ibs_monitoring_project p where 1=1 " + sql_additon + ") t     " +
                    "where alarm_result = 0";
            int projectNums = Db.use(dsName).queryLong(sql_project).intValue();
            int deviceNums = Db.use(dsName).queryLong(sql_device).intValue();
            int deviceOnlineNums = Db.use(dsName).queryLong(sql_device_online).intValue();
            int deviceOfflineNums = Db.use(dsName).queryLong(sql_device_offline).intValue();
            int deviceAlarmNums = Db.use(dsName).queryLong(sql_alarm).intValue();
            model.addAttribute("projectNums", projectNums);
            model.addAttribute("deviceNums", deviceNums);
            model.addAttribute("deviceOnlineNums", deviceOnlineNums);
            model.addAttribute("deviceOfflineNums", deviceOfflineNums);
            model.addAttribute("deviceAlarmNums", deviceAlarmNums);
            //获取排名数据
            try {
                List<MonitoringProjectData> pm25Top10 = MonitoringProjectData.dao.find("select d.*,p.pro_name from ibs_monitoring_project as p left join  ibs_monitoring_project_data as d   on d.pid=p.id order by pm25 desc limit 10");
                List<String> pm25Category = new ArrayList<String>();
                String pm25Data = "[";
                if (pm25Top10 != null) {
                    Collections.reverse(pm25Top10);
                    for (MonitoringProjectData pm25 : pm25Top10) {
                        pm25Category.add("'" + pm25.getStr("pro_name") + "'");
                        int pm25value = pm25.getInt("pm25");
                        pm25Data += "{";
                        String color = "green";
                        if (pm25value > parseInt(pm25AlertRed)) {
                            color = "red";
                        } else if (pm25value > parseInt(pm25AlertOrange)) {
                            color = "yellow";
                        }
                        pm25Data += "value:" + pm25value + ",itemStyle:{normal:{color:'" + color + "'}}";
                        pm25Data += "},";
                    }
                    if (",".equals(pm25Data.substring(pm25Data.length() - 1, pm25Data.length()))) {
                        pm25Data = pm25Data.substring(0, pm25Data.length() - 1);
                    }
                }
                pm25Data += "]";
                model.addAttribute("pm25Category", pm25Category);
                model.addAttribute("pm25Data", pm25Data);
            } catch (NullPointerException e) {
            }
            try {
                List<MonitoringProjectData> pm10Top10 = MonitoringProjectData.dao.find("select d.*,p.pro_name from ibs_monitoring_project as p left join ibs_monitoring_project_data as d on d.pid=p.id order by pm10 desc limit 10");
                List<String> pm10Category = new ArrayList<String>();
                String pm10Data = "[";
                if (pm10Top10 != null) {
                    Collections.reverse(pm10Top10);
                    for (MonitoringProjectData pm10 : pm10Top10) {
                        pm10Category.add("'" + pm10.getStr("pro_name") + "'");
                        int pm10value = pm10.getInt("pm10");
                        pm10Data += "{";
                        String color = "green";
                        if (pm10value > parseInt(pm10AlertRed)) {
                            color = "red";
                        } else if (pm10value > parseInt(pm10AlertOrange)) {
                            color = "yellow";
                        }
                        pm10Data += "value:" + pm10value + ",itemStyle:{normal:{color:'" + color + "'}}";
                        pm10Data += "},";
                    }
                    if (",".equals(pm10Data.substring(pm10Data.length() - 1, pm10Data.length()))) {
                        pm10Data = pm10Data.substring(0, pm10Data.length() - 1);
                    }
                }
                pm10Data += "]";
                model.addAttribute("pm10Category", pm10Category);
                model.addAttribute("pm10Data", pm10Data);
            } catch (NullPointerException e) {
            }
            try {
                List<MonitoringProjectData> dustTop10 = MonitoringProjectData.dao.find("select d.*,p.pro_name from ibs_monitoring_project as p left join ibs_monitoring_project_data as d on d.pid=p.id order by dust desc limit 10");
                List<String> dustCategory = new ArrayList<String>();
                String dustData = "[";
                if (dustTop10 != null) {
                    Collections.reverse(dustTop10);
                    for (MonitoringProjectData dust : dustTop10) {
                        dustCategory.add("'" + dust.getStr("pro_name") + "'");
                        int dustvalue = dust.getInt("dust");
                        dustData += "{";
                        String color = "green";
                        if (dustvalue > parseInt(dustAlertRed)) {
                            color = "red";
                        } else if (dustvalue > parseInt(dustAlertOrange)) {
                            color = "yellow";
                        }
                        dustData += "value:" + dustvalue + ",itemStyle:{normal:{color:'" + color + "'}}";
                        dustData += "},";
                    }
                    if (",".equals(dustData.substring(dustData.length() - 1, dustData.length()))) {
                        dustData = dustData.substring(0, dustData.length() - 1);
                    }
                }
                dustData += "]";
                model.addAttribute("dustCategory", dustCategory);
                model.addAttribute("dustData", dustData);
            } catch (NullPointerException e) {
            }
            try {
                List<MonitoringProjectData> noiseTop10 = MonitoringProjectData.dao.find("select d.*,p.pro_name from ibs_monitoring_project as p left join  ibs_monitoring_project_data as d on d.pid=p.id order by noise desc limit 10");
                List<String> noiseCategory = new ArrayList<String>();
                String noiseData = "[";
                if (noiseTop10 != null) {
                    Collections.reverse(noiseTop10);
                    for (MonitoringProjectData noise : noiseTop10) {
                        noiseCategory.add("'" + noise.getStr("pro_name") + "'");
                        int noisevalue = noise.getInt("noise");
                        noiseData += "{";
                        String color = "green";
                        if (noisevalue > parseInt(noiseAlertRed)) {
                            color = "red";
                        } else if (noisevalue > parseInt(noiseAlertOrange)) {
                            color = "yellow";
                        }
                        noiseData += "value:" + noisevalue + ",itemStyle:{normal:{color:'" + color + "'}}";
                        noiseData += "},";
                    }
                    if (",".equals(noiseData.substring(noiseData.length() - 1, noiseData.length()))) {
                        noiseData = noiseData.substring(0, noiseData.length() - 1);
                    }
                }
                noiseData += "]";
                model.addAttribute("noiseCategory", noiseCategory);
                model.addAttribute("noiseData", noiseData);
            } catch (NullPointerException e) {
            }
            arp.stop();
            dp.stop();
            return "index";
        } else {
            model.addAttribute("msg", object.toString());
            return "login";
        }
    }

    @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
    public String logout(Model model, HttpSession session) throws Exception {
        session.removeAttribute("username");
        session.removeAttribute("userid");
        session.removeAttribute("usertype");
        return "login";
    }

    @RequestMapping(value = "/home", method = {RequestMethod.POST, RequestMethod.GET})
    public String home(Model model, HttpSession session, UserQueryBean queryBean) throws Exception {

        Object object = userService.login(queryBean);
        if (object instanceof User) {
            User loginUser = (User) object;
            session.setAttribute("userid", loginUser.getId());
            session.setAttribute("username", loginUser.getUserName());
            session.setAttribute("usertype", loginUser.getUserType());
        }
        else
        {
            return "login";
        }
        Integer userId = (Integer) session.getAttribute("userid");
        User user = userService.findUserById(userId);
        if (user == null) {
            return "login";
        }
        UserRoleReleQueryBean userRoleReleQueryBean = new UserRoleReleQueryBean();
        userRoleReleQueryBean.setUserId(userId);
        List<UserRoleRele> userRoleReles = userRoleReleService.findUserRoleReleList(userRoleReleQueryBean);
        if (userRoleReles != null && userRoleReles.size() > 0) {
            Integer roleId = userRoleReles.get(0).getRole().getId();
            RoleMenuReleQueryBean roleMenuReleQueryBean = new RoleMenuReleQueryBean();
            roleMenuReleQueryBean.setRoleId(roleId);
            List<RoleMenuRele> roleMenuReles = roleMenuReleService.findRoleMenuReleList(roleMenuReleQueryBean);
            Map<Menu, List<Menu>> menuMap = new TreeMap<Menu, List<Menu>>();
            for (RoleMenuRele roleMenuRele : roleMenuReles) {
                if (roleMenuRele.getMenu().getpMenu() == null) {
                    List<Menu> menus = new ArrayList<Menu>();
                    for (RoleMenuRele roleMenu : roleMenuReles) {
                        if (roleMenu.getMenu().getpMenu() != null &&
                                roleMenu.getMenu().getpMenu().getId() == roleMenuRele.getMenu().getId()) {
                            menus.add(roleMenu.getMenu());
                        }
                    }
                    menuMap.put(roleMenuRele.getMenu(), menus);
                }
            }
            model.addAttribute("userMenus", menuMap);


            /*if (user.getUserType() == 3) {
                ProjectUserReleQueryBean projectUserReleQueryBean = new ProjectUserReleQueryBean();
                projectUserReleQueryBean.setUserId(userId);
                List<ProjectUserRele> reles = projectUserReleService.findProjectUserReleList(projectUserReleQueryBean);
                Integer[] projectIds = new Integer[reles.size()];
                for (int i = 0; i < reles.size(); i++) {
                    ProjectUserRele rele = reles.get(i);
                    projectIds[i] = rele.getMonitoringProject().getId();
                }
                alarmQueryBean.setProjectIdForIn(projectIds);
            }*/

            //alarmQueryBean.setResult(0);
            /*Integer count = alarmService.findAlarmCount(alarmQueryBean);
            model.addAttribute("alarmCount", count);*/
        }
        return "home";
    }
}
