package com.yksdy.desigin.patterns.creational.builder;

public abstract class ColdDrink implements Item{


	public Packing packing() {
		return new Bottle();
	}

}

