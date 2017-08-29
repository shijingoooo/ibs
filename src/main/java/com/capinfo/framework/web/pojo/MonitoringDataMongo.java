package com.capinfo.framework.web.pojo;

import java.util.Date;

/**
 * @Author Zhang Chuanjia
 * @Created with IntelliJ IDEA
 * @User: zhang
 * @Date: 2017/8/25 17:20
 */
public class MonitoringDataMongo {

    private String _id;

    private Double actualTwoPm;

    private Double calibrationTwoPm;

    private Double actualTenPm;

    private Double calibrationTenPm;

    private Double actualTsp;

    private Double calibrationTsp;

    private Double actualNoise;

    private Double calibrationNoise;

    private Double actualTemperature;

    private Double actualHumidity;

    private Double actualPressure;

    private Double actualWindSpeed;

    private Double actualWindDirection;

    private Date colTime;

    private Date createTime;

    private Integer version;

    private Integer device_id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public Double getActualTwoPm() {
        return actualTwoPm;
    }

    public void setActualTwoPm(Double actualTwoPm) {
        this.actualTwoPm = actualTwoPm;
    }

    public Double getCalibrationTwoPm() {
        return calibrationTwoPm;
    }

    public void setCalibrationTwoPm(Double calibrationTwoPm) {
        this.calibrationTwoPm = calibrationTwoPm;
    }

    public Double getActualTenPm() {
        return actualTenPm;
    }

    public void setActualTenPm(Double actualTenPm) {
        this.actualTenPm = actualTenPm;
    }

    public Double getCalibrationTenPm() {
        return calibrationTenPm;
    }

    public void setCalibrationTenPm(Double calibrationTenPm) {
        this.calibrationTenPm = calibrationTenPm;
    }

    public Double getActualTsp() {
        return actualTsp;
    }

    public void setActualTsp(Double actualTsp) {
        this.actualTsp = actualTsp;
    }

    public Double getCalibrationTsp() {
        return calibrationTsp;
    }

    public void setCalibrationTsp(Double calibrationTsp) {
        this.calibrationTsp = calibrationTsp;
    }

    public Double getActualNoise() {
        return actualNoise;
    }

    public void setActualNoise(Double actualNoise) {
        this.actualNoise = actualNoise;
    }

    public Double getCalibrationNoise() {
        return calibrationNoise;
    }

    public void setCalibrationNoise(Double calibrationNoise) {
        this.calibrationNoise = calibrationNoise;
    }

    public Double getActualTemperature() {
        return actualTemperature;
    }

    public void setActualTemperature(Double actualTemperature) {
        this.actualTemperature = actualTemperature;
    }

    public Double getActualHumidity() {
        return actualHumidity;
    }

    public void setActualHumidity(Double actualHumidity) {
        this.actualHumidity = actualHumidity;
    }

    public Double getActualPressure() {
        return actualPressure;
    }

    public void setActualPressure(Double actualPressure) {
        this.actualPressure = actualPressure;
    }

    public Double getActualWindSpeed() {
        return actualWindSpeed;
    }

    public void setActualWindSpeed(Double actualWindSpeed) {
        this.actualWindSpeed = actualWindSpeed;
    }

    public Double getActualWindDirection() {
        return actualWindDirection;
    }

    public void setActualWindDirection(Double actualWindDirection) {
        this.actualWindDirection = actualWindDirection;
    }

    public Date getColTime() {
        return colTime;
    }

    public void setColTime(Date colTime) {
        this.colTime = colTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDevice_id() {
        return device_id;
    }

    public void setDevice_id(Integer device_id) {
        this.device_id = device_id;
    }
}
