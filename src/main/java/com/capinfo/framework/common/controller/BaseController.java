package com.capinfo.framework.common.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 
 * ClassName	: BaseController 
 * Version		: 1.0
 * @Description	: 控制器公共父类
 */
public abstract class BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	private static final String SESSION_USER_KEY = "userid";
	
	public Integer getSessionUser(HttpServletRequest request){
		Object obj = request.getSession().getAttribute(SESSION_USER_KEY);
		if(obj!=null){
			Integer userId = (Integer)obj;
			return userId;
		}else{
			return null;
		}
	}
	
	/**
	 * 
	 * @Description: 异常处理
	 * @param @param response
	 * @param @param request
	 * @param @param ex
	 * @param @return
	 * @param @throws IOException   
	 * @return String  
	 * @throws
	 */
	@ExceptionHandler
	public String exp(HttpServletResponse response,HttpServletRequest request, Exception ex) throws IOException {
		request.setAttribute("ex", ex);
		logger.error(ex.toString()+"["+this.getClass().getName()+"]"+request.getRequestURI());
		ex.printStackTrace();
		return "error";
	}

	/**
	 * 
	 * @Description: ajax提交时返回文本
	 * @param @param response
	 * @param @param content
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 */
	public static void rendText(HttpServletResponse response, String content)throws IOException {
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(content);
	}

	/**
	 * 
	 * @Description: ajax提交时返回json
	 * @param @param response
	 * @param @param success
	 * @param @param message
	 * @param @throws IOException   
	 * @return void  
	 * @throws
	 */
	public static void rendJson(HttpServletResponse response, boolean success,String message) throws IOException {
		JSONObject json = new JSONObject();
		json.put("isSuccess", success);
		json.put("message", message);
		rendText(response, json.toString());
	}
}