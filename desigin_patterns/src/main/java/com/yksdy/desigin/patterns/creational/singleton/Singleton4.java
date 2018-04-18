package com.yksdy.desigin.patterns.creational.singleton;

public class Singleton4 {
	private static Singleton4 sigleton ;
	
	private Singleton4() {
		System.out.println("Creadt Singleton 4");
	}
	
	public static Singleton4 getInstance() {
		if(sigleton == null) {
			synchronized (Singleton4.class) {
				if(sigleton == null) {
					sigleton = new Singleton4();
				}
				
			}
		}
		return sigleton;
	}

}
