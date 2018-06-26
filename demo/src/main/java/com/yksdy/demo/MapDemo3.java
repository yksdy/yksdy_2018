package com.yksdy.demo;

import java.util.Hashtable;
import java.util.Map;

public class MapDemo3 {
	public static void main(String[] args) {
		Map<String, String> isMap = new Hashtable<String, String>();
		if(isMap.containsKey("ab")) {
			System.out.println("yes");
		}else {
			System.out.println("no");
			
		}
	}

}
