package com.cms.domains;

import java.util.Date;

public class PriceHistory {

	private Date effDate;
	private Double unitPrice;
	
	public PriceHistory() {
		this.effDate = new Date();
		this.unitPrice = 0.0;
	}
	
	public PriceHistory(Date effDate, Double unitPrice) {
		this.effDate = effDate;
		this.unitPrice = unitPrice;
	}

	public Date getEffDate() {
		return effDate;
	}

	public void setEffDate(Date effDate) {
		this.effDate = effDate;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

}