package com.capinfo.framework.web.controller.monitoringDevice;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.common.util.ExportExcel;
import com.capinfo.framework.common.util.StringUtil;
import com.capinfo.framework.web.pojo.MonitoringCompany;
import com.capinfo.framework.web.pojo.MonitoringDevice;
import com.capinfo.framework.web.pojo.MonitoringLED;
import com.capinfo.framework.web.pojo.MonitoringPower;
import com.capinfo.framework.web.service.MonitoringCompanyService;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import com.capinfo.framework.web.service.UserService;
import com.capinfo.framework.web.vo.MonitoringDeviceQueryBean;
import com.capinfo.framework.web.vo.MonitoringLEDQueryBean;
import com.capinfo.framework.web.vo.MonitoringPowerQueryBean;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/monitoringDevice")
public class MonitoringDeviceController extends BaseController {

    @Resource
    private MonitoringDeviceService monitoringDeviceService;
    @Resource
    private MonitoringCompanyService companyService;
    @Resource
    private UserService userService;

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

    //显示列表
    @RequestMapping(value = "/listByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String listByPage(Model model,HttpSession session, MonitoringDeviceQueryBean deviceQueryBean, Page<MonitoringDevice> page, Integer pageNum) throws Exception {
        //MonitoringDeviceGroupQueryBean deviceGroupQueryBean = new MonitoringDeviceGroupQueryBean();
        deviceQueryBean.setUserId((Integer)session.getAttribute("userid"));
        deviceQueryBean.setUserType((Integer)session.getAttribute("usertype"));
        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
        }
        //同时勾选上线下线则devStatus:'1,0'
        if(deviceQueryBean.getDevStatus()!=null && deviceQueryBean.getDevStatus().length() > 1)
            deviceQueryBean.setDevStatus("");
        monitoringDeviceService.findMonitoringDevicePage(page, deviceQueryBean);
        model.addAttribute("page", page);
        model.addAttribute("deviceQueryBean", deviceQueryBean);

