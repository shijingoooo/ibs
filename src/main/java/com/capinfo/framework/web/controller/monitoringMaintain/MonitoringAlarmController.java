package com.capinfo.framework.web.controller.monitoringMaintain;

import com.capinfo.framework.common.controller.BaseController;
import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.service.MonitoringAlarmService;
import com.capinfo.framework.web.vo.MonitoringAlarmQueryBean;
import com.capinfo.modules.orm.Page;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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
        //根据用户类型返回不同的数据
        alarmQueryBean.setDevId(devId);
        alarmQueryBean.setUserId((Integer) session.getAttribute("userid"));
        alarmQueryBean.setUserType((Integer) session.getAttribute("usertype"));
        monitoringAlarmService.findMonitoringAlarmPage(page, alarmQueryBean);
        //创建一周日期数组
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
            //储存一周数目总数
            strArr[7] = total.toString();
            countMap.put(key,strArr);
        }
        //获取每种类型的告警总数
        Map<String,Integer> totalCountByType = monitoringAlarmService.findAlarmCountByType();

        model.addAttribute("countMap",countMap);
        model.addAttribute("totalCountByType",totalCountByType);
        model.addAttribute("week",week);
        model.addAttribute("page", page);
        model.addAttribute("alarmQueryBean", alarmQueryBean);

        return "monitoringMaintain/alarmListByPage";
    }
    //打开处理告警的页面
    @RequestMapping(value = "/openHandle", method = {RequestMethod.POST, RequestMethod.GET})
    public String openHandle(Model model, MonitoringAlarmQueryBean alarmQueryBean, Integer id) throws Exception{
        //获取某一条记录的信息
        AlarmDevp alarmRecord = monitoringAlarmService.findAlarmRecordById(id);
        model.addAttribute("alarmRecord",alarmRecord);
        return "monitoringMaintain/handleAlarm";
    }
    //点击处理按钮
    @RequestMapping(value = "/handle", method = {RequestMethod.POST, RequestMethod.GET})
    public void updateHandle(Model model, HttpServletResponse response, MonitoringAlarmQueryBean alarmQueryBean, String callbackType, String rel) throws Exception{
        //更新某一条记录
        monitoringAlarmService.updateMonitoringAlarmRecord(alarmQueryBean);
        JSONObject jo = new JSONObject();
        jo.put("navTabId", rel);
        jo.put("callbackType", callbackType);
        jo.put("rel", rel);
        jo.put("statusCode", "200");
        jo.put("message", "操作成功");
        super.rendText(response, jo.toString());
    }
}
