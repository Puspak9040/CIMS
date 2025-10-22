package com.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

import org.testng.AssertJUnit;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.cycle.Cycle;
import com.cycle.CycleInventory;
import com.cycle.IPurchaseRecord;
import com.cycle.PurchaseRecord;
import com.cycle.sort.ISortBy;
import com.cycle.sort.Purchased_bysort;

public class CycleInventoryTest {
	private CycleInventory inventory;

	@BeforeMethod(enabled = true)
	public void setup() {
		inventory = new CycleInventory();
		inventory.setCurrentStock(50);
	}

	@Test(enabled = true, groups = "smoke")
	public void testAddStock() {
		inventory.addStock(30);
		AssertJUnit.assertEquals(inventory.getCurrentStock(), 80);
	}

	@Test(enabled = true, groups = "funtional")
	public void testPurchaseSuccess() throws Exception {
		Cycle cycle = new Cycle("Hero", "Red");
		inventory.purchase(10, cycle, "puspak", "Hyderabad");
		AssertJUnit.assertEquals(inventory.getCurrentStock(), 40);
	}

	@Test(groups = "regression", enabled = true, expectedExceptions = Exception.class, expectedExceptionsMessageRegExp = "\\w+ Not successful\\. .*")
	public void testPurchaseFailure() throws Exception {
		Cycle cycle = new Cycle("Hero", "Red");
		inventory.purchase(100, cycle, "puspak", "Hyderabad");
	} // exception expected as ordered quanity more than stock
		// quqntity

	@Test(enabled = true, groups = "smoke")
	public void testPurchaseHistory() throws Exception {
		Cycle cycle = new Cycle("Hero", "Red");
		inventory.purchase(10, cycle, "puspak", "Hyderabad");
		List<IPurchaseRecord> history = inventory.getAllPurchaseHistory();
		AssertJUnit.assertEquals(history.size(), 1);
	}

	/*
	 * @Test(enabled = true, groups = "smoke") public void
	 * testSortedPurchaseHistoryByName() throws Exception { Cycle cycle1 = new
	 * Cycle("Hero", "Red"); Cycle cycle2 = new Cycle("Atlas", "Blue"); Cycle cycle3
	 * = new Cycle("Firefox", "Green");
	 * 
	 * inventory.purchase(1, cycle1, "Zara", "Mumbai"); inventory.purchase(1,
	 * cycle2, "Anjali", "Delhi"); inventory.purchase(1, cycle3, "Ravi",
	 * "Secunderabad");
	 * 
	 * inventory.sortPurchaseHistoryByName(); List<PurchaseRecord> sortedHistory =
	 * inventory.getAllPurchaseHistory();
	 * 
	 * AssertJUnit.assertEquals(sortedHistory.get(0).getPurchasedBy(), "Anjali");
	 * AssertJUnit.assertEquals(sortedHistory.get(1).getPurchasedBy(), "Ravi");
	 * AssertJUnit.assertEquals(sortedHistory.get(2).getPurchasedBy(), "Zara");
	 * 
	 * System.out.println("Sorted purchase records by name:"); for (IPurchaseRecord
	 * record : sortedHistory) { System.out.println(record.toString()); } }
	 */

	@Test(groups = "functional")
	public void testInventory() throws Exception {
		CycleInventory hydInventory = new CycleInventory();
		hydInventory.setMaxStock(100);
		hydInventory.setMinStock(10);
		AssertJUnit.assertEquals(hydInventory.getMaxStock(), 100);
		AssertJUnit.assertEquals(hydInventory.getMinStock(), 10);
		hydInventory.setCurrentStock(0);
		AssertJUnit.assertEquals(hydInventory.getCurrentStock(), 0);
		hydInventory.setCurrentStock(10);
		AssertJUnit.assertEquals(hydInventory.getCurrentStock(), 10);
		hydInventory.purchase(3, new Cycle("Atlas", "Blue"), "Debasish Pradhan",
				"Flat 102, Sri Sai Balaji Residency, New Cyber Valley, Kondapur, Hyderabad");
		AssertJUnit.assertEquals(hydInventory.getCurrentStock(), 7);

		Thread.sleep(5000);
		hydInventory.purchase(3, new Cycle("Atlas", "Orange"), "Arup Mishra", "Shaikput, Golkonda, Hyderabad");
		AssertJUnit.assertEquals(hydInventory.getCurrentStock(), 4);

		Thread.sleep(10000);
		hydInventory.purchase(4, new Cycle("Atlas", "Red"), "Hare Krishna", "World ONe School, Kondapur, Hyderabad");
		AssertJUnit.assertEquals(hydInventory.getCurrentStock(), 0);

		List<PurchaseRecord> purchaseRecords = hydInventory.getAllPurchaseHistory();
		AssertJUnit.assertEquals(purchaseRecords.size(), 3);
		for (PurchaseRecord pr : purchaseRecords) {
			System.out.println(pr);
		}

	}

	@Test(expectedExceptions = Exception.class, expectedExceptionsMessageRegExp = "\\w+ Not successful. Current stock is 0")
	public void testInventoryOrder() throws Exception {
		CycleInventory hydInventory = new CycleInventory();

		hydInventory.purchase(1, new Cycle("Avon", "Black"), "Puspak", "Hafeezpet, Hyderabad");

	}

