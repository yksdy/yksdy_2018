package com.yksdy.create.demo;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class A03_TestRunner01 {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(A03_TestJunit01.class);
        for(Failure failure : result.getFailures()) {
            System.out.println("failure: "+failure.toString());
        }
        System.out.println("success: "+result.wasSuccessful());
    }
}
