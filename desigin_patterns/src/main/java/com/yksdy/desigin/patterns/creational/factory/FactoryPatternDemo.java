package com.yksdy.desigin.patterns.creational.factory;

public class FactoryPatternDemo {
	public static void main(String[] args) {
		ShapeFactory sf = new ShapeFactory();
		sf.getShape("CIRCLE").draw();
		sf.getShape("RECTANGLE").draw();
		sf.getShape("SQUARE").draw();
		
	}
}
