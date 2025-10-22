package com.cycle;

public class ThreadTest implements Runnable {
	private CycleInventory inventory;

	public ThreadTest(CycleInventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public void run() {

		try {
			String threadName = Thread.currentThread().getName();
			inventory.purchase(1, new Cycle("Hero", "Orange"), threadName, "Shaikput");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}

	}
}
