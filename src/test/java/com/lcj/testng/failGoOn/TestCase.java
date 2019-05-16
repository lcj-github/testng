package com.lcj.testng.failGoOn;

import org.testng.AssertJUnit;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//@Listeners(com.lcj.testng.failGoOn.AssertListener.class) 配置在testng.xml中
public class TestCase {	
	@Test
	public void Case1(){		
		AssertJUnit.assertEquals(1,2);
		System.out.println("=========Case1======="); //该方法不执行，因为上面的方法抛出异常，未被捕获处理。		
	}
	
	@Test
	public void Case2(){		
		Assertion.verifyEquals(1, 2);		
		System.out.println("=========Case2=======");//该方法会被执行，即使上面的方法抛出异常，但被处理了。
	}
	
	@Test
	public void Case3(){		
		Assertion.verifyEquals(1, 2);		
		System.out.println("=========Case3=======");//该方法会被执行，即使上面的方法抛出异常，但被处理了。
	}
 

}
