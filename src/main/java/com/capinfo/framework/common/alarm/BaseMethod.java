package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.mapper.MonitoringDataMapper;
import com.capinfo.framework.web.mapper.MonitoringDeviceMapper;
import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.pojo.MonitoringDevice;

import java.util.ArrayList;

/**
 * Created by shijing on 2017/8/4.
 */
public class BaseMethod {

    private CheckData checkData;

    private MonitoringDeviceMapper monitoringDeviceMapper;

    private MonitoringDataMapper monitoringDataMapper;

    public BaseMethod(CheckData checkData){
        checkData.registerMethod(this);
    }
    public void run(){}
}
