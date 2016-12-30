package com.sn.utils.dfa;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.sn.utils.string.StringUtil;

/**
 * 分词工具（过滤敏感词）
 */
public class DFA implements Serializable {

	private static final long serialVersionUID = -3756876761520461730L;

	private static DFA instance = null;

	private static TreeNode rootNode = null;

	private static ByteBuffer keywordBuffer = null;

	private static String charset = null;

	private static String DEFAULT_REP_CHAR = null;

	private DFA() {

	}

	public static DFA getInstance() {
		synchronized (DFA.class) {
			instance = new DFA();
			// 根节点
			rootNode = new TreeNode();
			// 关键词缓存
			keywordBuffer = ByteBuffer.allocate(1024);
			// 关键词编码
			charset = "GBK";
			// 默认替换字符
			DEFAULT_REP_CHAR = "*";
		}
		return instance;
	}

	public static TreeNode getRootNode() {
		return rootNode;
	}

	/*
	 * 创建树结构(DFA)
	 */
	public void createKeywordTree(List<String> keywordList)
			throws UnsupportedEncodingException {
		for (String keyword : keywordList) {
			if (keyword == null)
				continue;
			keyword = keyword.trim();
			byte[] bytes = keyword.getBytes(charset);
			TreeNode tempNode = rootNode;
			for (int i = 0; i < bytes.length; i++) {
				// 字符转换成数字
				int index = bytes[i] & 0xff;
				TreeNode node = tempNode.getSubNode(index);
				if (node == null) {
					node = new TreeNode();
					node.setIndex(index);
					node.setByteData(bytes[i]);
					tempNode.setSubNode(index, node);
					tempNode.setChildCount(tempNode.getChildCount() + 1);
				}
				tempNode = node;
				if (i == bytes.length - 1) {
					// 关键词结束,设置结束标志
					tempNode.setKeywordEnd(true);
				}
			}
		}
	}

	/**
	 * 搜索关键字
	 */
	public boolean searchKeyword(String text) throws Exception {
		return searchKeyword(text.getBytes(charset));
	}

	/**
	 * 替换关键字
	 */
	public String replaceKeyword(String text, String rep) throws Exception {
		return replaceKeyword(text.getBytes(charset), repCharCheck(rep)
				.getBytes(charset));
	}

	/*
	 * 搜索敏感词汇 存在:true 不存在:false
	 */
	public boolean searchKeyword(byte[] bytes) {
		StringBuilder words = new StringBuilder();
		if (bytes == null || bytes.length == 0) {
			return false;
		}
		TreeNode tempNode = rootNode;
		// 回滚数
		int rollback = 0;
		// 当前比较的位置
		int position = 0;
		while (position < bytes.length) {
			int index = bytes[position] & 0xFF;
			// 写关键词缓存
			keywordBuffer.put(bytes[position]);
			tempNode = tempNode.getSubNode(index);
			// 当前位置的匹配结束
			if (tempNode == null) {
				// 回退并测试下一个字节
				position = position - rollback;
				rollback = 0;
				// 状态机复位
				tempNode = rootNode;
				// 清空
				keywordBuffer.clear();
			} else if (tempNode.isKeywordEnd()) { // 是结束点记录关键词
				keywordBuffer.flip();
				String keyword = Charset.forName(charset).decode(keywordBuffer)
						.toString();
				keywordBuffer.limit(keywordBuffer.capacity());
				if (words.length() == 0)
					words.append(keyword);
				else
					words.append(":").append(keyword);
				// 遇到结束点 rollback置为1
				rollback = 1;
				return true;
			} else {
				// 非结束点回退数加1
				rollback++;
			}
			position++;
		}
		return false;
	}

	// 替换敏感词为*
	public String replaceKeyword(byte[] bytes, byte[] repByte) {
		StringBuilder words = new StringBuilder();
		if (bytes == null || bytes.length == 0) {
			return words.toString();
		}
		TreeNode tempNode = rootNode;
		int rollback = 0;
		int position = 0;
		while (position < bytes.length) {
			int index = bytes[position] & 0xFF;
			keywordBuffer.put(bytes[position]);
			tempNode = tempNode.getSubNode(index);
			if (tempNode == null) {
				position = position - rollback;
				rollback = 0;
				tempNode = rootNode;
				keywordBuffer.clear();
			} else if (tempNode.isKeywordEnd()) {
				keywordBuffer.flip();
				for (int i = 0; i <= rollback; i++) {
					bytes[position - i] = repByte[0];
				}
				keywordBuffer.limit(keywordBuffer.capacity());
				rollback = 1;
			} else {
				rollback++;
			}
			position++;
		}
		String result = null;
		try {
			result = new String(bytes, charset);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 打印整个树
	 */
	public void printAllKeywordTree(TreeNode rootNode) {
		List<TreeNode> allNodes = new ArrayList<TreeNode>();
		if (rootNode == null)
			return;
		allNodes.add(rootNode);
		while (true) {
			if (allNodes.size() == 1
					&& rootNode.getChildCount() == rootNode.getTraverSubNodes()
							.size()) {
				rootNode.setTraverSubNodes(new ArrayList<TreeNode>());
				break;
			}
			TreeNode curr = allNodes.get(0);
			if (curr.isKeywordEnd()) {
				print(allNodes);
				allNodes.remove(0);
			}
			List<TreeNode> subNodeList = curr.getAllSubNode();
			boolean flag = true;
			for (int i = 0; i < subNodeList.size(); i++) {
				TreeNode temp = subNodeList.get(i);
				if (temp != null && !curr.getTraverSubNodes().contains(temp)) {
					// curr.getTraverSubNodes().add(0,temp);
					curr.getTraverSubNodes().add(temp);
					allNodes.add(0, temp);
					flag = false;
					break;
				}
			}
			if (flag == true) {
				allNodes.remove(0);
			}

		}
	}

	public void print(List<TreeNode> allNodes) {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		for (int i = allNodes.size() - 2; i >= 0; i--) {
			// System.out.println("print byte:"+allNodes.get(i).getByteData());
			buffer.put(allNodes.get(i).getByteData());
		}
		buffer.flip();
		String keyword = Charset.forName(charset).decode(buffer).toString();
		System.out.println("keyword:" + keyword);
	}

	public int getSubNodeCount(TreeNode node) {
		int count = 0;
		if (node != null && node.getAllSubNode() != null) {
			for (int i = 0; i < node.getAllSubNode().size(); i++) {
				TreeNode temp = node.getAllSubNode().get(i);
				if (temp != null) {
					count++;
				}
			}
		}
		return count;
	}

	/**
	 * 替换字符校验 默认及非法字符为*
	 * 
	 * @param repChar
	 * @return
	 * @throws Exception
	 */
	private String repCharCheck(String repChar) throws Exception {
		if (StringUtil.isEmpty(repChar)) {
			repChar = DEFAULT_REP_CHAR;// 默认使用*
		} else {
			if (repChar.getBytes(charset).length > 1) {
				repChar = DEFAULT_REP_CHAR;// 默认使用*
			}
		}
		return repChar;
	}

}
