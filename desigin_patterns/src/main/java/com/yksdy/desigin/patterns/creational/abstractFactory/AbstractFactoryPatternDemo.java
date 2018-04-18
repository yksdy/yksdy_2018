package com.yksdy.desigin.patterns.creational.abstractFactory;

public class AbstractFactoryPatternDemo {
	public static void main(String[] args) {
		AbstractFactory af = FactroyProducer.getFactory("SHAPE");
		af.getShape("CIRCLE").draw();
		af.getShape("RECTANGLE").draw();
		af.getShape("SQUARE").draw();
		
		
		AbstractFactory af1 = FactroyProducer.getFactory("COLOR");
		af.getShape("RED").draw();
		af.getShape("BLUE").draw();
		af.getShape("GREEN").draw();
	}

}
