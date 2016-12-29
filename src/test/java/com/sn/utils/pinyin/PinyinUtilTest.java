package com.sn.utils.pinyin;

import junit.framework.TestCase;

public class PinyinUtilTest extends TestCase {

	public void testGetFirstSpell() {
		System.out.println("获取拼音首字母：" + PinyinUtil.getFirstSpell("周国鑫"));
	}

	public void testGetFullSpell() {
		System.out.println("获取拼音：" + PinyinUtil.getFullSpell("周国鑫"));
	}

}
