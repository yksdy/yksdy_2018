package com.yksdy.desigin.patterns.web.interceptingFilter;

public class DebugFilter implements Filter {
	public void execute(String request) {
		System.out.println("request log: " + request);
	}
}