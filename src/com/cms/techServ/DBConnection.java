package com.cms.techServ;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.cms.ui.utils.DialogBox;
import com.cms.utils.SQLstmts;

public class DBConnection {
	
	private final String jdbcClassName = "com.ibm.db2.jcc.DB2Driver",
						 url = "jdbc:db2:HOPE";
	private Connection connection = null;
	
	public DBConnection() {
		setDBConnection();
	}
	
	private void setDBConnection() {
		try {
			Class.forName(jdbcClassName);
			
			connection =  DriverManager.getConnection(url);
			
			PreparedStatement pStmt = connection.prepareStatement(SQLstmts.STORE_SETSCHEMA.query());
			pStmt.executeUpdate();
			
		} catch (ClassNotFoundException | SQLException e) {
			DialogBox.showErrorDialog("CANNOT ACCESS TO DATABASE 'HOPE' OR TO ITS SCHEMA 'STORE'");
			System.exit(0);
		}
	}
	
	public Connection getConnection() {
		return connection;
	}
}
