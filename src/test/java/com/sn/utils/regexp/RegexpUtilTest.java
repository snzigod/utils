package com.sn.utils.regexp;

import junit.framework.TestCase;

public class RegexpUtilTest extends TestCase {

	public void testRegexp() {
		System.out.println("手机号码正则校验（15926344148）：" + RegexpUtil.regexp("15926344148", "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"));
	}

}
