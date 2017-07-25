package com.capinfo.framework.common;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Constants {

	public static Map<Integer,Integer> ZY_AM_OVERPROOF_MAP = Collections.synchronizedMap(new HashMap<Integer,Integer>());
	
	public static Map<Integer,Integer> ZY_PM_OVERPROOF_MAP = Collections.synchronizedMap(new HashMap<Integer,Integer>());
	
	public static Map<Integer,Integer> YC_OVERPROOF_MAP = Collections.synchronizedMap(new HashMap<Integer,Integer>());
	
	public static Map<Integer,Integer> YC_RELIEVE_MAP = Collections.synchronizedMap(new HashMap<Integer,Integer>());

	public static String filePath = "/download/";

	//市级住建委用户
	public static final int USER_TYPE_CITY_ZHUJIAN = 1;
	//区县住建委用户
	public static final int USER_TYPE_DIS_ZHUJIAN = 2;
	//工地管理员
	public static final int USER_TYPE_PROJECT_MANAGER = 3;
	//管理员
	public static final int USER_TYPE_MANAGER = 4;
	//市级经信委用户
	public static final int USER_TYPE_CITY_JINGXIN = 5;
	//区县级经信局用户
	public static final int USER_TYPE_DIS_JINGXIN = 6;
	//区县环保局用户
	public static final int USER_TYPE_DIS_HUANBAO = 7;
	//市级环保局用户
	public static final int USER_TYPE_CITY_HUANBAO = 8;
	//省级住建委用户
	public static final int USER_TYPE_PRO_ZHUJIAN = 9;
	//省级经信委用户
	public static final int USER_TYPE_PRO_JINGXIN = 10;
	//省级环保局用户
	public static final int USER_TYPE_PRO_HUANBAO = 11;
}
