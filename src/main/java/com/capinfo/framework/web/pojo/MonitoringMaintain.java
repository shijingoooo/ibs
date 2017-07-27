package com.capinfo.framework.web.pojo;

import java.util.Date;

public class MonitoringMaintain {
    private Integer id;

    private Integer deviceId;

    private String deviceCode;

    private String troubleType;

    private String troubleDescription;

    private Date troubleTime;

    private String responsible;

    private String solveWay;

    private String solveMethod;

    private Date solveTime;

    private String methodDescription;

    private String remark;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public String getTroubleType() {
        return troubleType;
    }

    public void setTroubleType(String troubleType) {
        this.troubleType = troubleType == null ? null : troubleType.trim();
    }

    public String getTroubleDescription() {
        return troubleDescription;
    }

    public void setTroubleDescription(String troubleDescription) {
        this.troubleDescription = troubleDescription == null ? null : troubleDescription.trim();
    }

    public Date getTroubleTime() {
        return troubleTime;
    }

    public void setTroubleTime(Date troubleTime) {
        this.troubleTime = troubleTime;
    }

    public String getResponsible() {
        return responsible;
    }

    public void setResponsible(String responsible) {
        this.responsible = responsible == null ? null : responsible.trim();
    }

    public String getSolveWay() {
        return solveWay;
    }

    public void setSolveWay(String solveWay) {
        this.solveWay = solveWay == null ? null : solveWay.trim();
    }

    public String getSolveMethod() {
        return solveMethod;
    }

    public void setSolveMethod(String solveMethod) {
        this.solveMethod = solveMethod == null ? null : solveMethod.trim();
    }

    public Date getSolveTime() {
        return solveTime;
    }

    public void setSolveTime(Date solveTime) {
        this.solveTime = solveTime;
    }

    public String getMethodDescription() {
        return methodDescription;
    }

    public void setMethodDescription(String methodDescription) {
        this.methodDescription = methodDescription == null ? null : methodDescription.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}