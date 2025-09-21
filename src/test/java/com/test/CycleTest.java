package com.test;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cycle.Cycle;

public class CycleTest {
	Cycle cycle1 = null;
	Cycle cycle2 = null;

	@BeforeTest()
	public void initCycle() {

		cycle1 = new Cycle();
		cycle2 = new Cycle();

	}

	@BeforeMethod()
	public void displayTestsRunning(Method m) {
		System.out.println(String.format("Test To Run = %s", m.getName()));
	}

//	@BeforeMethod
//	public void showTestName(ITestResult result) {
//		Method method = result.getMethod().getConstructorOrMethod().getMethod();
//		Test testAnnotation = method.getAnnotation(Test.class);
//		String testName = testAnnotation.testName();
//		System.out.println("Running Test: " + testName);
//	}

	@AfterMethod
	public void displayTestsHavingRun(Method m) {
		System.out.println(String.format("Test Execution Completed = %s", m.getName()));
	}

	@Test(description = "verify default constructor , objects should not be null")
	public void testCycleInitialization() {

		Assert.assertNotNull(cycle1);
		Assert.assertTrue(cycle1 instanceof Cycle);
	}

	@Test(description = "verify object's attributes are null with default constructor and objet's attributes are set using setters")
	public void testGetters() {
		Assert.assertNull(cycle1.getColor());
		Assert.assertNull(cycle1.getModel());

		cycle1.setColor("Yellow");
		cycle1.setModel("Atlas");

		Assert.assertEquals(cycle1.getColor(), "Yellow");
		Assert.assertEquals(cycle1.getModel(), "Atlas");
	}

	@Test(description = "== compares memory of 2 objects which should be unique for both")
	public void testCycleEqualsOperator() {
		cycle2.setColor("Yellow");
		cycle2.setModel("Atlas");
		Assert.assertEquals(cycle1 == cycle2, false);
	}

	@Test(dependsOnMethods = "testGetters", description = "verify equals method must be overloaded to operate using equals method")
	public void testCycleEqualsMethodOverloading() {

		/*
		 * it will fail unless equals returns true... developer needs to implement
		 * equals by his own, as default equals does memory comparison.
		 */

		Assert.assertEquals(cycle1.equals(cycle2), true);
	}
}