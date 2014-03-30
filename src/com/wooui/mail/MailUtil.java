package com.wooui.mail;

import javax.mail.Part;
import javax.mail.internet.MimeUtility;

import sun.misc.BASE64Decoder;

public class MailUtil {

	// �����ˣ������ˣ���ִ���ʼ��������Ĵ�������,resΪ��ȡ�ĵ�ַ
	// httpĬ�ϵı��뷽ʽΪISO8859_1
	// �Ժ������ĵķ��͵�ַ��ʹ��MimeUtility.decodeTex����
	// ��������ѵ�ַ��ISO8859_1����ת����gbk����
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

	// ת��ΪGBK����
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

	// �����ʼ�ʱ����ȡĳ���ʼ������ĸ���������������
	// ������base64����������ģ������base64���룬
	// ����Ը���������ISO8859_1��gbk�ı���ת��
	public static String getFileChinese(Part part) throws Exception {
		String temp = part.getFileName();// partΪPartʵ��
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
