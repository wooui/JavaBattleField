package com.wooui.mail;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.internet.InternetAddress;

public class ReceiveMailTest {
	public static void main(String[] args) {
		Properties props = System.getProperties();
		String host = "pop3.163.com";
		String username = "jasonyifei@163.com";
		String password = "111111";
		String provider = "pop3";
		
		try{
			Session ss = Session.getDefaultInstance(props);
			Store store = ss.getStore(provider);
			store.connect(host, username, password);
			Folder inbox = store.getFolder("INBOX");
			if(inbox == null){
				System.out.println("no inbox");
				System.exit(1);
			}
			inbox.open(Folder.READ_ONLY);
			System.out.println("total email:"+inbox.getMessageCount());
			Message[] messages = inbox.getMessages();
			for(Message msg : messages){
				String from = InternetAddress.toString(msg.getFrom());
				if(from != null){
					System.out.println(from);
				}
				String replyTo = InternetAddress.toString(msg.getReplyTo());
				if(replyTo != null){
					System.out.println(replyTo);
				}
			}
			
		}catch(Exception e){
			
		}
	}
}
