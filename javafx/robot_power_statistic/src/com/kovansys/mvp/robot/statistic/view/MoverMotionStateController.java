package com.kovansys.mvp.robot.statistic.view;

import java.util.List;

import com.kovansys.mvp.robot.statistic.model.Robot;
import com.kovansys.mvp.robot.statistic.properties.RobotProperties;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

public class MoverMotionStateController {
	
	@FXML
	private void initialize() {
		
	}
	
	private ObservableList<Robot> moverData = FXCollections.observableArrayList();
    public ObservableList<Robot> getMoverData() {
    	return moverData;
    }
	
	public void setMoverData(ObservableList<Robot> robots) {
		for(Robot r : robots) {
			if(RobotProperties.MOVER.equals(r.getName())) {
				moverData.add(r);
			}
		}
	}
}
