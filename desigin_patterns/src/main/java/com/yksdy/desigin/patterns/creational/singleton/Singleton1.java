package com.yksdy.desigin.patterns.creational.singleton;

public class Singleton1 {
//	private static Singleton1 sigleton = new Singleton1();
	private static Singleton1 sigleton = null;
	private Singleton1() {
		System.out.println("Creadt Singleton1");
	}
	
	public static Singleton1 getInstance() {
		return sigleton;
	}

}
