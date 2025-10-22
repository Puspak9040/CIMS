package com.cycle.sort;

import java.util.Comparator;

import com.cycle.PurchaseRecord;

public class Purchased_bysort implements Comparator<PurchaseRecord> {

	private String sort_by = ISortBy.BY_PUR_NAME;

	public Purchased_bysort() {
	};

	public Purchased_bysort(String sort_by) {
		this.sort_by = sort_by;
	}

	@Override
	public int compare(PurchaseRecord one, PurchaseRecord two) {
		if (this.sort_by == ISortBy.BY_PUR_NAME) {
			return one.getPurchasedBy().compareTo(two.getPurchasedBy());
		} else if (this.sort_by == ISortBy.BY_PUR_DATE) {
			return one.getDate().compareTo(two.getDate());
		}

		else if (this.sort_by == ISortBy.BY_MODEL) {
			return one.getCycle().getModel().compareTo(two.getCycle().getModel());
		} else {
			return one.getCycle().getColor().compareTo(two.getCycle().getColor());
		}

	}
}