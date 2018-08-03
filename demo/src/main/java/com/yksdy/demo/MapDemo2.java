package com.yksdy.demo;

import java.util.Map;
import java.util.TreeMap;

public class MapDemo2 {
	TreeMap<String, Integer> dataMap ;
	public static void main(String[] args) {
		MapDemo2 md = new MapDemo2();

		md.testData3();

		
	}

	public void testData3(){
		System.out.println("------------testData3-----------");
		dataMap = new TreeMap<String, Integer>();
		dataMap.put("1", 1);
		dataMap.put("2", 1);
		dataMap.put("3", 2);
		dataMap.put("41", 2);
		dataMap.put("42", 2);
		dataMap.put("5", 3);
		dataMap.put("63", 3);
		dataMap.put("72", 3);
		dataMap.put("51", 9);
		dataMap.put("52", 8);
		dataMap.put("53", 3);
		dataMap.put("54", 3);
		dataMap.put("8", 5);
		dataMap.put("9", 3);
		dataMap.put("91", 3);
		dataMap.put("92", 3);
		dataMap.put("93", 9);
		dataMap.put("94", 3);
		dataMap.put("95", 3);
		System.out.println("----1--------testData3-----------");
		handleBrowseModeSelected();
		System.out.println("-------end-----testData3-----------");
	}
	 private void handleBrowseModeSelected() {
			TreeMap<Integer,String> dataMapTest = new TreeMap<Integer,String>();
			TreeMap<String,Integer> browseDataMap = new TreeMap<String,Integer>();
			printMap(dataMap);
			System.out.println("-------end-----testData3-----------");
			if(dataMap.size()<3) {
			} else {
				for(Map.Entry<String, Integer> entry : dataMap.entrySet()) {
					dataMapTest.put( entry.getValue(),entry.getKey());
				}
				printMap1(dataMapTest);
				System.out.println("-------end-----testData3-----------");
				int i =0;
				for(Map.Entry<Integer, String> entry : dataMapTest.descendingMap().entrySet()) {
					i++;
					if(i>3) break;
					browseDataMap.put( entry.getValue(),entry.getKey());
				}
				printMap(browseDataMap);
				System.out.println("-------end-----testData3-----------");
			}

	}

	 public void printMap1(TreeMap<Integer,String> datas) {
			
			for (Map.Entry<Integer, String> entry : datas.entrySet()) { 
			  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
			}
		}


	public void printMap(TreeMap<String,Integer> datas) {
		
		for (Map.Entry<String, Integer> entry : datas.entrySet()) { 
		  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		}
	}
}
