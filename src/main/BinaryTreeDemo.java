package main;

import javax.swing.RootPaneContainer;

public class BinaryTreeDemo {
	public static void main(String[] args) {
		//����������
		BinaryTree binaryTree = new BinaryTree();
		Node root = new Node(1, "�ν�");
		Node node2 = new Node(2, "����");
		Node node3 = new Node(3, "¬����");
		Node node4 = new Node(4, "�ֳ�");
		Node node5 = new Node(5, "³����");
		Node node6 = new Node(6, "����");
		root.left = node2;
		root.right = node3;
		node2.left = node4;
		node2.right = node5;
		node3.left = node6;
		binaryTree.setRoot(root);
		binaryTree.delNode(2);
		binaryTree.postOrder();
	}
}

class BinaryTree{
	private Node root;
	
	public void setRoot(Node root) {
		this.root = root;	
	}
	
	public void preOrder() {
		if (this.root!=null) {
			this.root.preOrder();
		}else {
			System.out.println("������Ϊ��");
		}
	}
	public void infixOrder() {
		if (this.root!=null) {
			this.root.infixOrder();
		}else {
			System.out.println("������Ϊ��");
		}
	}
	public void postOrder() {
		if (this.root!=null) {
			this.root.postOrder();
		}else {
			System.out.println("������Ϊ��");
		}
	}
	public Node preSearch(int No) {
		if (this.root!=null) {
			return this.root.preSearch(No);
		}else {
			return null;
		}
	}
	public Node infixSearch(int No) {
		if (this.root!=null) {
			return this.root.infixSearch(No);
		}else {
			return null;
		}
	}
	public Node postSearch(int No) {
		if (this.root!=null) {
			return this.root.postSearch(No);
		}else {
			return null;
		}
	}
	public void delNode(int No) {
		if (this.root!=null&&this.root.getNo()!=No) {
			this.root.delNode(No);
		}else {
			root = null;
		}
	}
}

class Node {
	private int No;
	private String name;
	public Node left;
	public Node right;
	public Node(int No, String name) {
		this.No = No;
		this.name = name;
	}
	public int getNo() {
		return No;
	}
	public void setNo(int no) {
		No = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Node [No="+No+", name="+name+"]";
	}
	//ǰ���������
	public void preOrder() {
		System.out.println(this);
		if (this.left!=null) {
			this.left.preOrder();
		}
		if (this.right!=null) {
			this.right.preOrder();
		}
	}
	
	//�������
	public void infixOrder() {
		if (this.left!=null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right!=null) {
			this.right.infixOrder();
		}
	}
	
	//��������
	
	public void postOrder(){
		if (this.left!=null) {
			this.left.postOrder();
		}
		if (this.right!=null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}
	
	//ǰ�����
	public Node preSearch(int No) {
		//�Ƚϵ�ǰ�ڵ��ǲ���
		if (this.No == No) {
			return this;
		}
		Node resNode = null;
		if (this.left!=null) {
			resNode = this.left.preSearch(No);
		}
		//�ҵ�ֱ�ӷ��أ����ٽ���������
		if (resNode!=null) {
			return resNode;
		}
		if (this.right!=null) {
			resNode = this.right.preSearch(No);
		}
		//��Ϊ��������������ֻ��ֱ�ӷ��أ���������û�ҵ�����Ϊ����û����������
		return resNode;
		
	}
	public Node infixSearch(int No) {
		
		Node resNode = null;
		if (this.left!=null) {
			resNode = this.left.infixSearch(No);
		}
		//�ҵ�ֱ�ӷ��أ����ٽ���������
		if (resNode!=null) {
			return resNode;
		}
		
		
		if (this.No == No) {
			return this;
		}
		
		
		if (this.right!=null) {
			resNode = this.right.infixSearch(No);
		}
		//��Ϊ��������������ֻ��ֱ�ӷ��أ���������û�ҵ�����Ϊ����û����������
		return resNode;
		
	}
	public Node postSearch(int No) {
		
		Node resNode = null;
		if (this.left!=null) {
			resNode = this.left.postSearch(No);
		}
		//�ҵ�ֱ�ӷ��أ����ٽ���������
		if (resNode!=null) {
			return resNode;
		}
		if (this.right!=null) {
			resNode = this.right.postSearch(No);
		}
		//�ҵ�ֱ�ӷ��أ����ٽ���������
		if (resNode!=null) {
			return resNode;
		}
		if (this.No == No) {
			return this;
		}
		return resNode;
	}
	//ɾ���ڵ㣬����к����ڵ��൱��ɾ����������
	public void delNode(int No) {
		if (this.left!=null&&this.left.No == No) {
			this.left = null;
			return;
		}
		if (this.right!=null&&this.right.No == No) {
			this.right = null;
			return;
		}
		if (this.left!=null) {
			this.left.delNode(No);
		}
		if (this.right!=null) {
			this.right.delNode(No);
		}
	}
}