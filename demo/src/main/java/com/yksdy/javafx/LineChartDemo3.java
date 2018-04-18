package com.yksdy.javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartDemo3 extends Application {

	  @Override
	  public void start(Stage stage) {
		  ObservableList<String> times = FXCollections.observableArrayList();
		  
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
		series.setName("My data");
		series.getData().add(new XYChart.Data<String, Number>("1", 23));
		series.getData().add(new XYChart.Data<String, Number>("2", 114));
		series.getData().add(new XYChart.Data<String, Number>("3", 15));
		series.getData().add(new XYChart.Data<String, Number>("4", 24));
		series.getData().add(new XYChart.Data<String, Number>("5", 134));
		times.add("1");
		times.add("2");
		times.add("5");
		
	    final CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Month");
	    final LineChart<String, Number> lineChart = new LineChart<String, Number>(xAxis, yAxis);
	    lineChart.setTitle("My Chart");
	    lineChart.getData().add(series);
	    xAxis.setAutoRanging(false);
	    xAxis.setGapStartAndEnd(true);
	    xAxis.autosize();
	    xAxis.setCategories(times);
	    
	    Scene scene = new Scene(lineChart, 800, 600);
	    stage.setScene(scene);
	    stage.show();
	  }

	  public static void main(String[] args) {
	    launch(args);
	  }
	}
