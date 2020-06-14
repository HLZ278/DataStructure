package main;

public class ArrBinaryTreeDemo {
	public static void main(String[] args) {
		int[] arr = {1,2,3,4,5,6,7};
		ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
		arrBinaryTree.preOrder(0);
	}
	
	
}
class ArrBinaryTree{
	private int[] arr;
	public ArrBinaryTree(int[] arr) {
		this.arr = arr;
	}
	/**
	 * @param index
	 * 顺序存储二叉树的前序遍历
	 * index数组下标
	 */
	public void preOrder(int index) {
		//如果数组为空或者arr.length = 0
		if(arr == null||arr.length == 0) {
			System.out.println("数组为空");
		}
		System.out.println(arr[index]);
		if ((index*2+1)<arr.length) {
			preOrder(2*index+1);
		}
		if ((index*2+2)<arr.length) {
			preOrder(2*index+2);
		}
	}
	public void preOrder() {
		this.preOrder(0);
	}
}
