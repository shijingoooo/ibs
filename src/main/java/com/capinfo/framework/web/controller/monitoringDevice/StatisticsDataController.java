package com.capinfo.framework.web.controller.monitoringDevice;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.common.util.ExportExcel;
import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.pojo.MonitoringDayData;
import com.capinfo.framework.web.pojo.MonitoringDevice;
import com.capinfo.framework.web.pojo.MonitoringHourData;
import com.capinfo.framework.web.service.MonitoringCompanyService;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import com.capinfo.framework.web.service.UserService;
import com.capinfo.framework.web.vo.MonitoringDataQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/monitoringDevice")
public class StatisticsDataController extends BaseController {

    @Resource
    private MonitoringDeviceService monitoringDeviceService;
    @Resource
    private MonitoringCompanyService companyService;
    @Resource
    private UserService userService;
    //显示列表

    /*public String savePage(Model model, Integer deviceId) throws Exception {
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
    }*/

    /**
     * @Author: Liu Qiuyan
     * @Date: 2017/8/30 17:05
     * @Description: 显示统计数据
     */
    @RequestMapping(value = "/devStatisticsDataListByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String findAll(Model model, MonitoringDataQueryBean dataQueryBean, Integer pageNum, Integer devId, Integer currentIndex) throws Exception {

       if(currentIndex == null)currentIndex = 0;

        MonitoringDevice device = monitoringDeviceService.findMonitoringDeviceById(devId);
        dataQueryBean.setDeviceId(devId);

        Page<MonitoringData> page = new Page<>();
        Page<MonitoringDayData> dayDataPage = new Page<>();
        Page<MonitoringHourData> hourDataPage = new Page<>();
        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
            hourDataPage.setPageNo(pageNum);
            dayDataPage.setPageNo(pageNum);
        }

        if (currentIndex != null && currentIndex == 0) {
            monitoringDeviceService.findStatisticalDataPage(page, dataQueryBean);
        } else if (currentIndex != null && currentIndex == 1) {
            monitoringDeviceService.findMonitoringHourDatePage(hourDataPage, dataQueryBean);
        } else if (currentIndex != null && currentIndex == 2) {
            monitoringDeviceService.findMonitoringDayDatePage(dayDataPage, dataQueryBean);
        }

        model.addAttribute("page", page);
        model.addAttribute("hourDataPage", hourDataPage);
        model.addAttribute("dayDataPage", dayDataPage);
        model.addAttribute("currentIndex", currentIndex);
        model.addAttribute("device", device);

        if (device.getDevType() != null && (device.getDevType() == 4 || device.getDevType() == 7)) {
            return "monitoringDevice/statisticsDataDustyAndNoise";
        } else if (device.getDevType() != null && device.getDevType() == 5) {
            return "monitoringDevice/statisticsDataAQI";
        } else
            return "monitoringDevice/noDeviceType";

    }
    /**
     * @Author: Zhang Chuanjia
     * @Date: 2017/8/30 17:11
     * @Description: 跳转统计数据导出的页面
     */
    @RequestMapping(value = "/goStatisticsExport", method = {RequestMethod.GET,RequestMethod.POST})
    public String goExport(String deviceType, String currentIndex,HttpServletResponse response, HttpServletRequest request) throws Exception {
        request.setAttribute("currentIndex",currentIndex);
        request.setAttribute("deviceType",deviceType);
        return "monitoringDevice/statisticsExport";
    }

    /**
     * @Author: Zhang Chuanjia
     * @Date: 2017/8/30 17:06
     * @Description: 统计数据的导出
     */
    @RequestMapping("/exportStatisticsExcel")
    public void exportExcel(HttpServletRequest request, HttpServletResponse response,
                            String currentIndex, String startDate,String endDate,Model model, String PM10, String PM2_5,
                            String NO2, String O3, String CO, String NOISE, String WIND_S, String WIND_D, String HUMIDITY,
                            String T, String PA, String TSP, String H2S, String HCL, String SO2, String NH3,
                            String CH4S, String TVOC, String C2H6S, String callbackType, String rel) {
        //通过用户选择的设备的id查询每个设备下面的所有的数据
        List<Map<String, Object>> dataList = monitoringDeviceService.exportStatistics(currentIndex,startDate,endDate);
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
        header[a] = "id";
        subHeader[a] = "id";
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
        ee.exportExcel(header, subHeader, "统计数据" + fileName, dataArr, dataList, response);
    }
/*public String listByPage(Model model, MonitoringDeviceQueryBean deviceQueryBean, Page<MonitoringDevice> page, Integer pageNum,Integer devId) throws Exception {
        //MonitoringDeviceGroupQueryBean deviceGroupQueryBean = new MonitoringDeviceGroupQueryBean();
        MonitoringDeviceQueryBean monitoringDevice = new MonitoringDeviceQueryBean();
       // if(monitoringDevice.getDevCode() !=null && monitoringDevice.getDevCode() != "")
        System.out.println(devId);
        //System.out.println(deviceQueryBean.getDevCode());
        List<MonitoringDevice> list= monitoringDeviceService.findStatisticalData(devId);
        model.addAttribute("list",list);
     *//*   public ModelAndView listByPage(Model model, MonitoringDeviceQueryBean deviceQueryBean, Page<MonitoringDevice> page, Integer pageNum,Integer devId) throws Exception {
            ModelAndView mav = new ModelAndView(super.getMsg("goods.add.page")) ;
            mav.addAllObjects(this.goodsService.findStatisticalData()) ;
            return mav ;
        }*//*
        *//*if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
        }
        monitoringDeviceService.findMonitoringDevicePage(page, deviceQueryBean);
        model.addAttribute("page", page);
        model.addAttribute("deviceQueryBean", deviceQueryBean);*//*
       return "monitoringDevice/statisticsDataDustyAndNoise";
       // return "monitoringDevice/statisticsDataAQI";
        //return "monitoringDevice/statisticsDataVOC";
    }*/
    /*//点击跳转到添加和修改页面
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
*/
}
