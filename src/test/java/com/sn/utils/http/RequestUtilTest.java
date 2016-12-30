package com.sn.utils.http;

import com.sn.utils.base.BaseTest;

public class RequestUtilTest extends BaseTest {

	public void testDoGet() {
		String url = "http://www.baidu.com";
		String param = "";
		logger.info(RequestUtil.doGet(url, param));
	}

	public void testDoPost() {
		// TODO
	}

}
