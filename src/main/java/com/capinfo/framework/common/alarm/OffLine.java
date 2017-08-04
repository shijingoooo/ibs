package com.capinfo.framework.common.alarm;

/**
 * Created by shijing on 2017/8/4.
 */
public class OffLine extends BaseMethod{
    public OffLine(CheckData checkData){
        super(checkData);
    }
    @Override
    public void run(){
        System.out.println("offLine !!!");
    }
}
