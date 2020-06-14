package main;

public class ���������� {
	public static void main(String[] args) {
		HeroNode root = new HeroNode(1, "�ν�");
		HeroNode node2 = new HeroNode(2, "����");
		HeroNode node3 = new HeroNode(3, "¬����");
		HeroNode node4 = new HeroNode(4, "�ֳ�");
		HeroNode node5 = new HeroNode(5, "³����");
		HeroNode node6 = new HeroNode(6, "����");
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
	 * ����Ҫ��ָ��ǰ�ڵ��ǰ���ڵ��ָ��
	 * �ڵݹ����������ʱ��pre����ָ��ǰһ���ڵ�
	 */
	private HeroNode pre = null;
	
	public void setRoot(HeroNode root) {
		this.root = root;	
	}
	
	/**
	 * �Զ��������������������ķ���
	 * @param node ����ǰ��Ҫ�������Ľڵ�
	 */
	public void threadedNodes(HeroNode node) {
		if (node==null) {
			return;
		}
		//��������������
		threadedNodes(node.left);
		//��������ǰ�ڵ�
		//�ȴ���ǰ�ڵ��ǰ���ڵ�
		if (node.left == null) {
			//ָ��ǰ���ڵ�
			node.left = pre;
			//�޸ĵ�ǰ�ڵ����ָ�������
			node.leftType = 1;
		}
		//�����̽ڵ�
		if (pre!=null && pre.right == null) {
			pre.right = node;
			pre.rightType = 1;
		}
		pre = node;
		//������������
		threadedNodes(node.right);
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
	//���leftType==0;��ʾָ���ʱ�����������Ϊ1��ָ��ǰ��/��̽ڵ�;
	
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
	public HeroNode preSearch(int No) {
		//�Ƚϵ�ǰ�ڵ��ǲ���
		if (this.No == No) {
			return this;
		}
		HeroNode resNode = null;
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
	public HeroNode infixSearch(int No) {
		
		HeroNode resNode = null;
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
	public HeroNode postSearch(int No) {
		
		HeroNode resNode = null;
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