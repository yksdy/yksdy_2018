package com.kovansys.mvp.robot.statistic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;
import java.util.prefs.Preferences;

import com.alibaba.fastjson.JSON;
import com.kovansys.mvp.robot.statistic.model.BothiveSystem;
import com.kovansys.mvp.robot.statistic.model.Robot;
import com.kovansys.mvp.robot.statistic.properties.RobotProperties;
import com.kovansys.mvp.robot.statistic.view.MoverMotionStateController;
import com.kovansys.mvp.robot.statistic.view.RobotOverviewController;
import com.kovansys.mvp.robot.statistic.view.RootLayoutController;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/*
 * @author meng.zhaoyang
 */
public class MainApp extends Application {

	private Stage primaryStage;
	private BorderPane rootLayout;

	private BothiveSystem bothiveSystem = new BothiveSystem();

	private ObservableList<Robot> robotData = FXCollections.observableArrayList();

	public ObservableList<Robot> getRobotData() {
		return robotData;
	}

	public MainApp() {

	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}

	public BothiveSystem getBothiveSystem() {
		return bothiveSystem;
	}

	public void setBothiveSystem(BothiveSystem bothiveSystem) {
		this.bothiveSystem = bothiveSystem;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Power Statistic");
		this.primaryStage.getIcons().add(new Image("file:resources/image/bothiveIcon.png"));

		initRootLayout();

		showRobotOverview();
		// System.out.println("----RobotOverview controller: start ");
		// File file = getRobotFilePath();
		// if (file != null) {
		// loadRobotDataFromFile(file);
		// }

	}

	public void initRootLayout() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);

			RootLayoutController controller = loader.getController();
			// System.out.println("----RootLayout controller: "+controller);
			controller.setMainApp(this);

			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void showRobotOverview() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/RobotOverview.fxml"));
			AnchorPane robotOverview = (AnchorPane) loader.load();
			rootLayout.setCenter(robotOverview);
			RobotOverviewController controller = loader.getController();
			// System.out.println("----RobotOverview controller: "+controller);
			controller.setMainApp(this);
			if (robotData.size() > 0) {
				// controller.showLineChart(robotData, new
				// TreeItem<String>(RobotProperties.MOVER),false);
				// controller.showLineChart(robotData, new
				// TreeItem<String>(RobotProperties.PICKER),false);
				// controller.showLineChart(robotData, new
				// TreeItem<String>(RobotProperties.SINGLE_SWITCH),false);
				// controller.showLineChart(robotData, new
				// TreeItem<String>(RobotProperties.SWITCH),false);
				// controller.showLineChart(robotData, new
				// TreeItem<String>(RobotProperties.PUSHER),false);
				controller.showLineChart(robotData, new TreeItem<String>(RobotProperties.BOTHIVE_SYSTEM), false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Map<String, Integer> getBothiveSystemItem(ObservableList<Robot> allItems) {
		Map<String, Integer> dataMap = new TreeMap<String, Integer>();
		for (Robot r : allItems) {
			if (r.getPower() <= 0) {
				continue;
			}
			String currentTime = r.getCurrentTime();
			// System.out.println(r.getName()+" "+r.getEntitySeq()+" "+r.getPower()+"
			// "+r.getCurrentTime()+" "+currentTime);
			if (dataMap.containsKey(currentTime)) {
				int temp = dataMap.get(currentTime);
				dataMap.put(currentTime, r.getPower() + temp);
			} else {
				dataMap.put(currentTime, r.getPower() + 1000 + 300);// 中央控制设备功率: 1000 单台工作站功率 300
			}
		}
		// System.out.println("BothiveSystemItem = "+dataMap.size());
		return dataMap;
	}

	public File getRobotFilePath() {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		String filePath = prefs.get("filePath", null);
		if (filePath != null) {
			return new File(filePath);
		} else {
			return null;
		}
	}

	public void setRobotFilePath(File file) {
		Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
		if (file != null) {
			prefs.put("filePath", file.getPath());
			primaryStage.setTitle("Power Statistic - " + file.getName());
		} else {
			prefs.remove("filePath");
			primaryStage.setTitle("Power Statistic");
		}
	}

	public void loadRobotDataFromFile(File file) {
		try {
//			JAXBContext context = JAXBContext.newInstance(RobotWrapper.class);
//			Unmarshaller um = context.createUnmarshaller();
//			File xmlFile = XmlJSONJavaUtil.jsonToXML(file);
//			RobotWrapper wrapper = (RobotWrapper) um.unmarshal(XmlJSONJavaUtil.jsonToXML(file));
			//robotData.addAll(wrapper.getRobots());
			
			robotData.clear();
			try (BufferedReader read = new BufferedReader(new FileReader(file))) {
				String line = null; 
				while ((line = read.readLine()) != null) {
					Robot robot = JSON.parseObject(line, Robot.class);
					handelRobotPowerAndName(robot);
					robotData.add(robot);
				}
			}
			bothiveSystem.initBothiveSystem(robotData);
			// setRobotFilePath(file);
			showRobotOverview();
		} catch (Exception e) { // catches ANY exception
			e.printStackTrace();
			// Dialogs.create()
			// .title("Error")
			// .masthead("Could not load data from file:\n" + file.getPath())
			// .showException(e);
		}
	}
	public void handelRobotPowerAndName(Robot r) {
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
	}

	public void showMoverMotionState() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(MainApp.class.getResource("view/MoverMotionState.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Mover Motion State");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			MoverMotionStateController controller = loader.getController();
			if (robotData.size() > 0) {
				// System.out.println("----robotData.size(): "+robotData.size());
				// System.out.println("----MoverMotionState controller: "+controller);

				controller.setMoverData(robotData);
			}

			dialogStage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}