package com.capinfo.framework.common.alarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by shijing on 2017/8/1.
 */

public class MyTask{
    @Resource
    private CheckData checkData;
    @Resource
    private PowerOff powerOff;

    public MyTask(CheckData checkData,PowerOff powerOff){
        this.checkData = checkData;
        this.powerOff = powerOff;
        powerOff.setData(checkData);
    }
    public void run() {
        //System.out.println(powerOff);
        checkData.notifyMethod();
    }

}
