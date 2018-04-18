package com.yksdy.desigin.patterns.creational.abstractFactory;

public class FactroyProducer {
	public static AbstractFactory getFactory(String choice) {
		if(choice.equalsIgnoreCase("SHAPE")) {
			return new ShapeFactory();
		}else if(choice.equalsIgnoreCase("COLOR")) {
			return new ColorFactory();
		}
		return null;
	}
}
