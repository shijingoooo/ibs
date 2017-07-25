/**   
 *  Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 * @Title: UserMgModel.java 
 * @Package com.donut.server.admin.model 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 李华洞  
 * @date 2016年6月13日 下午2:20:29 
 * @version V1.0   
 */
package com.donut.server.model;

import java.util.Date;

/**
 * @ClassName: UserMgModel
 * @Description: 用户管理列表的model
 * @author 李华洞
 * @date 2016年6月13日 下午2:20:29
 * @version 1.0
 */
public class UserInfoModel
{
    private String uuid;

    private String nickName;

    private String headPic;

    private Integer regPlaform;

    private Integer os;

    private Integer type;

    private String regPhone;

    private String mail;

    private Integer memberStatus;

    private Date expireTime;

    private Float consumptionAmount;

    private Integer status;

    /**
     * @return os
     */
    public Integer getOs()
    {
        return os;
    }

    /**
     * @param os
     *            the os to set
     */
    public void setOs(Integer os)
    {
        this.os = os;
    }

    /**
     * @return uuid
     */
    public String getUuid()
    {
        return uuid;
    }

    /**
     * @param uuid
     *            the uuid to set
     */
    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    /**
     * @return nickName
     */
    public String getNickName()
    {
        return nickName;
    }

    /**
     * @param nickName
     *            the nickName to set
     */
    public void setNickName(String nickName)
    {
        this.nickName = nickName;
    }

    /**
     * @return headPic
     */
    public String getHeadPic()
    {
        return headPic;
    }

    /**
     * @param headPic
     *            the headPic to set
     */
    public void setHeadPic(String headPic)
    {
        this.headPic = headPic;
    }

    /**
     * @return regPlaform
     */
    public Integer getRegPlaform()
    {
        return regPlaform;
    }

    /**
     * @param regPlaform
     *            the regPlaform to set
     */
    public void setRegPlaform(Integer regPlaform)
    {
        this.regPlaform = regPlaform;
    }

    /**
     * @return type
     */
    public Integer getType()
    {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(Integer type)
    {
        this.type = type;
    }

    /**
     * @return regPhone
     */
    public String getRegPhone()
    {
        return regPhone;
    }

    /**
     * @param regPhone
     *            the regPhone to set
     */
    public void setRegPhone(String regPhone)
    {
        this.regPhone = regPhone;
    }

    /**
     * @return mail
     */
    public String getMail()
    {
        return mail;
    }

    /**
     * @param mail
     *            the mail to set
     */
    public void setMail(String mail)
    {
        this.mail = mail;
    }

    /**
     * @return memberStatus
     */
    public Integer getMemberStatus()
    {
        return memberStatus;
    }

    /**
     * @param memberStatus
     *            the memberStatus to set
     */
    public void setMemberStatus(Integer memberStatus)
    {
        this.memberStatus = memberStatus;
    }

    /**
     * @return expireTime
     */
    public Date getExpireTime()
    {
        return expireTime;
    }

    /**
     * @param expireTime
     *            the expireTime to set
     */
    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
    }

    /**
     * @return consumptionAmount
     */
    public Float getConsumptionAmount()
    {
        return consumptionAmount;
    }

    /**
     * @param consumptionAmount
     *            the consumptionAmount to set
     */
    public void setConsumptionAmount(Float consumptionAmount)
    {
        this.consumptionAmount = consumptionAmount;
    }

    /**
     * @return status
     */
    public Integer getStatus()
    {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(Integer status)
    {
        this.status = status;
    }

}
