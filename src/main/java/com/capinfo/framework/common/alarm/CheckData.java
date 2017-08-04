package com.capinfo.framework.common.alarm;

import com.capinfo.framework.web.mapper.MonitoringDeviceMapper;
import com.capinfo.framework.web.pojo.AlarmDevp;
import com.capinfo.framework.web.pojo.MonitoringData;
import com.capinfo.framework.web.pojo.MonitoringDevice;

import java.util.ArrayList;

/**
 * Created by shijing on 2017/8/4.
 */
public class CheckData {
    private ArrayList<BaseMethod> methods;

    public CheckData(){
        methods = new ArrayList<BaseMethod>();
    }
    //注册方法
    public void registerMethod(BaseMethod method){
        methods.add(method);
    }
    //移除方法
    public void removeMethod(BaseMethod method){
        int i = methods.indexOf(method);
        if(i >= 0){
            methods.remove(method);
        }
    }
    //通知方法
    public void notifyMethod(){
        for(int i = 0; i < methods.size(); i++){
            BaseMethod baseMethod = (BaseMethod)methods.get(i);
            baseMethod.run();
        }
    }
    public void changed(){
        notifyMethod();
    }
}
