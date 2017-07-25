package com.capinfo.framework.common.tag;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

/**
 * 
 * ClassName ：ToolBarTag Version ：1.0 Description ：
 */
public class ToolBarTag extends RequestContextAwareTag {
	
	private static final long serialVersionUID = 221305657143947503L;
	private String title;
	private String checkAll;
	private String addid;
	private String delid;
	private String addPrarmeter;
	private String delPrarmeter;

	/**
	 * @return addPrarmeter
	 */
	public String getAddPrarmeter() {
		return addPrarmeter;
	}

	/**
	 * @param addPrarmeteraddPrarmeter
	 */
	public void setAddPrarmeter(String addPrarmeter) {
		this.addPrarmeter = addPrarmeter;
	}

	/**
	 * @return delPrarmeter
	 */
	public String getDelPrarmeter() {
		return delPrarmeter;
	}

	/**
	 * @param delPrarmeterdelPrarmeter
	 */
	public void setDelPrarmeter(String delPrarmeter) {
		this.delPrarmeter = delPrarmeter;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCheckAll() {
		return checkAll;
	}

	public void setCheckAll(String checkAll) {
		this.checkAll = checkAll;
	}

	public String getAddid() {
		return addid;
	}

	public void setAddid(String addid) {
		this.addid = addid;
	}

	public String getDelid() {
		return delid;
	}

	public void setDelid(String delid) {
		this.delid = delid;
	}

	private static String getUrl(String id) {
		if (StringUtils.isNotEmpty(id)) {
			// MenuDao menuDao = (MenuDao)
			// WebApplicationContextUtils.getRequiredWebApplicationContext(
			// Struts2Utils.getAppContext() ).getBean( "menuDao" );
			// return menuDao.load(Long.valueOf(id)).getMenuAddr();
		}
		return null;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		// UserAccount context = (UserAccount)
		// ServletActionContext.getRequest().getSession().getAttribute("user");
		// FunctionCheckService functionCheckService = (FunctionCheckService)
		// WebApplicationContextUtils.getRequiredWebApplicationContext(
		// Struts2Utils.getAppContext() ).getBean( "functionCheckService" );
		StringBuilder sb = new StringBuilder();
		sb.append(" <table width='100%' border='0' cellspacing='0' cellpadding='0'>");
		sb.append(" <tr>");
		sb.append(" <td height='24' bgcolor='#353c44'>");
		sb.append(" <table width='100%' border='0' cellspacing='0' cellpadding='0'>");
		sb.append("<tr>");
		sb.append(" <td>");
		sb.append("<table width='100%' border='0' cellspacing='0' cellpadding='0'>");
		sb.append("<tr>");
		// sb.append("<td width='6%' height='19' valign='bottom'><div align='center'><img src='"+Constants.DOMAINNAME+"/images/tb.gif' width='14' height='14' /></div></td>");
		sb.append("<td width='94%' valign='bottom'><span class='STYLE1'> <font style='color:white;'> "
				+ title + "</font></span></td>");
		sb.append(" </tr>");
		sb.append(" </table>");
		sb.append(" </td>");
		sb.append("<td><div align='right'><span class='STYLE1'>");
		if ("y".equals(checkAll))
			sb.append("<input type='checkbox'  id='checkboxAll2' onchange='checkAll(this)'/>  <font style='color:white;'>全选</font>      &nbsp;&nbsp;");
		// if(functionCheckService.funcheck(context.getId(),addid))
		if (addid != null && StringUtils.isNotEmpty(getUrl(addid))) {
			if (addPrarmeter != null) {
				sb.append("<a href='javascript:addPage(\"" + getUrl(addid)
						+ addPrarmeter
						+ "\")'><img src='"
						// + Constants.DOMAINNAME
						+ "/images/add.gif' width='10' height='10'  /> <font style='color:white;'>添加</font> </a>  &nbsp;");
			} else {
				sb.append("<a href='javascript:addPage(\""
						+ getUrl(addid)
						+ "\")'><img src='"
						// + Constants.DOMAINNAME
						+ "/images/add.gif' width='10' height='10'  /> <font style='color:white;'>添加</font> </a>  &nbsp;");
			}
		}
		// if(StringUtils.isNotEmpty(getUrl(delid))&&
		// functionCheckService.funcheck(context.getId(),delid))
		if (delid != null && StringUtils.isNotEmpty(getUrl(delid))) {
			if (delPrarmeter != null) {
				sb.append("<a href='#' name='" + getUrl(delid)
						+ delPrarmeter
						+ "' id='deleteAll2' ><img src='"
						// + Constants.DOMAINNAME
						+ "/images/del.gif' width='10' height='10' /> <font style='color:white;'>删除  </font> </a> &nbsp;&nbsp;");
			} else {
				sb.append("<a href='#' name='"
						+ getUrl(delid)
						+ "' id='deleteAll2' ><img src='"
						// + Constants.DOMAINNAME
						+ "/images/del.gif' width='10' height='10' /> <font style='color:white;'>删除  </font> </a> &nbsp;&nbsp;");
			}
		}
		sb.append(" </span><span class='STYLE1'> &nbsp;</span></div></td>");
		sb.append(" </tr>");
		sb.append(" </table>");
		sb.append(" </td>");
		sb.append(" </tr>");
		sb.append(" </table>");
		// ResponseUtils.write(pageContext, sb.toString());
		return 0;
	}
}
