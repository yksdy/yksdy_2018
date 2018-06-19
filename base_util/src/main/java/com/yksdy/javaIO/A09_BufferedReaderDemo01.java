package com.yksdy.javaIO;
import java.io.*;
public class A09_BufferedReaderDemo01 {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(br.readLine());
	}
}
