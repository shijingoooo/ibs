package com.capinfo.framework.common.tag;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.capinfo.framework.web.mapper.MenuMapper;
import com.capinfo.framework.web.pojo.Menu;

/**
 * 
 * ClassName	：CommonFunctions   
 * Version		：1.0
 * Description	：
 */
public class CommonFunctions {
	
	public static String getMenuUrl(Integer id) throws Exception {
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		MenuMapper menuMapper = (MenuMapper) WebApplicationContextUtils
				.getRequiredWebApplicationContext(webApplicationContext.getServletContext()).getBean("menuMapper");
		Menu menu = menuMapper.findMenuById(id);
		if(menu==null){
			return "";
		}
		return menu.getMenuUrl();
	}
}
