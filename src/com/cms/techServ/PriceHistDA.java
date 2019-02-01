package com.cms.techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cms.domains.PriceHistory;
import com.cms.exceptions.SQLmsgException;

import java.sql.SQLException;
import com.cms.utils.SQLstmts;

public class PriceHistDA {
	
	private ArrayList<PriceHistory> priceHistList;
	
	private Connection connection;

	private String prodCode;
	
	public PriceHistDA(Connection connection, String prodCode) throws SQLException{
		this.connection = connection;
		this.prodCode = prodCode;
		updatePriceHistList();
	}
	
	public ArrayList<PriceHistory> getPriceHistList(){
		return priceHistList;
	}

	private void updatePriceHistList() throws SQLmsgException {
		
		try {
		priceHistList = new ArrayList<PriceHistory>();
	
		PreparedStatement s = connection.prepareStatement(SQLstmts.PRICEHIST_SELECT.query());
		
		s.setString(1, prodCode);
		
		ResultSet rs_PriceHist = s.executeQuery();
		
			while(rs_PriceHist.next()){
				PriceHistory priceHist = new PriceHistory();
				
				priceHist.setEffDate(rs_PriceHist.getDate("effdate"));
				priceHist.setUnitPrice(rs_PriceHist.getDouble("unitprice"));
										  
				priceHistList.add(priceHist);		 
			}
			
		rs_PriceHist.close();
		
		} catch (SQLException e) {
			throw new SQLmsgException((e instanceof SQLmsgException)?
					((SQLmsgException) e).getErrMsg():"PriceHist");
		}
		
	}
	
}
