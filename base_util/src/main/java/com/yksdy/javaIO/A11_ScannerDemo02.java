package com.yksdy.javaIO;
import java.util.*;
import java.io.*;
import java.text.*;
import java.util.Date;
/*
20170619 22:20
Scanner 11 method
*/
public class A11_ScannerDemo02{
	public static void main(String[] args) throws Exception{
		Scanner scan = new Scanner(System.in); //1
		System.out.println("Please input1 next: ");
		String str = scan.next(); // 2
		System.out.println("Out put1 : "+str);
		System.out.println("Please input2 next: ");
		scan.useDelimiter("\n"); //3
		if(scan.hasNext()){  //4
			String str1 = scan.next(); //2
			System.out.println("Out put2 : "+str1);
		}
		System.out.println("Please input nextIInt: ");
		if(scan.hasNextInt()){  //5
			int a = scan.nextInt(); //6
			System.out.println("Out put3 : "+a);
		}
		System.out.println("Please input nextFloat: ");
		if(scan.hasNextFloat()){  //7
			float f = scan.nextFloat(); //8
			System.out.println("Out put4 : "+f);
		}
		System.out.println("Please input data yyyy-MM-dd: ");
		//if(true) {//scan.hasNext("^\\d{4}$")){ //9
		//if(scan.hasNext("^\\d{4}-\\d{2}-\\d{2}$")){ //9
		if(scan.hasNext("\\d{4}-\\d{2}-\\d{2}")){ //9
		//	String str2 = scan.next("^\\d{4}-\\d{2}-\\d{2}$");//10
			String str2 = scan.next("\\d{4}-\\d{2}-\\d{2}");//10
			try{
				Date date = new SimpleDateFormat("yyyy-MM-dd").parse(str2);
				System.out.println(date);
			}catch(Exception e){
				e.printStackTrace();
			}
		}else{
			System.out.println("error input date format");
		}
		//11
		System.out.println("-----start scanner file------");
		Scanner scanFile=null;
		try{
			scanFile = new Scanner(new File("A11_ScannerDemo01.java"));
			scanFile.useDelimiter("\n");
		}catch(Exception e){
			e.printStackTrace();
		}
		StringBuffer sb = new StringBuffer();
		while(scanFile.hasNext()){
			sb.append(scanFile.next()).append("\n");
		}
		System.out.println(sb);
		
		
	}
}
