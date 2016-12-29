package com.sn.utils.crypto;

import java.io.FileInputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * md5工具
 */
public class Md5Util {

	/**
	 * 获取字符串的Md5码
	 * 
	 * @param str
	 *            　文件路径
	 * @return
	 */
	public static String getMd5ByString(String str) {
		String inStr = str;
		java.security.MessageDigest md = null;
		String out = null;
		try {
			md = java.security.MessageDigest.getInstance("MD5");
			byte[] digest = md.digest(inStr.getBytes());
			out = byte2hex(digest);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
		return out;
	}

	/**
	 * 获取文件的Md5码
	 * 
	 * @param filePath
	 *            　文件路径
	 * @return
	 */
	public static String getMd5ByFile(String filePath) {
		try {
			InputStream fis;
			fis = new FileInputStream(filePath);
			byte[] buffer = new byte[1024];

			MessageDigest md5 = MessageDigest.getInstance("MD5");
			int numRead = 0;
			while ((numRead = fis.read(buffer)) > 0) {
				md5.update(buffer, 0, numRead);
			}
			fis.close();
			return byte2hex(md5.digest());
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 整理成32位大写的MD5
	 * 
	 * @param b
	 * @return
	 */
	private static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1) {
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
		}
		return hs.toUpperCase();
	}

}
