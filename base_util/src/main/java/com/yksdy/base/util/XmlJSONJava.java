package com.yksdy.base.util;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

public class XmlJSONJava {
	public static void main(String[] args) {
		XmlJSONJava a = new XmlJSONJava();
		a.jsonToXML();
		a.xmlToJSON();
	}
	public void jsonToJAVA(String jsonStr) {
		JSONObject jsonObj = JSONObject.fromObject(jsonStr);
		String username = jsonObj.getString("username");
		String password = jsonObj.optString("password");
	}

	public void javaToJSON() {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("username", "张三");
		jsonObj.put("password", "");
	}

	public void jsonToXML() {
		String[] jsonStr = {"{\"password\":\"456\",\"username\":\"aaa\"}","{\"password\":\"123\",\"username\":\"张三\"}\"}"};
		JSONArray json = JSONArray.fromObject(jsonStr);
		XMLSerializer xmlSerializer = new XMLSerializer();
		xmlSerializer.setRootName("user_info");
		xmlSerializer.setElementName("user");
		xmlSerializer.setTypeHintsEnabled(false);
		String xml = xmlSerializer.write(json);
		System.out.println(xml);;
	}

	public void xmlToJSON() {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><user_info><password></password><username>张三</username></user_info>";
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSON json = xmlSerializer.read(xml);
		System.out.println(json);;
	}

	public void javaBeanToJSON() {
//		Robot robot = new Robot();
//		robot.setId("testid");
//		robot.setName("testname");
//		robot.setStatus("teststatus");
//		robot.setTime("testtime");
//		JSONObject json = JSONObject.fromObject(robot);
	}

	public void javaBeanToXML() {
//		Robot robot = new Robot();
//		robot.setId("testid");
//		robot.setName("testname");
//		robot.setStatus("teststatus");
//		robot.setTime("testtime");
//		JSONObject json = JSONObject.fromObject(robot);
//		XMLSerializer xmlSerializer = new XMLSerializer();
//		String xml = xmlSerializer.write(json, "UTF-8");
	}

}