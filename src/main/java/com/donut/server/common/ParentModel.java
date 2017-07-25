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
 * @Title: ParentModel.java 
 * @Package com.sportedu.server.admin.model 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 王治威 
 * @date 2015年8月26日 下午4:50:38 
 * @version V1.0   
 */
package com.donut.server.common;

import java.io.Serializable;

/**
 * @ClassName: ParentModel
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 王治威
 * @date 2015年8月26日 下午4:50:38
 * @version 1.0
 */
public class ParentModel implements Serializable
{
    /**
     * @Fields
     */
    private static final long serialVersionUID = 1L;

    /** UUID */
    private String uuid;

    /** 标题 */
    private String pageTitle;

    /** 分页代码 */
    private String pageDisplay;

    /** 当前页 */
    private long pageIndex;

    /** 每页显示件数 */
    private int recordPerPages;

    /** 页码 */
    private long startIndex;

    /** 事件flg */
    private String flg;

    /** 页面初期化 */
    private String initflg;

    /** 错误标示 */
    private String errflg;

    /** 返回信息 */
    private String msg;
    
    /** 添加编辑返回等使用的pageIndex*/
    private long nowPageIndex;

    /**
     * 标题的设定
     * 
     * @param title
     *            标题
     */
    public void setPageTitle(String pageTitle)
    {
        this.pageTitle = pageTitle;
    }

    /**
     * 标题的取得
     * 
     * @return 标题
     */
    public String getPageTitle()
    {
        return pageTitle;
    }

    /**
     * 分页代码的设定
     * 
     * @param pageDisplay
     *            分页代码
     */
    public void setPageDisplay(String pageDisplay)
    {
        this.pageDisplay = pageDisplay;
    }

    /**
     * 分页代码的取得
     * 
     * @return 分页代码
     */
    public String getPageDisplay()
    {
        return pageDisplay;
    }

    /**
     * 当前页的设定
     * 
     * @param pageIndex
     *            当前页
     */
    public void setPageIndex(long pageIndex)
    {
        this.pageIndex = pageIndex;
    }

    /**
     * 当前页的取得
     * 
     * @return 当前页
     */
    public long getPageIndex()
    {
        return pageIndex;
    }

    /**
     * 每页显示件数的设定
     * 
     * @param recordPerPages
     *            每页显示件数
     */
    public void setRecordPerPages(int recordPerPages)
    {
        this.recordPerPages = recordPerPages;
    }

    /**
     * 每页显示件数的取得
     * 
     * @return 每页显示件数
     */
    public int getRecordPerPages()
    {
        return recordPerPages;
    }

    /**
     * 页码的设定
     * 
     * @param startIndex
     *            页码
     */
    public void setStartIndex(long startIndex)
    {
        this.startIndex = startIndex;
    }

    /**
     * 页码的取得
     * 
     * @return 页码
     */
    public long getStartIndex()
    {
        return startIndex;
    }

    /**
     * 事件flg的设定
     * 
     * @param flg
     *            事件flg
     */
    public void setFlg(String flg)
    {
        this.flg = flg;
    }

    /**
     * 事件flg的取得
     * 
     * @return 事件flg
     */
    public String getFlg()
    {
        return flg;
    }

    /**
     * 页面初期化的设定
     * 
     * @param initflg
     *            页面初期化
     */
    public void setInitflg(String initflg)
    {
        this.initflg = initflg;
    }

    /**
     * 页面初期化的取得
     * 
     * @return 页面初期化
     */
    public String getInitflg()
    {
        return initflg;
    }

    /**
     * 错误标示的设定
     * 
     * @param errflg
     *            错误标示
     */
    public void setErrflg(String errflg)
    {
        this.errflg = errflg;
    }

    /**
     * 错误标示的取得
     * 
     * @return 错误标示
     */
    public String getErrflg()
    {
        return errflg;
    }

    public String getMsg()
    {
        return msg;
    }

    public void setMsg(String msg)
    {
        this.msg = msg;
    }

    public String getUuid()
    {
        return uuid;
    }

    public void setUuid(String uuid)
    {
        this.uuid = uuid;
    }

    public long getNowPageIndex()
    {
        return nowPageIndex;
    }

    public void setNowPageIndex(long nowPageIndex)
    {
        this.nowPageIndex = nowPageIndex;
    }
}
