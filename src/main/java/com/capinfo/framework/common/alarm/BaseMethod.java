package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.mapper.MonitoringDataMapper;
import com.capinfo.framework.web.mapper.MonitoringDeviceMapper;
import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.pojo.MonitoringDevice;
import com.capinfo.framework.web.service.MonitoringDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by shijing on 2017/8/4.
 */

public class BaseMethod {

    private CheckData checkData;

    public void setData(CheckData checkData){
        checkData.registerMethod(this);
    }
    public void run(){}
}
