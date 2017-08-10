package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.pojo.DeviceRecentData;
import com.capinfo.framework.web.service.MonitoringAlarmService;
import com.capinfo.framework.web.service.MonitoringDataService;
import com.capinfo.framework.web.service.MonitoringDeviceService;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by shijing on 2017/8/7.
 */
public class ConstValue extends BaseMethod {
    @Resource
    private CheckData checkData;

    @Resource
    private MonitoringDeviceService deviceService;

    @Resource
    private MonitoringDataService dataService;

    @Resource
    private MonitoringAlarmService alarmService;

    public ConstValue(CheckData checkData){
        super(checkData);
        this.checkData = checkData;
    }
    @Override
    public void run() throws Exception{
        System.out.println("ConstValue!!!");
        //先判断0值再判断恒值
        //获取设备最新十条数据
        /*List<DeviceRecentData> dataList = dataService.findDeviceListWithData();
        for (DeviceRecentData data:dataList) {
            System.out.println(data.getDataList().toString());
        }*/
        //判断0值
    }
}
