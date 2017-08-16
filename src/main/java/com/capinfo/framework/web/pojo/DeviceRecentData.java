package com.capinfo.framework.web.pojo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by Administrator on 2017/2/21.
 */
public class DeviceRecentData {
    //设备ID
    private Integer id;
    private String devCode;
    private String devName;
    private String devStatus;
    private Integer devType;
    //OffLine
    private Integer dataId;
    //AbnormalTemperature
    private BigDecimal lastWeekAvgTemperature;
    private Integer projectId;
    //ConstValue
    private List<MonitoringData> dataList;
    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getDevCode() {
        return devCode;
    }

    public void setDevCode(String devCode) {
        this.devCode = devCode;
    }

    public String getDevStatus() {
        return devStatus;
    }

    public void setDevStatus(String devStatus) {
        this.devStatus = devStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getLastWeekAvgTemperature() {
        return lastWeekAvgTemperature;
    }

    public void setLastWeekAvgTemperature(BigDecimal lastWeekAvgTemperature) {
        this.lastWeekAvgTemperature = lastWeekAvgTemperature;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public List<MonitoringData> getDataList() {
        return dataList;
    }

    public void setDataList(List<MonitoringData> dataList) {
        this.dataList = dataList;
    }

    public Integer getDevType() {
        return devType;
    }

    public void setDevType(Integer devType) {
        this.devType = devType;
    }
}