package com.kovansys.mvp.robot.statistic.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.kovansys.mvp.robot.statistic.properties.RobotProperties;

import javafx.collections.ObservableList;

public class BothiveSystem {
	
	private RobotDataStructure system;
	
	private TreeMap<String, TreeMap<String,Integer>> moverTree = new TreeMap<String, TreeMap<String,Integer>> ();
	private TreeMap<String, TreeMap<String,Integer>> pickerTree = new  TreeMap<String, TreeMap<String,Integer>>();
	private TreeMap<String, TreeMap<String,Integer>> singleSwitchTree = new  TreeMap<String, TreeMap<String,Integer>>();
	private TreeMap<String, TreeMap<String,Integer>> switcherTree = new  TreeMap<String, TreeMap<String,Integer>>();
	private TreeMap<String, TreeMap<String,Integer>> pusherTree = new  TreeMap<String, TreeMap<String,Integer>>();
	
	private RobotDataStructure mover;
	private RobotDataStructure picker;
	private RobotDataStructure singleSwitch;
	private RobotDataStructure switcher;
	private RobotDataStructure pusher;
	
	public void initBothiveSystem(ObservableList<Robot> robots) {
		for(Robot r : robots) {
			String entitySeq = r.getEntitySeq();
			String currentTime = r.getCurrentTime();
			int power = r.getPower();
			
			if(entitySeq.startsWith(RobotProperties.MOVER_ITEM_FLAG)) {
				if(moverTree.containsKey(entitySeq)) {
					TreeMap<String,Integer> moverData = moverTree.get(entitySeq);
//					if(moverData.containsKey(currentTime)){
//						int temp = moverData.get(currentTime);
//						moverData.put(currentTime,r.getPower()+temp);
//					}else 
					{
						moverData.put(currentTime, r.getPower());
					}
					moverTree.put(entitySeq, moverData);
				}else {
					TreeMap<String,Integer> moverData = new TreeMap<String,Integer>();
					moverData.put(currentTime, power);
					moverTree.put(entitySeq, moverData);
					
				}
			}
			else if(entitySeq.startsWith(RobotProperties.PICKER_ITEM_FLAG)) {
				if(pickerTree.containsKey(entitySeq)) {
					TreeMap<String,Integer> data = pickerTree.get(entitySeq);
//					if(data.containsKey(currentTime)){
//						int temp = data.get(currentTime);
//						data.put(currentTime,r.getPower()+temp);
//					}else
					{
						data.put(currentTime, r.getPower());
					}
					pickerTree.put(entitySeq, data);
				}else {
					TreeMap<String,Integer> data = new TreeMap<String,Integer>();
					data.put(currentTime, power);
					pickerTree.put(entitySeq, data);
					
				}
			}else if(entitySeq.startsWith(RobotProperties.SINGLE_SWITCH_ITEM_FLAG)) {
				if(singleSwitchTree.containsKey(entitySeq)) {
					TreeMap<String,Integer> data = singleSwitchTree.get(entitySeq);
//					if(data.containsKey(currentTime)){
//						int temp = data.get(currentTime);
//						data.put(currentTime,power+temp);
//						System.out.println(" currentTime: " +currentTime +" power: "+ power);
//					}else 
					{
						data.put(currentTime, power);
					}
					singleSwitchTree.put(entitySeq, data);
				}else {
					TreeMap<String,Integer> data = new TreeMap<String,Integer>();
					data.put(currentTime, power);
					singleSwitchTree.put(entitySeq, data);
					
				}
			}else if(entitySeq.startsWith(RobotProperties.SWITCH_ITEM_FLAG)) {
				if(switcherTree.containsKey(entitySeq)) {
					TreeMap<String,Integer> data = switcherTree.get(entitySeq);
//					if(data.containsKey(currentTime)){
//						int temp = data.get(currentTime);
//						data.put(currentTime,r.getPower()+temp);
//					}else 
					{
						data.put(currentTime, r.getPower());
					}
					switcherTree.put(entitySeq, data);
				}else {
					TreeMap<String,Integer> data = new TreeMap<String,Integer>();
					data.put(currentTime, power);
					switcherTree.put(entitySeq, data);
					
				}
			}else if(entitySeq.startsWith(RobotProperties.PUSHER_ITEM_FLAG)) {
				if(pusherTree.containsKey(entitySeq)) {
					TreeMap<String,Integer> data = pusherTree.get(entitySeq);
//					if(data.containsKey(currentTime)){
//						int temp = data.get(currentTime);
//						data.put(currentTime,r.getPower()+temp);
//					}else 
					{
						data.put(currentTime, r.getPower());
					}
					pusherTree.put(entitySeq, data);
				}else {
					TreeMap<String,Integer> data = new TreeMap<String,Integer>();
					data.put(currentTime, power);
					pusherTree.put(entitySeq, data);
					
				}
			}
		}
		Collection<TreeMap<String, Integer>> tempCollection = moverTree.values();
		List<TreeMap<String,Integer>> list = new ArrayList<TreeMap<String,Integer>>();
		
		for(TreeMap<String, Integer> tm : tempCollection) {
			list.add(tm);
		}
		mover = new RobotDataStructure(RobotProperties.MOVER, list);
		
		tempCollection = pickerTree.values();
		list.clear();
		for(TreeMap<String, Integer> tm : tempCollection) {
			list.add(tm);
		}
		picker = new RobotDataStructure(RobotProperties.PICKER, list);
		
		tempCollection = singleSwitchTree.values();
		list.clear();
		for(TreeMap<String, Integer> tm : tempCollection) {
			list.add(tm);
		}
		singleSwitch = new RobotDataStructure(RobotProperties.SINGLE_SWITCH, list);
//		for(Map.Entry<String,Integer>  m: singleSwitch.getRobotItemData().entrySet()) {
//			System.out.println("MAP  currentTime: " +m.getKey() +" power: "+ m.getValue());
//		}
		
		tempCollection = switcherTree.values();
		list.clear();
		for(TreeMap<String, Integer> tm : tempCollection) {
			list.add(tm);
		}
		switcher = new RobotDataStructure(RobotProperties.SWITCH, list);
		
		tempCollection = pusherTree.values();
		list.clear();
		for(TreeMap<String, Integer> tm : tempCollection) {
			list.add(tm);
		}
		pusher = new RobotDataStructure(RobotProperties.PUSHER, list);
		
		
		list.clear();
		list.add(mover.getRobotItemData());
		list.add(picker.getRobotItemData());
		list.add(singleSwitch.getRobotItemData());
		list.add(switcher.getRobotItemData());
		list.add(pusher.getRobotItemData());
		system = new RobotDataStructure(RobotProperties.BOTHIVE_SYSTEM, list);
		
		
	}
	
	
	public RobotDataStructure getSystem() {
		return system;
	}




