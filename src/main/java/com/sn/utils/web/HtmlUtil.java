package com.sn.utils.web;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;

/**
 * 截取文章摘要
 * 
 * @author langhsu
 */
public class HtmlUtil {

	/**
	 * 提取纯文本
	 * 
	 * @param html
	 *            代码
	 * @return string
	 */
	public static String getText(String html) {
		return Jsoup.clean(html, Whitelist.none());
	}

	/**
	 * 以下标签可以通过 (b, em, i, strong, u. 纯文本)
	 * 
	 * @param html
	 *            代码
	 * @return string
	 */
	public static String getSimpleHtml(String html) {
		return Jsoup.clean(html, Whitelist.simpleText());
	}

	/**
	 * 获取文章中的img url
	 * 
	 * @param html
	 *            代码
	 * @return string
	 */
	public static String getImgSrc(String html) {
		Document doc = Jsoup.parseBodyFragment(html);
		Element image = doc.select("img").first();
		return image == null ? null : image.attr("src");
	}

	public static String xss(String value) {
		value = value.replaceAll("&lt;", "<").replaceAll("&gt;", ">");
		value = value.replaceAll("&#40;", "\\(").replaceAll("&#41;", "\\)");
		value = value.replaceAll("&#39;", "'");
		return value;
	}

}
