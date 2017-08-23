package com.capinfo.framework.web.pojo;

import java.math.BigDecimal;
import java.util.Date;

public class MonitoringData {
    private Integer id;

    private BigDecimal actualTsp;

    private BigDecimal calibrationTsp;

    private BigDecimal actualTwoPm;

    private BigDecimal calibrationTwoPm;

    private BigDecimal actualTenPm;

    private BigDecimal calibrationTenPm;

    private BigDecimal actualNoise;

    private BigDecimal calibrationNoise;

    private BigDecimal actualBgNoise;

    private BigDecimal calibrationBgNoise;

    private BigDecimal actual02;

    private BigDecimal calibration02;

    private BigDecimal actualNO2;

    private BigDecimal calibrationNo2;

    private BigDecimal actual03;

    private BigDecimal calibration03;

    private BigDecimal actual04;

    private BigDecimal calibration04;

    private BigDecimal actualAqi;

    private BigDecimal calibrationAqi;

    private Integer actualMaxLevel;

    private Integer calibrationMaxLevel;

    private BigDecimal beforeDevCalibration;

    private BigDecimal afterDevCalibration;

    private BigDecimal actualTemperature;

    private BigDecimal actualHumidity;

    private Integer actualPressure;

    private BigDecimal actualRainfall;

    private Boolean isRain;

    private BigDecimal actualWindSpeed;

    private Integer actualWindDirection;

    private Integer electricQuantity;

    private Date colTime;

    private Integer longTime;

    private String dataSign;

    private Integer creater;

    private Integer updater;

    private Date createTime;

    private Date updateTime;

    private Integer version;

    private Integer deviceId;

    private Boolean shSign;

    protected MonitoringDevice monitoringDevice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getActualTsp() {
        return actualTsp;
    }

    public void setActualTsp(BigDecimal actualTsp) {
        this.actualTsp = actualTsp;
    }

    public BigDecimal getCalibrationTsp() {
        return calibrationTsp;
    }

    public void setCalibrationTsp(BigDecimal calibrationTsp) {
        this.calibrationTsp = calibrationTsp;
    }

    public BigDecimal getActualTwoPm() {
        return actualTwoPm;
    }

    public void setActualTwoPm(BigDecimal actualTwoPm) {
        this.actualTwoPm = actualTwoPm;
    }

    public BigDecimal getCalibrationTwoPm() {
        return calibrationTwoPm;
    }

    public void setCalibrationTwoPm(BigDecimal calibrationTwoPm) {
        this.calibrationTwoPm = calibrationTwoPm;
    }

    public BigDecimal getActualTenPm() {
        return actualTenPm;
    }

    public void setActualTenPm(BigDecimal actualTenPm) {
        this.actualTenPm = actualTenPm;
    }

    public BigDecimal getCalibrationTenPm() {
        return calibrationTenPm;
    }

    public void setCalibrationTenPm(BigDecimal calibrationTenPm) {
        this.calibrationTenPm = calibrationTenPm;
    }

    public BigDecimal getActualNoise() {
        return actualNoise;
    }

    public void setActualNoise(BigDecimal actualNoise) {
        this.actualNoise = actualNoise;
    }

    public BigDecimal getCalibrationNoise() {
        return calibrationNoise;
    }

    public void setCalibrationNoise(BigDecimal calibrationNoise) {
        this.calibrationNoise = calibrationNoise;
    }

    public BigDecimal getActualBgNoise() {
        return actualBgNoise;
    }

    public void setActualBgNoise(BigDecimal actualBgNoise) {
        this.actualBgNoise = actualBgNoise;
    }

    public BigDecimal getCalibrationBgNoise() {
        return calibrationBgNoise;
    }

    public void setCalibrationBgNoise(BigDecimal calibrationBgNoise) {
        this.calibrationBgNoise = calibrationBgNoise;
    }

    public BigDecimal getActual02() {
        return actual02;
    }

    public void setActual02(BigDecimal actual02) {
        this.actual02 = actual02;
    }

    public BigDecimal getCalibration02() {
        return calibration02;
    }

    public void setCalibration02(BigDecimal calibration02) {
        this.calibration02 = calibration02;
    }

    public BigDecimal getActualNO2() {
        return actualNO2;
    }

    public void setActualNO2(BigDecimal actualNO2) {
        this.actualNO2 = actualNO2;
    }

    public BigDecimal getCalibrationNo2() {
        return calibrationNo2;
    }

    public void setCalibrationNo2(BigDecimal calibrationNo2) {
        this.calibrationNo2 = calibrationNo2;
    }

    public BigDecimal getActual03() {
        return actual03;
    }

