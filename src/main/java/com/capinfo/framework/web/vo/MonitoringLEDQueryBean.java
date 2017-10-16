package com.capinfo.framework.web.vo;

public class MonitoringLEDQueryBean {

	protected Integer id;

	// LED屏id
	protected String IdDev;
	
	// 设备id
	protected Integer IdDevice;

	// LED屏的ip地址/域名
	protected String ipSvr;
	
	// LED屏的端口
	protected Integer portSvr;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdDev() {
		return IdDev;
	}

	public void setIdDev(String idDev) {
		IdDev = idDev;
	}

	public Integer getIdDevice() {
		return IdDevice;
	}

	public void setIdDevice(Integer idDevice) {
		IdDevice = idDevice;
	}

	public String getIpSvr() {
		return ipSvr;
	}

	public void setIpSvr(String ipSvr) {
		this.ipSvr = ipSvr;
	}

	public Integer getPortSvr() {
		return portSvr;
	}

	public void setPortSvr(Integer portSvr) {
		this.portSvr = portSvr;
	}
}
