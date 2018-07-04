package com.lcj.testng.listener;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * MyListenerTest 类名前 添加 ：@Listeners标签，会在执行@Test标签方法前会执行：MyMethodInterceptor中 的方法
 * 具体执行效果如下： 
 * 
 *  只执行一次，在方法执行前
	groups +[]
	groups +[]
	groups +[]
	groups +[]
	=================test1=============
	=================test2=============
	只执行一次，在方法执行后
 *
 */
@Listeners({  MyMethodInterceptor.class }) //TEST方法执行前执行
public class MyListenerTest {
	
	@BeforeTest
	public void setUp() throws Exception {		
		System.out.println("只执行一次，在方法执行前");
	}
	
	@Test
	public void test1() { 
		System.out.println("=================test1=============");
	}  
    
	@Test
	public void test2() {  
		System.out.println("=================test2=============");
	} 
	
	@AfterTest
	public void tearDown() throws Exception {
		System.out.println("只执行一次，在方法执行后");
	}

}
