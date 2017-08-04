package com.capinfo.framework.web.controller.monitoringMaintain;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.web.pojo.MonitoringDevice;
import com.capinfo.framework.web.pojo.MonitoringMaintain;
import com.capinfo.framework.web.service.MonitoringMaintainService;
import com.capinfo.framework.web.vo.MonitoringMaintainQueryBean;
import com.capinfo.modules.orm.Page;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("/monitoringMaintain")
public class MonitoringMaintainController extends BaseController {

    @Resource
    private MonitoringMaintainService monitoringMaintainService;

    //显示列表
    @RequestMapping(value = "/listByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String listByPage(Model model, HttpSession session, MonitoringMaintainQueryBean monitoringMaintainQueryBean, Page<MonitoringMaintain> page, Integer pageNum,Integer devId) throws Exception {

        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
        }
        monitoringMaintainQueryBean.setDevId(devId);
        monitoringMaintainQueryBean.setUserId((Integer) session.getAttribute("userid"));
        monitoringMaintainService.findMonitoringMaintainPage(page, monitoringMaintainQueryBean);
        model.addAttribute("page", page);
        model.addAttribute("maintainQueryBean", monitoringMaintainQueryBean);

        return "monitoringMaintain/maintainListByPage";
    }
    //点击新增或修改按钮
    @RequestMapping(value = "/savePage", method = {RequestMethod.GET})
    public String savePage(Model model, HttpServletRequest request) throws Exception {
        String queryString = request.getQueryString();
        if(queryString.contains("recordId")){
            Integer recordId = Integer.parseInt(request.getParameter("recordId"));
            MonitoringMaintain maintain = monitoringMaintainService.findMonitoringMaintainById(recordId);
            maintain.setId(recordId);
            model.addAttribute("maintain", maintain);
        }else if(queryString.contains("deviceId")){
            Integer deviceId = Integer.parseInt(request.getParameter("deviceId"));
            String deviceCode = request.getParameter("deviceCode");
            String alarmTime = request.getParameter("alarmTime");
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM ddHH:mm:ss 'CST' yyyy", Locale.US);
            Date date =sdf.parse(alarmTime);
            MonitoringMaintain maintain = new MonitoringMaintain();
            maintain.setDeviceId(deviceId);
            maintain.setDeviceCode(deviceCode);
            maintain.setTroubleTime(date);
            maintain.setSolveTime(date);

            model.addAttribute("maintain", maintain);
        }
        return "monitoringMaintain/maintainSave";
    }
    //设备列表
    @RequestMapping(value = "/devSelectListInMaintain", method = {RequestMethod.POST, RequestMethod.GET})
    public String devSelectListInGroup(Model model, HttpSession session, MonitoringMaintainQueryBean maintainQueryBean, Integer deviceGroupId, String deviceId, String deviceName) throws Exception {
        maintainQueryBean.setUserId((Integer) session.getAttribute("userid"));
        List<MonitoringDevice> devices = monitoringMaintainService.findMonitoringDeviceList(maintainQueryBean);
        model.addAttribute("devices", devices);
        model.addAttribute("deviceQueryBean", maintainQueryBean);
        model.addAttribute("deviceId", deviceId);
        model.addAttribute("deviceName", deviceName);
        return "monitoringMaintain/devSelectListInMaintain";
    }
    //点击保存按钮
    @RequestMapping(value = "/save", method = {RequestMethod.GET, RequestMethod.POST})
    public void save(Model model, HttpServletResponse response, HttpSession session, MonitoringMaintainQueryBean maintainQueryBean,Integer recordId, String navTabId, String callbackType, String rel) throws Exception {
        if( maintainQueryBean.getId() != null ){
            //修改
            monitoringMaintainService.updateMonitoringMaintianRecord(maintainQueryBean);
        }else {
            //新增
            monitoringMaintainService.saveMonitoringMaintainRecord(maintainQueryBean);
        }
//			if(company==null){
//				JSONObject jo = new JSONObject();
//				jo.put("navTabId", rel);
//				jo.put("callbackType", callbackType);
//				jo.put("rel", rel);
//				jo.put("statusCode", "300");
//				jo.put("message", "操作失败，厂商信息在上海库中不存在");
//				super.rendText(response, jo.toString());
//			}else{
            JSONObject jo = new JSONObject();
            jo.put("navTabId", rel);
            jo.put("callbackType", callbackType);
            jo.put("rel", rel);
            jo.put("statusCode", "200");
            jo.put("message", "操作成功");
            super.rendText(response, jo.toString());
//			}

    }
    //点击删除按钮
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public void delete(Model model, HttpServletResponse response, String recordIds, String navTabId, String callbackType, String rel) throws Exception {

      if (StringUtils.isEmpty(recordIds)) {
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
        ids.addAll(Arrays.asList(recordIds.split(",")));
        monitoringMaintainService.deleteMaintainRecordBatch(ids);

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }

}
