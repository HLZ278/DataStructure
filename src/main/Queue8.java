package main;

import java.awt.print.Printable;
import java.lang.reflect.Array;

public class Queue8 {
	int max = 8;
	int [] array = new int[max];
	
	
	public static void main(String[] args) {
		Queue8 queue8 = new Queue8();
		queue8.check(0);
	}
	
	
	
	
	/**
	 * 放置皇后
	 * @param n 
	 */
	private void check(int n) {
		if (n==max) {
			printQueueMap();
			return;
		}
		//依次放入皇后
		for (int i = 0; i < max; i++) {
			//先把当前皇后n，放到该行第一列
			array[n] = i;
			//判断是否冲突
			if (judge(n)) {
				check(n + 1);
			}
			
		}
	}
	
	
	
	private void printQueueMap() {
		for (int i : array) {
			System.out.print(i);
		}
		System.out.println();
	}



	//判断是否冲突
	/**
	 * @param n 表示第n个皇后
	 * @return
	 */
	private boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			/**
			 * array[i]==array[n] 判断是否在同一列
			 * Math.abs(n-i) == Math.abs(array[n]-array[i]利用等腰直角三角形判断是否在同一条斜线上
			 */
			if (array[i]==array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])) {
				return false;
			}
		}
		return true;
	}
}
