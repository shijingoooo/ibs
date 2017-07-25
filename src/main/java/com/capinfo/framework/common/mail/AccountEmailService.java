package com.capinfo.framework.common.mail;

public interface AccountEmailService {

	void sendMail(String to, String subject, String htmlText) throws Exception;
}
