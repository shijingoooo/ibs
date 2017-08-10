package com.capinfo.framework.web.pojo;

/**
 * Created by Administrator on 2017/2/21.
 */
public class DeviceRecentData {
    private Integer id;
    private String devCode;
    private String devName;
    private String devStatus;
    private Integer devGroupId;
    private String devGroupCode;
    private String devGroupName;
    private Integer dataId;

    public Integer getDataId() {
        return dataId;
    }

    public void setDataId(Integer dataId) {
        this.dataId = dataId;
    }

    public Integer getDevGroupId() {
        return devGroupId;
    }

    public void setDevGroupId(Integer devGroupId) {
        this.devGroupId = devGroupId;
    }

    public String getDevGroupName() {
        return devGroupName;
    }

    public void setDevGroupName(String devGroupName) {
        this.devGroupName = devGroupName;
    }

    public String getDevGroupCode() {
        return devGroupCode;
    }

    public void setDevGroupCode(String devGroupCode) {
        this.devGroupCode = devGroupCode;
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
}