package com.capinfo.framework.web.pojo;

import java.util.Date;

public class ProjectUserRele {
	
	protected Integer id;
	
	protected User user;
	
	protected MonitoringProject monitoringProject;
	
	protected Date createTime;
	
	protected Date updateTime;
	
	protected Integer creater;
	
	protected Integer updater;
	
	protected Integer version;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public MonitoringProject getMonitoringProject() {
		return monitoringProject;
	}

	public void setMonitoringProject(MonitoringProject monitoringProject) {
		this.monitoringProject = monitoringProject;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ProjectUserRele [id=" + id + ", userId=" + user.getId() + ", projectId=" + monitoringProject.getId()
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", creater=" + creater + ", updater=" + updater
				+ ", version=" + version + "]";
	}

}
