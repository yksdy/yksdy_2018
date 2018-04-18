package com.yksdy.desigin.patterns.creational.singleton;

public class Singleton3 {
	private static Singleton3 sigleton ;
	
	private Singleton3() {
		System.out.println("Creadt Singleton 4");
	}
	
	public synchronized static Singleton3 getInstance() {
		if(sigleton == null) {
			sigleton = new Singleton3();
		}
		return sigleton;
	}

}
