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
 * @Title: ReflectUtil.java 
 * @Package com.donut.server.common 
 * @Description: TODO(用一句话描述该文件做什么) 
 * @author 陈淑晓
 * @date 2017年4月13日 下午3:55:07 
 * @version V1.0   
 */
package com.donut.server.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * @ClassName: ReflectUtil
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author 陈淑晓
 * @date 2017年4月13日 下午3:55:07
 * @version 1.0
 */
public class ReflectUtil
{
    /**
     * 取Bean的属性和值对应关系的MAP
     * 
     * @param bean
     * @return Map
     */
    public static void getFieldValueMap(Object bean,
            Map<String, String> valueMap)
    {
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();

        for (Field field : fields)
        {
            try
            {
                String fieldGetName = parGetName(field.getName());
                String fieldType = field.getType().getSimpleName();
                if (!checkGetMet(methods, fieldGetName))
                {
                    continue;
                }
                Method fieldGetMet = cls
                        .getMethod(fieldGetName, new Class[] {});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[] {});
                String result = null;
                if ("Date".equals(fieldType))
                {
                    result = fmtDate((Date) fieldVal);
                }
                else
                {
                    if (null != fieldVal)
                    {
                        result = String.valueOf(fieldVal);
                    }
                }
                // String fieldKeyName = parKeyName(field.getName());
                if (null != result)
                {
                    valueMap.put(field.getName(), result);
                }

            }
            catch(Exception e)
            {
                continue;
            }
        }
    }

    /**
     * 格式化string为Date
     * 
     * @param datestr
     * @return date
     */
    public static Date parseDate(String datestr)
    {
        if (null == datestr || "".equals(datestr))
        {
            return null;
        }
        try
        {
            String fmtstr = null;
            if (datestr.indexOf(':') > 0)
            {
                fmtstr = "yyyy-MM-dd HH:mm:ss";
            }
            else
            {
                fmtstr = "yyyy-MM-dd";
            }
            SimpleDateFormat sdf = new SimpleDateFormat(fmtstr, Locale.UK);
            return sdf.parse(datestr);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    /**
     * 日期转化为String
     * 
     * @param date
     * @return date string
     */
    public static String fmtDate(Date date)
    {
        if (null == date)
        {
            return null;
        }
        try
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
                    Locale.US);
            return sdf.format(date);
        }
        catch(Exception e)
        {
            return null;
        }
    }

    /**
     * 判断是否存在某属性的 set方法
     * 
     * @param methods
     * @param fieldSetMet
     * @return boolean
     */
    public static boolean checkSetMet(Method[] methods, String fieldSetMet)
    {
        for (Method met : methods)
        {
            if (fieldSetMet.equals(met.getName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否存在某属性的 get方法
     * 
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    public static boolean checkGetMet(Method[] methods, String fieldGetMet)
    {
        for (Method met : methods)
        {
            if (fieldGetMet.equals(met.getName()))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 拼接某属性的 get方法
     * 
     * @param fieldName
     * @return String
     */
    public static String parGetName(String fieldName)
    {
        if (null == fieldName || "".equals(fieldName))
        {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 拼接在某属性的 set方法
     * 
     * @param fieldName
     * @return String
     */
    public static String parSetName(String fieldName)
    {
        if (null == fieldName || "".equals(fieldName))
        {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "set"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 获取存储的键名称（调用parGetName）
     * 
     * @param fieldName
     * @return 去掉开头的get
     */
    public static String parKeyName(String fieldName)
    {
        String fieldGetName = parGetName(fieldName);
        if (fieldGetName != null && fieldGetName.trim() != ""
                && fieldGetName.length() > 3)
        {
            return fieldGetName.substring(3);
        }
        return fieldGetName;
    }

}
