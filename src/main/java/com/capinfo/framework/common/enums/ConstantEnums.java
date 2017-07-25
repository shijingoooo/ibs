package com.capinfo.framework.common.enums;

public enum ConstantEnums {
	
	UPLOAD_TEMP_DIR("/temp"),
	SUCCESS("0"),
	ERROR("1"),
	PUBLISHERROR("3"),
	FAILED("failed"),
	INTERFACE_STATUS_WAIT("1"),
	INTERFACE_STATUS_SUCCESS("2"),
	INTERFACE_STATUS_ERROR("3"),
	INTERFACE_STATUS_GOOUT("4"),
	INTERFACE_STATUS_TIMEOUT("5"),
	INTERFACE_STATUS_INVALID("6"),
	ORDER_CHANNEL_FLAG("1"),
	ORDER_CATE_FLAG("2"),
	WORK_NOT_SYN_AUTO("0"),
	WORK_SYN_AUTO("1"),
	LOGIN_ERROR_STATUS("1"),
	LOGIN_SUCCESS_STATUS("0"),
	ALARM_EMAIL_CONTEN("<p>#userName用户，</p><p>您好，您所关注的#proName(#proCode)</p><p>系统显示：</p><p>从#alarmTime开始，已经连续#keepTime分钟，#alarmCause值超过#keepThreshold</p><p>以上情况请您关注！</p>"),
	ALARM_EMAIL_CONTEN_SEC("<p>#userName用户，</p><p>您好，您所关注的#proName(#proCode)</p><p>系统显示：</p><p>#alarmTime监测到现场噪声值瞬间超过#keepThreshold</p><p>以上情况请您关注！</p>"),
	DEVICE_STOP_EMAIL_CONTEN("<p>您好，您所关注的'#groupName(#groupCode)'设备杆的'#deviceName(#deviceCode)'传感器已下线</p><p>以上情况请您关注！</p>"),
	ALARM_MSG_CONTEN("【智慧工地】站点名称：#proName,设备杆编号：#devGroupCode,告警类型：#alarmCause,告警时间：#alarmTime"),
	PROJECT_EMAIL_CONTEN("您的添加的站点【#proName】审核未通过"),
	DEVICE_STOP_MSG_CONTEN("【智慧工地】您所关注的'#groupName(#groupCode)'设备杆的'#deviceName(#deviceCode)'传感器已下线"),
	;

	private final String key;

	public String getKey() {
		return key;
	}

	private ConstantEnums(String key) {
		this.key = key;
	}

	public boolean equalsValue(String value) {
		ConstantEnums target = null;
		ConstantEnums[] values = values();
		for (ConstantEnums enumInstance : values) {
			if (enumInstance.getKey().equals(value)) {
				target = enumInstance;
			}
		}
		return this.equals(target);
	}
	
	public String getKeyDescription() {
		if (this != null) {
			if (this.equals(UPLOAD_TEMP_DIR)) {
				return "上传临时目录";
			} else if (this.equals(SUCCESS)) {
				return "成功标识";
			} else if (this.equals(ERROR)) {
				return "失败标识";
			} else if (this.equals(PUBLISHERROR)) {
				return "发布错误标识";
			} else if (this.equals(FAILED)) {
				return "错误";
			} else if (this.equals(INTERFACE_STATUS_WAIT)) {
				return "待同步";
			} else if (this.equals(INTERFACE_STATUS_SUCCESS)) {
				return "同步成功";
			}else if (this.equals(INTERFACE_STATUS_ERROR)) {
				return "同步失败";
			} else if (this.equals(INTERFACE_STATUS_GOOUT)) {
				return "同步过时";
			} else if (this.equals(INTERFACE_STATUS_TIMEOUT)) {
				return "同步超时";
			}else if (this.equals(INTERFACE_STATUS_INVALID)) {
					return "无效的同步";
			} else if (this.equals(ORDER_CHANNEL_FLAG)) {
				return "栏目排序";
			} else if (this.equals(ORDER_CATE_FLAG)) {
				return "分类法排序";
			} else if (this.equals(WORK_NOT_SYN_AUTO)) {
				return "停止自动同步";
			} else if (this.equals(WORK_SYN_AUTO)) {
				return "开启自动同步";
			} else if (this.equals(LOGIN_ERROR_STATUS)) {
				return "登录失败";
			} else if (this.equals(LOGIN_SUCCESS_STATUS)) {
				return "登录成功";
			} else if (this.equals(ALARM_EMAIL_CONTEN)) {
				return "告警邮件内容";
			} else if (this.equals(ALARM_EMAIL_CONTEN_SEC)) {
				return "瞬时告警邮件内容";
			} else if (this.equals(DEVICE_STOP_EMAIL_CONTEN)) {
				return "设备下线邮件内容";
			} else if (this.equals(ALARM_MSG_CONTEN)) {
				return "告警短信内容";
			} else if (this.equals(PROJECT_EMAIL_CONTEN)) {
				return "工地审核邮件内容";
			} else {
			}
		}
		return "";
	}

	public String getKeyDescriptionByValue(String status) {
		ConstantEnums enumInstance = ConstantEnums.valueOf(status);
		return enumInstance.getKeyDescription();
	}

	@Override
	public String toString() {
		return getKey();
	}

	public static final ConstantEnums valueOfByKey(String key) {
		ConstantEnums[] values = values();
		for (ConstantEnums enumInstance : values) {
			if (enumInstance.equalsValue(key)) {
				return enumInstance;
			}
		}
		//
		return null;
	}
}
