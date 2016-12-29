package com.sn.utils.crypto;

import junit.framework.TestCase;

public class MD5andKLTest extends TestCase {

	public void testMD5() {
		String s = new String("123123");
		System.out.println("原始：" + s);
		s = MD5andKL.MD5(s);
		System.out.println("MD5后：" + s);
		s = MD5andKL.KL(s);
		System.out.println("MD5后再加密：" + s);
		s = MD5andKL.JM(s);
		System.out.println("解密为MD5后的：" + s);
	}

	public void testKL() {

	}

	public void testJM() {

	}

}
