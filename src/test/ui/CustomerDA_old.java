package test.ui;

import java.sql.*;
import java.util.ArrayList;
import com.cms.domains.Customer;
import com.cms.domains.User;
import com.cms.exceptions.SQLmsgException;
import com.cms.techServ.SalesDA;
import com.cms.ui.utils.DialogBox;
import com.cms.utils.AdminChecker;
import com.cms.utils.SQLstmts;
import com.cms.utils.Stamp;

public class CustomerDA_old {
	
	private ArrayList<Customer> custList;
	
	private Connection connection;
	private User userMain;
	
	private String stamp = "";

	private boolean isAdmin;

	public CustomerDA_old(Connection connection, User userMain){
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
		
		String addStmt = String.format(SQLstmts.CUSTLIST_ADD.query(),
										quoteCharValue(cust.getCustNo()),
										quoteCharValue(cust.getCustName()),
										quoteCharValue(cust.getAddress()),
										quoteCharValue(cust.getPayTerm()),
										quoteCharValue(cust.getStamp()));
		
		
		execStmt(addStmt, cust.getStamp(), "add");
		
		return cust.getCustNo();
	}

	public String deleteCustomer(Customer cust){
		String deleteStmt = String.format(SQLstmts.CUSTLIST_FLAGSTAMP.query(), 
				Stamp.getStamp("DELETED", userMain.getUserName()), cust.getCustNo());
		
		execStmt(deleteStmt, cust.getStamp(), "delete");
		
		return cust.getCustNo();
	}
	
	public String editCustomer(Customer cust, String[] custInfoList){
		String editParam = ""; 
		
		for(int i=0; i<custInfoList.length; i++){
			if(custInfoList[i] == null) continue;
				
			switch(i){
			case 0: editParam += formatAssignStmt("CUSTNO",  quoteCharValue(custInfoList[0])); break;
			case 1: editParam += formatAssignStmt("CUSTNAME",quoteCharValue(custInfoList[1])); break;
			case 2: editParam += formatAssignStmt("ADDRESS", quoteCharValue(custInfoList[2])); break;
			case 3: editParam += formatAssignStmt("PAYTERM", quoteCharValue(custInfoList[3])); break;
			} 
			
			editParam += ", ";
		}
		
		editParam += formatAssignStmt("STAMP", 
				quoteCharValue(Stamp.getStamp("EDITED", userMain.getUserName())));
		
		String editStmt = String.format(SQLstmts.CUSTLIST_EDIT.query(), editParam, cust.getCustNo());
		
		execStmt(editStmt, cust.getStamp(), "edit");
		
		return cust.getCustNo();
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
	
	private String getErrAction(String action) {
		stamp = "";
		return String.format("Failed to %s customer\nPlease check inputs for validity", action);
	}
	
	private void execStmt(String query, String stamp, String action){
			try {
				PreparedStatement pStmt = connection.prepareStatement(query);
				pStmt.executeUpdate();
				setStamp(stamp);
				
			} catch (SQLException e) {
				setStamp("");
				DialogBox.showErrorDialog(getErrAction(action));
				e.printStackTrace();
			}
			
			updateCustList();
	}
	
	private String formatAssignStmt(String attr, String data) {
		return String.format("%s = %s", attr, data);
	}

	public String getStamp() {
		return stamp;
	}

	private void setStamp(String stamp) {
		this.stamp = stamp;		
	}

	private String quoteCharValue(String charVal) {
		return charVal != null? String.format("'%s'", charVal):charVal;
	}
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public String recoverCustomer(Customer cust) {
		cust.setStamp(Stamp.getStamp("RECOVERED", userMain.getUserName()));
		
		String recoverStmt = String.format(SQLstmts.CUSTLIST_FLAGSTAMP.query(), 
				cust.getStamp(), cust.getCustNo());
		
		execStmt(recoverStmt, cust.getStamp(), "recover");
		
		return cust.getCustNo(); 
	}


}
