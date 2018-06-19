package com.yksdy.demo;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {
	TreeMap<String, Integer> dataMap ;
	public static void main(String[] args) {
		MapDemo md = new MapDemo();
		md.testData1();
		md.testData2();
		md.testData3();
//		md.testData4();
		
	}
	public void testData1(){
		System.out.println("------------testData1-----------");
		dataMap = new TreeMap<String, Integer>();
		dataMap.put("1", 1);
		printMap(dataDistinct(dataMap));
		System.out.println("-------end-----testData1-----------");
	}
	public void testData2(){
		System.out.println("------------testData2-----------");
		dataMap = new TreeMap<String, Integer>();
		dataMap.put("1", 1);
		dataMap.put("2", 1);
		dataMap.put("21", 1);
		printMap(dataDistinct(dataMap));
		System.out.println("-------end-----testData2-----------");
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
		dataMap.put("51", 3);
		dataMap.put("52", 3);
		dataMap.put("53", 3);
		dataMap.put("54", 3);
		dataMap.put("8", 5);
		dataMap.put("9", 3);
		dataMap.put("91", 3);
		dataMap.put("92", 3);
		dataMap.put("93", 3);
		dataMap.put("94", 3);
		dataMap.put("95", 3);
		System.out.println("----1--------testData3-----------");
		printMap(dataMap);
		printMap(dataDistinct(dataMap));
		System.out.println("-------end-----testData3-----------");
	}
	public void testData4(){
		System.out.println("------------testData4-----------");
		dataMap = new TreeMap<String, Integer>();
		dataMap.put("1", 1);
		dataMap.put("2", 1);
		dataMap.put("3", 2);
		dataMap.put("4", 2);
		dataMap.put("5", 3);
		dataMap.put("8", 5);
		
		Iterator<Map.Entry<String, Integer>> datasIterRight = dataMap.entrySet().iterator();
    	Iterator<Map.Entry<String, Integer>> datasIterLeft = dataMap.entrySet().iterator();
    	if(datasIterRight.hasNext()) {
    		datasIterRight.next();
    	}
    //	while(datasIterRight.hasNext()) {
    		Map.Entry<String, Integer> dataseEntryRight = datasIterRight.next();
    		Map.Entry<String, Integer> dataseEntryLeft = datasIterLeft.next();
    		
    		
    		System.out.println("dataseEntryRight Key = " + dataseEntryRight.getKey() + ", Value = " + dataseEntryRight.getValue()); 
    		System.out.println("dataseEntryLeft Key = " + dataseEntryLeft.getKey() + ", Value = " + dataseEntryLeft.getValue()); 
    		
    		
    		if(dataseEntryRight.getValue()==dataseEntryLeft.getValue()) {
    			dataMap.remove(dataseEntryRight.getKey());
    		}else {
    		}
    		
    //		dataseEntryRight = datasIterRight.next();
    //		dataseEntryLeft = datasIterLeft.next();
    		
    		System.out.println("dataseEntryRight Key = " + dataseEntryRight.getKey() + ", Value = " + dataseEntryRight.getValue()); 
    		System.out.println("dataseEntryLeft Key = " + dataseEntryLeft.getKey() + ", Value = " + dataseEntryLeft.getValue()); 
    		
    //	}
		
		
		printMap(dataMap);
		System.out.println("-------end-----testData4-----------");
	}
	public TreeMap<String,Integer> dataDistinct(TreeMap<String,Integer> datas){
		TreeMap<String,Integer> treeMapData = new TreeMap<String, Integer>();
		Map.Entry<String, Integer> itemRight = datas.lastEntry();
		Map.Entry<String, Integer> itemLeft = null;
		for (Map.Entry<String, Integer> entry : datas.entrySet()) {
			if(treeMapData.isEmpty()) {
				treeMapData.put(entry.getKey(), entry.getValue());
				itemLeft = entry;
			}else if(entry.getValue() != itemLeft.getValue()) {
				treeMapData.put(entry.getKey(), entry.getValue());
				itemLeft = entry;
			}else {
		//		itemRight = entry;
			}
		}
		if(itemRight.getValue()==itemLeft.getValue()&&itemRight.getKey()!=itemLeft.getKey()) {
			treeMapData.put(itemRight.getKey(), itemRight.getValue());
		}
		
		return treeMapData;
	}
		
//		Iterator<Map.Entry<String, Integer>> datasIterRight = dataMap.entrySet().iterator();
//    	Iterator<Map.Entry<String, Integer>> datasIterLeft = dataMap.entrySet().iterator();
//    	if(datasIterRight.hasNext()) {
//    		datasIterRight.next();
//    	}
//    	Map.Entry<String, Integer> dataseEntryRight = datasIterRight.next();
//		Map.Entry<String, Integer> dataseEntryLeft = datasIterLeft.next();
//    	while(datasIterRight.hasNext()) {
//    		if(dataseEntryRight.getValue()==dataseEntryLeft.getValue()) {
//    			Map.Entry<String, Integer> temp = datasIterRight.next();
//    			dataMap.remove(dataseEntryRight.getKey());
//    			dataseEntryRight = temp;
//    		}else {
//    			if(datasIterRight.hasNext()) {
//    				dataseEntryRight = datasIterRight.next();
//    				dataseEntryLeft = datasIterLeft.next();
//    	    	}
//    		}
//    	}
//		return datas;
//	}
	public void printMap(TreeMap<String,Integer> datas) {
		
		for (Map.Entry<String, Integer> entry : datas.entrySet()) { 
		  System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue()); 
		}
	}
}
