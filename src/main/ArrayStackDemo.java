package main;

import java.util.Scanner;

public class ArrayStackDemo {
	public static void main(String[] args) {
		ArrayStack arrayStack = new ArrayStack(4);
		char key = ' ';
		Scanner scanner = new Scanner(System.in);
		boolean loop = true;
		while (loop) {
			System.out.println("s:显示栈");
			System.out.println("e:退出程序");
			System.out.println("a:压栈");
			System.out.println("g:弹栈");
			key = scanner.next().charAt(0);
			switch (key) {
			case 's':
				try {
					arrayStack.printStack();
				} catch (Exception e) {
					System.out.println("栈空");
				}
				break;
			case 'e':
				scanner.close();
				loop = false;
				break;			
			case 'a':
				System.out.println("输入一个数");
				int value = scanner.nextInt();
				arrayStack.push(value);
				break;		
			case 'g':
				try {
					arrayStack.pop();
				} catch (Exception e) {
					System.out.println("栈空");
				}
				break;			
			default:
				break;
			}
		}
		System.out.println("程序已退出");
	}
}

class ArrayStack{
	private int maxSize;
	private int [] stack;
	private int top = -1;
	
	public ArrayStack(int maxSize) {
		this.maxSize = maxSize;
		stack = new int[this.maxSize];
	}
	public boolean isFull() {
		return top == maxSize - 1;
	}
	public boolean isEmpty() {
		return top == -1;
	}
	public void push(int value) {
		if (isFull()) {
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("栈空");
		}
		return stack[top--];
	}
	public void printStack() {
		if (isEmpty()) {
			throw new RuntimeException("栈空");
		}
		for (int i = top; i >= 0 ; i--) {
			System.out.printf(""+stack[i]+"-");
		}
		System.out.println();
	}
	//返回运算符优先级
	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		}else if (oper == '+' || oper == '-') {
			return 0;
		}else {
			return -1;
		}
	}
	//判断是不是一个运算符
	public boolean isOper(char val) {
		return val == '+' || val == '-' || val == '*' || val == '/';
	}
	public int cal(int num1, int num2, int oper) {
		int res = 0;
		switch (oper) {
		case '+':
			res = num1 + num2;
			break;
		case '-':
			res = num2 - num1;
			break;
		case '*':
			res = num2 * num1;
			break;
		case '/':
			res = num2 / num1;
			break;
		default:
			break;
		}
		return res;
	}
}