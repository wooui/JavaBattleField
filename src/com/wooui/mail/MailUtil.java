package com.wooui.mail;

import javax.mail.Part;
import javax.mail.internet.MimeUtility;

import sun.misc.BASE64Decoder;

public class MailUtil {

	// 发信人，收信人，回执人邮件中有中文处理乱码,res为获取的地址
	// http默认的编码方式为ISO8859_1
	// 对含有中文的发送地址，使用MimeUtility.decodeTex方法
	// 对其他则把地址从ISO8859_1编码转换成gbk编码
	public static String getChineseFrom(String res) {
		String from = res;
		try {
			if (from.startsWith("=?GB") || from.startsWith("=?gb")
					|| from.startsWith("=?UTF")) {
				from = MimeUtility.decodeText(from);
			} else {
				from = new String(from.getBytes("ISO8859_1"), "GBK");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return from;
	}

	// 转换为GBK编码
	public static String toChinese(String strvalue) {
		try {
			if (strvalue == null)
				return null;
			else {
				strvalue = new String(strvalue.getBytes("ISO8859_1"), "GBK");
				return strvalue;
			}
		} catch (Exception e) {
			return null;
		}
	}

	// 接收邮件时，获取某个邮件的中文附件名，出现乱码
	// 对于用base64编码过的中文，则采用base64解码，
	// 否则对附件名进行ISO8859_1到gbk的编码转换
	public static String getFileChinese(Part part) throws Exception {
		String temp = part.getFileName();// part为Part实例
		if ((temp.startsWith("=?GBK?") && temp.endsWith("?="))
				|| (temp.startsWith("=?gbk?b?") && temp.endsWith("?="))) {
			temp = MailUtil.getFromBASE64(temp.substring(8,
					temp.indexOf("?=") - 1));
		} else {
			temp = MailUtil.toChinese(temp);
		}
		return temp;
	}

	public static String getFromBASE64(String s) {
		if (s == null)
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			byte[] b = decoder.decodeBuffer(s);
			return new String(b);
		} catch (Exception e) {
			return null;
		}
	}
}
