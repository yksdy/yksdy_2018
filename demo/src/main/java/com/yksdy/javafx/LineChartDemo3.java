package com.yksdy.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartDemo3 extends Application {

	  @Override
	  public void start(Stage stage) {
	    final CategoryAxis xAxis = new CategoryAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Month");

	    final LineChart<String, Number> lineChart = new LineChart<String, Number>(
	        xAxis, yAxis);

	    lineChart.setTitle("My Chart");

	    XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
	    series.setName("My data");

	    series.getData().add(new XYChart.Data<String, Number>("Jan", 23));
	    series.getData().add(new XYChart.Data<String, Number>("Feb", 114));
	    series.getData().add(new XYChart.Data<String, Number>("Mar", 15));
	    series.getData().add(new XYChart.Data<String, Number>("Apr", 24));
	    series.getData().add(new XYChart.Data<String, Number>("May", 134));

	    Scene scene = new Scene(lineChart, 800, 600);
	    lineChart.getData().add(series);

	    stage.setScene(scene);
	    stage.show();
	  }

	  public static void main(String[] args) {
	    launch(args);
	  }
	}
