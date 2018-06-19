package com.yksdy.javafx;



import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartDemo1 extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		
		Group root = new Group();
		Scene scene = new Scene(root,400,300);
		primaryStage.setScene(scene);
		primaryStage.setTitle("javaFX");
		primaryStage.show();
	}
	
	

}
