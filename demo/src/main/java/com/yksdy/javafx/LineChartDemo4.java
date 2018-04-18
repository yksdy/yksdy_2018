package com.yksdy.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartDemo4 extends Application {

	  @Override
	  public void start(Stage stage) {


	    XYChart.Series<String, Number> series1 = new XYChart.Series<String, Number>();
	    series1.setName("Portfolio 1");
	    series1.getData().add(new XYChart.Data<String, Number>("Jan", 23));
	    series1.getData().add(new XYChart.Data<String, Number>("Feb", 14));
	    series1.getData().add(new XYChart.Data<String, Number>("Mar", 15));
	    XYChart.Series<String, Number> series2 = new XYChart.Series<String, Number>();
	    series2.setName("Portfolio 2");
	    series2.getData().add(new XYChart.Data<String, Number>("Jan", 33));
	    series2.getData().add(new XYChart.Data<String, Number>("Feb", 34));
	    series2.getData().add(new XYChart.Data<String, Number>("Mar", 25));
	    series2.getData().add(new XYChart.Data<String, Number>("Apr", 44));
	    XYChart.Series<String, Number> series3 = new XYChart.Series<String, Number>();
	    series3.setName("Portfolio 3");
	    series3.getData().add(new XYChart.Data<String, Number>("Jan", 44));
	    series3.getData().add(new XYChart.Data<String, Number>("Feb", 35));
	    series3.getData().add(new XYChart.Data<String, Number>("Mar", 36));
	    series3.getData().add(new XYChart.Data<String, Number>("Apr", 33));
	    series3.getData().add(new XYChart.Data<String, Number>("May", 31));
	    
	    
	    final CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Month");
	    final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
	    lineChart.setTitle("My Chart");
	    lineChart.getData().addAll(series1, series2, series3);
	    
	    
	    Scene scene = new Scene(lineChart, 800, 600);
	    stage.setScene(scene);
	    stage.show();
	  }
	
	
	
	  public static void main(String[] args) {
	    launch(args);
	  }
	}
