package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author 96916
 *	1. ��ʼ������ջ:�����ջs1�ʹ����м�����ջs2;
 *	2. ��������ɨ����׺���ʽ;
 *	3. ����������ʱ������ѹs2;
 *	4. ���������ʱ���Ƚ�����s1ջ������������ȼ�:
 *	(1) ���s1Ϊ�գ���ջ�������Ϊ�����š�(������ֱ�ӽ����������ջ; 
 *	(2) ���������ȼ���ջ��������ĸߣ�Ҳ�������ѹ��s1;
 *	(3) ���򣬽�s1ջ���������������ѹ�뵽s2�У��ٴ�ת��(4.1)��s1���µ�ջ������
 *	����Ƚ�;
 *	5. ��������ʱ:
 *	(1)����������š�(������ֱ��ѹ��s1
 *	(2) ����������š�)���������ε���s1ջ�������������ѹ��s2,ֱ������������Ϊ
 *	ֹ����ʱ����һ�����Ŷ���
 *	6. �ظ�����2��5��ֱ�����ʽ�����ұ�
 *	7. ��s1��ʣ�����������ε�����ѹ��s2
 *	8. ���ε���s2�е�Ԫ�ز������������漴Ϊ��׺���ʽ��Ӧ�ĺ�׺���ʽ
 */
public class ��׺���ʽ������ {
	public static void main(String[] args) {
		 String expString = "10+((20+30)*40)-50";
		 List<String> s1 = toInfixExpressionList(expString);
		 System.out.println(s1);
		 List<String> s2 = parseSuffixExpressionList(s1);
		 System.out.println(s2);
	}
	
	public static List<String> parseSuffixExpressionList(List<String> list) {
		Stack<String>  s1 = new Stack<String>();//����ջ
 		List<String> s2 = new ArrayList<String>();//���
 		for(String item: list) {
 			if (item.matches("\\d+")) {
				s2.add(item);
			}else if (item.equals("(")) {
				s1.push(item);
			}else if (item.equals(")")) {
				while (!s1.peek().equals("(")) {
					s2.add(s1.pop());
				}
				s1.pop();
			}else {
				//��item�������ȼ�С�ڵ���ջ��Ԫ��
				while (s1.size()!=0 && Operation.getValue(item)<=Operation.getValue(s1.peek())) {
					s2.add(s1.pop());
				}
				s1.push(item);
			}
 		}
 		while (s1.size()!=0) {
			s2.add(s1.pop());
		}
		return s2;
	}
	
	//ת����׺���ʽΪlist
	public static List<String> toInfixExpressionList(String s){
		List<String> list = new ArrayList<String>();
		int i = 0;
		String str;
		char c;
		do {
			//��������
			if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
				list.add(""+c);
				i++;
			}else { //����,���Ƕ�λ������
				str = "";//����ʱ�ַ����ó�""
				while (i<s.length()&&(c = s.charAt(i))>=48 && (c = s.charAt(i)) <= 57) {
					str += c;
					i++;
				}
				list.add(str);
			}
		} while (i<s.length());
		return list;
	}
}
class Operation{
	private static int ADD = 1;
	private static int SUB = 1;
	private static int MUL = 2;
	private static int DIV = 2;
	public  static int  getValue(String operation) {
		int result = 0;
		switch (operation) {
		case "+":
			result = ADD;
			break;
		case "-":
			result = SUB;
			break;
		case "*":
			result = MUL;
			break;
		case "/":
			result = DIV;
			break;
		default:
			break;
		}
		return result;
	}
}
