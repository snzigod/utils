package com.sn.utils.jsoup;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
	public static void main(String[] arg) {
		try {
			Document doc = Jsoup.connect("http://news.sina.com.cn/").get();
			String title = doc.title();
			System.out.println("title: " + title);
			String baseUrl = doc.baseUri();
			System.out.println("baseUrl: " + baseUrl);
			Elements links = doc.getElementsByTag("a");
			for(Element link:links){
				String linkHref = link.attr("href");
				String linkText = link.text();
				System.out.println("linkHref: " + linkHref);
				System.out.println("linkText: " + linkText);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
