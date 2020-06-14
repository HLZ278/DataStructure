package main;

public class 线索二叉树 {
	public static void main(String[] args) {
		HeroNode root = new HeroNode(1, "宋江");
		HeroNode node2 = new HeroNode(2, "吴用");
		HeroNode node3 = new HeroNode(3, "卢俊义");
		HeroNode node4 = new HeroNode(4, "林冲");
		HeroNode node5 = new HeroNode(5, "鲁智深");
		HeroNode node6 = new HeroNode(6, "李逵");
		root.left = node2;
		root.right = node3;
		node2.left = node4;
		node2.right = node5; 
		node3.left = node6; 
		ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
		threadedBinaryTree.setRoot(root);
		threadedBinaryTree.threadedNodes(root);//   425163
		HeroNode left = node5.left;
		System.out.println(left);
	}
}
class ThreadedBinaryTree{
	private HeroNode root;
	
	/**
	 * 创建要给指向当前节点的前驱节点的指针
	 * 在递归进行线索化时，pre总是指向前一个节点
	 */
	private HeroNode pre = null;
	
	public void setRoot(HeroNode root) {
		this.root = root;	
	}
	
	/**
	 * 对二叉树进行中序线索化的方法
	 * @param node ：当前需要线索化的节点
	 */
	public void threadedNodes(HeroNode node) {
		if (node==null) {
			return;
		}
		//先线索化左子树
		threadedNodes(node.left);
		//线索化当前节点
		//先处理当前节点的前驱节点
		if (node.left == null) {
			//指向前驱节点
			node.left = pre;
			//修改当前节点的左指针的类型
			node.leftType = 1;
		}
		//处理后继节点
		if (pre!=null && pre.right == null) {
			pre.right = node;
			pre.rightType = 1;
		}
		pre = node;
		//线索化右子树
		threadedNodes(node.right);
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
	public HeroNode preSearch(int No) {
		if (this.root!=null) {
			return this.root.preSearch(No);
		}else {
			return null;
		}
	}
	public HeroNode infixSearch(int No) {
		if (this.root!=null) {
			return this.root.infixSearch(No);
		}else {
			return null;
		}
	}
	public HeroNode postSearch(int No) {
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
	
	public void threadedList() {
		HeroNode node = root;
		while (node != null) {
			while (node.leftType == 0) {
				node = node.left;
			}
			System.out.println(node);
			while (node.rightType == 1) {
				node = node.right;
				System.out.println(node);
			}
			node = node.right;
			
			
		}
	}
}

class HeroNode {
	private int No;
	private String name;
	public HeroNode left;
	public HeroNode right;
	//如果leftType==0;表示指向的时左子树，如果为1则指向前驱/后继节点;
	
	public int leftType;
	public int rightType;
	
	public HeroNode(int No, String name) {
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
	public HeroNode preSearch(int No) {
		//比较当前节点是不是
		if (this.No == No) {
			return this;
		}
		HeroNode resNode = null;
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
	public HeroNode infixSearch(int No) {
		
		HeroNode resNode = null;
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
	public HeroNode postSearch(int No) {
		
		HeroNode resNode = null;
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