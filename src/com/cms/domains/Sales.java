package com.cms.domains;

import java.util.*;

public class Sales {

	private String salesNo;
	private Date salesDate;
	private Employee employee;
	private Double totalSalesPrice;
	private List<SalesDetail> listSalesDetails;
	
	public Sales() {
		this.salesNo = "";
		this.salesDate = new Date();
		this.employee = new Employee();
		this.totalSalesPrice = 0.0;
		this.listSalesDetails = new ArrayList<SalesDetail>();
	}
	
	public Sales(String salesNo, Date salesDate, Employee employee, Double totalSalesPrice,
			List<SalesDetail> listSalesDetails) {
		this.salesNo = salesNo;
		this.salesDate = salesDate;
		this.employee = employee;
		this.totalSalesPrice = totalSalesPrice;
		this.listSalesDetails = listSalesDetails;
	}

	public String getSalesNo() {
		return salesNo;
	}

	public void setSalesNo(String salesNo) {
		this.salesNo = salesNo;
	}

	public Date getSalesDate() {
		return salesDate;
	}

	public void setSalesDate(Date SalesDate) {
		this.salesDate = SalesDate;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Double getTotalSalesPrice() {
		return totalSalesPrice;
	}

	public void setTotalSalesPrice(Double totalSalesPrice) {
		this.totalSalesPrice = totalSalesPrice;
	}

	public List<SalesDetail> getListSalesDetail() {
		return listSalesDetails;
	}

	public void setListSalesDetails(List<SalesDetail> listSalesDetails) {
		this.listSalesDetails = listSalesDetails;
	}
	
}
