package com.yksdy.desigin.patterns.web.serviceLocator;

public class Service2 implements Service {
	public void execute() {
		System.out.println("Executing Service2");
	}

	public String getName() {
		return "Service2";
	}
}
