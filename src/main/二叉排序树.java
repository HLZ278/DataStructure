package main;

import java.util.PrimitiveIterator.OfDouble;
import java.util.prefs.BackingStoreException;

/**
 * 添加节点还是很容易的，
 * 主要分析下删除节点
 * 二叉排序树的删除情况比较复杂，有下面三种情况需要考虑
 * 删除叶子节点
 * 删除只有-颗子树的节点
 * 删除有两颗子树的节点
 * 第一种情况:
 * 删除叶子节点
 * (1) 需求先去找到要删除的结点targetNode
 * (2) 找到targetNode 的父结点parent
 * (3) 确定targetNode 是parent的左子结点还是右子结点
 * (4) 根据前面的情况来对应删除
 * 左子结点parent.left = null
 * 右子结点parent.ight= null;
 * 第二种情况:删除只有一颗子树的节点比如1
 * (1) 需求先去找到要删除的结点targetNode
 * (2) 找到targetNode 的父结点parent
 * (3)确定targetNode的子结点是左子结点还是右子结点
 * (4) targetNode是parent 的左子结点还是右子结点
 * (5)如果targetNode有左子结点
 * 5.1如果targetNode 是parent 的左子结点
 * parent.left = targetNode .left;,
 * 5.2 如果targetNode 是parent 的右子结点
 * parentright = targetNode.left;
 * (6)如果targetNode有右子结点
 * 6.1 如果targetNode 是parent 的左子结点
 * parent.left = targetNode .right;
 * 6.2 如果targetNode 是parent 的右子结点
 * parentright = targetNode .right
 * 情况三:删除有 两颗子树的节点
 * (1) 需求先去找到要删除的结点targetNode
 * (2) 找到targetNode 的父结点parent
 * (3)从targetNode的右子树找到最小的结点
 * (4) 用一个临时变量，将最小结点的值保存temp= 11
 * (5) 删除该最小结点
 * (6) targetNode.value = temp
 * 
 * @author 96916
 *	
 */
public class 二叉排序树 {
	public static void main(String[] args) {
		int [] arr = {7,3,5,1,2};
		BinarySortTree binarySortTree = new BinarySortTree();
		//循环的添加结点到二叉排序树
		for(int i = 0; i < arr.length; i++) {
			binarySortTree.add(new BinarySortNode(arr[i]));
		}
		//中序遍历二叉排序树
		System.out.println("中序遍历二叉排序树");
		binarySortTree.infixOrder(); // 1, 3, 5, 7, 9, 10, 12
		//测试一下删除叶子结点
		binarySortTree.delNode(7);
		System.out.println("删除结点后");
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
			System.out.println("二叉树为空");
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
	//编写方法:
	//1.返回的以node为根结点的二叉排序树的最小结点的值
	//2.删除node 为根结点的二叉排序树的最小结点
	/**
	* @paramnode传入的结点(当做二叉排序树的根结点)
	* @return返回的以node为根结点的二叉排序树的最小结点的值
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
			//删除的是根节点,并且整棵树只有一个节点root节点
			if (root.left == null && root.right == null) {
				root = null;
				return;
			}
			BinarySortNode parent = searchParent(value);
			if (target.left == null && target.right == null) {
				//判断targetNode是父节点的左子节点还是右
				if (parent.left == target) {
					parent.left = null;
				}else if (parent.right == target) {
					parent.right = null;
				}
			}else if(target.left!=null&&target.right!=null){
				int min = delRightTreeMin(target.right);
				target.value = min;
			}else {//删除只有一个子树节点
				if (target.left!=null) {
					//如果target是parent的左子节点
					if (parent.left.value == value) {
						parent.left = target.left;
					}else {
						parent.right = target.left;
					}
				}else {
					//如果target是parent的左子节点
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
	 * 递归形式添加节点
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
	 * 查找删除节点
	 * @param value 删除节点的值
	 * @return 删除节点，找不到返回null
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
	 * 查找要删除节点的父节点
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