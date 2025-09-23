package com.cycle;

import java.time.LocalDate;

public class CycleInventory implements Inventory {
	private static final int maxStock = 100;
	private static final int minStock = 10;
	private int currentStock;
	private int addStock;
	private int purchase;
	private String personName;
	private LocalDate date;

	// getter for maxstock
	public static int getMaxStock() {
		return maxStock;
	}

	// getter for min stock
	public static int getMinStock() {
		return minStock;
	}

	@Override
	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;

	}

	@Override
	public int getCurrentStock() {
		return currentStock;
	}

	@Override
	public void setAddStock(int addStock) {
		this.addStock = addStock;

	}

	@Override
	public int getAddStock() {

		return addStock;
	}

	@Override
	public void addStock() {
		if (currentStock <= minStock) {
			currentStock += addStock;
			System.out.println("stock added successfully . the current stock is" + currentStock);
		} else {
			System.out.println("Stock is sufficent no need to add stock");
		}
	}

	// getter for purchase
	public int getPurchase() {
		return purchase;
	}

	// setter for purchase
	public void setPurchase(int purchase) {
		this.purchase = purchase;
	}

	@Override
	public void purchase(int purchase) {
		if (purchase > 0 && purchase <= currentStock) {
			currentStock -= currentStock;
			System.out.println("purchase successfully.the current stock is" + currentStock);
		} else {
			System.out.println("insufficent stock the current stock is" + currentStock);
		}

	}

	// getter for person name
	public String getPersonName() {
		return personName;
	}

	// setter for personname
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	// getter for date
	public LocalDate getDate() {
		return date;
	}

	// setter for date
	public void setdate(LocalDate date) {
		this.date = date;
	}
}
