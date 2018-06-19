package com.yksdy.javaIO;
import java.io.File;
public class A01_FileDemo01{
	public static void main(String[] args){
		File f = new File("A01_fileDemo.txt");
		File f1 = new File("A01_fileDemo");		
		if(f1.exists()){
			f1.delete();			
		}
		else
		{
			f1.mkdir();
		}
		if(f.exists()){
			f.delete();
			
		}
		else
		{
		try{
			f.createNewFile();
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		File f2 = new File("D:\\");
		if(f2.isDirectory()){
			String[] str = f2.list();
			for(String s:str)
				System.out.println(s);
			File[] ff = f2.listFiles();
			for(File file:ff)
				System.out.println(file);
		}
		else
		{
			System.out.println("not a directory");
		}
	}
}
