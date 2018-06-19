package com.yksdy.listSetDemo;

import java.util.ArrayList;
import java.util.List;

public class Request {
  private String orderId;
  private List<Item> items = new ArrayList<Item>();

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

}
