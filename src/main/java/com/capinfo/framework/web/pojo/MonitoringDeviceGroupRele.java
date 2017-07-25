package com.capinfo.framework.web.pojo;

import java.util.Date;
import java.util.List;

/**
 * 设备组与设备包含关系
 * @author shijing
 *
 */
public class MonitoringDeviceGroupRele {
	// 关系ID（自增）
	protected Integer id;
	// 设备组ID
	protected Integer groupId;
	// 设备ID
	protected Integer deviceId;
	//设备编号
	protected String devCode;
	//设备名称
	protected String devName;
	//设备组名称
	protected String devGroupName;
	//设备组类型
	protected String devGroupType;
	//是否在线
	protected String devStatus;
	//设备类型
	protected Integer devType;
	//所属设备组
	protected List<String> belongGroups;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGroupId() {
		return groupId;
	}

	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}

	public Integer getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevGroupName() {
		return devGroupName;
	}

	public void setDevGroupName(String devGroupName) {
		this.devGroupName = devGroupName;
	}

	public List<String> getBelongGroups() {
		return belongGroups;
	}

	public void setBelongGroups(List<String> belongGroups) {
		this.belongGroups = belongGroups;
	}

	public String getDevGroupType() {
		return devGroupType;
	}

	public void setDevGroupType(String devGroupType) {
		this.devGroupType = devGroupType;
	}

	public String getDevStatus() {
		return devStatus;
	}

	public void setDevStatus(String devStatus) {
		this.devStatus = devStatus;
	}

	public Integer getDevType() {
		return devType;
	}

	public void setDevType(Integer devType) {
		this.devType = devType;
	}
}
