package com.cms.techServ;

import java.sql.*;
import java.util.*;
import com.cms.domains.*;
import com.cms.exceptions.SQLmsgException;
import com.cms.ui.utils.DialogBox;
import com.cms.utils.*;

public class UserDA {
	
	private ArrayList<User> userList;
	
	private Connection connection;
	
	private String stamp = "";

	private User userMain;
	
	public UserDA(Connection connection){
		this.connection = connection;
		updateUserList();
	}
	
	public UserDA(Connection connection, User userMain){
		this.connection = connection;
		this.userMain = userMain;
		updateUserList();
	}
	
	public ArrayList<User> getUserList(){
		return userList;
	}

	public User validateUser(String userName, String password) {
		try{
		String[] queries = new String[]{SQLstmts.ADMINLIST_SELECT.query(), 
										SQLstmts.USERLIST_SELECT.query()}; 	
		
		for(String query:queries){
			ResultSet rs = getResultSet(query);
			
			while(rs.next()){
				
				String idRS = String.format("%sid", query.equals(queries[0])? "admin":"user");
				String nameRS = String.format("%sname", query.equals(queries[0])? "admin":"user"); 
				
				nameRS = rs.getString(nameRS); 
				String passwordRS = rs.getString("password");
				
				if(nameRS.equals(userName) && passwordRS.equals(password)){
					User user = new User();
					user.setUserID(rs.getString(idRS));
					user.setUserName(nameRS);
					user.setPassword(passwordRS);
					
					if(!query.equals(queries[0])){
						try {if(rs.getString("stamp").contains("DELETED")) 
							throw new NullPointerException(); 
						} catch(NullPointerException ex) {continue;}
						user.setLastName(rs.getString("lastname"));
						user.setFirstName(rs.getString("firstname"));
						user.setListModule(new ModuleDA(connection, user.getUserID()).getModuleList());
					}
					
					return user;
				}
			
			} rs.close();
		} 
				
		} catch (SQLException e){}
		
		return null;
		
	}

	private void updateUserList() {
		userList = new ArrayList<User>();
		
		try {
			ResultSet rs_User = getResultSet(SQLstmts.USERLIST_SELECT.query());
			
			while(rs_User.next()){
				User user = new User();
				
				user.setStamp(rs_User.getString("stamp"));
				
//					VERIFY IF STAMP IS NULL THEN DEFAULT IS FALSE
//				Boolean doesContains = user.getStamp() != null?
//						user.getStamp().toUpperCase().contains("DELETED"):false;
//				if(doesContains && !isAdmin) continue;
				
				user.setUserID(rs_User.getString("userid"));
				user.setUserName(rs_User.getString("username"));
				user.setLastName(rs_User.getString("lastname"));
				user.setFirstName(rs_User.getString("firstname"));
				user.setPassword(rs_User.getString("password"));
				user.setListModule(new ModuleDA(connection, user.getUserID()).getModuleList());
				user.setStamp(rs_User.getString("stamp"));
										  
				userList.add(user);
			}
			
		} catch (SQLException e) {
			DialogBox.showDBErrorDialog((e instanceof SQLmsgException)?
					((SQLmsgException) e).getErrMsg():"User");
		}
		
	}
	
	public String addUser(User user){

		user.setStamp(Stamp.getStamp("ADDED", userMain.getUserName()));
		
		try {
			if(user.getFirstName() == null || user.getLastName() == null) 
				throw new SQLException();
			
			PreparedStatement s = connection.prepareStatement(SQLstmts.USERLIST_ADD.query());
		
			s.setString(1, user.getUserID());
			s.setString(2, user.getUserName());
			s.setString(3, user.getPassword());
			s.setString(4, user.getLastName());
			s.setString(5, user.getFirstName());
			s.setString(6, user.getStamp());
		
			s.executeUpdate();
			
			s.close();
			
			for(Module mod:user.getListModule()){
			
				s = connection.prepareStatement(SQLstmts.MODULELIST_ADD.query());
				
				s.setString(1, mod.getModuleCode());
				s.setString(2, mod.getUserID());
				s.setInt(3, intValue(mod.getCanView()));
				s.setInt(4, intValue(mod.getCanAdd()));
				s.setInt(5, intValue(mod.getCanEdit()));
				s.setInt(6, intValue(mod.getCanDelete()));
	
				s.executeUpdate();
				
				s.close();
			}
			
			setStamp(stamp);
			
		} catch (SQLException e) {
			informErr(e, "add");
			return null;
		}
		
		updateUserList();
		
		return user.getUserID();
	
	}

	private Integer intValue(Boolean b) {
		return b? 1:0;
	}

	public String deleteUser(User user){
		String deleteStmt = String.format(SQLstmts.USERLIST_FLAGSTAMP.query(), 
				Stamp.getStamp("DELETED", userMain.getUserName()), user.getUserID());
		
		execStmt(deleteStmt, user.getStamp(), "delete");
		
		return user.getUserID();
	}
	
