package main;

import java.util.Scanner;

import javax.print.attribute.Size2DSyntax;
import javax.sound.midi.VoiceStatus;

public class MyHashTable {
	public static void main(String[] args) {
		HashTab hashTab = new HashTab(10);
		String key = "";
		Scanner scanner = new Scanner(System.in);
		while (true) {
			System.out.println("add: 添加雇员");
			System.out.println("list: 显示雇员");
			System.out.println("exit: 退出");
			System.out.println("find: 查找");
			key = scanner.next();
			switch (key) {
			case "add":
				System.out.println("输入id");
				int id = scanner.nextInt();
				System.out.println("输入名字");
				String nameString = scanner.next();
				Emp emp = new Emp(id, nameString);
				hashTab.add(emp);
				break;
			case "list":
				hashTab.list();
				break;
			case "exit":
				scanner.close();
				System.exit(0);
			case "find":
				System.out.println("请输入要查找雇员的id");
				int id1 = scanner.nextInt();
				hashTab.findEmpById(id1);
				break;
			default:
				break;
			}
		}
	}
}


class HashTab{
	private EmpLinkedList[] empLinkedListArray;
	private int size;
	public HashTab(int size) {
		//初始化有多少条链表
		this.size = size;
		empLinkedListArray = new EmpLinkedList[size];
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i] = new EmpLinkedList();
		}
	}
	public void add(Emp emp) {
		int empLinkedListIndex = hashFun(emp.id);
		empLinkedListArray[empLinkedListIndex].add(emp);
	}
	//遍历，输出hashTab
	public void list() {
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i].list();
		}
	}
	//散列函数
	public int hashFun(int id) {
		return id%size;
	}
	//查询
	public void findEmpById(int id) {
		int empLinkedListIndex = hashFun(id);
		Emp emp = empLinkedListArray[empLinkedListIndex].findEmpById(id);
		if (emp!=null) {
			System.out.println("在第"+empLinkedListIndex+"条链表找到雇员");
		}else {
			System.out.println("没有该员工");
		}
	}
}

class Emp{
	public int id;
	public String name;
	public Emp next;
	public Emp(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
}
//创建EmpLinkedList，表示链表
class EmpLinkedList{
	//头指针
	private Emp head;
	
	public void add(Emp emp) {
		if (head == null) {
			head = emp;
			return;
		}
		Emp curEmp = head;
		while (true) {
			if (curEmp.next == null) {
				break;
			}
			curEmp = curEmp.next;
		}
		curEmp.next = emp;
	}
	//打印链表
	public void list() {
		if (head == null) {
			System.out.println("=>null");
			return;
		}
		System.out.println("当前链表信息为：");
		Emp curEmp = head;
		while (true) {
			System.out.printf("=>"+curEmp.id+", "+curEmp.name);
			if (curEmp.next == null) {
				break;
			}
			curEmp = curEmp.next;
		}
	}

	// 根据id查找雇员
	// 如果查找到，就返回Emp,如果没有找到，就返回null
	public Emp findEmpById(int id) {
		// 判断链表是否为空
		if (head == null) {
			System.out.println("链表为空");
			return null;
		}
		// 辅助指针
		Emp curEmp = head;
		while (true) {
			if (curEmp.id == id) {// 找到
				break; // 这时curEmp就指向要查找的雇员
			}
			// 退出
			if (curEmp.next == null) {// 说明遍历当前链表没有找到该雇员
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;
		}
		return curEmp;
	}

}