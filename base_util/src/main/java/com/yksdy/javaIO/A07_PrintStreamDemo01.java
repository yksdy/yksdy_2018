package com.yksdy.javaIO;
import java.io.*;
public class A07_PrintStreamDemo01{
	public static void main(String[] args) throws Exception{
		PrintStream ps = new PrintStream(new FileOutputStream(new File("A07_PrintStream.txt")));
		ps.print("Hello World!");
		ps.println("hahaha");
		ps.println("000000");
		String str = "what's up! man";
		int age = 26;
		int score = 98;
		ps.printf( "str = %s, age = %s, score = %s ",str,age,score);
		ps.close();
	}
}
