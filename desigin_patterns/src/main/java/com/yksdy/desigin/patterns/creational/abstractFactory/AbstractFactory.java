package com.yksdy.desigin.patterns.creational.abstractFactory;

public abstract class AbstractFactory {
	public abstract Shape getShape(String shapeType);
	public abstract Color getColor(String colorType);
}
