package com.capinfo.framework.common.tag;

import java.lang.reflect.Method;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.capinfo.framework.common.util.ResponseUtils;
import com.capinfo.framework.web.mapper.MenuMapper;
import com.capinfo.framework.web.pojo.Menu;

/**
 * 
 * ClassName ：TableTag Version ：1.0 Description ：
 */
public class TableTag extends RequestContextAwareTag {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7714632979113255795L;
	// private Page<?> page;
	List<?> list;

	private String columnsNames;
	private String columns;
	private String widths;
	private String menu;
	private String click;
	private String condition;
	private final static String regex = ",";

	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getClick() {
		return click;
	}

	public void setClick(String click) {
		this.click = click;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getColumnsNames() {
		return columnsNames;
	}

	public void setColumnsNames(String columnsNames) {
		this.columnsNames = columnsNames;
	}

	public String getColumns() {
		return columns;
	}

	public void setColumns(String columns) {
		this.columns = columns;
	}

	public String getWidths() {
		return widths;
	}

	public void setWidths(String widths) {
		this.widths = widths;
	}

	@Override
	protected int doStartTagInternal() throws Exception {

		StringBuilder sb = new StringBuilder();
		String ths[] = columnsNames.split(regex);
		String tds[] = columns.split(regex);
		String syls[] = widths.split(regex);

		/**
		 * 表头
		 */
		sb.append(" <table width='100%' border='0' cellpadding='0' cellspacing='1' bgcolor='#a8c7ce' onmouseover='changeto()'  onmouseout='changeback()'>");
		sb.append("<tr>");
		sb.append("<td width='1%' height='20' bgcolor='d3eaef' class='STYLE10'><div align='center'> </div></td>");
		sb.append("<td width='1%' height='20' bgcolor='d3eaef' class='STYLE10'><div align='center'>序号 </div></td>");
		for (int i = 0; i < ths.length; i++) {
			sb.append(" <td "
					+ (syls != null ? "width='" + syls[i] + "%'" : "")
					+ " height='20' bgcolor='d3eaef' class='STYLE6'><div align='center'><span class='STYLE10'>");
			sb.append(ths[i]);
			sb.append("</span></div></td>");
		}
		if (menu != null && !menu.equals("")) {
			sb.append("<td width='8%' height='20' bgcolor='d3eaef' class='STYLE10'><div align='center'>操作 </div></td>");
		}
		sb.append("</tr>");
		/**
		 * 表内容
		 */
		for (int i = 0; i < list.size(); i++) {
			sb.append("<tr>");
			sb.append("<td height='20' bgcolor='#FFFFFF'><div align='center'> <input type='checkbox' name='checkbox'  value='"
					+ getter(list.get(i), "id") + "'/> </div></td>");
			sb.append("<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>"
					+ (i + 1) + "</div></td>");
			for (String td : tds) {
				sb.append("<td height='20' bgcolor='#FFFFFF' class='STYLE19'><div align='center'>"
						+ getter(list.get(i), td) + "</div></td>");
			}

			WebApplicationContext webApplicationContext = ContextLoader
					.getCurrentWebApplicationContext();
			/**
			 * 自定义事件
			 */
			if (menu != null && !menu.equals("")) {
				MenuMapper menuMapper = (MenuMapper) WebApplicationContextUtils
						.getRequiredWebApplicationContext(
								webApplicationContext.getServletContext())
						.getBean("menuMapper");
				String[] menus = menu.split(regex);
				String[] clicks = click.split(regex);
				String[] conditions = condition.split(regex);
				sb.append("<td width='4%' height='20'   bgcolor='#FFFFFF' class='STYLE19'><div align='center'>");
				if (menus.length > 0) {
					for (int j = 0; j < menus.length; j++) {
						Menu menu = menuMapper.findMenuById(Integer
								.valueOf(menus[j]));
						if (hasPermission(menu)) {
							String[] cts = conditions[j].split("&");
							StringBuffer click = new StringBuffer();
							StringBuffer urlCondition = new StringBuffer();
							click.append(clicks[j]);
							click.append("(");

							for (int t = 0; t < cts.length; t++) {
								urlCondition.append(cts[t]);
								if (cts[t].indexOf("=") == -1) {
									urlCondition.append("=");
									urlCondition.append(getter(list.get(i),
											cts[t]));
								}
								click.append("'");
								click.append(getter(list.get(i), cts[t]));
								click.append("'");
								if (t < cts.length - 1) {
									click.append(",");
									urlCondition.append("&");
								}
							}
							click.append(")");
							String href = menu.getMenuUrl() + "?"
									+ urlCondition;
							sb.append("<a href='"
									+ (clicks[j].equals("no") ? href : "#")
									+ "'  class='left-font03' id='menu"
									+ menu.getId() + "' onclick=" + click + ">"
									+ menu.getMenuName() + "</a>");
							if (j < menus.length - 1) {
								sb.append("&nbsp;&nbsp;");
							}
						}
					}
				}
				sb.append(" </div></td>");
			}
			sb.append("</tr>");
		}
		sb.append("</table>");
		ResponseUtils.write(pageContext, sb.toString());
		return super.doEndTag();
	}

	private static Object getter(Object obj, String att) {
		try {
			if (att.indexOf(".") > 0) {
				if (getter(obj, att.substring(0, att.indexOf("."))) == null
						|| "".equals(getter(obj,
								att.substring(0, att.indexOf("."))))) {
					return "";
				} else {
					return getter(
							getter(obj, att.substring(0, att.indexOf("."))),
							att.substring(att.indexOf(".") + 1));
				}
			} else {
				// String methodName = "get" +
				// att.substring(0,1).toUpperCase()+att.substring(1);

				if (att.indexOf("?") != -1) {
					Method method = obj.getClass().getMethod(
							"get" + att.substring(0, 1).toUpperCase()
									+ att.substring(1, att.indexOf("?")));
					Object result = method.invoke(obj);
					String cond = att.substring(att.indexOf("?") + 1);
					String values[] = cond.split("&");
					for (String value : values) {
						if (value.substring(0, value.indexOf("=")).equals(
								result == null ? "" : result.toString())
								|| allEmpty(result,
										value.substring(0, value.indexOf("=")))) {
							result = value.substring(value.indexOf("=") + 1);
							break;
						}
					}
					return result;
				} else {
					Method method = obj.getClass().getMethod(
							"get" + att.substring(0, 1).toUpperCase()
									+ att.substring(1));
					Object result = method.invoke(obj);
					return result == null ? "" : result;
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			return "";
		}
		// return null;
	}

	private static boolean allEmpty(Object result, String value) {
		return result == null && StringUtils.isEmpty(value);
	}

	private boolean hasPermission(Menu menu) {

		return true;
	}

	public static void main(String[] args) {
		// System.out.println("abc".substring("abc".indexOf("b")));
		Object o = "aaa";
		System.out.println(o.equals("aaa"));
	}
}
