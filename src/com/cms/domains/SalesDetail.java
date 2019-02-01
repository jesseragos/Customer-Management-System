package com.cms.domains;

public class SalesDetail {

	private Product product;
	private Double quantity;
	private Double unitPrice;
	
	public SalesDetail() {
		product = new Product();
		quantity = 0.0;
		unitPrice = 0.0;
	}
	
	public SalesDetail(Product product, Double quantity, Double unitPrice) {
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
	}
	
	public Double getQuantity() {
		return quantity;
	}
	
	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void setUnitPrice(Double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Double getUnitPrice() {
		return unitPrice;
	}
	
}
