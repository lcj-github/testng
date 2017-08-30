package com.lcj.testng.dependent;

import org.testng.annotations.Test;

public class DependentTestExamples {

	@Test(dependsOnMethods = { "testTwo" })
	public void testOne() {
		System.out.println("Test method one");
	}

	@Test
	public void testTwo() {
		System.out.println("Test method two");
	}
}
