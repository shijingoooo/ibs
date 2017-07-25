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

import java.util.List;

import com.donut.server.common.ParentModel;

/**
 * @ClassName: UserMgModel
 * @Description: 用户管理列表的model
 * @author 李华洞
 * @date 2016年6月13日 下午2:20:29
 * @version 1.0
 */
public class UserMgModel extends ParentModel
{
    private static final long serialVersionUID = 1L;

    private List<UserInfoModel> userInfoList;

    private String searchNickName;

    private String searchRegPhone;

    private String searchType;

    private String searchMemberStatus;

    private String searchStatus;

    private String searchRegPlaform;

    private Integer searchOrderBy;

    private Integer searchOs;

    /**
     * @return searchOs
     */
    public Integer getSearchOs()
    {
        return searchOs;
    }

    /**
     * @param searchOs
     *            the searchOs to set
     */
    public void setSearchOs(Integer searchOs)
    {
        this.searchOs = searchOs;
    }

    /**
     * @return serialversionuid
     */
    public static long getSerialversionuid()
    {
        return serialVersionUID;
    }

    /**
     * @return searchOrderBy
     */
    public Integer getSearchOrderBy()
    {
        return searchOrderBy;
    }

    /**
     * @param searchOrderBy
     *            the searchOrderBy to set
     */
    public void setSearchOrderBy(Integer searchOrderBy)
    {
        this.searchOrderBy = searchOrderBy;
    }

    /**
     * @return userInfoList
     */
    public List<UserInfoModel> getUserInfoList()
    {
        return userInfoList;
    }

    /**
     * @param userInfoList
     *            the userInfoList to set
     */
    public void setUserInfoList(List<UserInfoModel> userInfoList)
    {
        this.userInfoList = userInfoList;
    }

    /**
     * @return searchNickName
     */
    public String getSearchNickName()
    {
        return searchNickName;
    }

    /**
     * @param searchNickName
     *            the searchNickName to set
     */
    public void setSearchNickName(String searchNickName)
    {
        this.searchNickName = searchNickName;
    }

    /**
     * @return searchRegPhone
     */
    public String getSearchRegPhone()
    {
        return searchRegPhone;
    }

    /**
     * @param searchRegPhone
     *            the searchRegPhone to set
     */
    public void setSearchRegPhone(String searchRegPhone)
    {
        this.searchRegPhone = searchRegPhone;
    }

    /**
     * @return searchType
     */
    public String getSearchType()
    {
        return searchType;
    }

    /**
     * @param searchType
     *            the searchType to set
     */
    public void setSearchType(String searchType)
    {
        this.searchType = searchType;
    }

    /**
     * @return searchMemberStatus
     */
    public String getSearchMemberStatus()
    {
        return searchMemberStatus;
    }

    /**
     * @param searchMemberStatus
     *            the searchMemberStatus to set
     */
    public void setSearchMemberStatus(String searchMemberStatus)
    {
        this.searchMemberStatus = searchMemberStatus;
    }

    /**
     * @return searchStatus
     */
    public String getSearchStatus()
    {
        return searchStatus;
    }

    /**
     * @param searchStatus
     *            the searchStatus to set
     */
    public void setSearchStatus(String searchStatus)
    {
        this.searchStatus = searchStatus;
    }

    /**
     * @return searchRegPlaform
     */
    public String getSearchRegPlaform()
    {
        return searchRegPlaform;
    }

    /**
     * @param searchRegPlaform
     *            the searchRegPlaform to set
     */
    public void setSearchRegPlaform(String searchRegPlaform)
    {
        this.searchRegPlaform = searchRegPlaform;
    }

}
