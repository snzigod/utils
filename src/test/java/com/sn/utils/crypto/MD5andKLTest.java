package com.sn.utils.crypto;

import com.sn.utils.base.BaseTest;

public class MD5andKLTest extends BaseTest {

	public void testMD5() {
		String s = new String("123123");
		logger.info("原始：" + s);
		s = MD5andKL.MD5(s);
		logger.info("MD5后：" + s);
		s = MD5andKL.KL(s);
		logger.info("MD5后再加密：" + s);
		s = MD5andKL.JM(s);
		logger.info("解密为MD5后的：" + s);
	}

	public void testKL() {
		// TODO
	}

	public void testJM() {
		// TODO
	}

}
