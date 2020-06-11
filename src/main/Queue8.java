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
	 * ���ûʺ�
	 * @param n 
	 */
	private void check(int n) {
		if (n==max) {
			printQueueMap();
			return;
		}
		//���η���ʺ�
		for (int i = 0; i < max; i++) {
			//�Ȱѵ�ǰ�ʺ�n���ŵ����е�һ��
			array[n] = i;
			//�ж��Ƿ��ͻ
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



	//�ж��Ƿ��ͻ
	/**
	 * @param n ��ʾ��n���ʺ�
	 * @return
	 */
	private boolean judge(int n) {
		for (int i = 0; i < n; i++) {
			/**
			 * array[i]==array[n] �ж��Ƿ���ͬһ��
			 * Math.abs(n-i) == Math.abs(array[n]-array[i]���õ���ֱ���������ж��Ƿ���ͬһ��б����
			 */
			if (array[i]==array[n] || Math.abs(n-i) == Math.abs(array[n]-array[i])) {
				return false;
			}
		}
		return true;
	}
}
