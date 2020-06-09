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

//创建单向环形链表
class CircleSingleLinkedList{
	
	private Boy first = new Boy(-1);
	
	public void addBoy(int nums) {
		//做一个数据校验
		if (nums<1) {
			System.out.println("num值不正确");
			return;
			
		}
		Boy curBoy = null;
		for (int i = 1; i <= nums; i++) {
			Boy boy = new Boy(i);
			if (i == 1) {
				first = boy;
				first.next = first;//队首，首先只有一个孩子，自己形成循环链表
				curBoy = first; //让队尾指向第一个小孩
			}else {
				curBoy.next = boy;
				boy.next = first;
				curBoy = boy;
			}
		}
	}
	public void showBoy() {
		if (first ==null) {
			System.out.println("没有小孩参加游戏");
			return;
		}
		Boy curBoy = first;
		while (true) {
			System.out.printf("小孩编号：:%d\n", curBoy.getNo());
			if (curBoy.next==first) {
				break;
			}
			curBoy = curBoy.next;
		}
	}
	/**
	 * @param startNo 表示从第几个小孩开始数
	 * @param count 数几下出一个小孩
	 * @param nums 最初有多少个小孩
	 */
	public void countBoy(int startNo, int count, int nums) {
		if (first == null || startNo < 1 || startNo > nums) {
			System.out.println("参数输入有误");
		}
		Boy helper = first;
		while (true) {
			if (helper.next == first) {//说明helper是first的前一个
				break;
			}
			helper = helper.next;
		}
		for (int i = 0; i < startNo - 1; i++) {//找到开始的孩子
			first = first.next;
			helper = helper.next;
		}
		while (true) {
			if (helper == first) {
				break;
			}
			//同时移动first和helper
			for (int i = 0; i < count-1; i++) {
				first = first.next;
				helper = helper.next;
			}
			System.out.printf("小孩%d出圈\n", first.getNo());
			first = first.next;
			helper.next = first;
		}
		System.out.printf("最后留在圈中的小孩：%d\n", helper.getNo());
	}
}