package com.capinfo.framework.web.pojo;

import java.util.Date;
import java.util.List;

/**
 * 厂商
 * @author kevin_zhan
 *
 */
public class MonitoringCompany {
	
	protected Integer id;
	
	// 厂商名称
	protected String companyName;
	
	// 厂商编号
	protected String companyCode;
	
	// 省级
	protected String province;
		
	// 市级
	protected String city;
	
	protected String district;
	
	protected String street;
	
	// 经度
	protected String longitude;
	
	// 纬度
	protected String latitude;
	
	// 联系人
	protected String contacts;
	
	// 电话
	protected String telephone;
	
	protected Date createTime;
	
	protected Date updateTime;
	
	protected Integer creater;
	
	protected Integer updater;
	
	protected Integer version;

	protected Integer type;

	protected Integer protocol;
	
	protected List<MonitoringProject> monitoringProjects;
	
	protected List<MonitoringDevice> monitoringDevices;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
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

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getProtocol() {
		return protocol;
	}

	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}

	public List<MonitoringProject> getMonitoringProjects() {
		return monitoringProjects;
	}

	public void setMonitoringProjects(List<MonitoringProject> monitoringProjects) {
		this.monitoringProjects = monitoringProjects;
	}

	public List<MonitoringDevice> getMonitoringDevices() {
		return monitoringDevices;
	}

	public void setMonitoringDevices(List<MonitoringDevice> monitoringDevices) {
		this.monitoringDevices = monitoringDevices;
	}

	@Override
	public String toString() {
		return "Company [id=" + id + ", companyName=" + companyName
				+ ", companyCode=" + companyCode + ", city=" + city
				+ ", district=" + district + ", street=" + street
				+ ", longitude=" + longitude + ", latitude=" + latitude
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", creater=" + creater + ", updater=" + updater +", type=" + type
				+ ", protocol="+ protocol +", version=" + version + "]";
	}

}
