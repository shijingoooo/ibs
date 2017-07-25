package com.capinfo.framework.web.vo;

public class RoleMenuReleQueryBean {

	protected Integer roleId;
	
	protected Integer menuId;
	
	protected String menuUrl;
	
	protected Integer[] roleIdForIn;
	
	protected Integer[] menuIdForIn;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}

	public Integer getMenuId() {
		return menuId;
	}

	public void setMenuId(Integer menuId) {
		this.menuId = menuId;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer[] getRoleIdForIn() {
		return roleIdForIn;
	}

	public void setRoleIdForIn(Integer[] roleIdForIn) {
		this.roleIdForIn = roleIdForIn;
	}

	public Integer[] getMenuIdForIn() {
		return menuIdForIn;
	}

	public void setMenuIdForIn(Integer[] menuIdForIn) {
		this.menuIdForIn = menuIdForIn;
	}
	
}
