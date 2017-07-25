package com.capinfo.framework.web.pojo;

import java.util.Date;
import java.util.List;

/**
 * 设备组
 * @author shijing
 *
 */
public class MonitoringDeviceGroup {
	// 设备组ID
	protected Integer id;
	
	// 设备组名称
	protected String groupDevName;

	// 设备组类型
	protected String groupDevType;

	// 设备组描述
	protected String groupDevDescription;

	// 设备组包含设备数量
	protected Integer devCount;

	// 创建时间
	protected Date createTime;

	// 包含设备
	protected List<MonitoringDevice> monitoringDevices;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getGroupDevName() {
		return groupDevName;
	}

	public void setGroupDevName(String groupDevName) {
		this.groupDevName = groupDevName;
	}

	public String getGroupDevType() {
		return groupDevType;
	}

	public void setGroupDevType(String groupDevType) {
		this.groupDevType = groupDevType;
	}

	public String getGroupDevDescription() {
		return groupDevDescription;
	}

	public void setGroupDevDescription(String groupDevDescription) {
		this.groupDevDescription = groupDevDescription;
	}

	public Integer getDevCount() {
		return devCount;
	}

	public void setDevCount(Integer devCount) {
		this.devCount = devCount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<MonitoringDevice> getMonitoringDevices() {
		return monitoringDevices;
	}

	public void setMonitoringDevices(List<MonitoringDevice> monitoringDevices) {
		this.monitoringDevices = monitoringDevices;
	}
}
