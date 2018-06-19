package com.yksdy.demo;

public class Demo1 {
	public static void main(String[] args) {
		new Demo1().returnStatus1(new SimpleDto("xx",3,"dfd"));
		new Demo1().returnStatus2(new SimpleDto("yy",3,"dfd"));
	}
	public void returnStatus1(SimpleDto sd) {
		SimpleDto simpleDto = sd;
		System.out.println("--------"+simpleDto.getName());
	}
	public void returnStatus2(SimpleDto sd) {
		SimpleDto simpleDto = new SimpleDto();
		simpleDto.setAddress(sd.getAddress());
		simpleDto.setAge(sd.getAge());
		simpleDto.setName(sd.getName());
		System.out.println("--------"+simpleDto.getName());
	}

}
