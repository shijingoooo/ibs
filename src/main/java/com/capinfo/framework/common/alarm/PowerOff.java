package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.pojo.DeviceRecentData;
import com.capinfo.framework.web.service.MonitoringAlarmService;
import com.capinfo.framework.web.service.MonitoringDataService;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import com.capinfo.framework.web.vo.MonitoringAlarmQueryBean;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shijing on 2017/8/4.
 */
public class PowerOff extends BaseMethod{

    @Resource
    private CheckData checkData;

    @Resource
    private MonitoringDeviceService deviceService;

    @Resource
    private MonitoringDataService monitoringDataService;

    @Resource
    private MonitoringAlarmService alarmService;

    public PowerOff(CheckData checkData){
        super(checkData);
        this.checkData = checkData;
    }
    @Override
    public void run() throws Exception{
        System.out.println("PowerOff !!!");
        List<DeviceRecentData> dataList = deviceService.findDevicePowerType();
        for (DeviceRecentData item:dataList) {
            if (item.getElectricQuantity() != null) {
                if (1 == item.getElectricQuantity()) {
                    MonitoringAlarmQueryBean alarmQueryBean = new MonitoringAlarmQueryBean();
                    alarmQueryBean.setDevId(item.getId());
                    alarmQueryBean.setAlarmType(0);
                    List<AlarmDevp> alarmList = alarmService.findMonitoringAlarmList(alarmQueryBean);
                    if (alarmList.size() == 0)
                        //新增
                        alarmService.saveMonitoringAlarmRecord(alarmQueryBean);
                }
            }
        }
    }
}
