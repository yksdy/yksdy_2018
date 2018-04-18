package com.yksdy.desigin.patterns.web.interceptingFilter;

public class AuthenticationFilter implements Filter {
	public void execute(String request) {
		System.out.println("Authenticating request: " + request);
	}
}
