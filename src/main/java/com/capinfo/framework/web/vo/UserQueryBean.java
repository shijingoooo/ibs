package com.capinfo.framework.web.vo;


public class UserQueryBean {

	protected String userName;
	
	protected String userNameForLike;
	
	protected String userPassword;
	
	protected String version;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserNameForLike() {
		return userNameForLike;
	}

	public void setUserNameForLike(String userNameForLike) {
		this.userNameForLike = userNameForLike;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}
