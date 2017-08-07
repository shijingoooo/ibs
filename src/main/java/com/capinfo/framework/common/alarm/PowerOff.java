package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.pojo.MonitoringDevice;
import com.capinfo.framework.web.service.MonitoringAlarmService;
import com.capinfo.framework.web.service.MonitoringDataService;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shijing on 2017/8/4.
 */
@Component("powerOff")
public class PowerOff extends BaseMethod{

    private List<MonitoringData> dataList;
    @Resource
    private MonitoringDeviceService monitoringDeviceService;
    @Resource
    private MonitoringDataService monitoringDataService;
    @Resource
    private MonitoringAlarmService alarmService;
    @Override
    public void run(){
        try{
            System.out.println("powerOff !!!");
            dataList = monitoringDataService.findMonitoringDataList();
            for (MonitoringData data:dataList) {
                if( data.getElectricQuantity() != null && data.getElectricQuantity() == 1){
                    AlarmDevp alarmDevp = new AlarmDevp();
                    alarmDevp.setDeviceId(data.getDeviceId());
                    //告警类型设备断电0
                    alarmDevp.setAlarmType(0);
                    //告警原因0表示设备故障
                    alarmDevp.setAlarmCause(0);
                    //告警时间为当前时间
                    alarmDevp.setAlarmTime(new Date());
                    //处理状态默认未处理0
                    alarmDevp.setAlarmStatus(0);
                    alarmService.saveMonitoringAlarmRecord(alarmDevp);
                    //System.out.println(data.getMonitoringDevice().getDevName());
                }
            }
            System.out.println((dataList.get(1).getMonitoringDevice()).getDevName());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
