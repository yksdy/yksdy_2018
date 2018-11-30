package com.yksdy.create.demo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
public class A02_TestJunit{
    String message = "Hello World";
    A02_MessageUtil messageUtil = new A02_MessageUtil(message);
    
    @Test
    public void testPrintMessage(){
        assertEquals(message,messageUtil.printMessage());
    }
}
