package com.capinfo.framework.web.pojo;

import java.util.Date;
import java.util.List;

public class Role {
	
	protected Integer id;
	
	protected String roleName;
	
	protected String roleDesc;

	protected String roleNameForLike;
	
	protected Date createTime;
	
	protected Date updateTime;
	
	protected Integer creater;
	
	protected Integer updater;
	
	protected Integer version;
	
	protected List<RoleMenuRele> roleMenus;
	
	protected List<UserRoleRele> roleUsers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
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

	public List<RoleMenuRele> getRoleMenus() {
		return roleMenus;
	}

	public void setRoleMenus(List<RoleMenuRele> roleMenus) {
		this.roleMenus = roleMenus;
	}

	public List<UserRoleRele> getRoleUsers() {
		return roleUsers;
	}

	public void setRoleUsers(List<UserRoleRele> roleUsers) {
		this.roleUsers = roleUsers;
	}

	public String getRoleNameForLike() {
		return roleNameForLike;
	}

	public void setRoleNameForLike(String roleNameForLike) {
		this.roleNameForLike = roleNameForLike;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", roleName=" + roleName + ", roleDesc="
				+ roleDesc + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", creater=" + creater + ", updater=" + updater
				+ ", version=" + version + "]";
	}

}
