package com.sn.utils.crypto;

import com.sn.utils.base.BaseTest;

public class Md5Test extends BaseTest {

	public void testGetMd5ByString() {
		logger.info(Md5.getMd5ByString("Md5UtilTest.java"));
	}

	public void testGetMd5ByFile() {
		logger.info(Md5.getMd5ByFile("需要获取md5的文件"));
	}

}
