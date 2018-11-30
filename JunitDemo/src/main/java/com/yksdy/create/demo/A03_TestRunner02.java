package com.yksdy.create.demo;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class A03_TestRunner02 {
    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(A03_TestJunit02.class);
        for(Failure failure : result.getFailures()) {
            System.out.println("Failure: "+failure.toString());
        }
        System.out.println("Success: "+result.wasSuccessful());
    }
}