    public void setActual03(BigDecimal actual03) {
        this.actual03 = actual03;
    }

    public BigDecimal getCalibration03() {
        return calibration03;
    }

    public void setCalibration03(BigDecimal calibration03) {
        this.calibration03 = calibration03;
    }

    public BigDecimal getActual04() {
        return actual04;
    }

    public void setActual04(BigDecimal actual04) {
        this.actual04 = actual04;
    }

    public BigDecimal getCalibration04() {
        return calibration04;
    }

    public void setCalibration04(BigDecimal calibration04) {
        this.calibration04 = calibration04;
    }

    public BigDecimal getActualAqi() {
        return actualAqi;
    }

    public void setActualAqi(BigDecimal actualAqi) {
        this.actualAqi = actualAqi;
    }

    public BigDecimal getCalibrationAqi() {
        return calibrationAqi;
    }

    public void setCalibrationAqi(BigDecimal calibrationAqi) {
        this.calibrationAqi = calibrationAqi;
    }

    public Integer getActualMaxLevel() {
        return actualMaxLevel;
    }

    public void setActualMaxLevel(Integer actualMaxLevel) {
        this.actualMaxLevel = actualMaxLevel;
    }

    public Integer getCalibrationMaxLevel() {
        return calibrationMaxLevel;
    }

    public void setCalibrationMaxLevel(Integer calibrationMaxLevel) {
        this.calibrationMaxLevel = calibrationMaxLevel;
    }

    public BigDecimal getBeforeDevCalibration() {
        return beforeDevCalibration;
    }

    public void setBeforeDevCalibration(BigDecimal beforeDevCalibration) {
        this.beforeDevCalibration = beforeDevCalibration;
    }

    public BigDecimal getAfterDevCalibration() {
        return afterDevCalibration;
    }

    public void setAfterDevCalibration(BigDecimal afterDevCalibration) {
        this.afterDevCalibration = afterDevCalibration;
    }

    public BigDecimal getActualTemperature() {
        return actualTemperature;
    }

    public void setActualTemperature(BigDecimal actualTemperature) {
        this.actualTemperature = actualTemperature;
    }

    public BigDecimal getActualHumidity() {
        return actualHumidity;
    }

    public void setActualHumidity(BigDecimal actualHumidity) {
        this.actualHumidity = actualHumidity;
    }

    public Integer getActualPressure() {
        return actualPressure;
    }

    public void setActualPressure(Integer actualPressure) {
        this.actualPressure = actualPressure;
    }

    public BigDecimal getActualRainfall() {
        return actualRainfall;
    }

    public void setActualRainfall(BigDecimal actualRainfall) {
        this.actualRainfall = actualRainfall;
    }

    public Boolean getIsRain() {
        return isRain;
    }

    public void setIsRain(Boolean isRain) {
        this.isRain = isRain;
    }

    public BigDecimal getActualWindSpeed() {
        return actualWindSpeed;
    }

    public void setActualWindSpeed(BigDecimal actualWindSpeed) {
        this.actualWindSpeed = actualWindSpeed;
    }

    public Integer getActualWindDirection() {
        return actualWindDirection;
    }

    public void setActualWindDirection(Integer actualWindDirection) {
        this.actualWindDirection = actualWindDirection;
    }

    public Integer getElectricQuantity() {
        return electricQuantity;
    }

    public void setElectricQuantity(Integer electricQuantity) {
        this.electricQuantity = electricQuantity;
    }

    public Date getColTime() {
        return colTime;
    }

    public void setColTime(Date colTime) {
        this.colTime = colTime;
    }

    public Integer getLongTime() {
        return longTime;
    }

    public void setLongTime(Integer longTime) {
        this.longTime = longTime;
    }

    public String getDataSign() {
        return dataSign;
    }

    public void setDataSign(String dataSign) {
        this.dataSign = dataSign == null ? null : dataSign.trim();
    }

    public Integer getCreater() {
        return creater;
    }

    public void setCreater(Integer creater) {
        this.creater = creater;
    }

    public Integer getUpdater() {
        return updater;
    }

    public void setUpdater(Integer updater) {
        this.updater = updater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Integer getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Integer deviceId) {
        this.deviceId = deviceId;
    }

    public Boolean getShSign() {
        return shSign;
    }

    public void setShSign(Boolean shSign) {
        this.shSign = shSign;
    }

    public MonitoringDevice getMonitoringDevice() {
        return monitoringDevice;
    }

    public void setMonitoringDevice(MonitoringDevice monitoringDevice) {
        this.monitoringDevice = monitoringDevice;
    }
}