package com.cms.domains;

public class Employee {

	private String firstName;
	private String empNo;
	private String lastName;
	
	public Employee() {
		this.empNo = "";
		this.lastName = "";
		this.firstName = "";
	}
	
	public Employee(String empNo, String lastName, String firstName) {
		this.empNo = empNo;
		this.lastName = lastName;
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
}
