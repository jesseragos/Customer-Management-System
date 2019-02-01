package com.cms.domains;

import java.util.ArrayList;
import java.util.List;

public class Product {

	private String prodCode;
	private String description;
	private String unit;
	private List<PriceHistory> listPriceHist;
	
	public Product() {
		this.prodCode = "";
		this.description = "";
		this.unit = "";
		this.listPriceHist = new ArrayList<PriceHistory>();
	}
	
	public Product(String prodCode, String description, String unit, List<PriceHistory> priceDetails) {
		this.prodCode = prodCode;
		this.description = description;
		this.unit = unit;
		this.listPriceHist = priceDetails;
	}

	public String getProdCode() {
		return prodCode;
	}

	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public List<PriceHistory> getPriceDetails() {
		return listPriceHist;
	}

	public void setPriceDetails(List<PriceHistory> priceDetails) {
		this.listPriceHist = priceDetails;
	}

//	public Double getUnitPrice(Date salesDate) {
//		for(int i=0; i<priceDetails.size(); i++)
//			if(priceDetails.get(i).getEffDate().after(salesDate))
//				return priceDetails.get(i>0? i-1:0).getUnitPrice();
//		
//		return priceDetails.get(0).getUnitPrice();
//	}
	
}
