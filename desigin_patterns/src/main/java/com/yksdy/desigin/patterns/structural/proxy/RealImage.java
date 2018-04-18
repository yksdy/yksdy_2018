package com.yksdy.desigin.patterns.structural.proxy;

public class RealImage implements Image{
	private String fileName;
	
	public RealImage(String fileName) {
		this.fileName = fileName;
		loadFromDish(fileName);
		
	}
	
	public void loadFromDish(String fileName) {
		System.out.println("Loding "+fileName);
	}

	public void display() {
		System.out.println("Displaying "+fileName);
	}

}
