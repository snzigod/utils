package com.sn.utils.crypto;

import com.sn.utils.base.BaseTest;

public class Base64Test extends BaseTest {

	public void testEncode() {
		String s = Base64.encode("周国鑫".getBytes());
		logger.info("base64加密：" + s);
		s = new String(Base64.decode(s));
		logger.info("base64解密：" + s);
	}

	public void testDecode() {
		// TODO
	}

}
