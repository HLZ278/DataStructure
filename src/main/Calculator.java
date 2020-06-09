package main;

public class Calculator {
	public static void main(String[] args) {
		String expressionString = "30+20*60-20";
		ArrayStack2 numStack = new ArrayStack2(10);
		ArrayStack2 operStack = new ArrayStack2(10);
		int index = 0;
		int num1 = 0;
		int num2 = 0;
		int oper = 0;
		int res = 0;
		char ch = ' ';//��ÿ��ɨ��õ���char���浽ch
		//ɨ����ջ
		String keepNumString = "";//����ƴ�Ӷ�λ��
		while(true) {
			//���εĵ��ַ�
			ch = expressionString.substring(index, index+1).charAt(0);
			if (operStack.isOper(ch)) {
				if (!operStack.isEmpty()) {
					if (operStack.priority(ch)<=operStack.priority((operStack.peek()))) {
						num1 = numStack.pop();
						num2 = numStack.pop();
						oper = operStack.pop();
						res = numStack.cal(num1, num2, oper);
						numStack.push(res);
						operStack.push(ch);
					}else {
						//���ȼ�����ջ��Ԫ��
						operStack.push(ch);
					}
				}else {
					//ջ��ֱ����ջ
					operStack.push(ch);
				}
			}else {
				//�������������ջ
				//numStack.push(ch-48);  //��ȥ48��ԭ����1��ASCII�����ʽ49
				keepNumString += ch; 
				if (index==expressionString.length()-1) {
					numStack.push(Integer.parseInt(keepNumString));
				}else {
					if (operStack.isOper(expressionString.substring(index+1, index+2).charAt(0))) {
						numStack.push(Integer.parseInt(keepNumString));
						keepNumString = "";
					}
				}
				
			}
			index++;
			if (index>=expressionString.length()) {
				break;
			}
		}
		while (true) {
			//�������ջΪ�գ���������������ջ��ֻ��һ�����֡��Ǿ��ǽ����
			if (operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);
		}
		System.err.println("������:"+ numStack.pop());
	}
}


class ArrayStack2{
	private int maxSize;
	private int [] stack;
	private int top = -1;
	
	public ArrayStack2(int maxSize) {
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
			System.out.println("ջ��");
			return;
		}
		top++;
		stack[top] = value;
	}
	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("ջ��");
		}
		return stack[top];
	}
	public int pop() {
		if (isEmpty()) {
			throw new RuntimeException("ջ��");
		}
		return stack[top--];
	}
	public void printStack() {
		if (isEmpty()) {
			throw new RuntimeException("ջ��");
		}
		for (int i = top; i >= 0 ; i--) {
			System.out.printf(""+stack[i]+"-");
		}
		System.out.println();
	}
	//������������ȼ�
	public int priority(int oper) {
		if (oper == '*' || oper == '/') {
			return 1;
		}else if (oper == '+' || oper == '-') {
			return 0;
		}else {
			return -1;
		}
	}
	//�ж��ǲ���һ�������
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