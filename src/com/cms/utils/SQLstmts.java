package com.cms.utils;

public enum SQLstmts {

	STORE_SETSCHEMA ("SET SCHEMA store"),
	
//		CUSTOMER_DA QUERIES
	CUSTLIST_SELECT ("SELECT * FROM customer ORDER BY custno"),
	
	SALESLIST_SELECT ("SELECT DISTINCT cs.transno, cs.salesdate, cp.transprice, empno, "
					+ "lastname, firstname from employee INNER JOIN "
					+ "custsaleshist_view cs ON empname = lastname || ', ' || firstname "
					+ "INNER JOIN custpaymenthist_view cp ON cs.transno = cp.transno "
					+ "AND cs.custno = ?"),
	
	SALESDETAILSLIST_SELECT ("SELECT prodcode, description, unit, unitPrice, quantity FROM custsaleshist_view WHERE transno = ?"),
	
	PRICEHIST_SELECT ("SELECT * FROM pricehist WHERE prodcode = ?"),
	
	CUSTLIST_ADD ("INSERT INTO customer VALUES (?, ?, ?, ?, ?)"),
	
	CUSTLIST_FLAGSTAMP ("UPDATE customer SET stamp = ? WHERE custno = ?"),
	
	CUSTLIST_EDIT ("UPDATE customer SET custno = ?, custname = ?, address = ?, payterms = ?, stamp = ? WHERE custno = ?"),
	
//		USER_DA QUERIES
	USERLIST_SELECT ("SELECT * FROM user ORDER BY userid"),
	
	MODULELIST_SELECT ("SELECT * FROM module WHERE userid = ?"),
	
	USERLIST_ADD ("INSERT INTO user (userID, userName, password, lastName, firstName, stamp) VALUES (?, ?, ?, ?, ?, ?)"),
	
	MODULELIST_ADD ("INSERT INTO module VALUES (?, ?, ?, ?, ?, ?)"),
	
	USERLIST_EDIT ("UPDATE user SET userName = ?, password = ?, lastName = ?, firstName = ?, stamp = ? WHERE userID = ?"),
	
	MODULELIST_EDIT ("UPDATE module SET canView = ?, canAdd = ?, canEdit = ?, canDelete = ? WHERE moduleCode = ? AND userID = ?"),
	
	USERLIST_FLAGSTAMP ("UPDATE user SET stamp = '%s' WHERE userID = '%s'"),
	
	ADMINLIST_SELECT("SELECT * FROM admin ORDER BY adminID");
	
	private String query;
	
	SQLstmts(String query){
		this.query = query;
	}
	
	public String query(){
		return query;
	}
	
}
