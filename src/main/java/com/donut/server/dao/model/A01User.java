package com.donut.server.dao.model;

import java.util.Date;

public class A01User {
    private String uuid;

    private String regPhone;

    private String passwd;

    private Integer regPlaform;

    private Integer os;

    private String thirdTag;

    private Integer type;

    private String headPic;

    private String nickName;

    private String mail;

    private Integer sex;

    private String firstAreaCode;

    private String city;

    private Date birth;

    private Integer star;

    private String job;

    private Integer memberStatus;

    private Float balance;

    private Date expireTime;

    private Integer hasReceivedReward;

    private Integer status;

    private String token;

    private Date createTime;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    public String getRegPhone() {
        return regPhone;
    }

    public void setRegPhone(String regPhone) {
        this.regPhone = regPhone == null ? null : regPhone.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public Integer getRegPlaform() {
        return regPlaform;
    }

    public void setRegPlaform(Integer regPlaform) {
        this.regPlaform = regPlaform;
    }

    public Integer getOs() {
        return os;
    }

    public void setOs(Integer os) {
        this.os = os;
    }

    public String getThirdTag() {
        return thirdTag;
    }

    public void setThirdTag(String thirdTag) {
        this.thirdTag = thirdTag == null ? null : thirdTag.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail == null ? null : mail.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getFirstAreaCode() {
        return firstAreaCode;
    }

    public void setFirstAreaCode(String firstAreaCode) {
        this.firstAreaCode = firstAreaCode == null ? null : firstAreaCode.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job == null ? null : job.trim();
    }

    public Integer getMemberStatus() {
        return memberStatus;
    }

    public void setMemberStatus(Integer memberStatus) {
        this.memberStatus = memberStatus;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getHasReceivedReward() {
        return hasReceivedReward;
    }

    public void setHasReceivedReward(Integer hasReceivedReward) {
        this.hasReceivedReward = hasReceivedReward;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}