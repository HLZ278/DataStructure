package main;

import java.util.ArrayList;
import java.util.List;

//���ַ��ĸĽ�����ֵ�����㷨���±�����鶼���������еģ�
//�ò���ֵ����Сֵ֮��Ĳ������ƻ���˵׼ȷ��λ����ֵ�����λ��
//int mid= left+ (right- left) * (findVal- arr[left])/ (arr[right] - arr[left])

public class ��ֵ���ҷ� {
	public static void main(String[] args) {
		int [] a = new int [] {0,1,2,3,4,5,6,7,8,9,11,11,11,101,450};
		System.out.println(InsertValueSearch(a, 0));
	}

	private static List<Integer> InsertValueSearch(int[] a, int i) {
		if (i>a[a.length-1]||i<a[0]) {
			return null;
		}
		int left = 0;
		int right = a.length-1;
		//�����ҵ��������±�ŵ�result������
		List<Integer> result = new ArrayList<Integer>();
		int mid = -1;
		while (true) {
			//��ֹԽ��
			if (left > right) {
				break;
			}
			//ÿ��ȡ�м��ֵ���Ƚ�
			mid = left + (right - left) * (i - a[left]) / (a[right] - a[left]);
			if (a[mid]==i) {
				result.add(mid);
				//�����ظ�Ԫ��
				int temp = mid;
				while (mid+1<a.length-1&&a[++mid]==i) {
					result.add(mid);
				}
				mid = temp;
				while (mid-1>=0&&a[--mid]==i) {
					result.add(mid);
				}
				break;
			}else if (a[mid] > i) {
				right = mid-1;
			}else if (a[mid] < i) {
				left = mid + 1;
			}
		}
		return result;
	}
}
