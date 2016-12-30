package com.sn.utils.http;

import junit.framework.TestCase;

public class RequestUtilTest extends TestCase {

	public void testDoGet() {
		String url = "http://www.baidu.com";
		String param = "";
		System.out.println(RequestUtil.doGet(url, param));
	}

	public void testDoPost() {
		// TODO
	}

}
