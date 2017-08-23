package com.capinfo.framework.web.vo;

import com.capinfo.framework.web.pojo.MonitoringCompany;
import org.opensaml.xmlsec.keyinfo.impl.StaticKeyInfoGenerator;

import java.util.Date;

public class MonitoringDeviceQueryBean {
	//用户ID



	protected Integer userId;
	//用户类型
	protected Integer userType;
	//设备ID
	protected Integer id;
	//设备编码
	protected String devCode;
	//设备编码查询
	protected String devCodeForLike;
	//设备组名称查询
	protected String devGroupNameForLike;
	//设备名称
	protected String devName;
	//设备名称查询
	protected String devNameForLike;
	//站点查询
	protected String devProNameForLike;
	//设备类型
	protected Integer devType;
	//手机卡号
	protected String phoneCard;
	//厂商ID
	protected String vendorId;
	//厂商名称
	protected String vendorName;
	//厂商编号
	protected String vendorCode;
	//转发平台id
	protected String companyIds;
	//转发平台name
	protected String companyNames;
	//转发MN号
	protected String platformMN;
	//转发因子
	protected String forwardType;
	//转发频率
	protected Integer forwardFrequency;
	//经度
	protected String longitude;
	//纬度
	protected String latitude;
	//视频ID
	protected String cameraId;
	//视频名称
	protected String videoUser;
	//视频密码
	protected String password;
	//视频编码
	protected String cameraIndexCode;
	//IP地址
	protected String ipAddr;
	//MAC地址
	protected String macAddr;
	//端口
	protected String devPort;
	//设备版本
	protected String devVersion;
	//设备状态:在线or下线
	protected String devStatus;
	//视频地址
	protected String videoUrl;
	//修改人
	protected Integer updater;
	//修改时间
	protected Date updateTime;
	//创建人
	protected Integer creater;
	//创建时间
	protected Date createTime;
	//所属厂商
	protected MonitoringCompany monitoringCompany;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevCode() {
		return devCode;
	}

	public void setDevCode(String devCode) {
		this.devCode = devCode;
	}

	public String getDevCodeForLike() {
		return devCodeForLike;
	}

	public void setDevCodeForLike(String devCodeForLike) {
		this.devCodeForLike = devCodeForLike;
	}

	public String getDevGroupNameForLike() {
		return devGroupNameForLike;
	}

	public void setDevGroupNameForLike(String devGroupNameForLike) {
		this.devGroupNameForLike = devGroupNameForLike;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getDevNameForLike() {
		return devNameForLike;
	}

	public void setDevNameForLike(String devNameForLike) {
		this.devNameForLike = devNameForLike;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public String getDevPort() {
		return devPort;
	}

	public void setDevPort(String devPort) {
		this.devPort = devPort;
	}

	public String getDevVersion() {
		return devVersion;
	}

	public void setDevVersion(String devVersion) {
		this.devVersion = devVersion;
	}

	public String getDevStatus() {
		return devStatus;
	}

	public void setDevStatus(String devStatus) {
		this.devStatus = devStatus;
	}

	public String getVideoUrl() {
		return videoUrl;
	}

	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}

	public Integer getDevType() {
		return devType;
	}

	public void setDevType(Integer devType) {
		this.devType = devType;
	}

	public String getPhoneCard() {
		return phoneCard;
	}

	public void setPhoneCard(String phoneCard) {
		this.phoneCard = phoneCard;
	}

	public String getCompanyIds() {
		return companyIds;
	}

	public void setCompanyIds(String companyIds) {
		this.companyIds = companyIds;
	}

	public String getCompanyNames() {
		return companyNames;
	}

	public void setCompanyNames(String companyNames) {
		this.companyNames = companyNames;
	}

	public String getPlatformMN() {
		return platformMN;
	}

	public void setPlatformMN(String platformMN) {
		this.platformMN = platformMN;
	}

	public String getForwardType() {
		return forwardType;
	}

	public void setForwardType(String forwardType) {
		this.forwardType = forwardType;
	}

	public Integer getForwardFrequency() {
		return forwardFrequency;
	}

	public void setForwardFrequency(Integer forwardFrequency) {
		this.forwardFrequency = forwardFrequency;
	}

	public String getCameraId() {
		return cameraId;
	}

	public void setCameraId(String cameraId) {
		this.cameraId = cameraId;
	}

	public String getVideoUser() {
		return videoUser;
	}

	public void setVideoUser(String videoUser) {
		this.videoUser = videoUser;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCameraIndexCode() {
		return cameraIndexCode;
	}

	public void setCameraIndexCode(String cameraIndexCode) {
		this.cameraIndexCode = cameraIndexCode;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public Integer getUpdater() {
		return updater;
	}

	public void setUpdater(Integer updater) {
		this.updater = updater;
	}

	public Integer getCreater() {
		return creater;
	}

	public void setCreater(Integer creater) {
		this.creater = creater;
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

	public MonitoringCompany getMonitoringCompany() {
		return monitoringCompany;
	}

	public void setMonitoringCompany(MonitoringCompany monitoringCompany) {
		this.monitoringCompany = monitoringCompany;
	}

	public String getDevProNameForLike() {
		return devProNameForLike;
	}

	public void setDevProNameForLike(String devProNameForLike) {
		this.devProNameForLike = devProNameForLike;
	}
}
