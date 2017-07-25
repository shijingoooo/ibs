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
 * @Title: LableValueBean.java 
 * @Package com.sportedu.server.common 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 王治威 
 * @date 2015年9月2日 下午3:17:22 
 * @version V1.0   
 */
package com.donut.server.common;

import java.io.Serializable;

/**
 * @ClassName: LableValueBean
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 王治威
 * @date 2015年9月2日 下午3:17:22
 * @version 1.0
 */
public class LableValueBean implements Serializable
{
    /**
     * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
     */
    private static final long serialVersionUID = 1L;

    /**
     * The property which supplies the option label visible to the end user.
     */
    private String label = null;

    /**
     * The property which supplies the value returned to the server.
     */
    private String value = null;

    public String getLabel()
    {
        return this.label;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public String getValue()
    {
        return this.value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }
}
