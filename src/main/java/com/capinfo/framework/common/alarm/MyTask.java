package com.capinfo.framework.common.alarm;

import javax.servlet.ServletContext;
import java.util.Calendar;
import java.util.TimerTask;

/**
 * Created by shijing on 2017/8/1.
 */
public class MyTask extends TimerTask {

    public MyTask() {
        super();
    }

    /** 14：30点钟的时候执行各矿数据强制提交 */
    private static final int C_SCHEDULE_HOUR = 14;
    private static final int C_SCHEDULE_MINUTE = 30;
    /**报表的备份 */
    //private static final int C_SCHEDULE_HOUR = 12;
    //private static final int C_SCHEDULE_MINUTE = 32;
    /** 每个星期给各矿发邮件 */
    //private static final int C_SCHEDULE_HOUR = 12;
    //private static final int C_SCHEDULE_MINUTE = 32;
    private static boolean isRunning = false;

    private ServletContext context = null;

    public MyTask(ServletContext context) {
        this.context = context;
    }

    public void run() {

        CheckData checkdata = new CheckData();
        PowerOff powerOff = new PowerOff(checkdata);
        OffLine offLine = new OffLine(checkdata);
        checkdata.notifyMethod();
        /*Calendar cal = Calendar.getInstance();
        if (!isRunning) {


            System.out.println("当前时间为"+cal.get(Calendar.HOUR_OF_DAY) +":"+cal.get(Calendar.MINUTE));
            if (C_SCHEDULE_HOUR == cal.get(Calendar.HOUR_OF_DAY) && C_SCHEDULE_MINUTE == cal.get(Calendar.MINUTE)) {
                isRunning = true;
                context.log("开始执行指定任务");

                *//**
                 * 此处写执行任务代码
                 *//*
                CheckData checkdata = new CheckData();
                PowerOff powerOff = new PowerOff(checkdata);
                checkdata.notifyMethod();

                isRunning = false;
                context.log("指定任务执行结束");
            }
        } else {
            context.log("上一次任务执行还未结束");
        }*/
    }

}
