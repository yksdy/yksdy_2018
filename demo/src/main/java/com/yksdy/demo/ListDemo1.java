package com.yksdy.demo;

import java.util.ArrayList;
import java.util.List;

public class ListDemo1 {
	public static void main(String[] args) {
		String tempISL = new String();
		tempISL = "I'm line";
		Head tempISH = new Head();
		tempISH.setName("abc");
		List<String> line = new ArrayList();
		line.add(tempISL);
		tempISH.setLine(line);
	}
}
class Head{
	String name;
	List<String> line;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getLine() {
		return line;
	}
	public void setLine(List<String> line) {
		this.line = line;
	}
	
}