	public void setSystem(RobotDataStructure system) {
		this.system = system;
	}




	public TreeMap<String, TreeMap<String, Integer>> getMoverTree() {
		return moverTree;
	}




	public void setMoverTree(TreeMap<String, TreeMap<String, Integer>> moverTree) {
		this.moverTree = moverTree;
	}




	public TreeMap<String, TreeMap<String, Integer>> getPickerTree() {
		return pickerTree;
	}




	public void setPickerTree(TreeMap<String, TreeMap<String, Integer>> pickerTree) {
		this.pickerTree = pickerTree;
	}




	public TreeMap<String, TreeMap<String, Integer>> getSingleSwitchTree() {
		return singleSwitchTree;
	}




	public void setSingleSwitchTree(TreeMap<String, TreeMap<String, Integer>> singleSwitchTree) {
		this.singleSwitchTree = singleSwitchTree;
	}




	public TreeMap<String, TreeMap<String, Integer>> getSwitcherTree() {
		return switcherTree;
	}




	public void setSwitcherTree(TreeMap<String, TreeMap<String, Integer>> switcherTree) {
		this.switcherTree = switcherTree;
	}




	public TreeMap<String, TreeMap<String, Integer>> getPusherTree() {
		return pusherTree;
	}




	public void setPusherTree(TreeMap<String, TreeMap<String, Integer>> pusherTree) {
		this.pusherTree = pusherTree;
	}




	public RobotDataStructure getMover() {
		return mover;
	}




	public void setMover(RobotDataStructure mover) {
		this.mover = mover;
	}




	public RobotDataStructure getPicker() {
		return picker;
	}




	public void setPicker(RobotDataStructure picker) {
		this.picker = picker;
	}




	public RobotDataStructure getSingleSwitch() {
		return singleSwitch;
	}




	public void setSingleSwitch(RobotDataStructure singleSwitch) {
		this.singleSwitch = singleSwitch;
	}




	public RobotDataStructure getSwitcher() {
		return switcher;
	}




	public void setSwitcher(RobotDataStructure switcher) {
		this.switcher = switcher;
	}




	public RobotDataStructure getPusher() {
		return pusher;
	}




	public void setPusher(RobotDataStructure pusher) {
		this.pusher = pusher;
	}





	
}
