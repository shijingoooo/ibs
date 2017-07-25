package com.capinfo.framework.web.controller.monitoringDevice;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.common.util.*;
import com.capinfo.framework.web.pojo.*;
import com.capinfo.framework.web.service.*;
import com.capinfo.framework.web.vo.MonitoringDeviceGroupQueryBean;//设备组
import com.capinfo.modules.orm.Page;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
@RequestMapping("/monitoringDevice")
public class MonitoringDeviceGroupController extends BaseController {

    @Resource
    private MonitoringDeviceGroupService monitoringDeviceGroupService;
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


}
