package main;

import java.util.List;
import java.util.ArrayList;

import javax.naming.directory.SearchControls;


public class 二分查找法 {
	public static void main(String[] args) {
		int [] a = new int [] {0,1,2,3,4,5,6,7,8,9,11,11,11,101,450};
		System.out.println(binarySearch(a, 11));
	}

	private static List<Integer> binarySearch(int[] a, int i) {
		if (i>a[a.length-1]||i<a[0]) {
			return null;
		}
		int left = 0;
		int right = a.length-1;
		//将查找到的数的下标放到result数组中
		List<Integer> result = new ArrayList<Integer>();
		int mid = -1;
		while (true) {
			//防止越界
			if (left > right) {
				break;
			}
			//每次取中间的值作比较
			mid = left + (right - left) / 2;
			if (a[mid]==i) {
				result.add(mid);
				//查找重复元素
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
