package main;

public class Joseph {
	public static void main(String[] args) {
		CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
		circleSingleLinkedList.addBoy(25);
		circleSingleLinkedList.showBoy();
		circleSingleLinkedList.countBoy(1, 2, 5);
	}
}
class Boy {
	private int no;
	Boy next;
	public Boy(int no) {
		this.no = no;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
}

//��������������
class CircleSingleLinkedList{
	
	private Boy first = new Boy(-1);
	
	public void addBoy(int nums) {
		//��һ������У��
		if (nums<1) {
			System.out.println("numֵ����ȷ");
			return;
			
		}
		Boy curBoy = null;
		for (int i = 1; i <= nums; i++) {
			Boy boy = new Boy(i);
			if (i == 1) {
				first = boy;
				first.next = first;//���ף�����ֻ��һ�����ӣ��Լ��γ�ѭ������
				curBoy = first; //�ö�βָ���һ��С��
			}else {
				curBoy.next = boy;
				boy.next = first;
				curBoy = boy;
			}
		}
	}
	public void showBoy() {
		if (first ==null) {
			System.out.println("û��С���μ���Ϸ");
			return;
		}
		Boy curBoy = first;
		while (true) {
			System.out.printf("С����ţ�:%d\n", curBoy.getNo());
			if (curBoy.next==first) {
				break;
			}
			curBoy = curBoy.next;
		}
	}
	/**
	 * @param startNo ��ʾ�ӵڼ���С����ʼ��
	 * @param count �����³�һ��С��
	 * @param nums ����ж��ٸ�С��
	 */
	public void countBoy(int startNo, int count, int nums) {
		if (first == null || startNo < 1 || startNo > nums) {
			System.out.println("������������");
		}
		Boy helper = first;
		while (true) {
			if (helper.next == first) {//˵��helper��first��ǰһ��
				break;
			}
			helper = helper.next;
		}
		for (int i = 0; i < startNo - 1; i++) {//�ҵ���ʼ�ĺ���
			first = first.next;
			helper = helper.next;
		}
		while (true) {
			if (helper == first) {
				break;
			}
			//ͬʱ�ƶ�first��helper
			for (int i = 0; i < count-1; i++) {
				first = first.next;
				helper = helper.next;
			}
			System.out.printf("С��%d��Ȧ\n", first.getNo());
			first = first.next;
			helper.next = first;
		}
		System.out.printf("�������Ȧ�е�С����%d\n", helper.getNo());
	}
}