package com.capinfo.framework.common.tag;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.tags.RequestContextAwareTag;
import org.springframework.web.util.HtmlUtils;

import com.capinfo.framework.common.util.ResponseUtils;
import com.capinfo.framework.web.pojo.Menu;
import com.capinfo.framework.web.pojo.User;
import com.capinfo.framework.web.service.MenuService;
import com.capinfo.framework.web.service.UserService;
import com.capinfo.framework.web.vo.MenuQueryBean;

/**
 * 
 * ClassName ：PermissionValidateTag Version ：1.0 
 * Description ：
 */
public class PermissionValidateTag extends RequestContextAwareTag {

	private static final long serialVersionUID = -7555156918670080592L;
	
	private String code;
	private String name;
	private String parameter;
	private String onClick;
	private String style;
	private String target;
	private String targetType;
	private String title;
	private String rel;
	private String mask;
	private String callback;
	private String width;
	private String height;
	private String idAtt;
	private String postType;

	@Override
	protected int doStartTagInternal() throws Exception {
		
		RequestAttributes ra = RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = ((ServletRequestAttributes) ra).getRequest();
		String ctx = request.getContextPath();
		Integer userId = (Integer) request.getSession().getAttribute("userid");
		
		UserService userService = (UserService) this.getRequestContext().getWebApplicationContext().getBean("userService");
		MenuService menuService = (MenuService) this.getRequestContext().getWebApplicationContext().getBean("menuService");
		
		try {
			MenuQueryBean menuQueryBean = new MenuQueryBean();
			menuQueryBean.setMenuCode(code);
			Menu menu = menuService.findMenuUnique(menuQueryBean);
			
			User user = userService.findUserById(userId);
			
			if(userService.permissionValidate(user, menu)){
				StringBuffer sb = new StringBuffer();
					sb.append("<a href=\""+ctx);
					sb.append(menu.getMenuUrl());
					if(StringUtils.isNotEmpty(parameter)){
						sb.append(HtmlUtils.htmlEscape(parameter));
					}
					if(StringUtils.isNotEmpty(style)){
						sb.append("\" class=\"");
						sb.append(style);
					}
					if(StringUtils.isNotEmpty(title)){
						sb.append("\" title=\"");
						sb.append(title);
					}
					if(StringUtils.isNotEmpty(rel)){
						sb.append("\" rel=\"");
						sb.append(rel);
					}
					if(StringUtils.isNotEmpty(target)){
						sb.append("\" target=\"");
						sb.append(target);
					}
					if(StringUtils.isNotEmpty(targetType)){
						sb.append("\" targetType=\"");
						sb.append(targetType);
					}
					if(StringUtils.isNotEmpty(mask)){
						sb.append("\" mask=\"");
						sb.append(mask);
					}
					if(StringUtils.isNotEmpty(width)){
						sb.append("\" width=\"");
						sb.append(width);
					}
					if(StringUtils.isNotEmpty(height)){
						sb.append("\" height=\"");
						sb.append(height);
					}
					if(StringUtils.isNotEmpty(callback)){
						sb.append("\" callback=\"");
						sb.append(callback);
					}
					if(StringUtils.isNotEmpty(idAtt)){
						sb.append("\" id=\"");
						sb.append(idAtt);
					}
					if(StringUtils.isNotEmpty(postType)){
						sb.append("\" postType=\"");
						sb.append(postType);
					}
					sb.append("\">");
//					sb.append("<span>");
					if(StringUtils.isEmpty(name)){
						sb.append(menu.getMenuName());
					}else{
						sb.append(HtmlUtils.htmlEscape(name));
					}
//					sb.append("</span>");
					sb.append("</a>");
					
					ResponseUtils.write(pageContext, sb.toString());
			}else if(code.indexOf("_update")>-1 && style.equals("icon") && StringUtils.isNotEmpty(parameter)){
				ResponseUtils.write(pageContext, name);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getOnClick() {
		return onClick;
	}

	public void setOnClick(String onClick) {
		this.onClick = onClick;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParameter() {
		return parameter;
	}

	public void setParameter(String parameter) {
		this.parameter = parameter;
	}

	public String getMask() {
		return mask;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public String getCallback() {
		return callback;
	}

	public void setCallback(String callback) {
		this.callback = callback;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

	public String getWidth() {
		return width;
	}

	public void setWidth(String width) {
		this.width = width;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getIdAtt() {
		return idAtt;
	}

	public void setIdAtt(String idAtt) {
		this.idAtt = idAtt;
	}

}
