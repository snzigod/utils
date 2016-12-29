package com.sn.utils.crypto;

import junit.framework.TestCase;

public class Md5Test extends TestCase {

	public void testGetMd5ByString() {
		System.out.println(Md5.getMd5ByString("Md5UtilTest.java"));
	}

	public void testGetMd5ByFile() {
		System.out.println(Md5.getMd5ByFile("需要获取md5的文件"));
	}

}
