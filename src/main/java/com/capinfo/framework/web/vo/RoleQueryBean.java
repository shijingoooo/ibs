package com.capinfo.framework.web.vo;


public class RoleQueryBean {

	protected String roleName;
	
	protected String roleDesc;
	
	protected String version;

	protected String roleNameForLike;

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

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getRoleNameForLike() {
		return roleNameForLike;
	}

	public void setRoleNameForLike(String roleNameForLike) {
		this.roleNameForLike = roleNameForLike;
	}
}
