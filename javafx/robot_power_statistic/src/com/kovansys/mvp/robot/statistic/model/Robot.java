package com.kovansys.mvp.robot.statistic.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author meng.zhaoyang
 */
public class Robot {
	private StringProperty entitySeq;
	private StringProperty currentTime;
	private StringProperty actionType;
	private StringProperty name;
	private IntegerProperty power;
	public Robot() {
		 this("", "0", "");
	}
	
	public Robot( String entitySeq, String currentTime, String actionType) {
		this.entitySeq = new SimpleStringProperty(entitySeq);
		this.currentTime = new SimpleStringProperty(currentTime);
		this.actionType = new SimpleStringProperty(actionType);
		this.power = new SimpleIntegerProperty(0);
		this.name = new SimpleStringProperty("");
	}

	public final StringProperty nameProperty() {
		return this.name;
	}
	

	public final String getName() {
		return this.nameProperty().get();
	}
	

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}
	

	public final StringProperty entitySeqProperty() {
		return this.entitySeq;
	}
	

	public final String getEntitySeq() {
		return this.entitySeqProperty().get();
	}
	

	public final void setEntitySeq(final String entitySeq) {
		this.entitySeqProperty().set(entitySeq);
	}
	

	public final StringProperty currentTimeProperty() {
		return this.currentTime;
	}
	

	public final String getCurrentTime() {
		return this.currentTimeProperty().get();
	}
	

	public final void setCurrentTime(final String currentTime) {
		this.currentTimeProperty().set(currentTime);
	}
	

	public final StringProperty actionTypeProperty() {
		return this.actionType;
	}
	

	public final String getActionType() {
		return this.actionTypeProperty().get();
	}
	

	public final void setActionType(final String actionType) {
		this.actionTypeProperty().set(actionType);
	}
	

	public final IntegerProperty powerProperty() {
		return this.power;
	}
	

	public final int getPower() {
		return this.powerProperty().get();
	}
	

	public final void setPower(final int power) {
		this.powerProperty().set(power);
	}
	

	
	
	
}
