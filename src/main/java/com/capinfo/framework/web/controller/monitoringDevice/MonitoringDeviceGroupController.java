package com.capinfo.framework.web.controller.monitoringDevice;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.common.util.ExportExcel;
import com.capinfo.framework.common.util.StringUtil;
import com.capinfo.framework.web.pojo.GroupDevice;
import com.capinfo.framework.web.pojo.MonitoringDeviceGroup;
import com.capinfo.framework.web.pojo.MonitoringDeviceGroupRele;
import com.capinfo.framework.web.service.GroupDeviceService;
import com.capinfo.framework.web.service.MonitoringDeviceGroupService;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import com.capinfo.framework.web.vo.MonitoringDeviceGroupQueryBean;
import com.capinfo.modules.orm.Page;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/monitoringDevice")
public class MonitoringDeviceGroupController extends BaseController {

    @Resource
    private MonitoringDeviceGroupService monitoringDeviceGroupService;
    @Resource
    private MonitoringDeviceService mds;
    @Resource
    private GroupDeviceService groupDeviceService;

    @Value("#{c['mongo.ip']}")
    private String mongoIP;
    @Value("#{c['mongo.port']}")
    private String mongoPort;
    @Value("#{c['mongo.dbname']}")
    private String mongodbname;
    @Value("#{c['mongo.username']}")
    private String mongoUsername;
    @Value("#{c['mongo.password']}")
    private String mongoPassword;

    private MongoCollection collection;
    private MongoDatabase mongoDatabase;
    private MongoClient mongoClient;

    public void getConnection() {
        mongoClient = new MongoClient(mongoIP, Integer.parseInt(mongoPort));
        mongoDatabase = mongoClient.getDatabase(mongodbname);
        collection = mongoDatabase.getCollection("ibs_monitoring_data");
    }

    //设备组列表
    @RequestMapping(value = "/devGrouplistByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String devGrouplistByPage(Model model, HttpSession session, MonitoringDeviceGroupQueryBean deviceGroupQueryBean, Page<MonitoringDeviceGroup> page, Integer pageNum) throws Exception {

        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
        }
        Integer userId = (Integer) session.getAttribute("userid");
        Integer userType = (Integer) session.getAttribute("usertype");
        deviceGroupQueryBean.setAdminId(userId);
        deviceGroupQueryBean.setAdminType(userType);

        monitoringDeviceGroupService.findMonitoringDeviceGroupPage(page, deviceGroupQueryBean);

        model.addAttribute("page", page);
        model.addAttribute("deviceGroupQueryBean", deviceGroupQueryBean);

