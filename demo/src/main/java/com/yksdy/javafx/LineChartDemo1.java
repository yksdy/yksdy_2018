package com.yksdy.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineChartDemo1 extends Application {

	  @Override
	  public void start(Stage stage) {
	    
	    XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
	    series.setName("My Data");
	    // populating the series with data
	    series.getData().add(new XYChart.Data<Number, Number>(1, 23));
	    series.getData().add(new XYChart.Data<Number, Number>(2, 114));
	    series.getData().add(new XYChart.Data<Number, Number>(3, 15));
	    series.getData().add(new XYChart.Data<Number, Number>(4, 124));
	    
	    
	    final NumberAxis xAxis = new NumberAxis();
	    final NumberAxis yAxis = new NumberAxis();
	    xAxis.setLabel("Number of Month");
	    final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
	    lineChart.setTitle("Line Chart");
	    lineChart.getData().add(series);
	    
	    
	    Scene scene = new Scene(lineChart, 800, 600);
	    stage.setScene(scene);
	    stage.show();
	  }

	  public static void main(String[] args) {
	    launch(args);
	  }
	}
