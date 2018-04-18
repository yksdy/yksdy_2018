package com.yksdy.desigin.patterns.creational.abstractFactory;

public class ColorFactory extends AbstractFactory {
	public Color getColor(String colorType) {
		if(colorType == null) {
			return null;
		}
		if(colorType.equalsIgnoreCase("RED")) {
			return new Red();
		}else if(colorType.equalsIgnoreCase("BLUE")) {
			return new Blue();
		}if(colorType.equalsIgnoreCase("GREEN")) {
			return new Green();
		}
		return null;
	}

	@Override
	public Shape getShape(String shapeType) {
		// TODO Auto-generated method stub
		return null;
	}
}
