package com.capinfo.framework.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class SimpleHtmlUtils {

	public static String simpleHtml(String param){
		
		if(StringUtils.isEmpty(param)){
			return param;
		}else{
			
			String regEx = "[`~@#$%^&*+=|{}——]";
			Pattern p = Pattern.compile(regEx);
			Matcher m = p.matcher(param);
			param = m.replaceAll("").trim();
			
			param = param.replaceAll("\\(","（");
			param = param.replaceAll("\\)","）");
			param = param.replaceAll("\\[","【");
			param = param.replaceAll("\\]","】");
			param = param.replaceAll("\r\n","<BR>");
			param = param.replaceAll("&","&amp;amp;");
	    	param = param.replaceAll("<","&amp;lt;");
	    	param = param.replaceAll(">","&amp;gt;");
	    	param = param.replaceAll("'","&amp;#039;");
	    	param = param.replaceAll("\"","&amp;quot;");
	    	
			return param;
		}
		
	}
}
