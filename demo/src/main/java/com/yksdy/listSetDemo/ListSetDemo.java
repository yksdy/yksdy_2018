package com.yksdy.listSetDemo;

import java.util.ArrayList;
import java.util.List;

public class ListSetDemo {
	public static void main(String[] args) {
		Request r = new Request();
		r.setOrderId("123");
		r.setItems(getItems());
		System.out.println(r.getOrderId());
		for(Item i: r.getItems()) {
			System.out.println(i.toString());
		}
		System.out.println(r.getItems());
	}
	public static List<Item> getItems() {
		List<Item> items = new ArrayList<Item>();
		for(int i=0;i<3;i++) {
			Item item = new Item();
			item.setBatchNo("batchNo"+i);
			item.setLineId("lineId"+i);
			item.setMaterialId("materialId"+i);
			item.setProDate("proDate"+i);
			item.setQty(i);
			items.add(item);
		}
		return items;
	}
}
