package com.lcj.testng;

import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

//添加log4testng.properties进行配置
public class IndependentTest {
	
private static Logger log = Logger.getLogger(IndependentTest.class);
	
	@Test(threadPoolSize = 3, invocationCount = 6, timeOut = 1000)
    public void testMethod()
    {
        Long id = Thread.currentThread().getId();        
        log.info("Test method executing on thread with id: "+id);
    }

}
