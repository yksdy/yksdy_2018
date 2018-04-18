package com.yksdy.desigin.patterns.creational.abstractFactory;

public class ShapeFactory extends AbstractFactory{
	public Shape getShape(String shapeType) {
		if(shapeType == null) {
			return null;
		}
		if(shapeType.equalsIgnoreCase("CIRCLE")) {
			return new Circle();
		}else if(shapeType.equalsIgnoreCase("RECTANGLE")) {
			return new Rectangle();
		}if(shapeType.equalsIgnoreCase("SQUARE")) {
			return new Square();
		}
		return null;
	}

	@Override
	public Color getColor(String colorType) {
		// TODO Auto-generated method stub
		return null;
	}
}
