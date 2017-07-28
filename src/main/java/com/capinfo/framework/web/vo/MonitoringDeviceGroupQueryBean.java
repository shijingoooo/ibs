package com.capinfo.framework.web.vo;

import java.util.Date;

public class MonitoringDeviceGroupQueryBean {
	//用户
	protected Integer adminId;
	//用户类型
	protected Integer adminType;
	//设备ID
	protected Integer id;
	//设备名称
	protected String devName;
	//设备编号
	protected String devCode;
	//设备编号模糊查询
	protected String devCodeForLike;
	//设备组名称
	protected String devGroupName;
	//设备组名称模糊查询
	protected String devGroupNameForLike;
	//不属于类型模糊查询
	protected String notBelongTypeForLike;
	//设备组类型
	protected String devGroupType;
	//设备组类型模糊查询
	protected String devGroupTypeForLike;
	//设备类型
	protected String devType;
	//设备描述
	protected String devDescription;
	//创建设备组日期
	protected Date createTime;

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}

	public Integer getAdminType() {
		return adminType;
	}

	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevCodeForLike() {
		return devCodeForLike;
	}

	public void setDevCodeForLike(String devCodeForLike) {
		this.devCodeForLike = devCodeForLike;
	}

	public String getDevGroupName() {
		return devGroupName;
	}

	public void setDevGroupName(String devGroupName) {
		this.devGroupName = devGroupName;
	}

	public String getDevGroupNameForLike() {
		return devGroupNameForLike;
	}

	public void setDevGroupNameForLike(String devGroupNameForLike) {
		this.devGroupNameForLike = devGroupNameForLike;
	}

	public String getDevGroupType() {
		return devGroupType;
	}

	public void setDevGroupType(String devGroupType) {
		this.devGroupType = devGroupType;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNotBelongTypeForLike() {
		return notBelongTypeForLike;
	}

	public void setNotBelongTypeForLike(String notBelongTypeForLike) {
		this.notBelongTypeForLike = notBelongTypeForLike;
	}

	public String getDevDescription() {
		return devDescription;
	}

	public void setDevDescription(String devDescription) {
		this.devDescription = devDescription;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDevGroupTypeForLike() {
		return devGroupTypeForLike;
	}

	public void setDevGroupTypeForLike(String devGroupTypeForLike) {
		this.devGroupTypeForLike = devGroupTypeForLike;
	}
}
