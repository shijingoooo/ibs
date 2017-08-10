package com.capinfo.framework.common.alarm;

/**
 * Created by shijing on 2017/8/4.
 */
public class PowerOff extends BaseMethod{
    public PowerOff(CheckData checkData){
        super(checkData);
    }
    @Override
    public void run(){
        try{
            System.out.println("offLine !!!");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