	public String editUser(User selectedUser, User fieldUser){
		fieldUser.setStamp(Stamp.getStamp("EDITED", userMain.getUserName()));
		
		String userID = selectedUser.getUserID();
		
		try {
			if(fieldUser.getFirstName() == null || fieldUser.getLastName() == null) 
				throw new SQLException();
			
			if(isInAdminRecords(fieldUser)) throw new NullPointerException();
			
			PreparedStatement s = connection.prepareStatement(SQLstmts.USERLIST_EDIT.query());
		
			s.setString(1, fieldUser.getUserName());
			s.setString(2, fieldUser.getPassword());
			s.setString(3, fieldUser.getLastName());
			s.setString(4, fieldUser.getFirstName());
			s.setString(5, fieldUser.getStamp());
			s.setString(6, userID);
		
			s.executeUpdate();
			
			s.close();
			
			ArrayList<Module> fieldModList = fieldUser.getListModule();
			for(int i=0; i<fieldModList.size(); i++){
				Module mod = fieldModList.get(i);
				
				s = connection.prepareStatement(SQLstmts.MODULELIST_EDIT.query());
				
//				System.out.println(String.format("%s, %s, %s, %s, %s, %s, ", mod.getCanView(), mod.getCanAdd(), mod.getCanEdit(), mod.getCanDelete(), mod.getModuleCode(), userID));
				
				s.setInt(1, intValue(mod.getCanView()));
				s.setInt(2, intValue(mod.getCanAdd()));
				s.setInt(3, intValue(mod.getCanEdit()));
				s.setInt(4, intValue(mod.getCanDelete()));
				s.setString(5, mod.getModuleCode());
				s.setString(6, userID);
	
				s.executeUpdate();
				
				s.close();
			}
			
			setStamp(stamp);
			
			
		} catch (Exception e) {
			informErr(e, "edit");
			return null;
		}
		
		updateUserList();
		
		return userID;
	}
	
	private boolean isInAdminRecords(User fieldUser) {
		try {
			ResultSet rs_Admin = getResultSet(SQLstmts.ADMINLIST_SELECT.query());
			
			while(rs_Admin.next())
				if(rs_Admin.getString("adminid").equals(fieldUser.getUserID()) || 
				   rs_Admin.getString("adminname").equals(fieldUser.getUserName()))
						return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String recoverUser(User user) {
		String recoverStmt = String.format(SQLstmts.USERLIST_FLAGSTAMP.query(), 
				Stamp.getStamp("RECOVERED", userMain.getUserName()), user.getUserID());
		
		execStmt(recoverStmt, user.getStamp(), "recover");

		return user.getUserID();
	}
	
	public Integer searchUser(String searchWord){
		String sw = searchWord.toUpperCase();
		
		for(int i=0; i<userList.size(); i++)
			if(userList.get(i).getUserID().toUpperCase().contains(sw) ||
			   userList.get(i).getUserName().toUpperCase().contains(sw) ||
			   userList.get(i).getLastName().toUpperCase().contains(sw) ||
			   userList.get(i).getFirstName().toUpperCase().contains(sw))
			return i;
		
		return -1;
	}

	private ResultSet getResultSet(String query) throws SQLException {
		PreparedStatement pStmt = connection.prepareStatement(query);
		return pStmt.executeQuery();
	}
	
	private String getErrAction(String action, String error) {
		stamp = "";
		return String.format("Failed to %s user\n%s", action, error);
	}
	
	private void execStmt(String query, String stamp, String action){
			try {
				PreparedStatement pStmt = connection.prepareStatement(query);
				pStmt.executeUpdate();
				setStamp(stamp);
				
			} catch (SQLException e) {
				setStamp("");
				DialogBox.showErrorDialog(getErrAction(action, getErrReport(e.getErrorCode())));
				e.printStackTrace();
			}
			
			updateUserList();
	}
	
	private void informErr(Exception e, String action) {
		setStamp("");
		if(e instanceof SQLException)
			 DialogBox.showErrorDialog(getErrAction(action, getErrReport(((SQLException)e).getErrorCode())));
		else DialogBox.showErrorDialog(getErrAction(action, getErrReport(-803)));
		e.printStackTrace();
	}

	private String getErrReport(int errorCode) {
		String report = "";
		switch(errorCode){
		case -302: report = "A field has exceeded maximum input length"; break;
		case -803: report = "User ID or username is already taken by another user"; break;
		case 0:
		case -407: report = "Any field should not be blank"; break;
		}
		return report;
	}

	public String getStamp() {
		return stamp;
	}

	private void setStamp(String stamp) {
		this.stamp = stamp;		
	}

}
