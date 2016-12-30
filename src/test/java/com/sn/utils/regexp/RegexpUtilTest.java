package com.sn.utils.regexp;

import com.sn.utils.base.BaseTest;

public class RegexpUtilTest extends BaseTest {

	public void testRegexp() {
		logger.info("手机号码正则校验（15926344148）：" + RegexpUtil.regexp("15926344148", "^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$"));
	}

}
