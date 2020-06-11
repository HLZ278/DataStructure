package main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author 96916
 *	1. 初始化两个栈:运算符栈s1和储存中间结果的栈s2;
 *	2. 从左至右扫描中缀表达式;
 *	3. 遇到操作数时，将其压s2;
 *	4. 遇到运算符时，比较其与s1栈顶运算符的优先级:
 *	(1) 如果s1为空，或栈顶运算符为左括号“(”，则直接将此运算符入栈; 
 *	(2) 否则，若优先级比栈顶运算符的高，也将运算符压入s1;
 *	(3) 否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)与s1中新的栈顶运算
 *	符相比较;
 *	5. 遇到括号时:
 *	(1)如果是左括号“(”，则直接压入s1
 *	(2) 如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2,直到遇到左括号为
 *	止，此时将这一对括号丢弃
 *	6. 重复步骤2至5，直到表达式的最右边
 *	7. 将s1中剩余的运算符依次弹出并压入s2
 *	8. 依次弹出s2中的元素并输出，结果的逆即为中缀表达式对应的后缀表达式
 */
public class 后缀表达式生成器 {
	public static void main(String[] args) {
		 String expString = "10+((20+30)*40)-50";
		 List<String> s1 = toInfixExpressionList(expString);
		 System.out.println(s1);
		 List<String> s2 = parseSuffixExpressionList(s1);
		 System.out.println(s2);
	}
	
	public static List<String> parseSuffixExpressionList(List<String> list) {
		Stack<String>  s1 = new Stack<String>();//符号栈
 		List<String> s2 = new ArrayList<String>();//结果
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
				//当item符号优先级小于等于栈顶元素
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
	
	//转换中缀表达式为list
	public static List<String> toInfixExpressionList(String s){
		List<String> list = new ArrayList<String>();
		int i = 0;
		String str;
		char c;
		do {
			//不是数组
			if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
				list.add(""+c);
				i++;
			}else { //是数,考虑多位数问题
				str = "";//将临时字符串置成""
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
