package com.sn.utils.dfa;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private static final int NODE_LEN = 256;

	// 关键词是否结束 结束true 没有结束false
	private boolean end = false;

	// 每个节点处于其父节点的下标位置
	private int index = -1;

	// 持有的孩子节点个数
	private int childCount = 0;

	// 子节点列表
	private List<TreeNode> subNodes = new ArrayList<TreeNode>(NODE_LEN);

	// 遍历过的子节点列表
	private List<TreeNode> traverSubNodes = new ArrayList<TreeNode>(NODE_LEN);

	// 保存该节点的byte值
	private byte byteData;

	public TreeNode() {
		for (int i = 0; i < NODE_LEN; i++) {
			subNodes.add(i, null);
		}
	}

	public int getChildCount() {
		return childCount;
	}

	public void setChildCount(int childCount) {
		this.childCount = childCount;
	}

	/**
	 * 向指定位置添加节点树
	 */
	public void setSubNode(int index, TreeNode node) {
		subNodes.set(index, node);
	}

	public TreeNode getSubNode(int index) {
		return subNodes.get(index);
	}

	public List<TreeNode> getAllSubNode() {
		return subNodes;
	}

	public boolean isKeywordEnd() {
		return end;
	}

	public void setKeywordEnd(boolean end) {
		this.end = end;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public List<TreeNode> getTraverSubNodes() {
		return traverSubNodes;
	}

	public void setTraverSubNodes(List<TreeNode> traverSubNodes) {
		this.traverSubNodes = traverSubNodes;
	}

	public byte getByteData() {
		return byteData;
	}

	public void setByteData(byte byteData) {
		this.byteData = byteData;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof TreeNode))
			return false;
		TreeNode node = (TreeNode) obj;
		if (this.index != node.index) {
			return false;
		}
		return true;
	}
}
