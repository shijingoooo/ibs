package com.capinfo.framework.web.pojo;

import java.util.Date;
import java.util.List;

public class Menu implements Comparable<Menu> {
	
	protected Menu pMenu;
	
	protected Integer id;
	
	protected String menuName;
	
	protected String menuCode;
	
	protected Integer menuType;
	
	protected String menuUrl;
	
	protected Integer menuLevel;
	
	protected Integer menuLast;
	
	protected Date createTime;
	
	protected Date updateTime;
	
	protected Integer creater;
	
	protected Integer updater;
	
	protected Integer version;
	
	protected List<RoleMenuRele> roleMenus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Menu getpMenu() {
		return pMenu;
	}

	public void setpMenu(Menu pMenu) {
		this.pMenu = pMenu;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String getMenuUrl() {
		return menuUrl;
	}

	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public Integer getMenuLast() {
		return menuLast;
	}

	public void setMenuLast(Integer menuLast) {
		this.menuLast = menuLast;
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

	@Override
	public String toString() {
		return "Menu [pMenu=" + pMenu + ", id=" + id + ", menuName=" + menuName
				+ ", menuCode=" + menuCode + ", menuType=" + menuType
				+ ", menuUrl=" + menuUrl + ", menuLevel=" + menuLevel
				+ ", menuLast=" + menuLast + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + ", creater=" + creater
				+ ", updater=" + updater + ", version=" + version
				+ ", getId()=" + getId() + ", getpMenu()=" + getpMenu()
				+ ", getMenuName()=" + getMenuName() + ", getMenuCode()="
				+ getMenuCode() + ", getMenuType()=" + getMenuType()
				+ ", getMenuUrl()=" + getMenuUrl() + ", getMenuLevel()="
				+ getMenuLevel() + ", getMenuLast()=" + getMenuLast()
				+ ", getCreateTime()=" + getCreateTime() + ", getUpdateTime()="
				+ getUpdateTime() + ", getCreater()=" + getCreater()
				+ ", getUpdater()=" + getUpdater() + ", getVersion()="
				+ getVersion() + ", getRoleMenus()=" + getRoleMenus()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

	@Override
	public int compareTo(Menu o) {
		return this.getId().compareTo(o.getId());
	}

}
