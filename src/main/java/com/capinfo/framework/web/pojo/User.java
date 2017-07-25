package com.capinfo.framework.web.pojo;

import java.util.Date;
import java.util.List;

public class User {
	
	protected Integer id;
	
	protected String userName;
	
	protected String userPassword;
	
	protected String randomNum;
	
	// 1：市级建委用户、2：区县住建委用户、3：工地管理员、4：管理员
	protected Integer userType;
	
	protected String companyName;
	
	// 省级
	protected String province;
	
	// 市级
	protected String city;
	
	// 区县
	protected String district;
	
	// 电话
	protected String telephone;
	
	// 邮箱
	protected String email;
	
	protected Date createTime;
	
	protected Date updateTime;
	
	protected Integer creater;
	
	protected Integer updater;
	
	protected Integer version;
	
	protected List<UserRoleRele> userRoles;
	
	protected List<ProjectUserRele> projectUsers;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getRandomNum() {
		return randomNum;
	}

	public void setRandomNum(String randomNum) {
		this.randomNum = randomNum;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<UserRoleRele> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<UserRoleRele> userRoles) {
		this.userRoles = userRoles;
	}

	public List<ProjectUserRele> getProjectUsers() {
		return projectUsers;
	}

	public void setProjectUsers(List<ProjectUserRele> projectUsers) {
		this.projectUsers = projectUsers;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userName=" + userName + ", userPassword="
				+ userPassword + ", randomNum=" + randomNum + ", userType="
				+ userType + ", createTime=" + createTime + ", updateTime="
				+ updateTime + ", creater=" + creater + ", updater=" + updater
				+ ", version=" + version + "]";
	}

}
