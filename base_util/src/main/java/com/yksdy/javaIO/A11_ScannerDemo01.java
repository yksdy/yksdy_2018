package com.yksdy.javaIO;
import java.util.*;
import java.io.*;
public class A11_ScannerDemo01{
	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in);
		scan.useDelimiter("\n");
		/*
		System.out.println(scan.next());
		if(scan.hasNextInt())
			System.out.println(scan.nextInt());
		if(scan.hasNextFloat())
			System.out.println(scan.nextFloat());
			*/
		Scanner scan1 = new Scanner(new File("A09_BufferedReaderDemo01.java"));
		scan1.useDelimiter("\n");
		StringBuffer str = new StringBuffer();
		while(scan1.hasNext())
			str.append(scan1.next()).append("\n");
		System.out.print(str);
			
	}
}
