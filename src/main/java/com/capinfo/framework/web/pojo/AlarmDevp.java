package com.capinfo.framework.web.pojo;

import java.util.Date;

public class AlarmDevp {
    private Integer id;

    private Integer deviceId;

    private String deviceCode;

    private Boolean alarmStatus;

    private Boolean alarmType;

    private Boolean alarmCause;

    private Date alarmTime;

    private MonitoringProject monitoringProject;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Boolean getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Boolean alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public Boolean getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Boolean alarmType) {
        this.alarmType = alarmType;
    }

    public Boolean getAlarmCause() {
        return alarmCause;
    }

    public void setAlarmCause(Boolean alarmCause) {
        this.alarmCause = alarmCause;
    }

    public Date getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(Date alarmTime) {
        this.alarmTime = alarmTime;
    }

    public MonitoringProject getMonitoringProject() {
        return monitoringProject;
    }

    public void setMonitoringProject(MonitoringProject monitoringProject) {
        this.monitoringProject = monitoringProject;
    }
}