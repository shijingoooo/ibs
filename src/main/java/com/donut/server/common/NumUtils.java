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
 * @Title: NumUtils.java 
 * @Package com.donut.server.common 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 李华洞
 * @date 2016年12月15日 下午2:26:08 
 * @version V1.0   
 */
package com.donut.server.common;

import java.text.DecimalFormat;

/**
 * @ClassName: NumUtils
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 李华洞
 * @date 2016年12月15日 下午2:26:08
 * @version 1.0
 */
public class NumUtils
{
    public static final String DOUBLE_COMMON_FROMAT = "0.000";

    public static final String DOUBLE_1_FROMAT = "0.0";

    public static final String DOUBLE_2_FROMAT = "0.00";

    public static String formatDouble(Double str)
    {
        try
        {
            DecimalFormat df1 = new DecimalFormat(NumUtils.DOUBLE_COMMON_FROMAT);
            return df1.format(str);
        }
        catch(Exception e)
        {
            return str.toString();
        }

    }

    public static String formatDouble(Double num, String formatStr)
    {
        try
        {
            DecimalFormat df1 = new DecimalFormat(formatStr);
            return df1.format(num);
        }
        catch(Exception e)
        {
            return num.toString();
        }

    }

    public static String formatDouble2(Double str)
    {
        try
        {
            DecimalFormat df1 = new DecimalFormat(NumUtils.DOUBLE_2_FROMAT);
            return df1.format(str);
        }
        catch(Exception e)
        {
            return str.toString();
        }

    }

    public static Double convertString2Double(String str)
    {
        try
        {
            return Double.valueOf(str);
        }
        catch(Exception e)
        {
            return null;
        }

    }
}
