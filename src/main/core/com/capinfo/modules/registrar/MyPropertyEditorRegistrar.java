package com.capinfo.modules.registrar;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.PropertyEditorRegistrar;
import org.springframework.beans.PropertyEditorRegistry;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

public class MyPropertyEditorRegistrar implements PropertyEditorRegistrar,
		WebBindingInitializer {

	/**
	 * @Fields format : TODO(日期类型格式,默认格式：yyyy-MM-dd)
	 */
	private String format = "yyyy-MM-dd";

	public void setFormat(String format) {
		this.format = format;
	}

	@Override
	public void registerCustomEditors(PropertyEditorRegistry registry) {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		// 1.将string类型的日期字符串初始化为date类型;
		registry.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
		// 2.去除参数两边的空格;
		registry.registerCustomEditor(String.class, new StringTrimmerEditor(
				false));
	}

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {
		// TODO Auto-generated method stub
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		dateFormat.setLenient(false);
		// 1.将string类型的日期字符串初始化为date类型;
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
		// 2.去除参数两边的空格;
		binder.registerCustomEditor(String.class,
				new StringTrimmerEditor(false));
	}

	public String getFormat() {
		return format;
	}

}
