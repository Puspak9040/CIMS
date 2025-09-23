package com.test;

import java.time.LocalDate;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.cycle.CycleInventory;

public class CycleInventoryTest {

	private CycleInventory inventory;

	@BeforeMethod
	public void setup() {
		inventory = new CycleInventory();
	}

	@Test(description = "Add stock when current stock is below minStock")
	public void testAddStockBelowMin() {
		inventory.setCurrentStock(5);
		inventory.addStock(20);
		inventory.addStock();
		Assert.assertEquals(inventory.getCurrentStock(), 25);
	}

	@Test(description = "Do not add stock when current stock is above minStock")
	public void testAddStockAboveMin() {
		inventory.setCurrentStock(15);
		inventory.addStock(20);
		inventory.addStock();
		Assert.assertEquals(inventory.getCurrentStock(), 15);
	}

	@Test(description = "Purchase reduces stock correctly")
	public void testValidPurchase() {
		inventory.setCurrentStock(30);
		inventory.purchase(10);
		Assert.assertEquals(inventory.getCurrentStock(), 20);
	}

	@Test(description = "Purchase fails when amount exceeds current stock")
	public void testPurchaseExceedsStock() {
		inventory.setCurrentStock(5);
		inventory.purchase(10); // Invalid purchase
		Assert.assertEquals(inventory.getCurrentStock(), 5); // Should remain unchanged
	}

	@Test(description = "Purchase fails when amount is zero or negative")
	public void testZeroOrNegativePurchase() {
		inventory.setCurrentStock(20);
		inventory.purchase(0);
		Assert.assertEquals(inventory.getCurrentStock(), 20);

		inventory.purchase(-5);
		Assert.assertEquals(inventory.getCurrentStock(), 20);
	}

	@Test(description = "Set and get person name and date")
	public void testPersonNameAndDate() {
		inventory.setPersonName("puspak");
		inventory.setdate(LocalDate.of(2025, 9, 22));

		Assert.assertEquals(inventory.getPersonName(), "puspak");
		Assert.assertEquals(inventory.getDate(), LocalDate.of(2025, 9, 22));
	}

	@Test(priority = 1, description = "Verify static maxStock and minStock values")
	public void testStockLimits() {
		Assert.assertEquals(CycleInventory.getMaxStock(), 100);
		Assert.assertEquals(CycleInventory.getMinStock(), 10);
	}
}