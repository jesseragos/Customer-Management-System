package com.cms.techServ;

import java.sql.*;
import java.util.ArrayList;
import com.cms.domains.Customer;
import com.cms.domains.User;
import com.cms.exceptions.SQLmsgException;
import com.cms.ui.utils.DialogBox;
import com.cms.utils.AdminChecker;
import com.cms.utils.SQLstmts;
import com.cms.utils.Stamp;

public class CustomerDA {
	
	private ArrayList<Customer> custList;
	
	private Connection connection;
	private User userMain;
	
	private String stamp = "";

	private boolean isAdmin;

	public CustomerDA(Connection connection, User userMain){
		this.connection = connection;
		this.userMain = userMain;
		isAdmin = !AdminChecker.isAdmin(userMain);
		updateCustList();
	}
	
	public ArrayList<Customer> getCustList(){
		return custList;
//		return new ArrayList<Customer>();
	}

	private void updateCustList() {
		custList = new ArrayList<Customer>();
		
		try {
			ResultSet rs_Cust = getResultSet(SQLstmts.CUSTLIST_SELECT.query());
			
			while(rs_Cust.next()){
				Customer cust = new Customer();
				
				cust.setStamp(rs_Cust.getString("stamp"));
				
//					VERIFY IF STAMP IS NULL THEN DEFAULT IS FALSE
				Boolean doesContains = cust.getStamp() != null?
						cust.getStamp().toUpperCase().contains("DELETED"):false;
				if(doesContains && isAdmin) continue;
				
				cust.setCustNo(rs_Cust.getString("custno"));
				cust.setCustName(rs_Cust.getString("custname"));
				cust.setAddress(rs_Cust.getString("address"));
				cust.setPayTerms(rs_Cust.getString("payterms"));
				cust.setListTransactions(new SalesDA(connection, cust.getCustNo()).getSalesList());
										  
				custList.add(cust);
			}
			
			rs_Cust.close();
			
		} catch (SQLException e) {
			DialogBox.showDBErrorDialog((e instanceof SQLmsgException)?
					((SQLmsgException) e).getErrMsg():"Customer");
		}
		
	}
	
	public String addCustomer(Customer cust){
		cust.setStamp(Stamp.getStamp("ADDED", userMain.getUserName()));
		
		try {
			if(cust.getCustName() == null) throw new SQLException();
			
			PreparedStatement s = connection.prepareStatement(SQLstmts.CUSTLIST_ADD.query());
		
			s.setString(1, cust.getCustNo());
			s.setString(2, cust.getCustName());
			s.setString(3, cust.getAddress());
			s.setString(4, cust.getPayTerm());
			s.setString(5, cust.getStamp());
		
			s.executeUpdate();
			setStamp(stamp);
			
		} catch (SQLException e) {
			informErr(e, "add");
			return null;
		}
		
		updateCustList();
		
		return cust.getCustNo();
	}

	public String deleteCustomer(Customer cust){
		try {
			PreparedStatement s = connection.prepareStatement(SQLstmts.CUSTLIST_FLAGSTAMP.query());
		
			s.setString(1, Stamp.getStamp("DELETED", userMain.getUserName()));
			s.setString(2, cust.getCustNo());
		
			s.executeUpdate();
			setStamp(stamp);
			
		} catch (SQLException e) {
			informErr(e, "delete");
			return null;
		}
		
		updateCustList();
		
		return cust.getCustNo();
	}
	
	public String editCustomer(Customer selectedCust, Customer fieldCust){
		
		try {
			if(fieldCust.getCustName() == null) throw new SQLException();
		
			PreparedStatement s = connection.prepareStatement(SQLstmts.CUSTLIST_EDIT.query());
		
			s.setString(1, fieldCust.getCustNo());
			s.setString(2, fieldCust.getCustName());
			s.setString(3, fieldCust.getAddress());
			s.setString(4, fieldCust.getPayTerm());
			s.setString(5, Stamp.getStamp("EDITED", userMain.getUserName()));
			s.setString(6, selectedCust.getCustNo());
		
			s.executeUpdate();
			setStamp(stamp);
			
		} catch (SQLException e) {
			informErr(e, "edit");
			return null;
		}
		
		updateCustList();
		
		return selectedCust.getCustNo();
	}
	
	public Integer searchCustomer(String searchWord){
		String sw = searchWord.toUpperCase();
		
		for(int i=0; i<custList.size(); i++)
			if(custList.get(i).getCustNo().toUpperCase().contains(sw) ||
			   custList.get(i).getCustName().toUpperCase().contains(sw) ||
			   custList.get(i).getAddress().toUpperCase().contains(sw) ||
			   custList.get(i).getPayTerm().toUpperCase().contains(sw))
			return i;
		
		return -1;
	}

	private ResultSet getResultSet(String query) throws SQLException {
		PreparedStatement pStmt = connection.prepareStatement(query);
		return pStmt.executeQuery();
	}
	
	private String getErrAction(String action, String error) {
		stamp = "";
		return String.format("Failed to %s customer\n%s", action, error);
	}
	
	private void informErr(SQLException e, String action) {
		setStamp("");
		DialogBox.showErrorDialog(getErrAction(action, getErrReport(e.getErrorCode())));
		e.printStackTrace();		
	}

	private String getErrReport(int errorCode) {
		String report = "";
		switch(errorCode){
		case -302: report = "A field has exceeded maximum input length"; break;
		case -803: report = "Customer number is already taken by another customer"; break;
		case 0: report = "Customer name or any field should not be blank"; break;
		}
		return report;
	}

	public String getStamp() {
		return stamp;
	}

	private void setStamp(String stamp) {
		this.stamp = stamp;		
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String recoverCustomer(Customer cust) {
		try {
			PreparedStatement s = connection.prepareStatement(SQLstmts.CUSTLIST_FLAGSTAMP.query());
		
			s.setString(1, Stamp.getStamp("RECOVERED", userMain.getUserName()));
			s.setString(2, cust.getCustNo());
		
			s.executeUpdate();
			setStamp(stamp);
			
		} catch (SQLException e) {
			informErr(e, "recover");
			return null;
		}
		
		updateCustList();
		
		return cust.getCustNo(); 
	}


}
