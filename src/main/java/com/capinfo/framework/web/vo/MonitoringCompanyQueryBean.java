package com.capinfo.framework.web.vo;

public class MonitoringCompanyQueryBean {

	// 厂商名称
	protected String companyName;
	
	protected String companyNameForLike;

	// 厂商编号
	protected String companyCode;

	// 市级
	protected String city;

	protected String district;

	protected String street;

	// 经度
	protected String longitude;

	// 纬度
	protected String latitude;

    protected String companyId;



	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyNameForLike() {
		return companyNameForLike;
	}

	public void setCompanyNameForLike(String companyNameForLike) {
		this.companyNameForLike = companyNameForLike;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }
}
