package com.kovansys.mvp.robot.statistic.model;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.kovansys.mvp.robot.statistic.properties.RobotProperties;

public class RobotDataStructure implements Comparable {
	
	String robotItem;
	TreeMap<String,Integer> robotItemData;
	
	public RobotDataStructure(String robotItem, List<TreeMap<String,Integer>> list) {
		this.robotItem = robotItem;
		this.robotItemData = mergeRobotItemData(list);
	}
	
	public TreeMap<String,Integer> mergeRobotItemData(List<TreeMap<String,Integer>> tempList){
		TreeMap<String,Integer> temp = null;
		if(tempList==null || tempList.size() == 0  ) {
			return null;
		}else if(tempList.size() == 1) {
			return tempList.get(0);
		}else if(tempList.size() == 2) {
			return mergeTreeMap(tempList.get(0), tempList.get(1));
		}
		temp = mergeTreeMap(tempList.get(0), tempList.get(1));
		tempList.remove(0);
		tempList.remove(1);
		tempList.add(temp);
		return mergeRobotItemData(tempList);
	
	}
	
	public TreeMap<String,Integer> mergeTreeMap(TreeMap<String,Integer> tempMap1, TreeMap<String,Integer> tempMap2){
		TreeMap<String,Integer> treeMap1 = formatDateToIncrement(tempMap1);
		TreeMap<String,Integer> treeMap2 = formatDateToIncrement(tempMap2);
		
		treeMap1.put(RobotProperties.BIG_TIME_2255, 0);
		treeMap2.put(RobotProperties.BIG_TIME_2283, 0);
		
		TreeMap<String,Integer> treeMap = new TreeMap<String,Integer>();
		
		Iterator<Map.Entry<String, Integer>> iter1 = treeMap1.entrySet().iterator();
		Iterator<Map.Entry<String, Integer>> iter2 = treeMap2.entrySet().iterator();
		
		Map.Entry<String, Integer> entry1 = null;
		Map.Entry<String, Integer> entry2 = null;
		
		int treeMapLastvalue1 = 0;
		int treeMapLastvalue2 = 0;
		
		if(iter1.hasNext() && iter2.hasNext()) {
			entry1 = iter1.next();
			entry2 = iter2.next();
			
			while(iter1.hasNext() && iter2.hasNext()) {
				
				String keyStr1 = entry1.getKey();
				String keyStr2 = entry2.getKey();
				
				long key1 = Long.parseLong(keyStr1);
				long key2 = Long.parseLong(keyStr2);
			
				int length = treeMap.size();
				if(key1>key2) {
					if(length>0) {
						treeMapLastvalue2 = entry2.getValue()+treeMapLastvalue2;
						treeMap.put(keyStr2, treeMapLastvalue1+treeMapLastvalue2);
					//	System.out.println("mergeTreeMap  1 keyStr2: " +keyStr2 +" treeMapLastvalue: "+ (treeMapLastvalue1+treeMapLastvalue2)+" key1: " +key1 +" key2: "+ key2);
					}else {
						treeMapLastvalue2 = entry2.getValue();
						treeMap.put(keyStr2, treeMapLastvalue1+treeMapLastvalue2);
					//	System.out.println("mergeTreeMap  2 keyStr2: " +keyStr2 +" treeMapLastvalue: "+ (treeMapLastvalue1+treeMapLastvalue2)+" key1: " +key1 +" key2: "+ key2);
						
					}
					if(iter2.hasNext())
						entry2 = iter2.next();
				}else if(key1<key2) {
					if(length>0) {
						treeMapLastvalue1 = entry1.getValue()+treeMapLastvalue1;
						treeMap.put(keyStr1, treeMapLastvalue1+treeMapLastvalue2);
					//	System.out.println("mergeTreeMap  3 keyStr1: " +keyStr1 +" treeMapLastvalue: "+ (treeMapLastvalue1+treeMapLastvalue2)+" key1: " +key1 +" key2: "+ key2);
					}else {
						treeMapLastvalue1 = entry1.getValue();
						treeMap.put(keyStr1, treeMapLastvalue1+treeMapLastvalue2);
					//	System.out.println("mergeTreeMap  4 keyStr1: " +keyStr1 +" treeMapLastvalue: "+ (treeMapLastvalue1+treeMapLastvalue2)+" key1: " +key1 +" key2: "+ key2);
					}
					if(iter1.hasNext())
						entry1 = iter1.next();
				}else {
					if(length>0) {
						treeMapLastvalue1 = entry1.getValue()+treeMapLastvalue1;
						treeMapLastvalue2 = entry2.getValue()+treeMapLastvalue2;
						treeMap.put(keyStr1, treeMapLastvalue1+treeMapLastvalue2);
					//	System.out.println("mergeTreeMap  5 keyStr1: " +keyStr1 +" treeMapLastvalue: "+ (treeMapLastvalue1+treeMapLastvalue2)+" key1: " +key1 +" key2: "+ key2);
					}else {
						treeMapLastvalue1 = entry1.getValue()+treeMapLastvalue1;
						treeMapLastvalue2 = entry2.getValue()+treeMapLastvalue2;
						treeMap.put(keyStr1, treeMapLastvalue1+treeMapLastvalue2);
					//	System.out.println("mergeTreeMap  6 keyStr1: " +keyStr1 +" treeMapLastvalue: "+ (treeMapLastvalue1+treeMapLastvalue2)+" key1: " +key1 +" key2: "+ key2);
						
					}
					if(iter1.hasNext() && iter2.hasNext())
					{
						entry1 = iter1.next();
						entry2 = iter2.next();
					}
				}
			}
		}
		
		
		int sz = treeMap.size();
		while(iter1.hasNext()) {
			if(sz>0) {
				treeMapLastvalue1 = entry1.getValue()+treeMapLastvalue1;
				treeMap.put(entry1.getKey(), treeMapLastvalue1+treeMapLastvalue2);
			//	System.out.println("mergeTreeMap  7 keyStr1: " +entry1.getKey() +" treeMapLastvalue: "+ (treeMapLastvalue1+treeMapLastvalue2));
			}else {
				treeMapLastvalue1 = entry1.getValue();
				treeMap.put(entry1.getKey(), treeMapLastvalue1+treeMapLastvalue2);
			//	System.out.println("mergeTreeMap  8 keyStr1: " +entry1.getKey() +" treeMapLastvalue: "+ (treeMapLastvalue1+treeMapLastvalue2));
				
			}
			if(iter1.hasNext())
				entry1 = iter1.next();
		}
		sz = treeMap.size();
		while(iter2.hasNext()) {
			if(sz>0) {
				treeMapLastvalue2 = entry2.getValue()+treeMapLastvalue2;
				treeMap.put(entry2.getKey(), (treeMapLastvalue1+treeMapLastvalue2));
			//	System.out.println("mergeTreeMap  9 keyStr1: " +entry2.getKey() +" treeMapLastvalue: "+ (treeMapLastvalue1+treeMapLastvalue2));
			}else {
				treeMapLastvalue2 = entry2.getValue();
				treeMap.put(entry2.getKey(), (treeMapLastvalue1+treeMapLastvalue2));
			//	System.out.println("mergeTreeMap  10 keyStr1: " +entry2.getKey() +" treeMapLastvalue: "+ (treeMapLastvalue1+treeMapLastvalue2));
				
			}
			if(iter2.hasNext())
				entry2 = iter2.next();
		}
		for(Map.Entry<String,Integer>  m: treeMap.entrySet()) {
		//	System.out.println("mergeTreeMap  currentTime: " +m.getKey() +" power: "+ m.getValue());
		}
		return treeMap;
	}
	public TreeMap<String,Integer> formatDateToIncrement(TreeMap<String,Integer> tempMap){
		TreeMap<String,Integer> treeMap = new TreeMap<String,Integer>();
		int temp1 = 0;
		int temp2 = 0;
		for(Map.Entry<String, Integer> entry : tempMap.entrySet()) {
			temp2 = entry.getValue();
			treeMap.put(entry.getKey(), temp2-temp1);
			temp1 = temp2;
		}
//		for(Map.Entry<String,Integer>  m: treeMap.entrySet()) {
//			System.out.println("formatDateToIncrement  currentTime: " +m.getKey() +" power: "+ m.getValue());
//		}
		return treeMap;
	}
	@Override
	public int compareTo(Object o) {
		RobotDataStructure com = (RobotDataStructure)o;
		return robotItem.compareTo(com.getRobotItem());
	}
	public String getRobotItem() {
		return robotItem;
	}
	public void setRobotItem(String robotItem) {
		this.robotItem = robotItem;
	}
	public TreeMap<String, Integer> getRobotItemData() {
		return robotItemData;
	}
	public void setRobotItemData(TreeMap<String, Integer> robotItemData) {
		this.robotItemData = robotItemData;
	}
	
	
}

