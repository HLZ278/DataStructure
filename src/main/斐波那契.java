package main;

import java.util.List;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;

import java.util.ArrayList;

public class 斐波那契 {
	
	public static void main(String[] args) {
		System.out.println("请输入你需要第几个斐波那契数：");
		Scanner scanner = new Scanner(System.in);
		int i = scanner.nextInt();
		extracted(i);
	}
	static List<Integer> result = new ArrayList<Integer>();
	private static List<Integer> extracted(int i) {
		int temp = 1;
		if (i <= 2) {
			for (int j = 0; j < i; j++) {
				result.add(1);
			}
		}else {
			result.add(1);
			result.add(1);
			for (int j = 2; j < i; j++) {
				result.add(result.get(j - 2) + result.get(j-1));
			}
		}
		for (Integer num : result) {
			System.out.println(num);
		}
		return result;
	}
}
