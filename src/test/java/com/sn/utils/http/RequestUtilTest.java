package com.sn.utils.http;

import junit.framework.TestCase;

public class RequestUtilTest extends TestCase {

	public void testDoGet() {
		String url = "http://58.213.153.174:61080/services/InvoiceService130.ashx";
		for (int i = 0; i <= 1000; i++) {
			String param = "action={'ChannelId':'','AreaId':'" + i
					+ "'}&functionId=12010";
			try {
				Thread.currentThread();
				Thread.sleep(10000);
				System.out.println("areaId: " + i + "---"
						+ RequestUtil.doGet(url, param));
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void testDoPost() {
		// TODO
	}

}
