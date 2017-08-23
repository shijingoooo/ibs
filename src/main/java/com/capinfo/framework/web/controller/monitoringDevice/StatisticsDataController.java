package com.capinfo.framework.web.controller.monitoringDevice;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.pojo.MonitoringDayData;
import com.capinfo.framework.web.pojo.MonitoringDevice;
import com.capinfo.framework.web.pojo.MonitoringHourData;
import com.capinfo.framework.web.service.MonitoringCompanyService;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import com.capinfo.framework.web.service.UserService;
import com.capinfo.framework.web.vo.MonitoringDataQueryBean;
import com.capinfo.framework.web.vo.MonitoringDeviceQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    @RequestMapping(value = "/devStatisticsDataListByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String findAll(Model model, MonitoringDataQueryBean dataQueryBean,  Integer pageNum,Integer devId, Integer currentIndex)throws Exception{


        MonitoringDevice device=monitoringDeviceService.findMonitoringDeviceById(devId);
        dataQueryBean.setDeviceId(devId);

        Page<MonitoringData> page = new Page<>();
        Page<MonitoringDayData> dayDataPage = new Page<>();
        Page<MonitoringHourData> hourDataPage = new Page<>();
        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
            hourDataPage.setPageNo(pageNum);
            dayDataPage.setPageNo(pageNum);
        }

        if (currentIndex != null && currentIndex == 0){
            monitoringDeviceService.findStatisticalDataPage(page, dataQueryBean);
        }else if (currentIndex != null && currentIndex == 1){
            monitoringDeviceService.findMonitoringHourDatePage(hourDataPage, dataQueryBean);
        }else if (currentIndex != null && currentIndex == 2) {
            monitoringDeviceService.findMonitoringDayDatePage(dayDataPage, dataQueryBean);
        }else{
            monitoringDeviceService.findStatisticalDataPage(page, dataQueryBean);
            monitoringDeviceService.findMonitoringHourDatePage(hourDataPage, dataQueryBean);
            monitoringDeviceService.findMonitoringDayDatePage(dayDataPage, dataQueryBean);
        }

        model.addAttribute("page", page);
        model.addAttribute("hourDataPage", hourDataPage);
        model.addAttribute("dayDataPage", dayDataPage);
        model.addAttribute("currentIndex",currentIndex);
        model.addAttribute("device",device);

        if (device.getDevType() != null && (device.getDevType() == 4 || device.getDevType() == 7)){
            return "monitoringDevice/statisticsDataDustyAndNoise";
        }else if (device.getDevType() != null && device.getDevType() == 5){
            return "monitoringDevice/statisticsDataAQI";
        }else
            return "monitoringDevice/noDeviceType";

    }

    @RequestMapping(value = "/devHourDataListByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String findHourAll(Model model, MonitoringDataQueryBean hourQueryBean, Page<MonitoringHourData> page, Integer pageNum, Integer devId, HttpServletRequest request)throws Exception{

        MonitoringDevice device=monitoringDeviceService.findMonitoringDeviceById(devId);

       /* List<MonitoringHourData> listData = monitoringDeviceService.findHourData(devId);
        model.addAttribute("list1" ,listData);
        request.setAttribute("id",devId);
        System.out.println("request"+request.getAttribute("id"));*/
        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
        }
        hourQueryBean.setDeviceId(devId);
        monitoringDeviceService.findMonitoringHourDatePage(page, hourQueryBean);
        model.addAttribute("page1", page);
        model.addAttribute("device",device);

        return "monitoringDevice/statisticsDataDustyAndNoise";

    }

    /*@RequestMapping(value = "/devDayDataListByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String findDayAll(Model model, MonitoringDeviceQueryBean deviceQueryBean, Page<MonitoringDayData> page, Integer pageNum, Integer devId, HttpServletRequest request)throws Exception{


        MonitoringDevice device=monitoringDeviceService.findMonitoringDeviceById(devId);

        List<MonitoringDayData> listData = monitoringDeviceService.findDayData(devId);
        model.addAttribute("list2" ,listData);
        request.setAttribute("id",devId);
        System.out.println("request"+request.getAttribute("id"));


        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
        }
        monitoringDeviceService.findMonitoringDayDatePage(page, deviceQueryBean);
        model.addAttribute("page2", page);
        model.addAttribute("device",device);


        return "monitoringDevice/statisticsDataDustyAndNoise";

    }*/
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
