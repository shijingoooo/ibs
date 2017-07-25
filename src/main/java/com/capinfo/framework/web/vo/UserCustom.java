package com.capinfo.framework.web.vo;

import com.capinfo.framework.web.pojo.User;

public class UserCustom extends User {

	protected String nickName;

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

}