	@Test(groups = "regression")
	public void testOrder() throws Exception {
		CycleInventory hydInventory = new CycleInventory();
		hydInventory.setMaxStock(100);
		hydInventory.setMinStock(10);
		AssertJUnit.assertEquals(hydInventory.getMaxStock(), 100);
		AssertJUnit.assertEquals(hydInventory.getMinStock(), 10);
		hydInventory.setCurrentStock(0);
		AssertJUnit.assertEquals(hydInventory.getCurrentStock(), 0);
		hydInventory.setCurrentStock(10);
		AssertJUnit.assertEquals(hydInventory.getCurrentStock(), 10);
		hydInventory.purchase(3, new Cycle("Atlas", "Blue"), "Debasish Pradhan",
				"Flat 102, Sri Sai Balaji Residency, New Cyber Valley, Kondapur, Hyderabad");
		AssertJUnit.assertEquals(hydInventory.getCurrentStock(), 7);
		Thread.sleep(1000);
		hydInventory.purchase(3, new Cycle("Atlas", "Orange"), "Arup Mishra", "Shaikput, Golkonda, Hyderabad");
		AssertJUnit.assertEquals(hydInventory.getCurrentStock(), 4);
		Thread.sleep(1000);
		hydInventory.purchase(4, new Cycle("Atlas", "Red"), "Hare Krishna", "World ONe School, Kondapur, Hyderabad");
		AssertJUnit.assertEquals(hydInventory.getCurrentStock(), 0);

		/*
		 * List<PurchaseRecord> purchaseRecords = hydInventory.getAllPurchaseHistory();
		 * hydInventory.sortPurchaseHistory("byaddress"); // use choice to sort
		 * "byname","bydate","byaddress"
		 * System.out.println("Sorted purchase records by address:"); for
		 * (PurchaseRecord record : purchaseRecords) { System.out.println(record);*
		 * 
		 * }
		 */
		List<PurchaseRecord> listPurchaseHistory = hydInventory.getAllPurchaseHistory();
		Purchased_bysort ps = new Purchased_bysort();
		Collections.sort(listPurchaseHistory, ps);

		System.out.println("Sorted purchased_by name:");
		for (PurchaseRecord record : listPurchaseHistory) {
			System.out.println(record);
		}

		ps = new Purchased_bysort(ISortBy.BY_PUR_DATE);
		System.out.println("Sorted purchased_by date:");
		Collections.sort(listPurchaseHistory, ps);
		for (PurchaseRecord record : listPurchaseHistory) {
			System.out.println(record);
		}

	}

	@DataProvider
	public Object[][] getData() {
		return new Object[][] { { 3, new Cycle("Atlas", "Orange"), "Arup Mishra", "Shaikput, Golkonda, Hyderabad" },
				{ 4, new Cycle("Atlas", "Red"), "Hare Krishna", "World One School, Kondapur, Hyderabad" } };
	}

	@Test(dataProvider = "getData")
	public void testPurchase(int quantity, Cycle cycle, String purchased_by, String purchased_by_address) {
		CycleInventory hydInventory = new CycleInventory();
		hydInventory.setCurrentStock(20);

		try {
			hydInventory.purchase(quantity, cycle, purchased_by, purchased_by_address);
			System.out.println("Purchase successful for " + purchased_by);
			System.out.println("Remaining stock: " + hydInventory.getCurrentStock());

		} catch (Exception e) {
			System.out.println("Purchase failed for " + purchased_by + ": " + e.getMessage());
		}
	}

	/*
	 * @Test() public void testPurchaseRecordSerialization() throws Exception {
	 * String path = "C:\\Users\\lenovo\\Desktop\\serialization\\s.txt";
	 * CycleInventory hydInventory = new CycleInventory();
	 * hydInventory.setCurrentStock(10); hydInventory.purchase(3, new Cycle("Avon",
	 * "Blue"), "Puspak Sahoo", "Bhubaneswar"); hydInventory.purchase(3, new
	 * Cycle("Firefox", "Orange"), "Rajesh", "Shaikput, Golkonda, Hyderabad");
	 * 
	 * List<PurchaseRecord> purchaseRecords = hydInventory.getAllPurchaseHistory();
	 * Assert.assertEquals(purchaseRecords.size(), 2); for (PurchaseRecord pr :
	 * purchaseRecords) { System.out.println(pr); }
	 * 
	 * FileOutputStream fos = new FileOutputStream(path); ObjectOutputStream oos =
	 * new ObjectOutputStream(fos); oos.writeObject(hydInventory); oos.close();
	 * fos.close(); System.out.println("file saveeed successfully");
	 * 
	 * FileInputStream fis = new FileInputStream(path); ObjectInputStream ois = new
	 * ObjectInputStream(fis); hydInventory = (CycleInventory) ois.readObject();
	 * System.out.println("file deserialised successfully"); //
	 * System.out.println(hydInventory.getAllPurchaseHistory());
	 * List<PurchaseRecord> purchaseRecord = hydInventory.getAllPurchaseHistory();
	 * for (PurchaseRecord pr : purchaseRecord) { System.out.println(pr); }
	 * ois.close(); fis.close();
	 * 
	 * }
	 */
	@Test()
	public void purchaseRecordSerialization() throws Exception {
		String path2 = "C:\\Users\\lenovo\\Desktop\\serialization\\s2.txt";
		PurchaseRecord record = new PurchaseRecord(3, new Cycle("Roadstar", "blue"), "Alok prusty", "Bhubaneswar");
		FileOutputStream fos = new FileOutputStream(path2);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(record);
		System.out.println(record);
		oos.close();
		fos.close();
		System.out.println("file saveeed successfully");

	}

	@Test()
	public void deSerialization() throws Exception {
		String path2 = "C:\\Users\\lenovo\\Desktop\\serialization\\s2.txt";
		FileInputStream fis = new FileInputStream(path2);
		ObjectInputStream ois = new ObjectInputStream(fis);
		PurchaseRecord record = (PurchaseRecord) ois.readObject();
		System.out.println("file deserialised successfully");
		System.out.println(record);
		ois.close();
	}

}
