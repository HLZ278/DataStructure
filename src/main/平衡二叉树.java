package main;


/**平衡二叉树就是在二叉排序数上多个平衡功能
 * 
 * rightHeight()-leftHeight()>1不再是一颗avl树了，需要左旋
	怎么处理一进行左旋转.
	1.创建一个新的节点，值等于当前根节点的值
	//把新节点的左子树设置了当前节点的左子树
	2. newAVLNode.left = left
	//把新节点的右子树设置为当前节点的右子树的左子树
	3. newAVLNode.right =right.left;
	//把当前节点的值换为右子节点的值
	4.value=right.value;
	//把当前节点的右子树设置成右子树的右子树
	5. right=right.right;
	//把当前节点的左子树设置为新节点
	6. left=newLeft;
右旋同理

双旋操作
有些情况进行一次左旋或右旋是是无法满足AVL树的
1. 如果需要当前节点右旋时，则需先把当前节点的左节点进行右旋
判断条件：如果左子树的右子树高度大于他的左子树高度


2. 如果需要当前节点左旋时，则需先把当前节点的右节点进行左旋
判断条件：如果右子树的左子树高度大于他的右子树高度
 * @author 96916
 *
 */
public class 平衡二叉树 {
	public static void main(String[] args) {
		int [] arr = {10,7,4,8,9,11};
		AVLTree avlTree = new AVLTree();
		for (int i = 0; i < arr.length; i++) {
			avlTree.add(new AVLNode(arr[i]));
		}
		System.out.println("中序遍历");
		avlTree.infixOrder();
		System.out.println("在没有平衡处理前~~");
		System.out.println("树的高度="+ avlTree.root.height()); //4
		System.out.println("树的左子树高度=" + avlTree.root.leftHeight()); // 1
		System.out.println("树的右子树高度="+ avlTree.root.rightHeight()); // 3

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
			System.out.println("二叉树为空");
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
	//编写方法:
	//1.返回的以node为根结点的二叉排序树的最小结点的值
	//2.删除node 为根结点的二叉排序树的最小结点
	/**
	* @paramnode传入的结点(当做二叉排序树的根结点)
	* @return返回的以node为根结点的二叉排序树的最小结点的值
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
			//删除的是根节点,并且整棵树只有一个节点root节点
			if (root.left == null && root.right == null) {
				root = null;
				return;
			}
			AVLNode parent = searchParent(value);
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
	
	//得到树的高度
	public int height() {
		return Math.max(left==null?0:left.height(), right==null?0:right.height())+1;
	}
	
	
	//左旋
	public void leftRotate() {
		//1.创建新节点，以当前根节点为值
		AVLNode newNode = new AVLNode(value);
		//把新的节点的左子树设置成当前节点的左子树
		newNode.left = left;
		//把新的节点的右子树设置成当前节点节点的右子树的左子树
		newNode.right = right.left;
		//把当前节点的值替换成右子树的值
		value = right.value;
		//把当前节点的右子树设置成的当前节点的右子树的右子树
		right = right.right;
		//把当前节点的左子树设置成新创建出来的节点
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
	 * 递归形式添加节点
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
		
		//当右子树的高度-左子树高度>1时进行左旋
		if (rightHeight() - leftHeight() > 1) {
			//判断是否要双旋
			//如果右子树的左子树高度大于他的右子树高度
			if (right!=null && right.leftHeight() > right.rightHeight()) {
				right.leftRotate();
				leftRotate();
			}else {
				//直接进行左旋
				leftRotate();
			}
			//防止下面判断直接return
			return;
		}
		//当左子树的高度-右子树高度>1时进行右旋
		if (leftHeight() - rightHeight() > 1) {
			//如果左子树的右子树高度大于他的左子树高度
			if (left!=null && left.rightHeight() >left.leftHeight()) {
				left.leftRotate();
				rightRotate();
			}else {
				rightRotate();
			}
		}
	}
	/**
	 * 查找删除节点
	 * @param value 删除节点的值
	 * @return 删除节点，找不到返回null
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
	 * 查找要删除节点的父节点
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