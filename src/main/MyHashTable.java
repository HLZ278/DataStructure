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
			System.out.println("add: ��ӹ�Ա");
			System.out.println("list: ��ʾ��Ա");
			System.out.println("exit: �˳�");
			System.out.println("find: ����");
			key = scanner.next();
			switch (key) {
			case "add":
				System.out.println("����id");
				int id = scanner.nextInt();
				System.out.println("��������");
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
				System.out.println("������Ҫ���ҹ�Ա��id");
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
		//��ʼ���ж���������
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
	//���������hashTab
	public void list() {
		for (int i = 0; i < size; i++) {
			empLinkedListArray[i].list();
		}
	}
	//ɢ�к���
	public int hashFun(int id) {
		return id%size;
	}
	//��ѯ
	public void findEmpById(int id) {
		int empLinkedListIndex = hashFun(id);
		Emp emp = empLinkedListArray[empLinkedListIndex].findEmpById(id);
		if (emp!=null) {
			System.out.println("�ڵ�"+empLinkedListIndex+"�������ҵ���Ա");
		}else {
			System.out.println("û�и�Ա��");
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
//����EmpLinkedList����ʾ����
class EmpLinkedList{
	//ͷָ��
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
	//��ӡ����
	public void list() {
		if (head == null) {
			System.out.println("=>null");
			return;
		}
		System.out.println("��ǰ������ϢΪ��");
		Emp curEmp = head;
		while (true) {
			System.out.printf("=>"+curEmp.id+", "+curEmp.name);
			if (curEmp.next == null) {
				break;
			}
			curEmp = curEmp.next;
		}
	}

	// ����id���ҹ�Ա
	// ������ҵ����ͷ���Emp,���û���ҵ����ͷ���null
	public Emp findEmpById(int id) {
		// �ж������Ƿ�Ϊ��
		if (head == null) {
			System.out.println("����Ϊ��");
			return null;
		}
		// ����ָ��
		Emp curEmp = head;
		while (true) {
			if (curEmp.id == id) {// �ҵ�
				break; // ��ʱcurEmp��ָ��Ҫ���ҵĹ�Ա
			}
			// �˳�
			if (curEmp.next == null) {// ˵��������ǰ����û���ҵ��ù�Ա
				curEmp = null;
				break;
			}
			curEmp = curEmp.next;
		}
		return curEmp;
	}

}