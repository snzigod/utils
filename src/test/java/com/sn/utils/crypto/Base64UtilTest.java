package com.sn.utils.crypto;

import junit.framework.TestCase;

public class Base64UtilTest extends TestCase {

	public void testEncode() {
		String s = Base64Util.encode("周国鑫".getBytes());
		System.out.println("base64加密：" + s);
		s = new String(Base64Util.decode(s));
		System.out.println("base64解密：" + s);
	}

	public void testDecode() {
		String s = Base64Util.encode("周国鑫".getBytes());
		System.out.println("base64加密：" + s);
		s = new String(Base64Util.decode(s));
		System.out.println("base64解密：" + s);
	}

}