        return "monitoringDevice/devGrouplistByPage";
    }
    //设备列表
    @RequestMapping(value = "/devSelectListInGroup", method = {RequestMethod.POST, RequestMethod.GET})
    public String devSelectListInGroup(Model model, MonitoringDeviceGroupQueryBean deviceGroupQueryBean, Integer deviceGroupId, String deviceIds, String deviceNames) throws Exception {
        List<MonitoringDeviceGroupRele> devices = monitoringDeviceGroupService.findMonitoringDeviceList(deviceGroupQueryBean);
        model.addAttribute("devices", devices);
        model.addAttribute("deviceQueryBean", deviceGroupQueryBean);
        model.addAttribute("deviceIds", deviceIds);
        model.addAttribute("deviceNames", deviceNames);
        return "monitoringDevice/devSelectListInGroup";
    }
    //跳转到保存和修改页面
    @RequestMapping(value = "/devSaveGroupPage", method = {RequestMethod.GET})
    public String devSaveGroupPage(Model model, Integer deviceGroupId) throws Exception {

        MonitoringDeviceGroupQueryBean deviceGroupQueryBean = monitoringDeviceGroupService.findMonitoringDeviceGroupById(deviceGroupId);
        model.addAttribute("deviceGroup", deviceGroupQueryBean);
        if (deviceGroupQueryBean != null) {
            List<MonitoringDeviceGroupQueryBean> devices = monitoringDeviceGroupService.findMonitoringDeviceByGroupId(deviceGroupId);
            StringBuffer ids = new StringBuffer();
            StringBuffer names = new StringBuffer();
            for (MonitoringDeviceGroupQueryBean device : devices) {
                ids.append(device.getId()).append(",");
                names.append(device.getDevName()).append(",");
            }
            if (devices.size() > 0) {
                model.addAttribute("deviceIds", ids.substring(0, ids.length() - 1));
                model.addAttribute("deviceNames", names.substring(0, names.length() - 1));
            }
        }

        return "monitoringDevice/devSaveGroup";
    }
    //保存或修改
    @RequestMapping(value = "/devSaveGroup", method = {RequestMethod.POST})
    public void devSaveGroup(Model model, HttpServletResponse response, HttpSession session, MonitoringDeviceGroupQueryBean deviceGroupQueryBean, String deviceIds, String navTabId, String callbackType, String rel) throws Exception {

        Integer userId = (Integer) session.getAttribute("userid");

        if (deviceGroupQueryBean.getId() != null && deviceGroupQueryBean.getId() != 0) {
        //更新
            monitoringDeviceGroupService.updateMonitoringDeviceGroup(deviceGroupQueryBean.getId(), deviceGroupQueryBean);
            monitoringDeviceGroupService.updateMonitoringDeviceGroupRele(deviceIds,deviceGroupQueryBean);
        } else {
        //添加
            monitoringDeviceGroupService.saveMonitoringDeviceGroup(deviceGroupQueryBean);
            monitoringDeviceGroupService.updateMonitoringDeviceGroupRele(deviceIds,deviceGroupQueryBean);
        }

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }
    //批量删除
    @RequestMapping(value = "/devDeleteGroup", method = {RequestMethod.GET, RequestMethod.POST})
    public void devDeleteGroup(Model model, HttpServletResponse response, HttpSession session, String deviceGroupIds, String navTabId, String callbackType, String rel) throws Exception {
        if (StringUtil.isEmpty(deviceGroupIds)) {
            JSONObject jo = new JSONObject();
            jo.put("navTabId", rel);
            jo.put("callbackType", callbackType);
            jo.put("rel", rel);
            jo.put("statusCode", "300");
            jo.put("message", "请选择一条信息");
            super.rendText(response, jo.toString());
            return;
        }
        List<String> ids = new ArrayList<String>();
        ids.addAll(Arrays.asList(deviceGroupIds.split(",")));
        monitoringDeviceGroupService.deleteMonitoringDeviceGroupBatch(ids, (Integer) session.getAttribute("userid"));

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }
    //验证分组名称
    @RequestMapping(value = "/checkDevGroupName", method = {RequestMethod.GET})
    public void checkDevGroupName(Model model, HttpServletResponse response, String devGroupName) throws Exception {
        MonitoringDeviceGroupQueryBean monitoringDeviceGroupQueryBean = new MonitoringDeviceGroupQueryBean();
        monitoringDeviceGroupQueryBean.setDevGroupName(devGroupName);
        monitoringDeviceGroupQueryBean.setId(monitoringDeviceGroupService.findMonitoringDeviceGroupUnique(monitoringDeviceGroupQueryBean).getId());
        JSONObject jo = new JSONObject();
        if (monitoringDeviceGroupQueryBean.getId() != null) {
            jo.put("gourpId", monitoringDeviceGroupQueryBean.getId());
        } else {
            jo.put("groupId", "");//空字符串传到jsp中为undefined；
        }
        super.rendText(response, jo.toString());
    }

    /**
     * @Author: Zhang Chuanjia
     * @Date: 2017/8/21 16:45
     * @Description: 设备组数据导出
     */
    //@RequestMapping("/export")
    /*public void export(HttpServletRequest request, HttpServletResponse response, Integer deviceGroupId)
            throws Exception{
        *//**通过组的id查询该组信息*//*
        GroupDevice groupDevice = groupDeviceService.findGroupDevice(deviceGroupId);
        *//**通过设备组的id查询该组下面的所有的设备信息*//*
        List<MonitoringDevice> monitoringDevices = mds.findMonitoringDeviceListByGID(groupDevice);

        ExportExcel<MonitoringDevice> ee= new ExportExcel<MonitoringDevice>();
        //通过反射获取类或者接口的所有已经声明的字段
        MonitoringDevice monitoringDevice = new MonitoringDevice();
        Class clzz = monitoringDevice.getClass();
        Field[] fields = clzz.getDeclaredFields();

        //将已经获取的字段列表存在数组中
        String [] headers = new String[fields.length];
        for (int i = 0; i < headers.length; i++) {
            headers[i] = fields[i].getName().toString();
        }
        //设定导出的文件名
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = new Date();
        String fileName = "设备组报表（"+format.format(date).toString()+"）";
        ee.exportExcel(fields,headers,monitoringDevices,fileName,response);
    }*/

    /**
     * @Author: Zhang Chuanjia
     * @Date: 2017/8/24 23:28
     * @Description: 跳转到导出页面
     */
    @RequestMapping(value = "/goGroupExport", method = {RequestMethod.GET,RequestMethod.POST})
    public String goExport(String deviceGroupId, HttpServletResponse response, HttpServletRequest request) throws Exception {
        request.setAttribute("deviceGroupId",deviceGroupId);
        return "monitoringDevice/devGroupExport";
    }

    /**
     * @Author: Zhang Chuanjia
     * @Date: 2017/8/24 23:27
     * @Description: 设备组中的每个设备采集数据信息导出
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String deviceGroupId,
                            Model model, String startDate,String endDate,String PM10,String PM2_5,
                            String NO2,String O3,String CO, String NOISE,String WIND_S,String WIND_D,String HUMIDITY,
                            String T,String PA,String TSP,String H2S, String callbackType, String rel)
            throws Exception{
        /**通过组的id查询该组信息*/
        GroupDevice groupDevice = groupDeviceService.findGroupDevice(Integer.parseInt(deviceGroupId));
        /**通过组的id查询该组下面的所有的设备id*/
        List<Integer> deviceIds = groupDeviceService.findDevices(groupDevice);
        Integer[] devIds = deviceIds.toArray(new Integer[0]);
        //获取mongodb的连接
        getConnection();
        //通过设备组的id查询该组下面的所有设备的信息，在根据所有设备的id查询每个设备采集的数据信息
        //List<Map<String, Object>> dataList = groupDeviceService.exportMonitoringData(collection,devIds,startDate,endDate);

        /*测试数据开始*/
        List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("_id","1");
        map1.put("actual_ten_pm","3.2");
        map1.put("calibration_ten_pm","3.1");
        map1.put("actual_wind_direction","西北");
        map1.put("_id","1");
        map1.put("actual_ten_pm","3.2");
        map1.put("calibration_ten_pm","3.1");
        map1.put("actual_wind_direction","西北");

        Map<String, Object> map2 = new HashMap<String, Object>();
        map2.put("_id","1");
        map2.put("actual_ten_pm","3.2");
        map2.put("calibration_ten_pm","3.1");
        map2.put("actual_wind_direction","西北");
        map2.put("_id","1");
        map2.put("actual_ten_pm","3.2");
        map2.put("calibration_ten_pm","3.1");
        map2.put("actual_wind_direction","西北");

        dataList.add(map1);
        dataList.add(map2);
        /*测试数据结束*/

        int a = 0;
        List size = new ArrayList();
        //为了找到选择要导出的个数，对应实时数据和校准数据
        if(TSP != null && !"".equals(TSP)){ size.add(a);a++; size.add(a);a++; }
        if(PM10 != null && !"".equals(PM10)){ size.add(a); a++;size.add(a); a++; }
        if(PM2_5 != null && !"".equals(PM2_5)){ size.add(a); a++; size.add(a); a++; }
        if(NOISE != null && !"".equals(NOISE)){ size.add(a); a++;size.add(a); a++; }
        if(WIND_S != null && !"".equals(WIND_S)){size.add(a); a++; }
        if(WIND_D != null && !"".equals(WIND_D)){  size.add(a); a++; }
        if(HUMIDITY != null && !"".equals(HUMIDITY)){ size.add(a); a++; }
        if(T != null && !"".equals(T)){ size.add(a); a++;}
        if(PA != null && !"".equals(PA)){size.add(a); a++; }
        //构建Object[]
        Object[] dataArr = new Object[size.size()+5];
        for(int i = 0; i < size.size()+5; i++) {
            dataArr[i] = "";
        }
        //中文名
        String [] header = new String[size.size()+5];
        //英文名
        String [] subHeader = new String[size.size()+5];
        a = 0;
        //构建中文英文名称的数组
        header[a] = "_id";subHeader[a] = "_id";a++;
        header[a] = "col_time";subHeader[a] = "col_time";a++;
        header[a] = "创建时间";subHeader[a] = "create_time";a++;
        header[a] = "版本";subHeader[a] = "version";a++;
        header[a] = "设备id";subHeader[a] = "device_id";a++;

        if(TSP != null && !"".equals(TSP)){
            header[a] = "实际TSP"; subHeader[a] = "actual_tsp";  a++;
            header[a] = "校准TSP"; subHeader[a] = "calibration_tsp";  a++;
        }
        if(PM10 != null && !"".equals(PM10)){
            header[a] = "实际PM10"; subHeader[a] = "actual_ten_pm";  a++;
            header[a] = "校准PM10"; subHeader[a] = "calibration_ten_pm";  a++;
        }
        if(PM2_5 != null && !"".equals(PM2_5)){
            header[a] = "实际PM2_5"; subHeader[a] = "actual_two_pm";  a++;
            header[a] = "校准PM2_5"; subHeader[a] = "calibration_two_pm";  a++;
        }
        if(NOISE != null && !"".equals(NOISE)){
            header[a] = "实际噪声"; subHeader[a] = "actual_noise";  a++;
            header[a] = "校准噪声"; subHeader[a] = "calibration_noise";  a++;
        }
        if(WIND_S != null && !"".equals(WIND_S)){
            header[a] = "实际风速"; subHeader[a] = "actual_wind_speed";  a++;
        }
        if(WIND_D != null && !"".equals(WIND_D)){
            header[a] = "实际风向"; subHeader[a] = "actual_wind_direction";  a++;
        }
        if(HUMIDITY != null && !"".equals(HUMIDITY)){
            header[a] = "实际湿度"; subHeader[a] = "actual_humidity";  a++;
        }
        if(T != null && !"".equals(T)){
            header[a] = "实际温度"; subHeader[a] = "actual_temperature";  a++;
        }
        if(PA != null && !"".equals(PA)){
            header[a] = "实际气压"; subHeader[a] = "actual_pressure";  a++;
        }

       /* if(O3 != null && !"".equals(O3)){
            header[a] = "实际臭氧"; subHeader[a] = "actual_03";  a++;
            header[a] = "校准臭氧"; subHeader[a] = "calibration_03";  a++;
        }
        if(CO != null && !"".equals(CO)){
            header[a] = "实际一氧化碳"; subHeader[a] = "actual_04";  a++;
            header[a] = "校准一氧化碳"; subHeader[a] = "calibration_04";  a++;
        }*/

        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        ExportExcel ee = new ExportExcel();
        ee.exportExcel(header,subHeader,"设备组数据"+fileName,dataArr,dataList,response);

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }
}
