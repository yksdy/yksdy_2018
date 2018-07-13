package com.yksdy.listSetDemo;

import java.util.ArrayList;
import java.util.List;


public class ListGetDemo {
	public static void main(String[] args) {
		List<String> list = new ArrayList<String>();
		list.add("a0");
		list.add("a1");
		list.add("a2");
		list.add("a3");
		String a = "a1";
		//if(list.contains(a)) {
			int i = list.indexOf(a);
			String str = list.get(i);
			System.out.println(str);
			
		//}
	}

}
class CountHead{
	private String headId;

	public String getHeadId() {
		return headId;
	}

	public void setHeadId(String headId) {
		this.headId = headId;
	}
	
}
class CountLine{
	private String lineId;
	
	public String getLineId() {
		return lineId;
	}

	public void setLinieId(String lineId) {
		this.lineId = lineId;
	}
}
