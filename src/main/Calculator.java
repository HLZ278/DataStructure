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
		char ch = ' ';//将每次扫描得到的char保存到ch
		//扫描入栈
		String keepNumString = "";//用于拼接多位数
		while(true) {
			//依次的到字符
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
						//优先级大于栈顶元素
						operStack.push(ch);
					}
				}else {
					//栈空直接入栈
					operStack.push(ch);
				}
			}else {
				//如果是数则入数栈
				//numStack.push(ch-48);  //减去48的原因是1的ASCII码表中式49
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
			//如果符号栈为空，则计算最后结果，数栈中只有一个数字【那就是结果】
			if (operStack.isEmpty()) {
				break;
			}
			num1 = numStack.pop();
			num2 = numStack.pop();
			oper = operStack.pop();
			res = numStack.cal(num1, num2, oper);
			numStack.push(res);
		}
		System.err.println("运算结果:"+ numStack.pop());
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
			System.out.println("栈满");
			return;
		}
		top++;
		stack[top] = value;
	}
	public int peek() {
		if (isEmpty()) {
			throw new RuntimeException("栈空");
		}
		return stack[top];
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