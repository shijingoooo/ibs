package com.donut.server.common;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

public class JsonUtils
{
    /**
     * 将json字符串转换成对象
     * 
     * @param Class
     *            <E>
     * @param jsonStr
     * @return E
     */
    public static <E> E convertToObject(Class<E> c, String jsonStr)
    {
        E e = null;
        try
        {
            e = c.newInstance();
            JSONObject jsonObj = new JSONObject(jsonStr);
            Map<String, Method> methodMap = getMethod(c);
            Iterator<?> it = jsonObj.keys();
            while (it.hasNext())
            {
                String key = (String) it.next();
                if (methodMap.containsKey(key))
                {
                    Method method = methodMap.get(key);
                    Class<?> paramType = method.getParameterTypes()[0];
                    if (paramType.isAssignableFrom(int.class)
                            || paramType.isAssignableFrom(Integer.class))
                    {
                        method.invoke(e, jsonObj.getInt(key));
                        continue;
                    }
                    else if (paramType.isAssignableFrom(double.class)
                            || paramType.isAssignableFrom(Double.class))
                    {
                        method.invoke(e, jsonObj.getDouble(key));
                        continue;
                    }
                    else if (paramType.isAssignableFrom(long.class)
                            || paramType.isAssignableFrom(Long.class))
                    {
                        method.invoke(e, jsonObj.getLong(key));
                        continue;
                    }
                    else if (paramType.isAssignableFrom(float.class)
                            || paramType.isAssignableFrom(Float.class))
                    {                       
                        method.invoke(e, (Float) jsonObj.get(key));
                        continue;
                    }
                    else if (paramType.isAssignableFrom(boolean.class)
                            || paramType.isAssignableFrom(Boolean.class))
                    {
                        method.invoke(e, jsonObj.getBoolean(key));
                        continue;
                    }
                    else if (paramType.isAssignableFrom(String.class))
                    {
                        method.invoke(e, jsonObj.getString(key));
                        continue;
                    }
                }
            }
        }
        catch(Exception e1)
        {
            e1.printStackTrace();
        }
        return e;
    }

    /**
     * 获取DTO所有的方法名
     * 
     * @param c
     * @return Map<String,Method>
     */
    private static Map<String, Method> getMethod(Class<?> c)
    {
        Map<String, Method> map = new HashMap<String, Method>();
        Method[] methods = c.getDeclaredMethods();
        for (int i = 0; i < methods.length; i++)
        {
            Method method = methods[i];
            if ("set".equals(method.getName().substring(0, 3)))
            {
                String methodName = method.getName().substring(3);
                // map.put(methodName.replaceFirst(methodName.charAt(0),
                // ((char)(methodName.charAt(0)+32))), method);
                map.put(String.valueOf((char) (methodName.charAt(0) + 32))
                        + methodName.substring(1), method);
            }
        }
        return map;
    }

    /**
     * 将json字符串转换成数组
     * 
     * @param Class
     *            <E>
     * @param jsonStr
     * @return List<E>
     */
    public static <E> List<E> convertToList(Class<E> c, String jsonStr)
    {
        List<E> list = new ArrayList<E>();
        try
        {
            JSONArray array = new JSONArray(jsonStr);
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);
                list.add(convertToObject(c, obj.toString()));
            }
        }
        catch(JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 将json字符串转换成数组
     * 
     * @param Class
     *            <E>
     * @param jsonStr
     * @return List<E>
     */
    public static <E> LinkedList<E> convertToLinkeList(Class<E> c,
            String jsonStr)
    {
        LinkedList<E> list = new LinkedList<E>();
        try
        {
            JSONArray array = new JSONArray(jsonStr);
            for (int i = 0; i < array.length(); i++)
            {
                JSONObject obj = array.getJSONObject(i);
                list.add(convertToObject(c, obj.toString()));
            }
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public static String toJson(Object object)
    {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(object);
        return jsonStr;
    }

    public static <T> String toJson(Object object, Type type)
    {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(object, type);
        return jsonStr;
    }

    public static <T> T fromJson(String para, Type type)
    {
        Gson gson = new Gson();
        T response = gson.fromJson(para, type);
        return response;
    }
}
