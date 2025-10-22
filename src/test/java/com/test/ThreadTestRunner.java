package com.test;

import java.text.MessageFormat;
import java.util.List;

import org.testng.annotations.Test;

import com.cycle.CycleInventory;
import com.cycle.PurchaseRecord;
import com.cycle.ThreadTest;

public class ThreadTestRunner {

	@Test
	public void purchaseThread() throws InterruptedException {
		CycleInventory inventory = new CycleInventory();
		inventory.setMaxStock(10);
		inventory.setCurrentStock(10);

		Thread t1 = new Thread(new ThreadTest(inventory));
		Thread t2 = new Thread(new ThreadTest(inventory));
		Thread t3 = new Thread(new ThreadTest(inventory));
		Thread t4 = new Thread(new ThreadTest(inventory));
		Thread t5 = new Thread(new ThreadTest(inventory));
		Thread t6 = new Thread(new ThreadTest(inventory));
		Thread t7 = new Thread(new ThreadTest(inventory));
		Thread t8 = new Thread(new ThreadTest(inventory));
		Thread t9 = new Thread(new ThreadTest(inventory));
		Thread t10 = new Thread(new ThreadTest(inventory));

		t1.setName("Debasish");
		t2.setName("Puspak");
		t3.setName("Hare Krishna");
		t4.setName("Promod Sahoo");
		t5.setName("Anjan Nani");
		t6.setName("Prabira Pradhan");
		t7.setName("Deepak Sahoo");
		t8.setName("Sumitra Sahoo");
		t9.setName("Pratyush Mohanty");
		t10.setName("Abhiram Kumar");

		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();

		t1.join();
		t2.join();
		t3.join();
		t4.join();
		t5.join();
		t6.join();
		t7.join();
		t8.join();
		t9.join();
		t10.join();

		System.out.println(String.format("Current Stock - %d", inventory.getCurrentStock()));
		List<PurchaseRecord> records = inventory.getAllPurchaseHistory();
		System.out.println(MessageFormat.format("Total purchases: {0}", records.size()));
		for (PurchaseRecord pr : records) {
			System.out.println(pr);

		}
	}
}