        return "monitoringDevice/devListByPage";
    }
    //设备应用(GPRS && LED) 2017/09/14
    @RequestMapping(value = "/devapplication", method = {RequestMethod.POST, RequestMethod.GET})
    public String devapplication(Model model, Integer currentIndex, MonitoringLEDQueryBean ledQueryBean,
                                 MonitoringPowerQueryBean powerQueryBean,  Integer pageNum, Integer deviceId) throws Exception {

        if(currentIndex == null) currentIndex=0;

        Page<MonitoringPower> page = new Page<>();
        Page<MonitoringLED> ledPage = new Page<>();
        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
            ledPage.setPageNo(pageNum);
        }

        if (currentIndex != null && currentIndex ==0) {
            monitoringDeviceService.finddevicepower(page, powerQueryBean);
        }else if (currentIndex != null && currentIndex ==1){
            monitoringDeviceService.finddeviceLED(ledPage, ledQueryBean);
        }
        model.addAttribute("page", page);
        model.addAttribute("ledpage", ledPage);
        model.addAttribute("powerQueryBean", powerQueryBean);
        model.addAttribute("ledQueryBean", ledQueryBean);

        model.addAttribute("currentIndex",currentIndex);
        return "monitoringDevice/devAPPlication";
    }
    //LED设备跳转到添加和修改页面
    @RequestMapping(value="/devSaveLedPage",method = RequestMethod.GET)
    public String AddLED(Model model, Integer id_dev, HttpSession session,MonitoringLEDQueryBean ledQueryBean)throws Exception {

        MonitoringLEDQueryBean ledBean = monitoringDeviceService.findledidDev(id_dev);
        model.addAttribute("ledBean",ledBean);

        return "monitoringDevice/devSaveLED";
    }
    //GPRS设备跳转到添加和修改页面
    @RequestMapping(value="/devSavePowerPage",method = RequestMethod.GET)
    public String AddPower(Model model, String ip, HttpSession session,MonitoringPowerQueryBean powerQueryBean)throws Exception {

        MonitoringPowerQueryBean powerBean = monitoringDeviceService.findPowerIP(ip);
        model.addAttribute("powerBean",powerBean);

        return "monitoringDevice/devSaveGPRS";
    }
    //LED核对idDev是否唯一
    @RequestMapping(value="/checkidDev",method = RequestMethod.GET)
    public void checkidDev(Model model, HttpServletResponse response,String idDev)throws Exception {
        MonitoringLEDQueryBean monitoringLEDQueryBean = new MonitoringLEDQueryBean();
        monitoringLEDQueryBean.setIdDev(idDev);
        monitoringLEDQueryBean.setId(monitoringDeviceService.findcheckledidDev(monitoringLEDQueryBean).getId());
        JSONObject jo = new JSONObject();
        if (monitoringLEDQueryBean.getId() != null){
            jo.put("idDev",monitoringLEDQueryBean.getId());
        } else {
            jo.put("idDev","undefined");//空字符串传到jsp中为undefined；
        }
        super.rendText(response, jo.toString());


    }
    //核对ip是否唯一
    @RequestMapping(value="/checkDevIP",method = RequestMethod.GET)
    public void checkDevIP(Model model, HttpServletResponse response,String ip)throws Exception {
        MonitoringPowerQueryBean powerBean = new MonitoringPowerQueryBean();
        powerBean.setIp(ip);
        powerBean.setId(monitoringDeviceService.findDevicePowerIP(powerBean).getId());
        JSONObject jo = new JSONObject();
        if (powerBean.getId() != null){
            jo.put("ip",powerBean.getId());
        } else {
            jo.put("ip","undefined");//空字符串传到jsp中为undefined；
        }
        super.rendText(response, jo.toString());


    }
    //改变开关的状态
    @RequestMapping(value = "/devPower", method = {RequestMethod.POST})
    public void updatePower(Model model,Integer id,Integer status,HttpServletRequest request, HttpServletResponse response, HttpSession session, MonitoringPowerQueryBean powerQueryBean, String deviceIds, String navTabId, String callbackType, String rel) throws Exception {

        String controller = "GuangZhouLiXiang";
        //根据id在数据库获取一条完整信息。
        MonitoringPower power = monitoringDeviceService.findPowerId(id);
        //连接nettty
        //1.建立TCP连接
        String ip="127.0.0.1";   //服务器端ip地址
        int port=4001;        //端口号
        Socket sck=new Socket(ip,port);
        //2.传输内容
        int st = 2;
        String content="";
        if (power.getStatus() == 0) {
            powerQueryBean.setStatus(1);
            String str = "{\"deviceId\":\""+power.getDeviceId()+"\",\"controller\":\""+controller+"\",\"id\":\""+power.getId()+"\",\"status\":\""+1+"\",\"ip\":\""+power.getIp()+"\",\"port\":\""+power.getPort()+"\"}";

            st = 1;
            byte[] bstream=str.getBytes("GBK");//转化为字节流
            OutputStream os=sck.getOutputStream();   //输出流
            os.write(bstream);
        } else {
            powerQueryBean.setStatus(0);
            String str = "{\"deviceId\":\""+power.getDeviceId()+"\",\"controller\":\""+controller+"\",\"id\":\""+power.getId()+"\",\"status\":\""+0+"\",\"ip\":\""+power.getIp()+"\",\"port\":\""+power.getPort()+"\"}";
            st = 0;
            byte[] bstream=str.getBytes("GBK");  //转化为字节流
            OutputStream os=sck.getOutputStream();   //输出流
            os.write(bstream);
        }
        //3.关闭连接
        sck.close();
        monitoringDeviceService.updatePower(powerQueryBean.getId(), powerQueryBean);
        model.addAttribute("model",powerQueryBean);
        response.getWriter().write(st+",更改成功");

    }
    @RequestMapping(value = "/devSavePower", method = {RequestMethod.POST})
    public void devSavePower(Model model,HttpServletRequest request, HttpServletResponse response, HttpSession session, MonitoringPowerQueryBean powerQueryBean, String deviceIds, String navTabId, String callbackType, String rel) throws Exception {
        if (powerQueryBean.getId() != null && powerQueryBean.getId() != 0) {
            //更新

            monitoringDeviceService.updateMonitoringDevicePower(powerQueryBean.getId(),powerQueryBean);
        } else {
            //添加
            monitoringDeviceService.devicepowerAdd(powerQueryBean);

        }

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());

    }
    //LED保存或修改
    @RequestMapping(value = "/devSaveLed", method = {RequestMethod.POST})
    public void devSaveLed(Model model,String id, HttpServletResponse response, HttpSession session, MonitoringLEDQueryBean monitoringLEDQueryBean, String deviceIds, String navTabId, String callbackType, String rel) throws Exception {
        if (monitoringLEDQueryBean.getId() != null && monitoringLEDQueryBean.getId() != 0) {
            //更新
            monitoringDeviceService.updateMonitoringDeviceLED(monitoringLEDQueryBean.getId(), monitoringLEDQueryBean);
        } else {
            //添加
            monitoringDeviceService.deviceLedAdd(monitoringLEDQueryBean);

        }

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());

    }
    //批量删除开关
    @RequestMapping(value = "/devDeletePower", method = {RequestMethod.GET, RequestMethod.POST})
    public void devDeletePower(Model model,HttpServletResponse response, HttpSession session,String ips, String navTabId, String callbackType, String rel)throws Exception{
        if (StringUtil.isEmpty(ips)){
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
        ids.addAll(Arrays.asList(ips.split(",")));
        monitoringDeviceService.deleteDevicePower(ids);

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }
    //批量删除LED数据
    @RequestMapping(value = "/devDeleteLed", method = {RequestMethod.GET, RequestMethod.POST})
    public void devDeleteLed(Model model,HttpServletResponse response, HttpSession session,String devIds, String navTabId, String callbackType, String rel)throws Exception{
        if (StringUtil.isEmpty(devIds)){
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
        ids.addAll(Arrays.asList(devIds.split(",")));
        monitoringDeviceService.deleteDeviceLed(ids);

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }
    //点击跳转到添加和修改页面
    @RequestMapping(value = "/savePage", method = {RequestMethod.GET})
    public String savePage(Model model, Integer deviceId) throws Exception {
        try{
            MonitoringDevice device = monitoringDeviceService.findMonitoringDeviceById(deviceId);
            model.addAttribute("device", device);
            if (device != null && (device.getPlatformId() != null || device.getPlatformId() != "0")) {
                String companyIds[] = device.getPlatformId().split(",");
                List<MonitoringCompany> companys = companyService.findCompanyListByIds(companyIds);
                StringBuffer ids = new StringBuffer();
                StringBuffer names = new StringBuffer();
                for (MonitoringCompany company : companys) {
                    ids.append(company.getId()).append(",");
                    names.append(company.getCompanyName()).append(",");
                }
                if (companys.size() > 0) {
                    model.addAttribute("companyIds", ids.substring(0, ids.length() - 1));
                    model.addAttribute("companyNames", names.substring(0, names.length() - 1));
                }
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return "monitoringDevice/devSave";
    }
    //保存数据
    @RequestMapping(value = "/save", method = {RequestMethod.POST})
    public void save(Model model, HttpServletResponse response, HttpSession session, MonitoringDeviceQueryBean monitoringDeviceQueryBean, String navTabId, String callbackType, String rel,
                     @RequestParam(value = "monitoringCompany.id", required = false, defaultValue = "0") int monitoringCompanyID,
                     @RequestParam(value = "companyIds", required = false, defaultValue = "") String platformId
    ) throws Exception {

        Integer userId = (Integer) session.getAttribute("userid");
        MonitoringDevice monitoringDevice = monitoringDeviceService.findMonitoringDeviceUnique(monitoringDeviceQueryBean);
        if (monitoringDevice != null) {
            if (monitoringDeviceQueryBean.getId() == null || (monitoringDevice.getId().intValue() != monitoringDeviceQueryBean.getId().intValue())) {
                JSONObject jo = new JSONObject();
                jo.put("navTabId", rel);
                jo.put("callbackType", callbackType);
                jo.put("rel", rel);
                jo.put("statusCode", "300");
                jo.put("message", "传感器名称已存在");
                super.rendText(response, jo.toString());
                return;
            }
        }
        if (monitoringCompanyID > 0) {
            MonitoringCompany company = companyService.findMonitoringCompanyById(monitoringCompanyID);
            monitoringDeviceQueryBean.setVendorCode(company.getCompanyCode());
        }
        if (platformId != null || platformId != "") {
            monitoringDeviceQueryBean.setCompanyIds(platformId);
        }
        if (monitoringDeviceQueryBean.getId() != null && monitoringDeviceQueryBean.getId() != 0) {
            monitoringDeviceQueryBean.setUpdater(userId);
            monitoringDeviceService.updateMonitoringDevice(monitoringDeviceQueryBean.getId(), monitoringDeviceQueryBean);
        } else {
            monitoringDeviceQueryBean.setCreater(userId);
            monitoringDeviceService.saveMonitoringDevice(monitoringDeviceQueryBean);
        }

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }
    //批量删除设备
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public void delete(Model model, HttpServletResponse response, HttpSession session, String deviceIds, String navTabId, String callbackType, String rel) throws Exception {

        if (StringUtil.isEmpty(deviceIds)) {
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
        ids.addAll(Arrays.asList(deviceIds.split(",")));
        monitoringDeviceService.deleteMonitoringDeviceBatch(ids, (Integer) session.getAttribute("userid"));

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");

        super.rendText(response, jo.toString());
    }

    @RequestMapping(value = "/checkDevCode", method = {RequestMethod.GET})
    public void checkDevCode(Model model, HttpServletResponse response, String devCode) throws Exception {
        MonitoringDeviceQueryBean deviceQueryBean = new MonitoringDeviceQueryBean();
        deviceQueryBean.setDevCode(devCode);
        MonitoringDevice mvDevice = monitoringDeviceService.findMonitoringDeviceUnique(deviceQueryBean);
        JSONObject jo = new JSONObject();
        if (mvDevice != null) {
            jo.put("deviceId", mvDevice.getId());
        } else {
            jo.put("deviceId", "");
        }
        super.rendText(response, jo.toString());
    }
    @RequestMapping(value = "/checkDevName", method = {RequestMethod.GET})
    public void checkDevName(Model model, HttpServletResponse response, String devName) throws Exception {
        MonitoringDeviceQueryBean deviceQueryBean = new MonitoringDeviceQueryBean();
        deviceQueryBean.setDevName(devName);
        MonitoringDevice mvDevice = monitoringDeviceService.findMonitoringDeviceUnique(deviceQueryBean);
        JSONObject jo = new JSONObject();
        if (mvDevice != null) {
            jo.put("deviceId", mvDevice.getId());
        } else {
            jo.put("deviceId", "");
        }
        super.rendText(response, jo.toString());
    }
    /**
     * @Author: Zhang Chuanjia
     * @Date: 2017/8/30 10:31
     * @Description: 跳转设备数据的导出页面
     */
    @RequestMapping(value = "/goDeviceExport", method = {RequestMethod.GET,RequestMethod.POST})
    public String goDeviceExport(String deviceIds, HttpServletResponse response, HttpServletRequest request) throws Exception {
        request.setAttribute("deviceIds",deviceIds);
        return "monitoringDevice/devExport";
    }

    @RequestMapping(value = "/liveVideo" ,method = {RequestMethod.GET,RequestMethod.POST})
    public String liveVideo(HttpServletResponse response, HttpServletRequest request) throws Exception{
        return "monitoringDevice/liveVideo";
    }

    /**
     * @Author: Zhang Chuanjia
     * @Date: 2017/8/30 11:33
     * @Description: 导出设备
     */
    @RequestMapping("/exportDevExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response, String deviceIds,
                            Model model, String startDate,String endDate,String PM10,String PM2_5,
                            String NO2,String O3,String CO, String NOISE,String WIND_S,String WIND_D,String HUMIDITY,
                            String T,String PA,String TSP,String H2S,   String HCL,String SO2,String NH3,
                            String CH4S,String TVOC, String C2H6S,   String callbackType, String rel)
            throws Exception {
        //获取mongodb的连接
        //MongoCollection collection = new MongoUtil().getCollection();
        getConnection();
        String[] devIds = deviceIds.split(",");
        Integer[] devis = new Integer[devIds.length];
        for (int i = 0; i < devIds.length; i++) {
            devis[i] = Integer.parseInt(devIds[i]);
        }
        //通过用户选择的设备的id查询每个设备下面的所有的数据
        List<Map<String, Object>> dataList = monitoringDeviceService.exportMonitoringDataMongo(collection, devis, startDate, endDate);
        int a = 0;
        List size = new ArrayList();
        //为了找到选择要导出的个数，对应实时数据和校准数据
        if (TSP != null && !"".equals(TSP)) {
            size.add(a);a++;size.add(a);a++;
        }
        if (PM10 != null && !"".equals(PM10)) {
            size.add(a);a++;size.add(a); a++;
        }
        if (PM2_5 != null && !"".equals(PM2_5)) {
            size.add(a);a++;size.add(a);a++;
        }
        if (NOISE != null && !"".equals(NOISE)) {
            size.add(a);a++;size.add(a);a++;
        }
        if (WIND_S != null && !"".equals(WIND_S)) {
            size.add(a);a++;
        }
        if (WIND_D != null && !"".equals(WIND_D)) {
            size.add(a);a++;
        }
        if (HUMIDITY != null && !"".equals(HUMIDITY)) {
            size.add(a);a++;
        }
        if (T != null && !"".equals(T)) {
            size.add(a);a++;
        }
        if (PA != null && !"".equals(PA)) {
            size.add(a);a++;
        }
        if(O3 != null && !"".equals(O3)){
            size.add(a);a++;size.add(a);a++;
        }
        if(CO != null && !"".equals(CO)){
            size.add(a);a++;size.add(a);a++;
        }
        if(NO2 != null && !"".equals(NO2)){
            size.add(a);a++;size.add(a);a++;
        }
        if(H2S != null && !"".equals(H2S)){
            size.add(a);a++;size.add(a);a++;
        }
        if(HCL != null && !"".equals(HCL)){
            size.add(a);a++;size.add(a);a++;
        }
        if(SO2 != null && !"".equals(SO2)){
            size.add(a);a++;size.add(a);a++;
        }
        if(NH3 != null && !"".equals(NH3)){
            size.add(a);a++;size.add(a);a++;
        }
        if(CH4S != null && !"".equals(CH4S)){
            size.add(a);a++;size.add(a);a++;
        }
        if(TVOC != null && !"".equals(TVOC)){
            size.add(a);a++;size.add(a);a++;
        }
        if(C2H6S != null && !"".equals(C2H6S)){
            size.add(a);a++;size.add(a);a++;
        }
        //构建Object[]
        Object[] dataArr = new Object[size.size() + 5];
        for (int i = 0; i < size.size() + 5; i++) {
            dataArr[i] = "";
        }
        //构建中文英文名称的数组
        String[] header = new String[size.size() + 5];//中文名
        String[] subHeader = new String[size.size() + 5];//英文名
        a = 0;
        header[a] = "_id";
        subHeader[a] = "_id";
        a++;
        header[a] = "设备id";
        subHeader[a] = "device_id";
        a++;
        if (TSP != null && !"".equals(TSP)) {
            header[a] = "实际TSP"; subHeader[a] = "actual_tsp";a++;
            header[a] = "校准TSP";subHeader[a] = "calibration_tsp";a++;
        }
        if (PM10 != null && !"".equals(PM10)) {
            header[a] = "实际PM10";subHeader[a] = "actual_ten_pm";a++;
            header[a] = "校准PM10";subHeader[a] = "calibration_ten_pm"; a++;
        }
        if (PM2_5 != null && !"".equals(PM2_5)) {
            header[a] = "实际PM2_5";subHeader[a] = "actual_two_pm";a++;
            header[a] = "校准PM2_5";subHeader[a] = "calibration_two_pm";a++;
        }
        if (NOISE != null && !"".equals(NOISE)) {
            header[a] = "实际噪声";subHeader[a] = "actual_noise";a++;
            header[a] = "校准噪声";subHeader[a] = "calibration_noise"; a++;
        }
        if (WIND_S != null && !"".equals(WIND_S)) {
            header[a] = "实际风速";subHeader[a] = "actual_wind_speed";a++;
        }
        if (WIND_D != null && !"".equals(WIND_D)) {
            header[a] = "实际风向";subHeader[a] = "actual_wind_direction";a++;
        }
        if (HUMIDITY != null && !"".equals(HUMIDITY)) {
            header[a] = "实际湿度";subHeader[a] = "actual_humidity"; a++;
        }
        if (T != null && !"".equals(T)) {
            header[a] = "实际温度";subHeader[a] = "actual_temperature";a++;
        }
        if (PA != null && !"".equals(PA)) {
            header[a] = "实际气压";subHeader[a] = "actual_pressure";a++;
        }
        //
        if(O3 != null && !"".equals(O3)){
            header[a] = "实际臭氧"; subHeader[a] = "actual_03";  a++;
            header[a] = "校准臭氧"; subHeader[a] = "calibration_03";  a++;
        }
        if(CO != null && !"".equals(CO)){
            header[a] = "实际一氧化碳"; subHeader[a] = "actual_04";  a++;
            header[a] = "校准一氧化碳"; subHeader[a] = "calibration_04";  a++;
        }
        ////////
        if(NO2 != null && !"".equals(NO2)){
            header[a] = "实际二氧化氮"; subHeader[a] = "actual_NO2";  a++;
            header[a] = "校准二氧化氮"; subHeader[a] = "calibration_NO2";  a++;
        }
        if(H2S != null && !"".equals(H2S)){
            header[a] = "实际硫化氢"; subHeader[a] = "actual_H2S";  a++;
            header[a] = "校准硫化氢"; subHeader[a] = "calibration_H2S";  a++;
        }
        if(HCL != null && !"".equals(HCL)){
            header[a] = "实际HCL"; subHeader[a] = "actual_HCL";  a++;
            header[a] = "校准HCL"; subHeader[a] = "calibration_HCL";  a++;
        }
        if(SO2 != null && !"".equals(SO2)){
            header[a] = "实际二氧化硫"; subHeader[a] = "actual_02";  a++;
            header[a] = "校准二氧化硫"; subHeader[a] = "calibration_02";  a++;
        }
        if(NH3 != null && !"".equals(NH3)){
            header[a] = "实际氨气"; subHeader[a] = "actual_NH3";  a++;
            header[a] = "校准氨气"; subHeader[a] = "calibration_NH3";  a++;
        }
        if(CH4S != null && !"".equals(CH4S)){
            header[a] = "实际甲硫醇"; subHeader[a] = "actual_CH4S";  a++;
            header[a] = "校准甲硫醇"; subHeader[a] = "calibration_CH4S";  a++;
        }
        if(TVOC != null && !"".equals(TVOC)){
            header[a] = "实际指标"; subHeader[a] = "actual_TVOC";  a++;
            header[a] = "校准指标"; subHeader[a] = "calibration_TVOC";  a++;
        }
        if(C2H6S != null && !"".equals(C2H6S)){
            header[a] = "实际乙硫醇"; subHeader[a] = "actual_C2H6S";  a++;
            header[a] = "校准乙硫醇"; subHeader[a] = "calibration_C2H6S";  a++;
        }
        header[a] = "col_time";subHeader[a] = "col_time";a++;
        header[a] = "创建时间"; subHeader[a] = "create_time";a++;
        header[a] = "版本";subHeader[a] = "version";a++;

        String fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        ExportExcel ee = new ExportExcel();
        ee.exportExcel(header, subHeader, "设备组数据" + fileName, dataArr, dataList, response);
    }
    //导出数据
   /* @RequestMapping(value = "/downloadDevice", method = {RequestMethod.GET})
    public void downloadDevice(String deviceIds, HttpServletResponse response) throws Exception {
        String[] header = {"id", "传感器编号", "报建号", "vendor_code", "传感器名称", "经度", "纬度", "IP地址", "MAC地址", "端口",
                "设备版本", "设备状态", "视频地址", "开始时间", "结束时间", "安装时间", "创建时间", "修改时间", "创建人", "修改人",
                "版本号", "工地主键", "厂商ID", "设备杆ID", "传感器类型", "云端控制参数串", "serial"};
        String[] subHeader = {"id", "dev_code", "pro_code", "vendor_code", "dev_name", "longitude", "latitude", "ip_addr",
                "mac_addr", "dev_port", "dev_version", "dev_status", "video_url", "start_time", "end_time", "install_time",
                "create_time", "update_time", "creater", "updater", "version", "project_id", "company_id", "device_group_id",
                "dev_type", "paramstr", "serial"};
        Object[] dataArr = {"", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", ""};
        List<String> ids = new ArrayList<String>();
        ids.addAll(Arrays.asList(deviceIds.split(",")));
        List<Map<String, Object>> dataList = monitoringDeviceService.downloadMonitoringDeviceList(ids);
        ExcelUtil excelUtil = new ExcelUtil();
        excelUtil.downLoadModel(header, subHeader, dataArr, "传感器表", dataList, response);
    }*/
}
//保存或修改
