package com.kovansys.mvp.robot.statistic.view;

import java.text.DateFormatSymbols;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import com.kovansys.mvp.robot.statistic.MainApp;
import com.kovansys.mvp.robot.statistic.model.BothiveSystem;
import com.kovansys.mvp.robot.statistic.model.Robot;
import com.kovansys.mvp.robot.statistic.properties.RobotProperties;
import com.kovansys.mvp.robot.statistic.util.FormatDateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

public class RobotOverviewController {
	@FXML
	private TreeView<String> systemTreeView;
	
	@FXML
    private LineChart<String, Integer> lineChart;
	
//	@FXML
//    private StackedBarChart<String, Integer> stackedBarChart;
	
	@FXML
    private CategoryAxis xAxis;
	
//	@FXML
//    private CategoryAxis xAxisStackedBarChart;
	
	@FXML
	private Slider slider;
	
	@FXML
	private RadioButton browseMode;
	
	@FXML
	private RadioButton panoramaMode;
	
	@FXML
    private Label maxPower;
	
    @FXML
    private Label minPower;
	
	@FXML
	private void initialize() {
		
		initTreeView();
		String[] months = DateFormatSymbols.getInstance(Locale.ENGLISH).getMonths();
		times.addAll(Arrays.asList(months));
        xAxis.setCategories(times);
//        xAxisStackedBarChart.setCategories(times);
	}
	
	ObservableList<String> times = FXCollections.observableArrayList();
	
	private TreeItem<String> rootTreeItem;
	private TreeItem<String> moverTreeItem;
	private TreeItem<String> pickerTreeItem;
	private TreeItem<String> singleSwitchTreeItem;
	private TreeItem<String> switchTreeItem;
	private TreeItem<String> pusherTreeItem;
	
	private MainApp mainApp = null;
	
