package com.yksdy.demo;

public class SimpleDto {
	String name;
	int age;
	String address;
	public SimpleDto(String name,int age, String address) {
		this.name = name;
		this.age = age;
		this.address = address;
		
	}
	public SimpleDto() {
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
