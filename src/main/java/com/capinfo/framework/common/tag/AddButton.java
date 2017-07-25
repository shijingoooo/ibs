package com.capinfo.framework.common.tag;

import org.springframework.web.servlet.tags.RequestContextAwareTag;

import com.capinfo.framework.common.util.ResponseUtils;

/**
 * 
 * ClassName ：AddButton Version ：1.0 Description ：
 */
public class AddButton extends RequestContextAwareTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String id;
	private String userId;
	private String addAction;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddAction() {
		return addAction;
	}

	public void setAddAction(String addAction) {
		this.addAction = addAction;
	}

	public AddButton() {
		userId = null;
		addAction = null;
	}

	@Override
	protected int doStartTagInternal() throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append("<button id='");
		sb.append(id);
		sb.append("'>");
		sb.append(name);
		sb.append("</button>");
		String ss = ("<button></button>");
		ResponseUtils.write(pageContext, ss);
		return 0;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
