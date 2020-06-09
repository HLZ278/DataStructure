package main;

import java.util.Scanner;

public class ArrayQueueDemo {
	public static void main(String[] args) {
		ArrayQueue arrayQueue = new ArrayQueue(4);
		char key = ' ';
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("s:��ʾ����");
			System.out.println("e:�˳�����");
			System.out.println("a:�������");
			System.out.println("g:��ȡ����");
			key = scanner.next().charAt(0);
			switch (key) {
			case 's':
				try {
					arrayQueue.printQueue();
				} catch (Exception e) {
					System.out.println("�ӿ�");
				}
				break;
			case 'e':
				scanner.close();
				loop = false;
				break;			
			case 'a':
				System.out.println("����һ����");
				int value = scanner.nextInt();
				arrayQueue.addQueue(value);
				break;		
			case 'g':
				try {
					arrayQueue.getQueue();
				} catch (Exception e) {
					System.out.println("�ӿ�");
				}
				break;			
			default:
				break;
			}
		}
		System.out.println("�������˳�");
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
			System.out.println("�����������޷��������");
			return;
		}
		arr[rear] = n; 
		rear = ++rear%maxSize;
	}
	//����
	public void getQueue() {
		if (isEmpty()) {
			throw new RuntimeException("���пգ��޷���ȡ����");
		}
		System.out.println("���ӣ�"+arr[front%maxSize]);
		arr[front] = 0;
		front = ++front%maxSize;
		
	}
	public void printQueue() {
		if (isEmpty()) {
			throw new RuntimeException("���пգ��޷���ӡ����");
		}
		//(rear+maxSize-front)%maxSize������Ч����
		int num = (rear+maxSize-front)%maxSize;
		for (int i = front; i < front+num; i++) {
			System.out.print(arr[i%maxSize]);
			
		}
		System.out.println();
	}
}