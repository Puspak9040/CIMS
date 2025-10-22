
package com.cycle;

import java.util.List;

public interface IInventory {
	void setCurrentStock(int currentStock);

	int getCurrentStock();

	void addStock(int addStock);

	void purchase(int quantity, Cycle cycle, String purchased_by, String purchased_by_address) throws Exception;

	List<?> getAllPurchaseHistory();

}
