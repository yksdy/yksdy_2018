package com.yksdy.demo;

import java.util.Date;

public class DateDemo {
	public static void main(String[] args) {
		
		long time1 = new Date().getTime();
		long time2 = System.currentTimeMillis();
		System.out.println(time1);
		System.out.println(time2);
		
	}
	
}
