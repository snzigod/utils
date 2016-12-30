package com.sn.utils.dfa;

import java.util.ArrayList;
import java.util.List;

import com.sn.utils.base.BaseTest;

public class DFATest extends BaseTest {

	public void testSearchKeywordString() {
		DFA dfa = DFA.getInstance();
		List<String> keywordList = new ArrayList<String>();
		keywordList.add("中国人民");
		keywordList.add("中国共和国");
		try {
			dfa.createKeywordTree(keywordList);
			dfa.printAllKeywordTree(DFA.getRootNode());
			String str = dfa.replaceKeyword("2014中国人民富裕了", "=");
			logger.info(str);
		} catch (Exception e) {
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
