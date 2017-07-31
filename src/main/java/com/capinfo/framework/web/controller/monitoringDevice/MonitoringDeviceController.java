package com.capinfo.framework.web.controller.monitoringDevice;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.common.util.*;
import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.service.MonitoringCompanyService;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import com.capinfo.framework.web.service.UserService;
import com.capinfo.framework.web.vo.*;
import com.capinfo.modules.orm.Page;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
    //显示列表
    @RequestMapping(value = "/listByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String listByPage(Model model,HttpSession session, MonitoringDeviceQueryBean deviceQueryBean, Page<MonitoringDevice> page, Integer pageNum) throws Exception {
        //MonitoringDeviceGroupQueryBean deviceGroupQueryBean = new MonitoringDeviceGroupQueryBean();
        deviceQueryBean.setUserId((Integer)session.getAttribute("userid"));
        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
        }
        monitoringDeviceService.findMonitoringDevicePage(page, deviceQueryBean);
        model.addAttribute("page", page);
        model.addAttribute("deviceQueryBean", deviceQueryBean);

        return "monitoringDevice/devListByPage";
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

    //导出数据
    @RequestMapping(value = "/downloadDevice", method = {RequestMethod.GET})
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
    }

}
