package com.capinfo.framework.web.vo;

import java.util.Date;

public class MonitoringMaintainQueryBean {

	//记录id
	protected Integer id;
	// 设备编号查询
	protected String devCodeForLike;
	// 开始时间
	protected String startTime;
	// 结束时间
	protected String endTime;
	// 设备id
	protected Integer devId;
	// 设备编号
	protected String devCode;
	// 故障时间
	protected String troubleTime;
	// 故障原因
	protected String troubleReason;
	// 原因描述
	protected String troubleDescription;
	// 解决时间
	protected String solveTime;
	// 解决途径
	protected String solveWay;
	// 解决方法
	protected String solveMethod;
	//方法描述
	protected String methodDescription;
	// 责任人
	protected String responsible;
	// 备注
	protected String remark;
	//创建时间
	protected Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevCodeForLike() {
		return devCodeForLike;
	}

	public void setDevCodeForLike(String devCodeForLike) {
		this.devCodeForLike = devCodeForLike;
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

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getTroubleTime() {
		return troubleTime;
	}

	public void setTroubleTime(String troubleTime) {
		this.troubleTime = troubleTime;
	}

	public String getTroubleReason() {
		return troubleReason;
	}

	public void setTroubleReason(String troubleReason) {
		this.troubleReason = troubleReason;
	}

	public String getSolveTime() {
		return solveTime;
	}

	public void setSolveTime(String solveTime) {
		this.solveTime = solveTime;
	}

	public String getSolveWay() {
		return solveWay;
	}

	public void setSolveWay(String solveWay) {
		this.solveWay = solveWay;
	}

	public String getSolveMethod() {
		return solveMethod;
	}

	public void setSolveMethod(String solveMethod) {
		this.solveMethod = solveMethod;
	}

	public String getResponsible() {
		return responsible;
	}

	public void setResponsible(String responsible) {
		this.responsible = responsible;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getDevId() {
		return devId;
	}

	public void setDevId(Integer devId) {
		this.devId = devId;
	}

	public String getTroubleDescription() {
		return troubleDescription;
	}

	public void setTroubleDescription(String troubleDescription) {
		this.troubleDescription = troubleDescription;
	}

	public String getMethodDescription() {
		return methodDescription;
	}

	public void setMethodDescription(String methodDescription) {
		this.methodDescription = methodDescription;
	}
}
