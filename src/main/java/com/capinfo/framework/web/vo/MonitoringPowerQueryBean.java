package com.capinfo.framework.web.vo;

import java.util.Date;

public class MonitoringPowerQueryBean {

	protected Integer id;
	//GPRS开关编号
	protected Integer devId;

	// GPRS开关名称
	protected String devName;
	
	// GPRS开关地址
	protected String addr;

	// ip地址/域名
	protected String ip;
	
	// 端口
	protected Integer port;

	// 时间
	protected Date time;
	
	// 设备ID
	protected Integer deviceId;

	// GPRS开关状态
	protected Integer status;
	//GPRS开关控制方式
	protected Integer mode;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDevId() {
		return devId;
	}

	public void setDevId(Integer devId) {
		this.devId = devId;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}
}
