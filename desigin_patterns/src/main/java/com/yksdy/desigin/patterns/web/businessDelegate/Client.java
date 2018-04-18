package com.yksdy.desigin.patterns.web.businessDelegate;

public class Client {

	BusinessDelegate businessService;

	public Client(BusinessDelegate businessService) {
		this.businessService = businessService;
	}

	public void doTask() {
		businessService.doTask();
	}
}
