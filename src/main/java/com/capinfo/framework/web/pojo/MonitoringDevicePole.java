package com.capinfo.framework.web.pojo;

import java.util.Date;
import java.util.List;

/**
 * 设备杆
 * @author kevin_zhan
 *
 */
public class MonitoringDevicePole {
	
	protected MonitoringCompany monitoringCompany;
	
	protected MonitoringProject monitoringProject;
	
	protected Integer id;
	
	// 设备杆编号
	protected String devGroupCode;
	
	// 设备杆名称
	protected String devGroupName;
	
	// 经度
	protected String longitude;
	
	// 纬度
	protected String latitude;
	
	protected Integer creater;
	
	protected Integer updater;
	
	protected Date createTime;
	
	protected Date updateTime;
	
	protected Integer version;

	protected Integer position;
	
	protected List<MonitoringDevice> monitoringDevices;

	public MonitoringCompany getMonitoringCompany() {
		return monitoringCompany;
	}

	public void setMonitoringCompany(MonitoringCompany monitoringCompany) {
		this.monitoringCompany = monitoringCompany;
	}

	public MonitoringProject getMonitoringProject() {
		return monitoringProject;
	}

	public void setMonitoringProject(MonitoringProject monitoringProject) {
		this.monitoringProject = monitoringProject;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevGroupCode() {
		return devGroupCode;
	}

	public void setDevGroupCode(String devGroupCode) {
		this.devGroupCode = devGroupCode;
	}

	public String getDevGroupName() {
		return devGroupName;
	}

	public void setDevGroupName(String devGroupName) {
		this.devGroupName = devGroupName;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
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

	public Integer getPosition() {
		return position;
	}

	public void setPosition(Integer position) {
		this.position = position;
	}

	public List<MonitoringDevice> getMonitoringDevices() {
		return monitoringDevices;
	}

	public void setMonitoringDevices(List<MonitoringDevice> monitoringDevices) {
		this.monitoringDevices = monitoringDevices;
	}

}