	private TreeMap<String,Integer> systemDataMap;
	private TreeItem<String> newValue;
	boolean isBothiveSystemStatistic = false;
	
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
//      systemDataMap = setRobotData(mainApp.getRobotData(),rootTreeItem);
        loadTreeItems(moverTreeItem, loadRobotItems(RobotProperties.MOVER_ITEM_FLAG));
    	loadTreeItems(pickerTreeItem, loadRobotItems(RobotProperties.PICKER_ITEM_FLAG));
    	loadTreeItems(singleSwitchTreeItem, loadRobotItems(RobotProperties.SINGLE_SWITCH_ITEM_FLAG));
    	loadTreeItems(switchTreeItem, loadRobotItems(RobotProperties.SWITCH_ITEM_FLAG));
    	loadTreeItems(pusherTreeItem, loadRobotItems(RobotProperties.PUSHER_ITEM_FLAG));
    }
	
	public void initTreeView() {
		rootTreeItem = new TreeItem<String>(RobotProperties.BOTHIVE_SYSTEM);
		moverTreeItem = new TreeItem<String>(RobotProperties.MOVER);
		pickerTreeItem = new TreeItem<String>(RobotProperties.PICKER);
		singleSwitchTreeItem = new TreeItem<String>(RobotProperties.SINGLE_SWITCH);
		switchTreeItem = new TreeItem<String>(RobotProperties.SWITCH);
		pusherTreeItem = new TreeItem<String>(RobotProperties.PUSHER);
		
		rootTreeItem.getChildren().add(moverTreeItem);
		rootTreeItem.getChildren().add(pickerTreeItem);
		rootTreeItem.getChildren().add(singleSwitchTreeItem);
		rootTreeItem.getChildren().add(switchTreeItem);
		rootTreeItem.getChildren().add(pusherTreeItem);
		rootTreeItem.setExpanded(true);
		
		systemTreeView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> {
					showLineChart(mainApp.getRobotData(), newValue, true);
					});
		systemTreeView.setRoot(rootTreeItem);
		
	}

	public void loadTreeItems(TreeItem<String> root, Set<String> rootItems ) {
		for (String itemString : rootItems) {
			root.getChildren().add(new TreeItem<String>(itemString));
		}
	}
	public Set<String> loadRobotItems(String robotMode) {
		Set<String> robotItems = new TreeSet<String>();
		if(mainApp!=null) {
			ObservableList<Robot> robots = mainApp.getRobotData();
			for (Robot r : robots) {
				if(r.getEntitySeq().startsWith(robotMode)) {
					robotItems.add(r.getEntitySeq());
				}
			}
		}
		return robotItems;
	}
	
	public void setLineChartData(TreeMap<String,Integer> treeMapData, TreeItem<String> newValue, boolean clear) {
		    String treeItemValue = newValue.getValue();
		    XYChart.Series<String, Integer> series = new XYChart.Series<>();
	        for(Map.Entry<String, Integer> entry : treeMapData.entrySet() ) {
	        	if(RobotProperties.BOTHIVE_SYSTEM.equals(treeItemValue)) {
	        		// 中央控制设备功率: 1000  单台工作站功率 300
	        		isBothiveSystemStatistic = true;
	        		series.getData().add(new XYChart.Data<>(FormatDateUtil.formatDate2yMdHmsS(entry.getKey()), (entry.getValue()+1000+300)));
	        	}else {
	        		isBothiveSystemStatistic = false;
	        		series.getData().add(new XYChart.Data<>(FormatDateUtil.formatDate2yMdHmsS(entry.getKey()), entry.getValue()));
	        	}
	        }
	        series.setName("Bothive power statistic");
	        
	        lineChart.setAnimated(true);
//	        stackedBarChart.setAnimated(true);
	        times.clear();
	        
	        int itemRange =0;
	        if(!browseMode.isSelected()) {
	        	itemRange = (int)slider.getValue();
	        }
	        try {
	        	Set<String> xKey = treeMapData.keySet();
	        	Iterator<String> iter = xKey.iterator();
	        	int i = 0;
	        	while(iter.hasNext()) {
	        		i++;
	        		String dateStr = iter.next();
	        		if(i< itemRange) continue;
	        		if(i> (itemRange+200)) break;
	        		times.add(FormatDateUtil.formatDate2yMdHmsS(dateStr));
	        	}
	        	xAxis.autosize();
	    //    	xAxis.setAnimated(true);
	        	xAxis.setCategories(times);
//	        	xAxisStackedBarChart.autosize();
//	        	xAxisStackedBarChart.setCategories(times);
	        }catch (Exception e) {
	        	e.printStackTrace();
	        }
	        
	        if(times.size()>1) {
	        	if(!browseMode.isSelected()) {
	        		series.setName(treeItemValue+" All Items: "+treeMapData.size()+" Current Range Items: "+itemRange+" ~ "+(itemRange+200));
		        }else {
		        	series.setName(treeItemValue+" All Items: "+treeMapData.size());
		        }
	        }
	        else {
	        	series.setName(treeItemValue+": "+times.size()+" item");
	        }
	        if(clear) {
	          lineChart.getData().clear();
//	          stackedBarChart.getData().clear();
	        }
	        lineChart.getData().add(series);
//	        stackedBarChart.getData().add(series);
	        
	}
	
	public TreeMap<String,Integer> entityDataSource(TreeMap<String,Integer> dataMap) {
		 TreeMap<String,Integer> treeMapData = new TreeMap<String, Integer>();
			Map.Entry<String, Integer> itemRight = dataMap.lastEntry();
			Map.Entry<String, Integer> itemLeft = null;
			for (Map.Entry<String, Integer> entry : dataMap.entrySet()) {
				if(treeMapData.isEmpty()) {
					treeMapData.put(entry.getKey(), entry.getValue());
					itemLeft = entry;
				}else if(!entry.getValue().equals(itemLeft.getValue())) {
					treeMapData.put(entry.getKey(), entry.getValue());
					itemLeft = entry;
				}
			}
			if(itemLeft!=null) {
				if(itemRight.getValue().equals(itemLeft.getValue())&&!itemRight.getKey().equals(itemLeft.getKey())) {
					treeMapData.put(itemRight.getKey(), itemRight.getValue());
				}
			}
			
		 return treeMapData;
	}
	public TreeMap<String,Integer> setRobotData(ObservableList<Robot> robots, TreeItem<String> newValue) {
		TreeMap<String,Integer> dataMap = new TreeMap<String, Integer>();
		String treeItemValue = newValue.getValue();
    	
		dataMap = getRobotItem(treeItemValue);
//    	}else if(RobotProperties.BOTHIVE_SYSTEM.equals(treeItemValue)){
//    		//dataMap.put(currentTime, r.getPower()+1000+300);// 中央控制设备功率: 1000  单台工作站功率 300
//    	}
		
		
//        for (Robot r : robots) {
//        	if(treeItemValue.equals(r.getEntitySeq())) {
//        		if(r.getPower()<=0) {
//        			continue;
//        		}
//        		String currentTime = r.getCurrentTime();
//        		if(dataMap.containsKey(currentTime)){
//            		int temp = dataMap.get(currentTime);
//            		dataMap.put(currentTime,r.getPower()+temp);
//            	}else {
//            		
//            			dataMap.put(currentTime, r.getPower());
//            	}
//        	}
//        		
//        }
		
		
        return dataMap;
    }
	public TreeMap<String,Integer> getRobotItem(String name){
		TreeMap<String,Integer> treeMapData = new TreeMap<String, Integer>();
		BothiveSystem bs = mainApp.getBothiveSystem();
		if(RobotProperties.BOTHIVE_SYSTEM.equals(name)) {
			treeMapData = bs.getSystem().getRobotItemData();
		}else if(RobotProperties.MOVER.equals(name)) {
			treeMapData = bs.getMover().getRobotItemData();
		}else if(RobotProperties.PICKER.equals(name)) {
			treeMapData = bs.getPicker().getRobotItemData();
		}else if(RobotProperties.SINGLE_SWITCH.equals(name)) {
			treeMapData = bs.getSingleSwitch().getRobotItemData();
		}else if(RobotProperties.SWITCH.equals(name)) {
			treeMapData = bs.getSwitcher().getRobotItemData();
		}else if(RobotProperties.PUSHER.equals(name)) {
			treeMapData = bs.getPusher().getRobotItemData();
		}else if(name.startsWith(RobotProperties.MOVER_ITEM_FLAG)) {
			treeMapData = bs.getMoverTree().get(name);
		}else if(name.startsWith(RobotProperties.PICKER_ITEM_FLAG)) {
			treeMapData = bs.getPickerTree().get(name);
		}else if(name.startsWith(RobotProperties.SINGLE_SWITCH_ITEM_FLAG)) {
			treeMapData = bs.getSingleSwitchTree().get(name);
		}else if(name.startsWith(RobotProperties.SWITCH_ITEM_FLAG)) {
			treeMapData = bs.getSwitcherTree().get(name);
		}else if(name.startsWith(RobotProperties.PUSHER_ITEM_FLAG)) {
			treeMapData = bs.getPusherTree().get(name);
		}
		

		return treeMapData;
	}
	public void showLineChart(ObservableList<Robot> robots, TreeItem<String> newValue, boolean clear) {
		this.newValue = newValue;
		systemDataMap =  entityDataSource(setRobotData(robots,newValue));
		slider.setMax(systemDataMap.size());
	//	System.out.println("---  "+systemDataMap.size());
		if(browseMode.isSelected()) {
			handleBrowseModeSelected();
		}else {
			handlePanoramaModeSelected();
		}
		getMaxAndMinPowerInfo();
	}
	public void getMaxAndMinPowerInfo() {
		maxPower.setText("0"); 
		minPower.setText("0"); 
		TreeMap<Integer,String> dataMap = new TreeMap<Integer,String>();
		for(Map.Entry<String, Integer> entry : systemDataMap.entrySet()) {
			dataMap.put( entry.getValue(),entry.getKey());
		}
		if(dataMap.size()>0) {
			if(isBothiveSystemStatistic) {
				int max = dataMap.lastEntry().getKey()+1000+300;
				int min = dataMap.firstEntry().getKey()+1000+300;
				maxPower.setText(""+max); 
				minPower.setText(""+min); 
			}else {
				maxPower.setText(dataMap.lastEntry().getKey().toString()); 
				minPower.setText(dataMap.firstEntry().getKey().toString()); 
			}
		}
	}
	@FXML
    private void handleSliderMouseDragReleased() {
		if(browseMode.isSelected()) {
			handleBrowseModeSelected();
		}else {
			handlePanoramaModeSelected();
		}
		getMaxAndMinPowerInfo();
    }
	@FXML
    private void handlePanoramaModeSelected() {
		setLineChartData(systemDataMap,newValue,true);
		getMaxAndMinPowerInfo();
    }
	@FXML
    private void handleBrowseModeSelected() {
		TreeMap<Integer,String> dataMap = new TreeMap<Integer,String>();
		TreeMap<String,Integer> browseDataMap = new TreeMap<String,Integer>();
		if(systemDataMap.size()<200) {
			setLineChartData(systemDataMap,newValue,true);
		} else {
			for(Map.Entry<String, Integer> entry : systemDataMap.entrySet()) {
				dataMap.put( entry.getValue(),entry.getKey());
			}
			if(isBothiveSystemStatistic) {
				int max = dataMap.lastEntry().getKey()+1000+300;
				int min = dataMap.firstEntry().getKey()+1000+300;
				maxPower.setText(""+max); 
				minPower.setText(""+min); 
			}else {
				maxPower.setText(dataMap.lastEntry().getKey().toString()); 
				minPower.setText(dataMap.firstEntry().getKey().toString()); 
			}
			for(Map.Entry<Integer, String> entry : dataMap.descendingMap().entrySet()) {
				browseDataMap.put( entry.getValue(),entry.getKey());
			}
			setLineChartData(browseDataMap,newValue,true);
		}

    }
}
