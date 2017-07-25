package com.capinfo.framework.common.mail.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.capinfo.framework.common.mail.AccountEmailService;

@Service(value="accountEmailServiceService")
public class AccountEmailServiceImpl implements AccountEmailService {

	private JavaMailSender javaMailSender;
	private String systemMail;
	
	
	public String getSystemMail() {
		return systemMail;
	}
	public void setSystemMail(String systemMail) {
		this.systemMail = systemMail;
	}
	public JavaMailSender getJavaMailSender() {
		return javaMailSender;
	}
	public void setJavaMailSender(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	public void sendMail(String to, String subject, String htmlText)
			throws Exception {
		try {
			MimeMessage msg = javaMailSender.createMimeMessage();
			MimeMessageHelper msgHelper = new MimeMessageHelper(msg,true,"utf-8");
			msgHelper.setFrom(systemMail);
			msgHelper.setTo(to);
			msgHelper.setSubject(subject);
			msgHelper.setText(htmlText, true);
			javaMailSender.send(msg);
		} catch(MessagingException e) {
			e.printStackTrace();
		}
		
	}

}
