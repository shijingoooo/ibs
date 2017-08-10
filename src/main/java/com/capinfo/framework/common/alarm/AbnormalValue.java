package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.service.MonitoringAlarmService;
import com.capinfo.framework.web.service.MonitoringDataService;
import com.capinfo.framework.web.service.MonitoringDeviceService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * Created by shijing on 2017/8/7.
 */
public class AbnormalValue extends BaseMethod {
    @Resource
    private CheckData checkData;
    private List<MonitoringData> dataList;
    @Resource
    private MonitoringDeviceService monitoringDeviceService;
    @Resource
    private MonitoringDataService monitoringDataService;
    @Resource
    private MonitoringAlarmService alarmService;

    public AbnormalValue(CheckData checkData){
        super(checkData);
        this.checkData = checkData;
        this.checkData.registerMethod(this);
    }
    @Override
    public void run(){
        try{
            System.out.println("AbnormalValue !!!");
            dataList = monitoringDataService.findMonitoringDataList();
            for (MonitoringData data:dataList) {
                if( data.getElectricQuantity() != null){

                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
