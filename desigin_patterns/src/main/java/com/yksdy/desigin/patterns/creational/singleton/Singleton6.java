package com.yksdy.desigin.patterns.creational.singleton;

public class Singleton6 {
	private static Singleton6 sigleton ;
	
	private Singleton6() {
		System.out.println("Creadt Singleton 5");
	}
	
	public static final Singleton6 getInstance() {
		return SingletonHolder.INSTANCE;
	}
	
	private static class SingletonHolder{
		private static final Singleton6 INSTANCE = new Singleton6();
	}

}
