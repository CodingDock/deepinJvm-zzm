package com.universe.introduces.第10章;

import java.util.HashMap;
import java.util.Map;

public class C10_3 {

	public static void main(String[] args) {
		Map map = new HashMap();
		map.put("hello", "你好");
		map.put("how are you?", "吃了没？");
		System.out.println((String) map.get("hello"));
		System.out.println((String) map.get("how are you?"));
	}

}