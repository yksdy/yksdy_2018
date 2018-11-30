package com.yksdy.create.demo;

import static org.junit.Assert.*;

import org.junit.Test;
public class A03_TestJunit01 {
    @Test
    public void testAdd() {
        int num = 5;
        String temp = null;
        String str = "Junit is working fie";
        assertEquals("Junit is working fine", str);
        assertFalse(num>6);
        assertNotNull(str);
    }
}
