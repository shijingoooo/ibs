package com.capinfo.framework.web.controller.monitoringDevice;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.common.util.StringUtil;
import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.service.DataCalibrationService;
import com.capinfo.framework.web.service.MonitoringCompanyService;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import com.capinfo.framework.web.service.UserService;
import com.capinfo.framework.web.vo.DataCalibrationQueryBean;
import com.capinfo.framework.web.vo.MonitoringDeviceGroupQueryBean;
import com.capinfo.framework.web.vo.MonitoringDeviceQueryBean;
import com.capinfo.modules.orm.Page;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/monitoringDevice")
public class DataCalibrationController extends BaseController {

    @Resource
    private MonitoringDeviceService monitoringDeviceService;
    @Resource
    private DataCalibrationService dataCalibrationService;
    @Resource
    private UserService userService;
    //规则列表
    @RequestMapping(value = "/devDataCalibrationListByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String devDataCalibrationListByPage(Model model, DataCalibrationQueryBean dataCalibrationQueryBean, Page<DataCalibrationRule> page, Integer pageNum, Integer devId) throws Exception {
        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
        }
        dataCalibrationQueryBean.setDeviceId(devId);
        dataCalibrationService.findDataCalibrationPage(page, dataCalibrationQueryBean);
        model.addAttribute("page", page);
        model.addAttribute("dataCalibrationQueryBean", dataCalibrationQueryBean);

        return "monitoringDevice/devDataRuleListByPage";
    }
    //点击跳转新增页面
    @RequestMapping(value = "/devDataCalibrationSave", method = {RequestMethod.GET})
    public String devDataCalibrationSave(Model model, Integer deviceId) throws Exception {
        DataCalibrationQueryBean dataCalibrationQueryBean = dataCalibrationService.findMonitoringDeviceById(deviceId);
        model.addAttribute("rule", dataCalibrationQueryBean);
        return "monitoringDevice/devSaveRule";
    }
    //点击保存，将数据保存数据库，更新规则列表
    @RequestMapping(value = "devSaveRule", method = {RequestMethod.POST})
    public void devSaveRule(Model model, HttpServletResponse response, HttpSession session, DataCalibrationQueryBean dataCalibrationQueryBean,String navTabId, String callbackType, String rel) throws Exception{
        if (!dataCalibrationService.checkRule(dataCalibrationQueryBean))
        {
            JSONObject jo = new JSONObject();
            jo.put("navTabId", rel);
            jo.put("callbackType", callbackType);
            jo.put("rel", rel);
            jo.put("statusCode", "300");
            jo.put("message", "添加规则与原有规则冲突！");
            super.rendText(response, jo.toString());
            return;
        }

        dataCalibrationService.saveDataCalibrationRule(dataCalibrationQueryBean);
        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }
    //点击更新
    @RequestMapping(value = "devUpdateRule", method = {RequestMethod.POST})
    public void devUpdateRule(Model model, HttpServletResponse response, HttpSession session, DataCalibrationQueryBean dataCalibrationQueryBean, String ruleId, String navTabId, String callbackType, String rel) throws Exception{
        if (!dataCalibrationService.checkRule(dataCalibrationQueryBean))
        {
            JSONObject jo = new JSONObject();
            jo.put("navTabId", rel);
            jo.put("callbackType", callbackType);
            jo.put("rel", rel);
            jo.put("statusCode", "300");
            jo.put("message", "更新规则与原有规则冲突！");
            super.rendText(response, jo.toString());
            return;
        }
        dataCalibrationService.updateDataCalibrationRule(dataCalibrationQueryBean);
        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }
    //点击跳转到更新页面
    @RequestMapping(value = "/devDataCalibrationUpdate", method = {RequestMethod.GET})
    public String devDataCalibrationUpdate(Model model, Integer ruleId , Integer deviceId) throws Exception {
        DataCalibrationQueryBean dataCalibrationQueryBean = dataCalibrationService.findDataCalibrationRuleById(ruleId);
        dataCalibrationQueryBean.setDeviceId(deviceId);
        model.addAttribute("rule", dataCalibrationQueryBean);
        return "monitoringDevice/devUpdateRule";
    }
    //点击跳转到设备选择页面
    @RequestMapping(value = "/devSelectListInRule", method = {RequestMethod.GET})
    public String devSelectListInRule(Model model, DataCalibrationQueryBean dataCalibrationQueryBean, Integer deviceGroupId, String deviceId, String deviceName) throws Exception {
        List<MonitoringDevice> devices = dataCalibrationService.findMonitoringDeviceList(dataCalibrationQueryBean);
        model.addAttribute("devices", devices);
        model.addAttribute("deviceQueryBean", dataCalibrationQueryBean);
        model.addAttribute("deviceId", deviceId);
        model.addAttribute("deviceName", deviceName);
        return "monitoringDevice/devSelectListInRule";
    }
    //点击删除按钮可批量删除规则
    @RequestMapping(value = "/devDataCalibrationDelete", method = {RequestMethod.GET, RequestMethod.POST})
    public void devDataCalibrationDelete(Model model, HttpServletResponse response, HttpSession session, String ruleIds, String navTabId, String callbackType, String rel) throws Exception {

        if (StringUtil.isEmpty(ruleIds)) {
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
        ids.addAll(Arrays.asList(ruleIds.split(",")));
        dataCalibrationService.deleteDataCalibrationRuleBatch(ids, (Integer) session.getAttribute("userid"));

        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");

        super.rendText(response, jo.toString());
    }

    @RequestMapping(value = "/changeStatus", method = {RequestMethod.POST})
    @ResponseBody
    public String changeStatus(String id,String status) throws Exception{
        //更新状态
        String msg = dataCalibrationService.updateDataCalibrationRuleStatus(id,status);
        return msg;
    }

}
