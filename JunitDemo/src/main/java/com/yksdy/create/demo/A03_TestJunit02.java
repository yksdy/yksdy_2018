package com.yksdy.create.demo;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class A03_TestJunit02 extends TestCase{
    protected double fValue1;
    protected double fValue2;
    @Before
    public void setUp() {
        fValue1 = 2.0;
        fValue2 = 3.0;
    }
    @Test
    public void testAdd() {
        System.out.println("No of Test case = "+this.countTestCases());
        String name = this.getName();
        System.out.println("Test case name = "+name);
        this.setName("testNewAdd");
        String newName = this.getName();
        System.out.println("New Test case name = "+newName);
    }
    public void tearDown() {
        
    }
}
