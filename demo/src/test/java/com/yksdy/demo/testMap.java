package com.yksdy.demo;

import java.util.Hashtable;
import java.util.Map;

public class testMap {
	
	private static Map<String, String> tempMap = new Hashtable();
	private static Map<String, String> skuIdMap = new Hashtable();
	
	
	public static boolean isSkuExist(String extSkuId, String departmenntId) {
		String tempSkuId = getTempSkuId(extSkuId, departmenntId);
//		if (skuIdMap.isEmpty() && tempMap.isEmpty()) {
//			return false;
//		}
		if (skuIdMap.get(tempSkuId) != null && tempMap.get(tempSkuId) != null) {
			return true;
		}
		return false;
	}
	public static String getTempSkuId(String extSkuId, String departmenntId) {
		String tempSkuId = departmenntId + "-" + extSkuId;
		return tempSkuId;
	}
	
	
	
	public static Map<String, String> custBarcodeMap = new Hashtable<String, String>();
	public static void main(String[] args) {
		isEquals("abd","fds");
		System.out.println("----"+isSkuExist("SKU002","CUST001"));
	}
	public static void test() {
		Map<String, String> mapTest = new Hashtable<String, String>();
	
		try {
			System.out.println(mapTest.get("aa"));
			System.out.println(mapTest);
			System.out.println(mapTest.size());
			if(mapTest.size()<=0) {
				
				System.out.println("111");
				return;
			}
			
			if(mapTest== null) {
				return;
			}
			if(mapTest.get("aa")== null) {
				return;
			}
			if(mapTest.get("aa").equals("bb")) {
				System.out.println("Yes");
			}else {
				System.out.println("No");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static boolean isEquals(String departmenntId, String barcode) {
		if (custBarcodeMap.size() <= 0) {
			return true;
		} else {
			String temp = custBarcodeMap.get(departmenntId);
			if (temp == null) {
				return true;
			} else if (temp.equals(barcode)) {
				return false;
			}
		}
		return true;
		
	}

}
