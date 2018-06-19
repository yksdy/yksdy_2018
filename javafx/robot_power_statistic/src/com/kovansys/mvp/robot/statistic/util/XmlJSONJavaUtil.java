package com.kovansys.mvp.robot.statistic.util;

import com.kovansys.mvp.robot.statistic.model.Robot;
import com.kovansys.mvp.robot.statistic.properties.RobotProperties;

public class XmlJSONJavaUtil {

	public static String getPowerAndName(String s) {
		String name="";
		int power=0;
		String actionType="";
		String currentTime="";
		String entitySeq="";
		
		String[] temp = s.split(",");
		for(String str : temp) {
			if(str.contains("actionType")) {
				actionType = str.substring("actionType".length()+4, str.length()-1);
			}else if(str.contains("currentTime")) {
				currentTime = str.substring("currentTime".length()+4, str.length()-1);
			}else if(str.contains("entitySeq")) {
				entitySeq = str.substring("entitySeq".length()+4, str.length()-1);
			}
		}

		if(entitySeq.startsWith(RobotProperties.MOVER_ITEM_FLAG)) {
			name = RobotProperties.MOVER;
			if("CHARGING_START".equalsIgnoreCase(actionType)) {
				power = 300;
			}else if("CHARGING_END".equalsIgnoreCase(actionType)) {
				power = 0;
			}else{
				power = 0;
			}
		}else if(entitySeq.startsWith(RobotProperties.PICKER_ITEM_FLAG)) {
			name = RobotProperties.PICKER;
			if("END_PICK".equalsIgnoreCase(actionType)) {
				power = 150;
			}else {
				power = 500;
			}
		}else if(entitySeq.startsWith(RobotProperties.SINGLE_SWITCH_ITEM_FLAG)) {
			name = RobotProperties.SINGLE_SWITCH;
			if("END_CHANGE".equalsIgnoreCase(actionType)) {
				power = 10;
			}else {
				power = 50;
			}
		}else if(entitySeq.startsWith(RobotProperties.SWITCH_ITEM_FLAG)) {
			name = RobotProperties.SWITCH;
			if("END_CHANGE".equalsIgnoreCase(actionType)) {
				power = 25;
			}else {
				power = 150;
			}
		}else if(entitySeq.startsWith(RobotProperties.PUSHER_ITEM_FLAG)) {
			name = RobotProperties.PUSHER;
			if("END_RESUME".equalsIgnoreCase(actionType)) {
				/// TO DO add log
				power = 45;
			}else {
				power = 800;
			}
		}else {
			return "";
		}
	//	System.out.println("{"+ s + ",\"name\":"+"\""+name+ "\""+",\"power\":"+ power+"}");
		return "{"+ s + ",\"name\":"+"\""+name+ "\""+",\"power\":"+ power+"}";
	}
	
	public static void handelRobotPowerAndName(Robot r) {
		String name="";
		int power=0;
		String actionType=r.getActionType();
		String currentTime=r.getCurrentTime();
		String entitySeq=r.getEntitySeq();
		
		if(entitySeq.startsWith(RobotProperties.MOVER_ITEM_FLAG)) {
			name = RobotProperties.MOVER;
			if("CHARGING_START".equalsIgnoreCase(actionType)) {
				power = 300;
			}else if("CHARGING_END".equalsIgnoreCase(actionType)) {
				power = 0;
			}else{
				power = 0;
			}
		}else if(entitySeq.startsWith(RobotProperties.PICKER_ITEM_FLAG)) {
			name = RobotProperties.PICKER;
			if("END_PICK".equalsIgnoreCase(actionType)) {
				power = 150;
			}else {
				power = 500;
			}
		}else if(entitySeq.startsWith(RobotProperties.SINGLE_SWITCH_ITEM_FLAG)) {
			name = RobotProperties.SINGLE_SWITCH;
			if("END_CHANGE".equalsIgnoreCase(actionType)) {
				power = 10;
			}else {
				power = 50;
			}
		}else if(entitySeq.startsWith(RobotProperties.SWITCH_ITEM_FLAG)) {
			name = RobotProperties.SWITCH;
			if("END_CHANGE".equalsIgnoreCase(actionType)) {
				power = 25;
			}else {
				power = 150;
			}
		}else if(entitySeq.startsWith(RobotProperties.PUSHER_ITEM_FLAG)) {
			name = RobotProperties.PUSHER;
			if("END_RESUME".equalsIgnoreCase(actionType)) {
				/// TO DO add log
				power = 45;
			}else {
				power = 800;
			}
		}
		r.setName(name);
		r.setPower(power);
	//	System.out.println("{"+ s + ",\"name\":"+"\""+name+ "\""+",\"power\":"+ power+"}");
	//	return "{"+ s + ",\"name\":"+"\""+name+ "\""+",\"power\":"+ power+"}";
	}

//	public static File jsonToXML(File file) {
//		File xmlFile = null;
//		try {
//			String strTmp = "";
//			StringBuffer str = new StringBuffer();
//			StringBuffer s = new StringBuffer();
//			BufferedReader buffReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
//			while ((strTmp = buffReader.readLine()) != null) {
//				strTmp = strTmp.substring(1, strTmp.length()-1);
//				String[] formatStr = strTmp.split(",");
//				int index=0;
//				for(int i=0;i<formatStr.length;i++) {
//					if(formatStr[i].contains("actionType")||formatStr[i].contains("currentTime")||formatStr[i].contains("entitySeq")) {
//						index++;
//						if(index>=3) {
//							s = s.append(formatStr[i]); 
//						}else {
//							s = s.append(formatStr[i]).append(",");
//						}
//					}
//				}
//				str = str.append(getPowerAndName(s.toString())).append(";");
//				s.setLength(0);
//				
//			}
//			String[] jsonStr = str.toString().split(";");
//			///JSONArray json = null;// JSONArray.fromObject(jsonStr);
//			//XMLSerializer xmlSerializer = new XMLSerializer();
//			xmlSerializer.setRootName("robots");
//			xmlSerializer.setElementName("robot");
//			xmlSerializer.setTypeHintsEnabled(false);
//			String xml = xmlSerializer.write(json);
//			
//		//	String xmlFilePath = file.getPath()+".xml";
//		//	String xmlFilePath = file.getAbsolutePath()+".xml";
//			String xmlFilePath = file.getCanonicalPath()+".xml";
//			xmlFile = new File(xmlFilePath);
//			FileWriter writer = new FileWriter(xmlFile);
//			writer.write(xml);
//			buffReader.close();
//			writer.flush();
//			writer.close();
//		} catch (IOException e) {
//			System.out.println("#################--err--#######################");
//			e.printStackTrace();
//		}
//		return xmlFile;
//	}
}
