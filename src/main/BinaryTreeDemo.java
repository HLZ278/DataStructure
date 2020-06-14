package main;

import javax.swing.RootPaneContainer;

public class BinaryTreeDemo {
	public static void main(String[] args) {
		//创建二叉树
		BinaryTree binaryTree = new BinaryTree();
		Node root = new Node(1, "宋江");
		Node node2 = new Node(2, "吴用");
		Node node3 = new Node(3, "卢俊义");
		Node node4 = new Node(4, "林冲");
		Node node5 = new Node(5, "鲁智深");
		Node node6 = new Node(6, "李逵");
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
			System.out.println("二叉树为空");
		}
	}
	public void infixOrder() {
		if (this.root!=null) {
			this.root.infixOrder();
		}else {
			System.out.println("二叉树为空");
		}
	}
	public void postOrder() {
		if (this.root!=null) {
			this.root.postOrder();
		}else {
			System.out.println("二叉树为空");
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
	//前序遍历方法
	public void preOrder() {
		System.out.println(this);
		if (this.left!=null) {
			this.left.preOrder();
		}
		if (this.right!=null) {
			this.right.preOrder();
		}
	}
	
	//中序遍历
	public void infixOrder() {
		if (this.left!=null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right!=null) {
			this.right.infixOrder();
		}
	}
	
	//后续遍历
	
	public void postOrder(){
		if (this.left!=null) {
			this.left.postOrder();
		}
		if (this.right!=null) {
			this.right.postOrder();
		}
		System.out.println(this);
	}
	
	//前序查找
	public Node preSearch(int No) {
		//比较当前节点是不是
		if (this.No == No) {
			return this;
		}
		Node resNode = null;
		if (this.left!=null) {
			resNode = this.left.preSearch(No);
		}
		//找到直接返回，不再进行搜索。
		if (resNode!=null) {
			return resNode;
		}
		if (this.right!=null) {
			resNode = this.right.preSearch(No);
		}
		//因为子树最后的搜索，只需直接返回，不关心找没找到，因为后面没其他方法了
		return resNode;
		
	}
	public Node infixSearch(int No) {
		
		Node resNode = null;
		if (this.left!=null) {
			resNode = this.left.infixSearch(No);
		}
		//找到直接返回，不再进行搜索。
		if (resNode!=null) {
			return resNode;
		}
		
		
		if (this.No == No) {
			return this;
		}
		
		
		if (this.right!=null) {
			resNode = this.right.infixSearch(No);
		}
		//因为子树最后的搜索，只需直接返回，不关心找没找到，因为后面没其他方法了
		return resNode;
		
	}
	public Node postSearch(int No) {
		
		Node resNode = null;
		if (this.left!=null) {
			resNode = this.left.postSearch(No);
		}
		//找到直接返回，不再进行搜索。
		if (resNode!=null) {
			return resNode;
		}
		if (this.right!=null) {
			resNode = this.right.postSearch(No);
		}
		//找到直接返回，不再进行搜索。
		if (resNode!=null) {
			return resNode;
		}
		if (this.No == No) {
			return this;
		}
		return resNode;
	}
	//删除节点，如果有后续节点相当于删除整个子树
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