package com.capinfo.framework.web.pojo;

import java.util.Date;
import java.util.List;

public class AlarmDevp {
    private Integer id;

    private Integer deviceId;

    private String deviceCode;

    private Integer alarmStatus;

    private Integer alarmType;

    private Integer alarmCause;

    private Date alarmTime;

    private Integer count;

    private MonitoringProject monitoringProject;

    private List<MonitoringMaintain> maintainRecord;

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

    public Integer getAlarmStatus() {
        return alarmStatus;
    }

    public void setAlarmStatus(Integer alarmStatus) {
        this.alarmStatus = alarmStatus;
    }

    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }

    public Integer getAlarmCause() {
        return alarmCause;
    }

    public void setAlarmCause(Integer alarmCause) {
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

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<MonitoringMaintain> getMaintainRecord() {
        return maintainRecord;
    }

    public void setMaintainRecord(List<MonitoringMaintain> maintainRecord) {
        this.maintainRecord = maintainRecord;
    }
}