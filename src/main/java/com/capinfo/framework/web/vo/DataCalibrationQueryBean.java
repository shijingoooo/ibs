package com.capinfo.framework.web.vo;

import java.util.Date;

public class DataCalibrationQueryBean {
	//规则Id
	protected Integer id;
	//设备Id
	protected Integer deviceId;
	//设备编号
	protected String deviceCode;
	//设备编号查询
	protected String deviceCodeForLike;
	//设备名称
	protected String deviceName;
	//设备名称查询
	protected String deviceNameForLike;
	//数据指标
	protected String indicator;
	//单位
	protected String unit;
	//下限
	protected String mins;
	//上限
	protected String maxs;
	//校正系数
	protected String calibrationFactors;
	//状态：启用或停用
	protected Integer status;

	protected Date createTime;
	//
	protected String deviceType;
	//添加规则时记录个数
	protected Integer count;

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

	public String getDeviceCodeForLike() {
		return deviceCodeForLike;
	}

	public void setDeviceCodeForLike(String deviceCodeForLike) {
		this.deviceCodeForLike = deviceCodeForLike;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getDeviceNameForLike() {
		return deviceNameForLike;
	}

	public void setDeviceNameForLike(String deviceNameForLike) {
		this.deviceNameForLike = deviceNameForLike;
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getMins() {
		return mins;
	}

	public void setMins(String mins) {
		this.mins = mins;
	}

	public String getMaxs() {
		return maxs;
	}

	public void setMaxs(String maxs) {
		this.maxs = maxs;
	}

	public String getCalibrationFactors() {
		return calibrationFactors;
	}

	public void setCalibrationFactors(String calibrationFactors) {
		this.calibrationFactors = calibrationFactors;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
}
