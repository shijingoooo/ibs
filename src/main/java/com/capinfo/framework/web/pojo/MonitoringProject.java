package com.capinfo.framework.web.pojo;

import java.util.Date;
import java.util.List;

/**
 * 工地
 *
 * @author kevin_zhan
 */
public class MonitoringProject {

    protected MonitoringCompany monitoringCompany;

    protected Integer id;

    // 报建编号
    protected String proCode;


    // 所属局办
    protected String bureauOffice;

    // 工地名称
    protected String proName;

    // 工地类型  1:建筑工地、2:材料堆场、3:混凝土搅拌站、4:拆迁工地、5:道路交通、5:其他
    protected Integer proSiteType;

    // 工程类型  1:住宅、2:商场、3:公共设施
    protected Integer proType;

    // 省级
    protected String province;

    // 市级
    protected String city;

    // 区县
    protected String district;

    // 街道
    protected String street;

    // 经度
    protected String longitude;

    // 纬度
    protected String latitude;

    // 总承包商
    protected String contractors;

    // 负责人
    protected String superintendent;

    // 电话
    protected String telephone;

    // 工地地址
    protected String address;

    // 占地面积
    protected String siteArea;

    // 建筑面积
    protected String buildingArea;

    // 开工时间
    protected Date startDate;

    // 竣工时间
    protected Date endDate;

    // 工期  1:基础工程、2:主体结构、3:内外装饰、4:环境市政
    protected Integer stage;

    // 工地状态
    protected Integer isCompleted;

    // 审核状态 0未提交审核 1待审核 2审核通过 3审核未通过
    protected Integer auditSign;

    protected String remark;

    protected Integer creater;

    protected Integer updater;

    protected Date createTime;

    protected Date updateTime;

    protected Integer version;

    protected Integer platformId;

    protected Integer groupId;

    protected String alarm;

    protected List<MonitoringDevice> monitoringDevices;

    protected List<ProjectUserRele> projectUsers;

    protected String detail;

    protected Integer havDevice;
    protected String alarmType;

    protected String code;

    public MonitoringCompany getMonitoringCompany() {
        return monitoringCompany;
    }

    public void setMonitoringCompany(MonitoringCompany monitoringCompany) {
        this.monitoringCompany = monitoringCompany;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProCode() {
        return proCode;
    }

    public void setProCode(String proCode) {
        this.proCode = proCode;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Integer getProSiteType() {
        return proSiteType;
    }

    public void setProSiteType(Integer proSiteType) {
        this.proSiteType = proSiteType;
    }

    public Integer getProType() {
        return proType;
    }

    public String getBureauOffice() {
        return bureauOffice;
    }

    public void setBureauOffice(String bureauOffice) {
        this.bureauOffice = bureauOffice;
    }

    public void setProType(Integer proType) {
        this.proType = proType;
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

    public String getContractors() {
        return contractors;
    }

    public void setContractors(String contractors) {
        this.contractors = contractors;
    }

    public String getSuperintendent() {
        return superintendent;
    }

    public void setSuperintendent(String superintendent) {
        this.superintendent = superintendent;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSiteArea() {
        return siteArea;
    }

    public void setSiteArea(String siteArea) {
        this.siteArea = siteArea;
    }

    public String getBuildingArea() {
        return buildingArea;
    }

    public void setBuildingArea(String buildingArea) {
        this.buildingArea = buildingArea;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public Integer getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(Integer isCompleted) {
        this.isCompleted = isCompleted;
    }

    public Integer getAuditSign() {
        return auditSign;
    }

    public void setAuditSign(Integer auditSign) {
        this.auditSign = auditSign;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getAlarm() {
        return alarm;
    }

    public void setAlarm(String alarm) {
        this.alarm = alarm;
    }

    public List<MonitoringDevice> getMonitoringDevices() {
        return monitoringDevices;
    }

    public void setMonitoringDevices(List<MonitoringDevice> monitoringDevices) {
        this.monitoringDevices = monitoringDevices;
    }

    public List<ProjectUserRele> getProjectUsers() {
        return projectUsers;
    }

    public void setProjectUsers(List<ProjectUserRele> projectUsers) {
        this.projectUsers = projectUsers;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Integer getHavDevice() {
        return havDevice;
    }

    public void setHavDevice(Integer havDevice) {
        this.havDevice = havDevice;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
