package com.cycle;

public interface Inventory {
	void setCurrentStock(int currentStock);

	int getCurrentStock();

	void setAddStock(int addStock);

	int getAddStock();

	void addStock();

	void purchase(int purchase);
}
