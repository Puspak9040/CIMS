
package com.cycle;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class CycleInventory implements IInventory, Serializable {
	private int maxStock = 100;
	private int minStock = 10;
	private IPurchaseRecord record = null;
	private int currentStock;
	private List<PurchaseRecord> PurchaseHistory = new ArrayList<PurchaseRecord>();

	public void setMaxStock(int stock) {
		this.maxStock = stock;
	}

	public void setMinStock(int stock) {
		this.minStock = stock;
	}

	// getter for maxstock
	public int getMaxStock() {
		return this.maxStock;
	}

	// getter for min stock
	public int getMinStock() {
		return this.minStock;
	}

	@Override
	public void setCurrentStock(int currentStock) {
		this.currentStock = currentStock;

	}

	@Override
	public int getCurrentStock() {
		return this.currentStock;
	}

	// public void sortPurchaseHistoryByName() {
	// PurchaseHistory.sort((r1, r2) ->
	// r1.getPurchasedBy().compareToIgnoreCase(r2.getPurchasedBy()));
	// }

	@Override
	public synchronized void purchase(int quantity, Cycle cycle, String purchased_by, String purchased_by_address)
			throws Exception {
		if (quantity > 0 && quantity <= this.currentStock) {

			try {

				PurchaseRecord record = new PurchaseRecord(quantity, cycle, purchased_by, purchased_by_address);
				PurchaseHistory.add(record);
				this.currentStock -= quantity;
				System.out.println(MessageFormat.format("{0} - Purchase Successful. Current Stocke - {1}",
						Thread.currentThread().getName(), this.currentStock));
//				System.out.println(String.format("Cycle purchased  by %s", Thread.currentThread().getName()));

			} catch (Exception e) {
				throw new Exception("Purchase Not successful. Current stock is " + this.currentStock);
			}
		} else

		{
			System.out.println(String.format("Purchase not successful by [%s]. Current stock is: %d",
					Thread.currentThread().getName(), this.getCurrentStock()));

			throw new Exception("Purchase Not successful. Current stock is " + this.currentStock);
		}

	}

	@Override
	public List getAllPurchaseHistory() {
		return PurchaseHistory;
	}

	@Override
	public void addStock(int addStock) {
		if (addStock > 0 && currentStock + addStock <= maxStock) {
			currentStock += addStock;
			System.out.println("stock added successfully,the currentStock is" + currentStock);
		} else {
			System.out.println("dont need to add stock");
		}

	}

	/*
	 * public void sortPurchaseHistory(String sortBy) { Comparator<PurchaseRecord>
	 * comparator;
	 * 
	 * switch (sortBy.toLowerCase()) { case "byname": comparator =
	 * Comparator.comparing(PurchaseRecord::getPurchased_by,
	 * String.CASE_INSENSITIVE_ORDER); break; case "bydate": comparator =
	 * Comparator.comparing(PurchaseRecord::getDate); break; case "byaddress":
	 * comparator = Comparator.comparing(PurchaseRecord::getPurchased_by_address,
	 * String.CASE_INSENSITIVE_ORDER); break; default: System.out.
	 * println("Invalid sort option. Use 'byname', 'bydate', or 'byaddress'.");
	 * return; }
	 * 
	 * listPurchaseHistory.sort(comparator); }
	 * 
	 */

}