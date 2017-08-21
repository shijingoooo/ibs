package com.capinfo.framework.web.controller.monitoringMaintain;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.service.MonitoringAlarmService;
import com.capinfo.framework.web.vo.MonitoringAlarmQueryBean;
import com.capinfo.modules.orm.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/monitoringAlarm")
public class MonitoringAlarmController extends BaseController {

    @Resource
    private MonitoringAlarmService monitoringAlarmService;

    //显示列表
    @RequestMapping(value = "/listByPage", method = {RequestMethod.POST, RequestMethod.GET})
    public String listByPage(Model model, HttpSession session, MonitoringAlarmQueryBean alarmQueryBean, Page<AlarmDevp> page, Integer pageNum, Integer devId) throws Exception {

        if (page != null && pageNum != null) {
            page.setPageNo(pageNum);
        }
        alarmQueryBean.setDevId(devId);
        alarmQueryBean.setUserId((Integer) session.getAttribute("userid"));
        alarmQueryBean.setUserType((Integer) session.getAttribute("usertype"));
        monitoringAlarmService.findMonitoringAlarmPage(page, alarmQueryBean);

        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        String[] week = new String[7];
        for (int i = 0; i < 7; i++) {
            c.set(Calendar.DATE, day--);
            week[i] = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        }
        //获取每个告警类型，每天的告警数量
        Map<String,List<AlarmDevp>> map = monitoringAlarmService.findDayCountByType();
        //创建Map，方便页面读取数据
        Map<String,String[]>countMap = new HashMap<String,String[]>();
        for (String key: map.keySet()) {
            String[] strArr = {"0","0","0","0","0","0","0","0"} ;
            Integer total = 0;
            for(int i = 0;i < 7; i++){
                for(AlarmDevp item: map.get(key)){
                    String time = new SimpleDateFormat("yyyy-MM-dd").format(item.getAlarmTime());
                    if (week[i].equals(time)) {
                        strArr[i] = item.getCount().toString();
                        total += item.getCount();
                        break;
                    }
                }
            }
            strArr[7] = total.toString();
            countMap.put(key,strArr);
        }
        model.addAttribute("countMap",countMap);
        model.addAttribute("week",week);
        model.addAttribute("page", page);
        model.addAttribute("alarmQueryBean", alarmQueryBean);

        return "monitoringMaintain/alarmListByPage";
    }
    /*//点击新增或修改按钮
    @RequestMapping(value = "/savePage", method = {RequestMethod.GET})
    public String savePage(Model model, Integer recordId) throws Exception {
        if (recordId != null) {
            MonitoringMaintain maintain = monitoringMaintainService.findMonitoringMaintainById(recordId);
            maintain.setId(recordId);
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
*/
}
