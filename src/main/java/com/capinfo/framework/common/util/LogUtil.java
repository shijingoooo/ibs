package com.capinfo.framework.common.util;

import org.apache.log4j.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil{
	
	private static final Logger logger = LoggerFactory.getLogger(LogUtil.class);
	
	public static void printLog(String level, String... content) {
		level = level.toUpperCase();
		if(level.equals(String.valueOf(Level.DEBUG))){
			for(String msg : content){
				logger.debug(msg);
			}
		}
		else if(level.equals(String.valueOf(Level.INFO))){
			for(String msg : content){
				logger.info(msg);
			}
		}
		else if(level.equals(String.valueOf(Level.WARN))){
			for(String msg : content){
				logger.warn(msg);
			}
		}
		else if(level.equals(String.valueOf(Level.ERROR))){
			for(String msg : content){
				logger.error(msg);
			}
		}
	}
}
