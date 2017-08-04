package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.pojo.MonitoringDevice;

import java.util.ArrayList;

/**
 * Created by shijing on 2017/8/4.
 */
public class PowerOff extends BaseMethod{
    public PowerOff(CheckData checkData){
        super(checkData);
    }
    @Override
    public void run(){
        System.out.println("powerOff !!!");
    }
}
