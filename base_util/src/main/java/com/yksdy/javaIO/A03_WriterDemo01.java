package com.yksdy.javaIO;
import java.io.*;
public class A03_WriterDemo01{
	public static void main(String[] args) throws Exception{
		File f = new File("A03_Writer.txt");
		Writer w = new FileWriter(f,true);
		w.write("Hello world");
		w.close();
	}
}
