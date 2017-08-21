package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.pojo.DeviceRecentData;
import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.pojo.MonitoringDevice;
import com.capinfo.framework.web.service.MonitoringAlarmService;
import com.capinfo.framework.web.service.MonitoringDataService;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import com.capinfo.framework.web.vo.MonitoringAlarmQueryBean;
import com.capinfo.framework.web.vo.MonitoringDeviceQueryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by shijing on 2017/8/4.
 */
public class OffLine extends BaseMethod{

    @Resource
    private CheckData checkData;

    @Resource
    private MonitoringDeviceService deviceService;

    @Resource
    private MonitoringDataService monitoringDataService;

    @Resource
    private MonitoringAlarmService alarmService;

    public OffLine(CheckData checkData){
        super(checkData);
        this.checkData = checkData;
    }
    @Override
    public void run() throws Exception{
        System.out.println("start scan !");
        List<DeviceRecentData>dataList = deviceService.findMonitoringDeviceRecentDataList();
        for (DeviceRecentData item:dataList) {
            if (item.getDataId() == null) {
                if ("1".equals(item.getDevStatus())) {
                    //MonitoringDeviceQueryBean deviceQueryBean = new MonitoringDeviceQueryBean();
                    //deviceQueryBean.setDevStatus("0");
                    //deviceService.updateMonitoringDevice(item.getId(), deviceQueryBean);
                    //更新告警，告警类型修改为0，告警消除
                    //查找该设备有没有同类型的告警记录：有则更新，无则新增
                    MonitoringAlarmQueryBean alarmQueryBean = new MonitoringAlarmQueryBean();
                    alarmQueryBean.setDevId(item.getId());
                    alarmQueryBean.setAlarmType(1);
                    List<AlarmDevp> alarmList = alarmService.findMonitoringAlarmList(alarmQueryBean);
                    if (alarmList.size() == 0)
                        //新增
                        alarmService.saveMonitoringAlarmRecord(alarmQueryBean);
                }
            }
        }
    }
}
