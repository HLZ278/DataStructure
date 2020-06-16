package main;


/**ƽ������������ڶ����������϶��ƽ�⹦��
 * 
 * rightHeight()-leftHeight()>1������һ��avl���ˣ���Ҫ����
	��ô����һ��������ת.
	1.����һ���µĽڵ㣬ֵ���ڵ�ǰ���ڵ��ֵ
	//���½ڵ�������������˵�ǰ�ڵ��������
	2. newAVLNode.left = left
	//���½ڵ������������Ϊ��ǰ�ڵ����������������
	3. newAVLNode.right =right.left;
	//�ѵ�ǰ�ڵ��ֵ��Ϊ���ӽڵ��ֵ
	4.value=right.value;
	//�ѵ�ǰ�ڵ�����������ó���������������
	5. right=right.right;
	//�ѵ�ǰ�ڵ������������Ϊ�½ڵ�
	6. left=newLeft;
����ͬ��

˫������
��Щ�������һ�����������������޷�����AVL����
1. �����Ҫ��ǰ�ڵ�����ʱ�������Ȱѵ�ǰ�ڵ����ڵ��������
�ж�������������������������߶ȴ��������������߶�


2. �����Ҫ��ǰ�ڵ�����ʱ�������Ȱѵ�ǰ�ڵ���ҽڵ��������
�ж�������������������������߶ȴ��������������߶�
 * @author 96916
 *
 */
public class ƽ������� {
	public static void main(String[] args) {
		int [] arr = {10,7,4,8,9,11};
		AVLTree avlTree = new AVLTree();
		for (int i = 0; i < arr.length; i++) {
			avlTree.add(new AVLNode(arr[i]));
		}
		System.out.println("�������");
		avlTree.infixOrder();
		System.out.println("��û��ƽ�⴦��ǰ~~");
		System.out.println("���ĸ߶�="+ avlTree.root.height()); //4
		System.out.println("�����������߶�=" + avlTree.root.leftHeight()); // 1
		System.out.println("�����������߶�="+ avlTree.root.rightHeight()); // 3

	}
}
class AVLTree{
	public AVLNode root;
	
