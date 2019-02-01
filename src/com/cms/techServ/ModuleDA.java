package com.cms.techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import com.cms.domains.Module;
import com.cms.exceptions.SQLmsgException;

import java.sql.SQLException;
import com.cms.utils.SQLstmts;

public class ModuleDA {
	
	private ArrayList<Module> moduleList;
	
	private Connection connection;

	private String userID;
	
	public ModuleDA(Connection connection, String userID) throws SQLException{
		this.connection = connection;
		this.userID = userID;
		updateModuleList();
	}
	
	public ArrayList<Module> getModuleList(){
		return moduleList;
	}

	private void updateModuleList() throws SQLmsgException {
		
		try {
		moduleList = new ArrayList<Module>();
	
		PreparedStatement s = connection.prepareStatement(SQLstmts.MODULELIST_SELECT.query());
		s.setString(1, userID);
		ResultSet rs_Module = s.executeQuery();
		
			while(rs_Module.next()){
				Module module = new Module();
				
				module.setModuleCode(rs_Module.getString("modulecode"));
				module.setUserID(rs_Module.getString("userid"));
				module.setCanView(getBoolValue(rs_Module.getInt("canview")));
				module.setCanAdd(getBoolValue(rs_Module.getInt("canadd")));
				module.setCanEdit(getBoolValue(rs_Module.getInt("canedit")));
				module.setCanDelete(getBoolValue(rs_Module.getInt("candelete")));
										  
				moduleList.add(module);		 
			}
			
		} catch (SQLException e) {
			throw new SQLmsgException((e instanceof SQLmsgException)?
					((SQLmsgException) e).getErrMsg():"Module");
		}
		
	}
	
	private Boolean getBoolValue(int i) {
		return i>0? true:false;
	}

}
