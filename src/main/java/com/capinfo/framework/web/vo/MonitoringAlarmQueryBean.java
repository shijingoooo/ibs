package com.capinfo.framework.web.vo;

/**
 * Created by shijing on 2017/8/4.
 */
public class MonitoringAlarmQueryBean {
    //告警记录ID
    protected Integer id;
    //用户ID
    protected Integer userId;
    //设备ID
    protected Integer devId;
    //设备编号
    protected Integer devCode;
    //设备编号查询
    protected String devCodeForLike;
    //站点
    protected String proName;
    //站点查询
    protected String proNameForLike;
    //告警类型
    protected Integer alarmType;
    //处理状态
    protected Integer alarmStatus;
    //告警原因
    protected Integer alarmCause;
    //开始时间
    protected String startTime;
    //结束时间
    protected String endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public Integer getDevCode() {
        return devCode;
    }

    public void setDevCode(Integer devCode) {
        this.devCode = devCode;
    }

    public String getDevCodeForLike() {
        return devCodeForLike;
    }

    public void setDevCodeForLike(String devCodeForLike) {
        this.devCodeForLike = devCodeForLike;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getProNameForLike() {
        return proNameForLike;
    }

    public void setProNameForLike(String proNameForLike) {
        this.proNameForLike = proNameForLike;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Integer alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public Integer getAlarmCause() {
        return alarmCause;
    }

    public void setAlarmCause(Integer alarmCause) {
        this.alarmCause = alarmCause;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
}
