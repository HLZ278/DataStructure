package main;

import java.util.Scanner;

public class ArrayQueueDemo {
	public static void main(String[] args) {
		ArrayQueue arrayQueue = new ArrayQueue(4);
		char key = ' ';
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("s:显示队列");
			System.out.println("e:退出程序");
			System.out.println("a:添加数据");
			System.out.println("g:获取数据");
			key = scanner.next().charAt(0);
			switch (key) {
			case 's':
				try {
					arrayQueue.printQueue();
				} catch (Exception e) {
					System.out.println("队空");
				}
				break;
			case 'e':
				scanner.close();
				loop = false;
				break;			
			case 'a':
				System.out.println("输入一个数");
				int value = scanner.nextInt();
				arrayQueue.addQueue(value);
				break;		
			case 'g':
				try {
					arrayQueue.getQueue();
				} catch (Exception e) {
					System.out.println("队空");
				}
				break;			
			default:
				break;
			}
		}
		System.out.println("程序已退出");
	}
}
class ArrayQueue {
	private int maxSize;
	private int front;
	private int rear;
	private int [] arr;
	
	public ArrayQueue(int maxSize) {
		this.maxSize = maxSize;
		arr = new int[maxSize];
		front = 0;
		rear = 0;
	}
	
	public boolean isFull() {
		return (rear+1)%maxSize == front;
	}
	
	public boolean isEmpty() {
		return rear == front;

	}
	public void addQueue(int n) {
		if (isFull()) {
			System.out.println("队列已满，无法添加数据");
			return;
		}
		arr[rear] = n; 
		rear = ++rear%maxSize;
	}
	//出队
	public void getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列空，无法获取数据");
		}
		System.out.println("出队："+arr[front%maxSize]);
		arr[front] = 0;
		front = ++front%maxSize;
		
	}
	public void printQueue() {
		if (isEmpty()) {
			throw new RuntimeException("队列空，无法打印数据");
		}
		//(rear+maxSize-front)%maxSize计算有效个数
		int num = (rear+maxSize-front)%maxSize;
		for (int i = front; i < front+num; i++) {
			System.out.print(arr[i%maxSize]);
			
		}
		System.out.println();
	}
}