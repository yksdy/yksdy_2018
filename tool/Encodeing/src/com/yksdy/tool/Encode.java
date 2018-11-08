package com.yksdy.tool;

import java.io.UnsupportedEncodingException;

public class Encode {
	
	public static void main(String[] args) {
		encodeTest1();
	}
	public static void encodeTest1() {
		String str = "merries花王尿不湿M64片装64片装";
		System.out.println(str);
		String param = null;
		try {
			param = new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(param);
		
	}

}
