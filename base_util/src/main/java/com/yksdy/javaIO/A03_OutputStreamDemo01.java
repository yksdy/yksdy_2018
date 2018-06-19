package com.yksdy.javaIO;
import java.io.*;
public class A03_OutputStreamDemo01{
	public static void main(String[] args) throws Exception{
		File f = new File("A03_OutputStream.txt");
		OutputStream os = new FileOutputStream(f,true);
		String str = " again I'm OutputStream";
		byte[] b = str.getBytes();
		os.write(b);
		os.close();
		InputStream is = new FileInputStream(f);
		byte[] b1 = new byte[(int)f.length()];
		int len = is.read(b1);
		is.close();
		System.out.println(new String(b1,0,len));
	}
}
