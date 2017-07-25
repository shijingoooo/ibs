package com.capinfo.framework.common.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.BodyContent;

/**
 * 
 * ClassName	：ResponseUtils   
 * Version		：1.0    
 * Author		：GaoYang   
 * Date			：2013-10-13 上午11:06:55    
 * Copyright	：首都信息发展股份有限公司
 * Description	：
 */
public class ResponseUtils
{

	public ResponseUtils()
	{
	}


	public static void write(PageContext pagecontext, String s)
		throws JspException
	{
		JspWriter jspwriter = pagecontext.getOut();
		try
		{
			jspwriter.print(s);
		}
		catch (IOException ioexception)
		{
			throw new JspException(ioexception.toString());
		}
	}

	public static void writePrevious(PageContext pagecontext, String s)
		throws JspException
	{
		JspWriter jspwriter = pagecontext.getOut();
		if (jspwriter instanceof BodyContent)
			jspwriter = ((BodyContent)jspwriter).getEnclosingWriter();
		try
		{
			jspwriter.print(s);
		}
		catch (IOException ioexception)
		{
			throw new JspException(ioexception.toString());
		}
	}
}