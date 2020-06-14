package main;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class �շ����� {

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
		System.out.println("�����������" + codes);
	}

	public static HuffmanNode createHuffmanTree(Map<Integer, Character> map) {
		// �������齫ÿ��Ԫ�ع�����HuffmanNode����ArrayList��
		List<HuffmanNode> HuffmanNodes = new ArrayList<HuffmanNode>();
		for (Map.Entry<Integer, Character> entry : map.entrySet()) {
			HuffmanNodes.add(new HuffmanNode(entry.getKey(), entry.getValue()));
		}
		// ��ʼ����
		while (HuffmanNodes.size() > 1) {
			Collections.sort(HuffmanNodes);

			HuffmanNode left = HuffmanNodes.get(0);
			HuffmanNode right = HuffmanNodes.get(1);
			// �����¶�����
			HuffmanNode parent = new HuffmanNode(left.weight + right.weight, '\0');
			parent.left = left;
			parent.right = right;

			HuffmanNodes.remove(left);
			HuffmanNodes.remove(right);

			HuffmanNodes.add(parent);
		}

		return HuffmanNodes.get(0);

	}

	// ����·��������
	static StringBuilder stringBuilder = new StringBuilder();

	// Ϊ�˵��÷��㣬��������getCodes
	private static Map<Character, String> getCodes(HuffmanNode root) {
		if (root == null) {
			return null;
		}
		// ����root��������
		getCodes(root.left, "0", stringBuilder);
		// ����root��������
		getCodes(root.right, "1", stringBuilder);
		return huffmancodesMap;
	}

	// ��������Ӧ��ϵ
	static Map<Character, String> huffmancodesMap = new HashMap<Character, String>();

	/**
	 * ���ɹ����������
	 * 
	 * @param node          ����ڵ�
	 * @param code          ���������룬��ͬ��·��
	 * @param stringBuilder ����ƴ��·��
	 */
	public static void getCodes(HuffmanNode node, String code, StringBuilder stringBuilder) {
		StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
		stringBuilder2.append(code);
		if (node != null) {
			if (node.data == '\0') {// ��Ȼͬʱ���������ӽڵ㣬������Ҷ��õݹ�
				getCodes(node.left, "0", stringBuilder2);
				getCodes(node.right, "1", stringBuilder2);
			} else {// ˵����Ҷ�ӽڵ�
				huffmancodesMap.put(node.data, stringBuilder2.toString());
			}
		}
	}

}

class HuffmanNode implements Comparable<HuffmanNode> {
	int weight;// Ȩ��
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
		// ��С��������
		return this.weight - HuffmanNode.weight;
	}

	// ǰ�����
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