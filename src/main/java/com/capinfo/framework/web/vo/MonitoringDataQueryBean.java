package com.capinfo.framework.web.vo;

import java.util.Date;

public class MonitoringDataQueryBean {

	protected Integer[] idForIn;
	// 粉尘 实际测量值
	protected Float actualTsp;

	// 校准值
	protected Float calibrationTsp;
	
	// pm2.5
	protected Float actualTwoPm;

	// 校准值
	protected Float calibrationTwoPm;
	
	// pm10
	protected Float actualTenPm;

	// 校准值
	protected Float calibrationTenPm;	
	
	// 噪声
	protected Float actualNoise;

	// 校准值
	protected Float calibrationNoise;
	
	// 背景噪声
	protected Float actualBgNoise;
	
	// 校准值
	protected Float calibrationBgNoise;
	
	// 最大声值级别
	protected Integer actualMaxLevel;
	
	// 校准值
	protected Integer calibrationMaxLevel;
	
	// 测前仪器校准值
	protected Float beforeDevCalibration;
	
	// 测后仪器校准值
	protected Float afterDevCalibration;
	
	// 温度
	protected Float actualTemperature;

	// 湿度
	protected Float actualHumidity;

	// 气压
	protected Integer actualPressure;

	// 降雨量
	protected Float actualRainfall;

	// 是否下雨
	protected Integer isRain; 
	
	// 风速
	protected Float actualWindSpeed;

	// 风向 按360度划分，0° 北向，90°东向，180°南向，270°西向
	protected Integer actualWindDirection;
	
	// 电池电量
	protected Integer electricQuantity;
	
	// 采集时间
	protected Date colTime;
	
	protected Integer longTime;
	
	// 数据标示
	protected String dataSign;
	
	protected Integer version;
	
	protected String startTime;
	
	protected String endTime;
	
	protected Integer shSign;
	
	protected Integer deviceId;

	public Integer[] getIdForIn() {
		return idForIn;
	}

	public void setIdForIn(Integer[] idForIn) {
		this.idForIn = idForIn;
	}

	public Float getActualTsp() {
		return actualTsp;
	}

	public void setActualTsp(Float actualTsp) {
		this.actualTsp = actualTsp;
	}

	public Float getCalibrationTsp() {
		return calibrationTsp;
	}

	public void setCalibrationTsp(Float calibrationTsp) {
		this.calibrationTsp = calibrationTsp;
	}

	public Float getActualTwoPm() {
		return actualTwoPm;
	}

	public void setActualTwoPm(Float actualTwoPm) {
		this.actualTwoPm = actualTwoPm;
	}

	public Float getCalibrationTwoPm() {
		return calibrationTwoPm;
	}

	public void setCalibrationTwoPm(Float calibrationTwoPm) {
		this.calibrationTwoPm = calibrationTwoPm;
	}

	public Float getActualTenPm() {
		return actualTenPm;
	}

	public void setActualTenPm(Float actualTenPm) {
		this.actualTenPm = actualTenPm;
	}

	public Float getCalibrationTenPm() {
		return calibrationTenPm;
	}

	public void setCalibrationTenPm(Float calibrationTenPm) {
		this.calibrationTenPm = calibrationTenPm;
	}

	public Float getActualNoise() {
		return actualNoise;
	}

	public void setActualNoise(Float actualNoise) {
		this.actualNoise = actualNoise;
	}

	public Float getCalibrationNoise() {
		return calibrationNoise;
	}

	public void setCalibrationNoise(Float calibrationNoise) {
		this.calibrationNoise = calibrationNoise;
	}

	public Float getActualBgNoise() {
		return actualBgNoise;
	}

	public void setActualBgNoise(Float actualBgNoise) {
		this.actualBgNoise = actualBgNoise;
	}

	public Float getCalibrationBgNoise() {
		return calibrationBgNoise;
	}

	public void setCalibrationBgNoise(Float calibrationBgNoise) {
		this.calibrationBgNoise = calibrationBgNoise;
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

	public Float getBeforeDevCalibration() {
		return beforeDevCalibration;
	}

	public void setBeforeDevCalibration(Float beforeDevCalibration) {
		this.beforeDevCalibration = beforeDevCalibration;
	}

	public Float getAfterDevCalibration() {
		return afterDevCalibration;
	}

	public void setAfterDevCalibration(Float afterDevCalibration) {
		this.afterDevCalibration = afterDevCalibration;
	}

	public Float getActualTemperature() {
		return actualTemperature;
	}

	public void setActualTemperature(Float actualTemperature) {
		this.actualTemperature = actualTemperature;
	}

	public Float getActualHumidity() {
		return actualHumidity;
	}

	public void setActualHumidity(Float actualHumidity) {
		this.actualHumidity = actualHumidity;
	}

	public Integer getActualPressure() {
		return actualPressure;
	}

	public void setActualPressure(Integer actualPressure) {
		this.actualPressure = actualPressure;
	}

	public Float getActualRainfall() {
		return actualRainfall;
	}

	public void setActualRainfall(Float actualRainfall) {
		this.actualRainfall = actualRainfall;
	}

	public Integer getIsRain() {
		return isRain;
	}

	public void setIsRain(Integer isRain) {
		this.isRain = isRain;
	}

	public Float getActualWindSpeed() {
		return actualWindSpeed;
	}

	public void setActualWindSpeed(Float actualWindSpeed) {
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
		this.dataSign = dataSign;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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

	public Integer getShSign() {
		return shSign;
	}

	public void setShSign(Integer shSign) {
		this.shSign = shSign;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

}
