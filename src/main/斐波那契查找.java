package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 96916
 *	쳲�����(�ƽ�ָ)ԭ��;
	쳲���������ԭ������ֲ��ҷ��Ͳ�ֵ���ҷ����ƣ�����
	�ı����м���(mid)��λ�ã�mid�������м���ֵ�õ���
	����λ�ڻƽ�ָ�㸽������mid=low+F(k-1)-1
	(F����쳲���������)
*/
public class 쳲��������� {

	static List<Integer> fib = new ArrayList<Integer>();
	public static void main(String[] args) {
		int [] a = new int [] {0,1,2,3,4,5,6,7,8,9,11,11,11,101,450};
		extracted(a.length);
		System.out.println(fibSearch(a, 0));
	}

	private static List<Integer> fibSearch(int[] a, int key) {
		if (key>a[a.length-1]||key<a[0]) {
			return null;
		}
		int left = 0;
		int right = a.length-1;
		
		int m = 0;//��ʾ쳲����������±�
		//�����ҵ��������±�ŵ�result������
		List<Integer> result = new ArrayList<Integer>();
		while (right > fib.get(m) - 1) {
			m++;
		}
		//��ֹmidԽ��
		int [] tempArray = Arrays.copyOf(a, fib.get(m));
		for (int i = right + 1; i < tempArray.length; i++) {
			tempArray[i] = a[right];
		}
		
		int mid = -1;
		while (true) {
			
			//��ֹԽ��
			if (left > right) {
				break;
			}
			//ÿ��ȡ�м��ֵ���Ƚ�
			mid = left + fib.get(m - 1) - 1;
			if (tempArray[mid]==key) {
				//���ж�mid�Ƿ����ԭ����߽�
				if (mid > right) {
					result.add(right);
					return result;
				}
				
				result.add(mid);
				//�����ظ�Ԫ��
				int temp = mid;
				while (mid+1<tempArray.length-1&&tempArray[++mid]==key) {
					result.add(mid);
				}
				mid = temp;
				while (mid-1>=0&&tempArray[--mid]==key) {
					result.add(mid);
				}
				break;
			}else if (tempArray[mid] > key) {
				right = mid-1;
				m--;
			}else if (tempArray[mid] < key) {
				left = mid + 1;
				m-=2;
			}
		}
		return result;
	}
	//쳲���������
	private static void extracted(int i) {
		int temp = 1;
		if (i <= 2) {
			for (int j = 0; j < i; j++) {
				fib.add(1);
			}
		}else {
			fib.add(1);
			fib.add(1);
			for (int j = 2; j < i; j++) {
				fib.add(fib.get(j - 2) + fib.get(j-1));
			}
		}
		for (Integer num : fib) {
			System.out.println(num);
		}
	}
}
