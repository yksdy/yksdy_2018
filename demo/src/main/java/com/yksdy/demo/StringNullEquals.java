package com.yksdy.demo;

public class StringNullEquals {
	public static void main(String[] args) {
		
		String a = "a";
		String b = null;
		
		if(a.equals(b)) {
			System.out.println("yse");
		}else {
			System.out.println("no");
		}
	}
}
