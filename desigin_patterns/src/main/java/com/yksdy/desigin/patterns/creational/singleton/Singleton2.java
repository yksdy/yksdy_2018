package com.yksdy.desigin.patterns.creational.singleton;

public class Singleton2 {
	private static Singleton2 sigleton ;
	
	private Singleton2() {
		System.out.println("Creadt Singleton 2");
	}
	
	public static Singleton2 getInstance() {
		if(sigleton == null) {
			sigleton = new Singleton2();
		}
		return sigleton;
	}

}
