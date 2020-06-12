package main;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author 96916
 *	斐波那契(黄金分割法)原理;
	斐波那契查找原理与二分查找法和插值查找法相似，仅仅
	改变了中间结点(mid)的位置，mid不再是中间或插值得到，
	而是位于黄金分割点附近，即mid=low+F(k-1)-1
	(F代表斐波那契数列)
*/
public class 斐波那契查找 {

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
		
		int m = 0;//表示斐波那契数列下标
		//将查找到的数的下标放到result数组中
		List<Integer> result = new ArrayList<Integer>();
		while (right > fib.get(m) - 1) {
			m++;
		}
		//防止mid越界
		int [] tempArray = Arrays.copyOf(a, fib.get(m));
		for (int i = right + 1; i < tempArray.length; i++) {
			tempArray[i] = a[right];
		}
		
		int mid = -1;
		while (true) {
			
			//防止越界
			if (left > right) {
				break;
			}
			//每次取中间的值作比较
			mid = left + fib.get(m - 1) - 1;
			if (tempArray[mid]==key) {
				//先判断mid是否大于原数组边界
				if (mid > right) {
					result.add(right);
					return result;
				}
				
				result.add(mid);
				//查找重复元素
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
	//斐波那契生成
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
