package main;

import java.util.PrimitiveIterator.OfDouble;
import java.util.prefs.BackingStoreException;

/**
 * ��ӽڵ㻹�Ǻ����׵ģ�
 * ��Ҫ������ɾ���ڵ�
 * ������������ɾ������Ƚϸ��ӣ����������������Ҫ����
 * ɾ��Ҷ�ӽڵ�
 * ɾ��ֻ��-�������Ľڵ�
 * ɾ�������������Ľڵ�
 * ��һ�����:
 * ɾ��Ҷ�ӽڵ�
 * (1) ������ȥ�ҵ�Ҫɾ���Ľ��targetNode
 * (2) �ҵ�targetNode �ĸ����parent
 * (3) ȷ��targetNode ��parent�����ӽ�㻹�����ӽ��
 * (4) ����ǰ����������Ӧɾ��
 * ���ӽ��parent.left = null
 * ���ӽ��parent.ight= null;
 * �ڶ������:ɾ��ֻ��һ�������Ľڵ����1
 * (1) ������ȥ�ҵ�Ҫɾ���Ľ��targetNode
 * (2) �ҵ�targetNode �ĸ����parent
 * (3)ȷ��targetNode���ӽ�������ӽ�㻹�����ӽ��
 * (4) targetNode��parent �����ӽ�㻹�����ӽ��
 * (5)���targetNode�����ӽ��
 * 5.1���targetNode ��parent �����ӽ��
 * parent.left = targetNode .left;,
 * 5.2 ���targetNode ��parent �����ӽ��
 * parentright = targetNode.left;
 * (6)���targetNode�����ӽ��
 * 6.1 ���targetNode ��parent �����ӽ��
 * parent.left = targetNode .right;
 * 6.2 ���targetNode ��parent �����ӽ��
 * parentright = targetNode .right
 * �����:ɾ���� ���������Ľڵ�
 * (1) ������ȥ�ҵ�Ҫɾ���Ľ��targetNode
 * (2) �ҵ�targetNode �ĸ����parent
 * (3)��targetNode���������ҵ���С�Ľ��
 * (4) ��һ����ʱ����������С����ֵ����temp= 11
 * (5) ɾ������С���
 * (6) targetNode.value = temp
 * 
 * @author 96916
 *	
 */
public class ���������� {
	public static void main(String[] args) {
		int [] arr = {7,3,5,1,2};
		BinarySortTree binarySortTree = new BinarySortTree();
		//ѭ������ӽ�㵽����������
		for(int i = 0; i < arr.length; i++) {
			binarySortTree.add(new BinarySortNode(arr[i]));
		}
		//�����������������
		System.out.println("�����������������");
		binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12
		//����һ��ɾ��Ҷ�ӽ��
		binarySortTree.delNode(7);
		System.out.println("ɾ������");
		binarySortTree.infixOrder();
	}
}
class BinarySortTree{
	private BinarySortNode root;
	
	public void add(BinarySortNode node) {
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
	public BinarySortNode search(int value) {
		if (root == null) {
			return null;
		}else {
			return root.search(value);
		}
	}
	public BinarySortNode searchParent(int value) {
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
	public int delRightTreeMin(BinarySortNode node) {
		BinarySortNode target= node;
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
			BinarySortNode target = search(value);
			if (target == null) {
				return;
			}
			//ɾ�����Ǹ��ڵ�,����������ֻ��һ���ڵ�root�ڵ�
			if (root.left == null && root.right == null) {
				root = null;
				return;
			}
			BinarySortNode parent = searchParent(value);
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
class BinarySortNode {
	int value;
	BinarySortNode left;
	BinarySortNode right;
	public BinarySortNode(int value) {
		super();
		this.value = value;
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
		return "BinarySortNode [value=" + value + "]";
	}
	/**
	 * �ݹ���ʽ��ӽڵ�
	 * @param node
	 */
	public void add(BinarySortNode node) {
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
		
	}
	/**
	 * ����ɾ���ڵ�
	 * @param value ɾ���ڵ��ֵ
	 * @return ɾ���ڵ㣬�Ҳ�������null
	 */
	public BinarySortNode search(int value) {
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
	public BinarySortNode searchParent(int value) {
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