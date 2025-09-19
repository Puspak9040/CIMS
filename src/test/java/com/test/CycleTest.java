package com.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cycle.Cycle;

public class CycleTest {

	@Test
	public void testCycleInitialization() {
		Cycle cycle1 = new Cycle();
		Assert.assertNotNull(cycle1);
		Assert.assertTrue(cycle1 instanceof Cycle);

		Assert.assertNull(cycle1.getColor());
		Assert.assertNull(cycle1.getModel());

		cycle1.setColor("Yellow");
		cycle1.setModel("Atlas");

		Assert.assertEquals(cycle1.getColor(), "Yellow");
		Assert.assertEquals(cycle1.getModel(), "Atlas");

		Cycle cycle2 = new Cycle();
		cycle2.setColor("Yellow");
		cycle2.setModel("Atlas");

		Assert.assertEquals(cycle1.equals(cycle2), false);
	}
}