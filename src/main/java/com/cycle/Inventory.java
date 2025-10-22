
package com.cycle;

import java.util.List;

public interface Inventory {
	void setCurrentStock(int currentStock);

	int getCurrentStock();

	void addStock(int addStock);

	void purchase(int purchase);

	List<IPurchaseRecord> getAllPurchaseHistory();

}
