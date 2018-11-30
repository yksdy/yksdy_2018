package com.yksdy.create.demo;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class A02_TestRunner {
   public static void main(String[] args) {
      Result result = JUnitCore.runClasses(A02_TestJunit.class);
      for (Failure failure : result.getFailures()) {
         System.out.println("failure: "+failure.toString());
      }
      System.out.println("success: "+result.wasSuccessful());
   }
}
