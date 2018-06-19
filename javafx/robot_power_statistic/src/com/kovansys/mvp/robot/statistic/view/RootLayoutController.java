package com.kovansys.mvp.robot.statistic.view;

import java.io.File;

import com.kovansys.mvp.robot.statistic.MainApp;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

public class RootLayoutController {
	private MainApp mainApp;

	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
	}

	@FXML
	private void handleOpen() {
		FileChooser fileChooser = new FileChooser();

		FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("files (*.*)", "*.*");
		fileChooser.getExtensionFilters().add(extFilter);
		// Show save file dialog
		File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
		if (file != null) {
			System.out.println(file.getPath());
			mainApp.loadRobotDataFromFile(file);
		}
		
	}
	
	@FXML
    private void handleExit() {
        System.exit(0);
    }
	
	@FXML
    private void handleShowMoverMotionState() {
      mainApp.showMoverMotionState();
    }

}
