package com.cms.techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cms.domains.Employee;
import com.cms.domains.Sales;
import com.cms.exceptions.SQLmsgException;

import java.sql.SQLException;
import com.cms.utils.SQLstmts;

public class SalesDA {
	
	private ArrayList<Sales> salesList;
	
	private Connection connection;

	private String custNo;
	
	public SalesDA(Connection connection, String custNo) throws SQLException{
		this.connection = connection;
		this.custNo = custNo;
		updateSalesList();
	}
	
	public ArrayList<Sales> getSalesList(){
		return salesList;
	}

	private void updateSalesList() throws SQLmsgException {
		
		try {
		salesList = new ArrayList<Sales>();
	
		PreparedStatement s = connection.prepareStatement(SQLstmts.SALESLIST_SELECT.query());
		
		s.setString(1, custNo);
		
		ResultSet rs_Sales = s.executeQuery();;
		
			while(rs_Sales.next()){
				Sales sales = new Sales();
				
				sales.setSalesNo(rs_Sales.getString("transno"));
				sales.setSalesDate(rs_Sales.getDate("salesdate"));
				sales.setEmployee(new Employee(rs_Sales.getString("empno"),
											   rs_Sales.getString("lastname"),
											   rs_Sales.getString("firstname")));
				sales.setTotalSalesPrice(rs_Sales.getDouble("transprice"));
				sales.setListSalesDetails(
						new SalesDetailsDA(connection, sales.getSalesNo()).getSalesDetailList());
										  
				salesList.add(sales);		 
			}
			
		rs_Sales.close();
			
		} catch (SQLException e) {
			throw new SQLmsgException((e instanceof SQLmsgException)?
					((SQLmsgException) e).getErrMsg():"Sales");
		}
		
	}
}
