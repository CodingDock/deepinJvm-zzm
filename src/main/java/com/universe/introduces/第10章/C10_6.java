package com.universe.introduces.第10章;

import java.util.Arrays;
import java.util.List;

public class C10_6 {
	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		// 濡傛灉鍦↗DK 1.7涓紝杩樻湁鍙﹀涓�棰楄娉曠硸 锛�
		// 鑳借涓婇潰杩欏彞浠ｇ爜杩涗竴姝ョ畝鍐欐垚List<Integer> list = [1, 2, 3, 4];
		int sum = 0;
		for (int i : list) {
			sum += i;
		}
		System.out.println(sum);
	}

}