	public void add(AVLNode node) {
		if (root == null) {
			 root = node;
		}else {
			root.add(node);
		}
	}
	public void infixOrder() {
		if (this.root!=null) {
			this.root.infixOrder();
		}else {
			System.out.println("������Ϊ��");
		}
	}
	public AVLNode search(int value) {
		if (root == null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	public AVLNode searchParent(int value) {
		if (root == null) {
			return null;
		}else {
			return root.searchParent(value);
		}
	}
	//��д����:
	//1.���ص���nodeΪ�����Ķ�������������С����ֵ
	//2.ɾ��node Ϊ�����Ķ�������������С���
	/**
	* @paramnode����Ľ��(���������������ĸ����)
	* @return���ص���nodeΪ�����Ķ�������������С����ֵ
	*/
	public int delRightTreeMin(AVLNode node) {
		AVLNode target= node;
		while (target.left != null) {
			target = target.left;
		}
		delNode(target.value);
		return target.value;
	}
	public void delNode(int value) {
		if (root == null) {
			return;
		}else {
			AVLNode target = search(value);
			if (target == null) {
				return;
			}
			//ɾ�����Ǹ��ڵ�,����������ֻ��һ���ڵ�root�ڵ�
			if (root.left == null && root.right == null) {
				root = null;
				return;
			}
			AVLNode parent = searchParent(value);
			if (target.left == null && target.right == null) {
				//�ж�targetNode�Ǹ��ڵ�����ӽڵ㻹����
				if (parent.left == target) {
					parent.left = null;
				}else if (parent.right == target) {
					parent.right = null;
				}
			}else if(target.left!=null&&target.right!=null){
				int min = delRightTreeMin(target.right);
				target.value = min;
			}else {//ɾ��ֻ��һ�������ڵ�
				if (target.left!=null) {
					//���target��parent�����ӽڵ�
					if (parent.left.value == value) {
						parent.left = target.left;
					}else {
						parent.right = target.left;
					}
				}else {
					//���target��parent�����ӽڵ�
					if (parent.left.value == value) {
						parent.left = target.right;
					}else {
						parent.right = target.right;
					}
				}
			}
		}
	}
}

class AVLNode {
	int value;
	AVLNode left;
	AVLNode right;
	public AVLNode(int value) {
		super();
		this.value = value;
	}
	
	public int leftHeight() {
		if (left == null) {
			return 0;
		}
		return left.height();
	}
	public int rightHeight() {
		if (right == null) {
			return 0;
		}
		return right.height();
	}
	
	//�õ����ĸ߶�
	public int height() {
		return Math.max(left==null?0:left.height(), right==null?0:right.height())+1;
	}
	
	
	//����
	public void leftRotate() {
		//1.�����½ڵ㣬�Ե�ǰ���ڵ�Ϊֵ
		AVLNode newNode = new AVLNode(value);
		//���µĽڵ�����������óɵ�ǰ�ڵ��������
		newNode.left = left;
		//���µĽڵ�����������óɵ�ǰ�ڵ�ڵ����������������
		newNode.right = right.left;
		//�ѵ�ǰ�ڵ��ֵ�滻����������ֵ
		value = right.value;
		//�ѵ�ǰ�ڵ�����������óɵĵ�ǰ�ڵ����������������
		right = right.right;
		//�ѵ�ǰ�ڵ�����������ó��´��������Ľڵ�
		left = newNode;
	}
	private void rightRotate() {
		AVLNode newNode = new AVLNode(value);
		newNode.right = right;
		newNode.left = left.right;
		value = left.value;
		left = left.left;
		right = newNode;
	}
	
	public void infixOrder() {
		if (this.left!=null) {
			this.left.infixOrder();
		}
		System.out.println(this);
		if (this.right!=null) {
			this.right.infixOrder();
		}
	}
	@Override
	public String toString() {
		return "AVLNode [value=" + value + "]";
	}
	/**
	 * �ݹ���ʽ��ӽڵ�
	 * @param node
	 */
	public void add(AVLNode node) {
		if (node == null) {
			return;
		}
		if (node.value < this.value) {
			if (this.left == null) {
				this.left = node;
			}else {
				this.left.add(node);
			}
		}else {
			if (this.right == null) {
				this.right = node;
			}else {
				this.right.add(node);
			}
		}
		
		//���������ĸ߶�-�������߶�>1ʱ��������
		if (rightHeight() - leftHeight() > 1) {
			//�ж��Ƿ�Ҫ˫��
			//������������������߶ȴ��������������߶�
			if (right!=null && right.leftHeight() > right.rightHeight()) {
				right.leftRotate();
				leftRotate();
			}else {
				//ֱ�ӽ�������
				leftRotate();
			}
			//��ֹ�����ж�ֱ��return
			return;
		}
		//���������ĸ߶�-�������߶�>1ʱ��������
		if (leftHeight() - rightHeight() > 1) {
			//������������������߶ȴ��������������߶�
			if (left!=null && left.rightHeight() >left.leftHeight()) {
				left.leftRotate();
				rightRotate();
			}else {
				rightRotate();
			}
		}
	}
	/**
	 * ����ɾ���ڵ�
	 * @param value ɾ���ڵ��ֵ
	 * @return ɾ���ڵ㣬�Ҳ�������null
	 */
	public AVLNode search(int value) {
		if (value == this.value) {
			return this;
		}else if (value < this.value) {
			if (this.left == null) {
				return null;
			}
			return this.left.search(value);
		}else {
			if (this.right == null) {
				return null;
			}else {
				return this.right.search(value);
			}
		}
	}
	/**
	 * ����Ҫɾ���ڵ�ĸ��ڵ�
	 * @param value
	 * @return 
	 */
	public AVLNode searchParent(int value) {
		if ((this.left != null && this.left.value == value)
				||(this.right != null&&this.right.value == value)) {
			return this;
		}else {
			if (this.left != null && this.value > value) {
				return this.left.searchParent(value);
			}else if (value >= this.value && this.right != null) {
				return this.right.searchParent(value);
			}else {
				return null;
			}
		}
	}
}