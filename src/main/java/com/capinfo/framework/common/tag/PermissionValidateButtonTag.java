package com.capinfo.framework.common.tag;


import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.capinfo.framework.common.util.ResponseUtils;
import com.capinfo.framework.web.pojo.Menu;
import com.capinfo.framework.web.service.MenuService;
import com.capinfo.framework.web.vo.MenuQueryBean;

/**
 * 
 * ClassName	：PermissionValidateButtonTag   
 * Version		：1.0
 * Description	：
 */
public class PermissionValidateButtonTag extends RequestContextAwareTag {

	private static final long serialVersionUID = 8822628201457509251L;
	
	private String code;
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	protected int doStartTagInternal() throws Exception {
		
		MenuService menuService = (MenuService) this.getRequestContext().getWebApplicationContext().getBean("menuService");
		
		MenuQueryBean menuQueryBean = new MenuQueryBean();
		menuQueryBean.setMenuCode(code);
		Menu menu = menuService.findMenuUnique(menuQueryBean);
		
		String ss =("<li><div class=\"buttonActive\"><div class=\"buttonContent\"><button type=\"submit\">"
				+(StringUtils.isEmpty(name)?menu.getMenuName():name)+"</button></div></div></li>");
		ResponseUtils.write(pageContext, ss);
		
		return 0;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
