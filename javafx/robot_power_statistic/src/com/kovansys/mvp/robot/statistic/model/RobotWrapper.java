package com.kovansys.mvp.robot.statistic.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * 
 * @author meng.zhaoyang
 */
@XmlRootElement(name = "robots")
public class RobotWrapper {

    private List<Robot> robots;

    @XmlElement(name = "robot")
    public List<Robot> getRobots() {
        return robots;
    }

    public void setRobots(List<Robot> robots) {
        this.robots = robots;
    }
}