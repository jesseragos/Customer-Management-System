package com.cms.techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cms.domains.Product;
import com.cms.domains.SalesDetail;
import com.cms.exceptions.SQLmsgException;

import java.sql.SQLException;
import com.cms.utils.SQLstmts;

public class SalesDetailsDA {
	
	private ArrayList<SalesDetail> salesDetailsList;
	
	private Connection connection;

	private String transNo;

	public SalesDetailsDA(Connection connection, String transNo) throws SQLException{
		this.connection = connection;
		this.transNo = transNo;
		updateSalesDetailList();
	}
	
	public ArrayList<SalesDetail> getSalesDetailList(){
		return salesDetailsList;
	}

	private void updateSalesDetailList() throws SQLmsgException {
		
		try {
		salesDetailsList = new ArrayList<SalesDetail>();
	
		PreparedStatement s = connection.prepareStatement(SQLstmts.SALESDETAILSLIST_SELECT.query());
		
		s.setString(1, transNo);
		
		ResultSet rs_SalesDtls = s.executeQuery();
		
			while(rs_SalesDtls.next()){
				SalesDetail salesDetails = new SalesDetail();
				
				Product product = new Product();
				
				product.setProdCode(rs_SalesDtls.getString("prodcode"));
				product.setDescription(rs_SalesDtls.getString("description"));
				product.setUnit(rs_SalesDtls.getString("unit"));
				product.setPriceDetails(new PriceHistDA(connection, product.getProdCode()).getPriceHistList());
				
				salesDetails.setProduct(product);
				salesDetails.setUnitPrice(rs_SalesDtls.getDouble("unitprice"));
				salesDetails.setQuantity(rs_SalesDtls.getDouble("quantity"));
										  
				salesDetailsList.add(salesDetails);		 
			}
			
		rs_SalesDtls.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new SQLmsgException((e instanceof SQLmsgException)?
					((SQLmsgException) e).getErrMsg():"SalesDetails");
		}
		
	}
	
}
