package com.lcj.maven;

import static org.junit.Assert.*;

import org.junit.Test;


public class TestHello {
	
	@Test
    public void testHello(){ 
		Hello h = new Hello();         
        assertEquals(h.sayHello("jizg"),"hello :jizg");
    }
	
	@Test
    public void testHello2(){ 
		Hello h = new Hello();         
        assertEquals(h.sayHello("jizgtest"),"hello :jizgtest");
    }

}
