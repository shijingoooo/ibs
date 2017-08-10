package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.pojo.DeviceRecentData;
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
public class AbnormalTemperature extends BaseMethod {
    @Resource
    private CheckData checkData;

    @Resource
    private MonitoringDeviceService deviceService;

    @Resource
    private MonitoringDataService dataService;

    @Resource
    private MonitoringAlarmService alarmService;

    public AbnormalTemperature(CheckData checkData){
        super(checkData);
        this.checkData = checkData;
    }
    @Override
    public void run() throws Exception{
        System.out.println("AbnormalTemperature !!!");
        //获取扬尘噪声和AQI设备的站点ID以及近一周内设备收集的平均温度值
        List<DeviceRecentData> dataList = dataService.findLastWeekAvgTemperature();
        for (DeviceRecentData data:dataList) {
            System.out.println(data.getId());
            //根据设备ID获取该设备最新十条数据
            List<MonitoringData> dataRecords = dataService.findDataListByDevId(data.getId());
            for (MonitoringData item:dataRecords) {
                System.out.println(item.toString());
            }
        }
    }
}
