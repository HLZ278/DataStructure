package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 赫夫曼树 {

	public static void main(String[] args) {
		HashMap<Integer, Character> hashMap = new HashMap<Integer, Character>();
		hashMap.put(29, 'I');
		hashMap.put(13, 'L');
		hashMap.put(8, 'O');
		hashMap.put(7, 'V');
		hashMap.put(6, 'E');
		hashMap.put(3, 'U');
		hashMap.put(1, '!');
		HuffmanNode huffmanTree = createHuffmanTree(hashMap);
		huffmanTree.preOrder();
		Map<Character, String> codes = getCodes(huffmanTree);
		System.out.println("哈夫曼编码表：" + codes);
	}

	public static HuffmanNode createHuffmanTree(Map<Integer, Character> map) {
		// 遍历数组将每个元素构建成HuffmanNode放入ArrayList中
		List<HuffmanNode> HuffmanNodes = new ArrayList<HuffmanNode>();
		for (Map.Entry<Integer, Character> entry : map.entrySet()) {
			HuffmanNodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
		}
		// 开始构建
		while (HuffmanNodes.size() > 1) {
			Collections.sort(HuffmanNodes);

			HuffmanNode left = HuffmanNodes.get(0);
			HuffmanNode right = HuffmanNodes.get(1);
			// 构建新二叉树
			HuffmanNode parent = new HuffmanNode(left.weight + right.weight, '\0');
			parent.left = left;
			parent.right = right;

			HuffmanNodes.remove(left);
			HuffmanNodes.remove(right);

			HuffmanNodes.add(parent);
		}

		return HuffmanNodes.get(0);

	}

	// 保存路径即编码
	static StringBuilder stringBuilder = new StringBuilder();

	// 为了调用方便，我们重载getCodes
	private static Map<Character, String> getCodes(HuffmanNode root) {
		if (root == null) {
			return null;
		}
		// 处理root的左子树
		getCodes(root.left, "0", stringBuilder);
		// 处理root的右子树
		getCodes(root.right, "1", stringBuilder);
		return huffmancodesMap;
	}

	// 保存编码对应关系
	static Map<Character, String> huffmancodesMap = new HashMap<Character, String>();

	/**
	 * 生成哈夫曼编码表
	 * 
	 * @param node          传入节点
	 * @param code          哈夫曼编码，等同于路径
	 * @param stringBuilder 用于拼接路径
	 */
	public static void getCodes(HuffmanNode node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		stringBuilder2.append(code);
		if (node != null) {
			if (node.data == '\0') {// 必然同时存在左右子节点，因此左右都得递归
				getCodes(node.left, "0", stringBuilder2);
				getCodes(node.right, "1", stringBuilder2);
			} else {// 说明是叶子节点
				huffmancodesMap.put(node.data, stringBuilder2.toString());
			}
		}
	}

}

class HuffmanNode implements Comparable<HuffmanNode> {
	int weight;// 权重
	char data;
	HuffmanNode left;
	HuffmanNode right;

	public HuffmanNode(int value, char data) {
		this.weight = value;
		this.data = data;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "HuffmanNode [weight=" + weight + ",data=" + data + "]";

	}

	@Override
	public int compareTo(HuffmanNode HuffmanNode) {
		// 从小到大排序
		return this.weight - HuffmanNode.weight;
	}

	// 前序遍历
	public void preOrder() {
		System.out.println(this);
		if (this.left != null) {
			this.left.preOrder();
		}
		if (this.right != null) {
			this.right.preOrder();
		}
	}

}