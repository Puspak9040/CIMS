package com.cycle;

import java.io.Serializable;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PurchaseRecord implements IPurchaseRecord, Serializable {

	@SuppressWarnings("unused")
	private Cycle cycle = null;
	@SuppressWarnings("unused")
	private Date date = null;
	@SuppressWarnings("unused")
	private String purchased_by = null;
	@SuppressWarnings("unused")
	private String purchased_by_address = null;
	private int quantity = 0;

	public PurchaseRecord(int quantity, Cycle cycle, String purchased_by, String purchased_by_address) {
		this.cycle = cycle;
		this.date = new Date();
		this.purchased_by = purchased_by;
		this.purchased_by_address = purchased_by_address;
		this.quantity = quantity;

	}

	public PurchaseRecord() {

	}

	// setter for cycle
	public void setCycle(Cycle cycle) {
		this.cycle = cycle;
	}

	// getter for cycle
	public Cycle getCycle() {
		return cycle;
	}

	// setter for date
	public void setDate(Date date) {
		this.date = date;
	}

	// getter for date
	public Date getDate() {
		return date;
	}

	// setter for purchased_by
	public void setPurchased_by(String purchased_by) {
		this.purchased_by = purchased_by;
	}

	// getter for purchased_by

	public String getPurchased_by() {
		return purchased_by;
	}

	// setter for purchased_by_address
	public void setPurchased_by_address(String purchased_by_address) {
		this.purchased_by_address = purchased_by_address;
	}

	// getter for purchased_by_address
	public String getPurchased_by_address() {
		return purchased_by_address;
	}

	public String getPurchasedBy() {
		return this.purchased_by;
	}

//	@Override
//	public String toString() {
//		return "PurchaseRecord [cycle=" + cycle.getModel() + ", color=" + cycle.getColor() + ", date=" + date
//				+ ", purchased_by=" + purchased_by + ", address=" + purchased_by_address + "]";
//	}

	@Override
	public String toString() {
		String message = "PurchaseRecord [cycle model = {0}, cycle color = {1}, Quantity = {2}, date = {3}, purchased by = {4}, address = {5}]";
		return MessageFormat.format(message, this.cycle.getModel(), this.cycle.getColor(), this.quantity,
				new SimpleDateFormat("dd/MM/yyyy, HH:mm:ss").format(this.date), this.purchased_by,
				this.purchased_by_address);
	}
}
