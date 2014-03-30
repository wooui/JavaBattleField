package com.wooui.mail;

import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

public class SendMailTest {
	public static void main(String[] args) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.163.com");
		Session session = Session.getInstance(props, null);
		try{
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom("jasonyifei@163.com");
			msg.setRecipients(Message.RecipientType.TO, "37978634@qq.com");
			msg.setSubject("Test java mail");
			msg.setSentDate(new Date());
			msg.setText("Hello Java Mail");
			Transport.send(msg, "jasonyifei@163.com","111111");
		}catch (MessagingException e){
			System.out.println("send failed, excepton:" + e);
		}
	}
}
