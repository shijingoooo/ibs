package com.capinfo.framework.web.pojo;

import java.util.Date;
import java.util.List;

/**
 * 传感器
 * @author kevin_zhan
 *
 */
public class MonitoringDevice {
	
	protected MonitoringProject monitoringProject;
	
	protected MonitoringCompany monitoringCompany;
	
	protected MonitoringDevicePole monitoringDevicePole;
	//设备所属设备组
	protected List<String> belongGroups;

	protected Integer id;
	
	// 传感器编号
	protected String devCode;
	
	// 报建号
	protected String proCode;
	
	// 厂商编号
	protected String vendorCode;
	
	// 传感器名称
	protected String devName;
	
	// 传感器类型 1:扬尘 2:噪声 3:视频
	protected Integer devType;
	
	// 经度
	protected String longitude;
	
	// 纬度
	protected String latitude;
	
	// IP地址
	protected String ipAddr;
	
	// MAC地址
	protected String macAddr;
	
	// 端口
	protected String devPort;
	
	// 设备版本
	protected String devVersion;
	
	// 设备状态 接口传来的是boolean类型
	protected String devStatus;
	
	// 视频服务器地址
	protected String videoUrl;
	
	// 云端控制字符串
	protected String paramstr;

	//视频设备序列号
	protected String serial;
	
	protected Date startTime;
	
	protected Date endTime;
	
	protected Date installTime;
	
	protected Integer creater;
	
	protected Integer updater;
	
	protected Date createTime;
	
	protected Date updateTime;
	
	protected Integer version;

    protected String platformId;
	//转发平台名称
    protected String platformName;

	protected String platformMN;
	
	protected String phoneCard;

	protected String forwardType;

	protected Integer forwardFrequency;
	//维护次数
	protected Integer maintainCount;

    /**
     * chenshuxiao
     **/
    protected String cameraId;

    protected String videoUser;

    protected String password;

    protected String cameraIndexCode;

    /**
     * chenshuxiao
     **/

	public MonitoringProject getMonitoringProject() {
		return monitoringProject;
	}

	public void setMonitoringProject(MonitoringProject monitoringProject) {
		this.monitoringProject = monitoringProject;
	}

	public MonitoringCompany getMonitoringCompany() {
		return monitoringCompany;
	}

	public void setMonitoringCompany(MonitoringCompany monitoringCompany) {
		this.monitoringCompany = monitoringCompany;
	}

	public MonitoringDevicePole getMonitoringDevicePole() {
		return monitoringDevicePole;
	}

	public void setMonitoringDevicePole(MonitoringDevicePole monitoringDevicePole) {
		this.monitoringDevicePole = monitoringDevicePole;
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

	public String getProCode() {
		return proCode;
	}

	public void setProCode(String proCode) {
		this.proCode = proCode;
	}

	public String getVendorCode() {
		return vendorCode;
	}

	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public Integer getDevType() {
		return devType;
	}

	public void setDevType(Integer devType) {
		this.devType = devType;
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

	public String getParamstr() {
		return paramstr;
	}

	public void setParamstr(String paramstr) {
		this.paramstr = paramstr;
	}

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getInstallTime() {
		return installTime;
	}

	public void setInstallTime(Date installTime) {
		this.installTime = installTime;
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

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

    public String getPlatformId() {
        return platformId;
	}

    public void setPlatformId(String platformId) {
        this.platformId = platformId;
	}

	public String getPlatformMN() {
		return platformMN;
	}

	public void setPlatformMN(String platformMN) {
		this.platformMN = platformMN;
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

	@Override
	public String toString() {
		return "MonitoringDevice [id=" + id + ", devCode=" + devCode
				+ ", proCode=" + proCode + ", vendorCode=" + vendorCode
				+ ", devName=" + devName + ", longitude=" + longitude
				+ ", latitude=" + latitude + ", ipAddr=" + ipAddr
				+ ", macAddr=" + macAddr + ", devPort=" + devPort
				+ ", devVersion=" + devVersion + ", devStatus=" + devStatus
				+ ", videoUrl=" + videoUrl + ", startTime=" + startTime
				+ ", endTime=" + endTime + ", installTime=" + installTime
				+ ", creater=" + creater + ", updater=" + updater
				+ ", createTime=" + createTime + ", updateTime=" + updateTime
				+ ", version=" + version + "，platformId="+platformId+", platformMN="+platformMN+"]";
	}

	public List<String> getBelongGroups() {
		return belongGroups;
	}

	public void setBelongGroups(List<String> belongGroups) {
		this.belongGroups = belongGroups;
	}

	public String getPhoneCard() {
		return phoneCard;
	}

	public void setPhoneCard(String phoneCard) {
		this.phoneCard = phoneCard;
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

	public String getPlatformName() {
		return platformName;
	}

	public void setPlatformName(String platformName) {
		this.platformName = platformName;
	}

	public Integer getMaintainCount() {
		return maintainCount;
	}

	public void setMaintainCount(Integer maintainCount) {
		this.maintainCount = maintainCount;
	}
}
