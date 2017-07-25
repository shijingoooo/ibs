package com.capinfo.framework.common.tag;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.capinfo.framework.common.util.ResponseUtils;
import com.capinfo.modules.orm.Page;

/**
 * '
 * ClassName	：PageTag   
 * Version		：1.0
 * Description	：
 */
public class PageTag extends RequestContextAwareTag{
	
	private static final long serialVersionUID = 7714632979113255795L;
	
	private Page<?> page;
	private String pageName;
	private String targetType;
	private String rel;
	
	public String getPageName() {
		return pageName;
	}
	public void setPageName(String pageName) {
		this.pageName = pageName;
	}
	public Page<?> getPage() {
		return page;
	}
	public void setPage(Page<?> page) {
		this.page = page;
	}	

	public String getTargetType() {
		return targetType == null ? "navTab" : targetType;
	}
	
	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}
	
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	@Override
	protected int doStartTagInternal() throws Exception {
		
		WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
		String path = webApplicationContext.getServletContext().getRealPath("/template")+File.separator+"pagination.xml";
		String content = FileUtils.readFileToString(new File(path), "utf-8");
		content=content.replaceAll("#totalCount", String.valueOf(page.getTotalCount()));
		content=content.replaceAll("#pageSize", String.valueOf(page.getPageSize()));
		content=content.replaceAll("#pageNo", String.valueOf(page.getPageNo()));
		content=content.replaceAll("#targetType", getTargetType());
		content=content.replaceAll("#rel", StringUtils.isEmpty(getRel())?"":getRel());
		if(page.getPageSize()>0){
			content=content.replaceAll("value=\""+page.getPageSize()+"\"", "value=\""+page.getPageSize()+"\" selected");
		}
		ResponseUtils.write(pageContext, content);
		return 0;
	}
}
