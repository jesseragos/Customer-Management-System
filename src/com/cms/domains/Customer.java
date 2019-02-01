package com.cms.domains;

import java.util.ArrayList;
import java.util.List;

public class Customer {

	private String custNo;
	private String custName;
	private String address;
	private String payTerms;
	private String stamp;
	private List<Sales> listTransactions;
	
	public final static String[] payterms = new String[]{"COD", "30D", "45D"};
	
	public Customer() {
		this.custNo = "";
		this.custName = "";
		this.address = "";
		this.payTerms = "";
		this.stamp = "";
		this.listTransactions = new ArrayList<Sales>();
	}
	
	public Customer(String custNo, String custName, String address, String payTerms, String stamp,
			List<Sales> listTransactions) {
		this.custNo = custNo;
		this.custName = custName;
		this.address = address;
		this.payTerms = payTerms;
		this.stamp = stamp;
		this.listTransactions = listTransactions;
	}
	
	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}

	public String getCustNo() {
		return custNo;
	}
	
	public void setCustNo(String custNo) {
		this.custNo = custNo;
	}
	
	public String getCustName() {
		return custName;
	}
	
	public void setCustName(String custName) {
		this.custName = custName;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPayTerm() {
		return payTerms;
	}
	
	public void setPayTerms(String payTerms) {
		this.payTerms = payTerms;
	}
	
	public List<Sales> getListTransactions() {
		return listTransactions;
	}
	
	public void setListTransactions(List<Sales> listTransactions) {
		this.listTransactions = listTransactions;
	}
	
}
