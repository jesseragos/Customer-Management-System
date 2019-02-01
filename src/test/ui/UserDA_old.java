package com.cms.techServ;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cms.domains.Customer;
import com.cms.domains.Module;
import com.cms.domains.User;
import com.cms.exceptions.SQLmsgException;
import com.cms.ui.utils.DialogBox;
import com.cms.utils.AdminChecker;
import com.cms.utils.ModuleCodes;
import com.cms.utils.SQLstmts;
import com.cms.utils.Stamp;

public class UserDA_old {
	
	private ArrayList<User> userList;
	
	private Connection connection;
	
	private String stamp = "";

	private boolean isAdmin;

	private User userMain;
	
	public UserDA_old(Connection connection){
		this.connection = connection;
		updateUserList();
	}
	
	public UserDA_old(Connection connection, User userMain){
		this.connection = connection;
		this.userMain = userMain;
		isAdmin = !AdminChecker.isAdmin(userMain);
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
						user.setLastName(rs.getString("lastname"));
						user.setFirstName(rs.getString("firstname"));
						user.setListModule(new ModuleDA(connection, user.getUserID()).getModuleList());
						if(rs.getString("stamp").contains("DELETED")) continue;
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
	
	public String editUser(User user, String[] userInfoList, Boolean[] modInfoList){
		editUserInfo(userInfoList, user);
		editModInfo(modInfoList, user);
		
		return user.getUserID();
	}
	
	private void editUserInfo(String[] userInfoList, User user) {
		String editParam_user = ""; 
		
		Boolean userInfoFlag = false;
		
		for(int i=0; i<userInfoList.length; i++){
			if(userInfoList[i] == null) continue;
				
			userInfoFlag = true;
			
			switch(i){
			case 0: editParam_user += formatAssignStmt("userid",  quoteCharValue(userInfoList[i])); break;
			case 1: editParam_user += formatAssignStmt("username",quoteCharValue(userInfoList[i])); break;
			case 2: editParam_user += formatAssignStmt("password", quoteCharValue(userInfoList[i])); break;
			case 3: editParam_user += formatAssignStmt("lastname", quoteCharValue(userInfoList[i])); break;
			case 4: editParam_user += formatAssignStmt("firstname", quoteCharValue(userInfoList[i])); break;
			} 
			
			editParam_user += ", ";
		}
		
		System.out.println(editParam_user);
		
		if(userInfoFlag){
			editParam_user += formatAssignStmt("STAMP", 
					quoteCharValue(Stamp.getStamp("EDITED", userMain.getUserName())));

			String editStmt = String.format(SQLstmts.USERLIST_EDIT.query(), editParam_user, user.getUserID());

			System.out.println(editStmt);
			
			execStmt(editStmt, user.getStamp(), "edit");
		}		
	}

	private void editModInfo(Boolean[] modInfoList, User user) {
		String editParam_custMod = "";
		String editParam_rep1Mod = "";
		String editParam_rep2Mod = "";
		String editParam_rep3Mod = "";
		String editParam_rep4Mod = "";
		
		for(int i=0; i<modInfoList.length; i++){
			if(modInfoList[i] == null) continue;
				
			switch(i){
			case 0: editParam_custMod += formatAssignStmt("canview", intValue(modInfoList[i]).toString()) + ", "; break;
			case 1: editParam_custMod += formatAssignStmt("canadd",intValue(modInfoList[i]).toString()) + ", "; break;
			case 2: editParam_custMod += formatAssignStmt("canedit", intValue(modInfoList[i]).toString()) + ", "; break;
			case 3: editParam_custMod += formatAssignStmt("candelete", intValue(modInfoList[i]).toString()) + ", "; break;
			case 4: editParam_rep1Mod += formatAssignStmt("canview", intValue(modInfoList[i]).toString()) + ", "; break;
			case 5: editParam_rep2Mod += formatAssignStmt("canview", intValue(modInfoList[i]).toString()) + ", "; break;
			case 6: editParam_rep3Mod += formatAssignStmt("canview", intValue(modInfoList[i]).toString()) + ", "; break;
			case 7: editParam_rep4Mod += formatAssignStmt("canview", intValue(modInfoList[i]).toString()) + ", "; break;
			}
			
//			editParam_custMod += !editParam_custMod.isEmpty()? ", ":"";
//			editParam_rep1Mod += !editParam_rep1Mod.isEmpty()? ", ":"";
//			editParam_rep2Mod += !editParam_rep2Mod.isEmpty()? ", ":"";
//			editParam_rep3Mod += !editParam_rep3Mod.isEmpty()? ", ":"";
//			editParam_rep4Mod += !editParam_rep4Mod.isEmpty()? ", ":"";
		}
		
		String[] paramMods = new String[]{editParam_custMod, editParam_rep1Mod, editParam_rep2Mod, editParam_rep3Mod, editParam_rep4Mod};
		
		for(int i=0; i<paramMods.length; i++)
		if(!paramMods[i].isEmpty()){
			paramMods[i] = paramMods[i].substring(0, paramMods[i].length()-2);
			String editModStmt = String.format(SQLstmts.MODULELIST_EDIT.query(), paramMods[i], ModuleCodes.values()[i].name(), user.getUserID());
			
			execStmt(editModStmt, user.getStamp(), "edit");
		}
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
	
	private String getErrAction(String action) {
		stamp = "";
		return String.format("Failed to %s user\nPlease check inputs for validity", action);
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
			
			updateUserList();
	}
	
	private void informErr(SQLException e, String action) {
		setStamp("");
		DialogBox.showErrorDialog(getErrAction(action));
		e.printStackTrace();		
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

}
