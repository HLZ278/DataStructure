package main;

import java.util.ArrayList;
import java.util.List;

//二分法的改进：插值查找算法：下标和数组都是升序排列的，
//用查找值和最小值之间的差来估计或者说准确定位查找值的相对位置
//int mid= left+ (right- left) * (findVal- arr[left])/ (arr[right] - arr[left])

public class 插值查找法 {
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
		//将查找到的数的下标放到result数组中
		List<Integer> result = new ArrayList<Integer>();
		int mid = -1;
		while (true) {
			//防止越界
			if (left > right) {
				break;
			}
			//每次取中间的值作比较
			mid = left + (right - left) * (i - a[left]) / (a[right] - a[left]);
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
