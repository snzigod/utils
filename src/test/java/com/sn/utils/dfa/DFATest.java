package com.sn.utils.dfa;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

public class DFATest extends TestCase {

	public void testSearchKeywordString() {
		DFA dfa = DFA.getInstance();
		List<String> keywordList = new ArrayList<String>();
		keywordList.add("中国人民");
		keywordList.add("中国共和国");
		try {
			dfa.createKeywordTree(keywordList);
			dfa.printAllKeywordTree(DFA.getRootNode());

			String str = dfa.replaceKeyword("2014中国人民富裕了", "=");

			System.out.println("1--->" + str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// dfa.deleteNodeToKeywordTree("中国人民");
		// flag=dfa.searchKeyword("2014中国人民富裕了");
		// System.out.println("2--->"+flag);
	}

	public void testReplaceKeywordStringString() {
		// TODO
	}

}
