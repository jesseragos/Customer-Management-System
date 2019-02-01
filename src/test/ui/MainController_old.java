package test.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.cms.controller.LoginController;
import com.cms.domains.*;
import com.cms.techServ.*;
import com.cms.ui.*;
import com.cms.ui.utils.DialogBox;
import com.cms.utils.AdminChecker;
import com.cms.utils.ModuleCodes;
import com.cms.utils.SQLstmts;

public class MainController_old {

	private MainUI mainUI;
	private JPanel mainViewPNL;
	private MaintainCustomerUI maintainCustUI;
	private ViewTransactionUI viewTransUI;
	private SetAdminUI setAdminUI;
	private GenerateReportsUI generateReportsUI;

	private CardLayout cardLO;
	
//		HANDLERS
	private MainOptionsHandler optListener;
	private MaintainCustomerHandler maintainCustHandler;
	private SetAdminHandler setAdminHandler;
	private GenerateReportsHandler generateReportsHandler;
	private ViewTransHandler viewTransHandler;
	
	private CustomerDA custDA;
	public UserDA userDA;
	
	private final String maintainCustTag = "Maintain Customer",
						 setAdminTag = "Set Admin",
						 generateReportsTag = "Generate Reports";
	
	private Connection connection = null;
	private Reports reports;
	private DefaultTableModel custRecordsModel;
	private DefaultTableModel userRecordsModel;
	
	private User userMain;
	private Boolean isAdmin;
	private Boolean isCustModEnabled;
	
	public MainController_old(User user, Connection connection) {
		this.connection = connection;
		this.userMain = user;
		isAdmin = AdminChecker.isAdmin(userMain);
		
		setMainUI();
	}
	
	private void setMainUI() {
		initMainUI();
		
		setMainViewPNL();
		setMainViewOptionUI();
		
		mainUI.setVisible(true);
	}

	public MainUI getMainUI() {
		return mainUI;
	}

	private void setMainViewOptionUI() {
		setTechServ();
		
		setMainOptionComps();
		
		processMainOptions();
	}

	private void processMainOptions() {
		ArrayList<JButton> bSet = new ArrayList<JButton>();
		
		if(isCustModEnabled){
		bSet.add(new JButton(maintainCustTag));
		mainViewPNL.add(maintainCustUI, maintainCustTag);
		}
		
		if(isAdmin) {
		bSet.add(new JButton(setAdminTag));
		mainViewPNL.add(setAdminUI, setAdminTag);
		}
		
		if(generateReportsHandler.getReportList().length != 0){
		bSet.add(new JButton(generateReportsTag));
		mainViewPNL.add(generateReportsUI, generateReportsTag);	
		}
		
		mainUI.setOptionBtns(bSet);
		
		optListener = new MainOptionsHandler();
		
		for(JButton btn:bSet)
			btn.addActionListener(optListener);		
	}

	private void setTechServ() {
		userDA = new UserDA(connection, userMain);
		custDA = new CustomerDA(connection, userMain);
		reports = new Reports(connection, userMain);
	}

	private void setMainOptionComps() {
		setMaintainCustomerUI();
		setViewTransactionsUI();
		if(isAdmin) setSetAdminUI();
		setGenerateReportsUI();
	}

	private void setViewTransactionsUI() {
		viewTransHandler = new ViewTransHandler();
		
		viewTransUI.getNextAccB().addActionListener(viewTransHandler);
		viewTransUI.getPrevAccB().addActionListener(viewTransHandler);
		viewTransUI.getFirstAccB().addActionListener(viewTransHandler);
		viewTransUI.getLastAccB().addActionListener(viewTransHandler);
		
		viewTransHandler.setSalesDetailsModel(viewTransUI.getSalesDetailsRecordsModel());
	}

	private void setGenerateReportsUI() {
		generateReportsHandler = new GenerateReportsHandler();
		
		generateReportsUI.setReportsList(generateReportsHandler.getReportList());
		generateReportsUI.getReportsLIST().addListSelectionListener(generateReportsHandler);
		generateReportsUI.getViewReportB().addActionListener(generateReportsHandler);
	}

	private void setSetAdminUI() {
		setAdminHandler = new SetAdminHandler();
		setAdminUI.getAddB().addActionListener(setAdminHandler);
		setAdminUI.getEditB().addActionListener(setAdminHandler);
		setAdminUI.getDeleteB().addActionListener(setAdminHandler);
		setAdminUI.getRecoverB().addActionListener(setAdminHandler);
		setAdminUI.getSearchB().addActionListener(setAdminHandler);
		setAdminUI.getUserRecordsTBL().addMouseListener(setAdminHandler);
		setAdminUI.addMouseListener(setAdminHandler);
		
		setAdminHandler.enableTableRelatedButtons(false);
		setAdminHandler.updateUserRecords();
	}

	private void setMaintainCustomerUI() {
		maintainCustHandler = new MaintainCustomerHandler();
		
		Module custMod = new Module();
		for(Module mod:userMain.getListModule()) 
			if(mod.getModuleCode().equals(ModuleCodes.MODC001.name())){
				custMod = mod;
				break;
			}
		
		if(!isAdmin) maintainCustUI.getStampP().setVisible(false);
		
		Boolean canView = custMod.getCanView() || isAdmin;
		maintainCustUI.getViewTransactionsB().addActionListener(canView? maintainCustHandler:null);
		maintainCustUI.getViewTransactionsB().setVisible(canView);
		
		Boolean canAdd = custMod.getCanAdd() || isAdmin;
		maintainCustUI.getAddB().addActionListener(canAdd? maintainCustHandler:null);
		maintainCustUI.getAddB().setVisible(canAdd);
		
		Boolean canEdit = custMod.getCanEdit() || isAdmin;
		maintainCustUI.getEditB().addActionListener(canEdit? maintainCustHandler:null);
		maintainCustUI.getEditB().setVisible(canEdit);
		
		Boolean canDelete = custMod.getCanDelete() || isAdmin;
		maintainCustUI.getDeleteB().addActionListener(canDelete? maintainCustHandler:null);
		maintainCustUI.getDeleteB().setVisible(canDelete);
		
		isCustModEnabled = false;
		if(canView || canAdd || canEdit || canDelete) isCustModEnabled = true;
		
		maintainCustUI.getRecoverB().addActionListener(isAdmin? maintainCustHandler:null);
		maintainCustUI.getRecoverB().setVisible(isAdmin);
		
		maintainCustUI.getSearchB().addActionListener(maintainCustHandler);
		maintainCustUI.getCustRecordsTBL().addMouseListener(maintainCustHandler);
		maintainCustUI.addMouseListener(maintainCustHandler);
		
		maintainCustHandler.enableTableRelatedButtons(false);
		maintainCustHandler.updateCustRecords();
	}

	private void initMainUI() {
		mainUI = new MainUI();
		mainUI.setUserNameInfo(userMain.getUserName());
		
		mainUI.getLogoutL().addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {
				Integer opt = DialogBox.showOKCancelMsg("", "Are you sure you want to logout?");
				
				if(opt == JOptionPane.OK_OPTION){
					mainUI.dispose();
					new LoginController();
				}
			}
			
			public void mouseEntered(MouseEvent arg0) {
				changeLogoutColor(mainUI.getLogoutL().getForeground().darker());
			}
			
			public void mouseExited(MouseEvent arg0) {
				defaultLogOutColor();
			}
			
			private void changeLogoutColor(Color color) {
				mainUI.getLogoutL().setForeground(color);
			}

			private void defaultLogOutColor(){
				changeLogoutColor(MainUI.prefColor);
			}
		});
	}

	private void setMainViewPNL() {
		mainViewPNL = mainUI.getViewP();
		
			//	SET LAYOUT: CardLayout
		cardLO = mainUI.getViewLayout();
		mainViewPNL.setLayout(cardLO);
		
		initMainOptions();
	}

	private void initMainOptions() {
		maintainCustUI = new MaintainCustomerUI();
		viewTransUI = maintainCustUI.getViewTrans();
		setAdminUI = new SetAdminUI();
		generateReportsUI = new GenerateReportsUI();
	}
	
	private class MainOptionsHandler implements ActionListener {

		public void actionPerformed(ActionEvent mainOptEV) {
			String action = mainOptEV.getActionCommand();
			
			if(action.equals(maintainCustTag))
				cardLO.show(mainViewPNL, maintainCustTag);
			else if(action.equals(setAdminTag))
				cardLO.show(mainViewPNL, setAdminTag);
			else if(action.equals(generateReportsTag))
				cardLO.show(mainViewPNL, generateReportsTag);
			
//			mainViewPNL.repaint();
		}
		
	}

	private class MaintainCustomerHandler implements ActionListener, MouseListener {

		public void actionPerformed(ActionEvent ev) {
			Object evo = ev.getSource();
			
			if(evo == maintainCustUI.getAddB()) processAddCustomer();
			else if(evo == maintainCustUI.getEditB()) processEditCustomer();
			else if(evo == maintainCustUI.getDeleteB()) processDeleteCustomer();
			else if(evo == maintainCustUI.getViewTransactionsB()) displayViewTransactions();
			else if(evo == maintainCustUI.getSearchB()) processSearchCustomer();
			else if(evo == maintainCustUI.getRecoverB()) processRecoverCustomer();
		}

		private void processRecoverCustomer() {
			Integer opt = DialogBox.showOKCancelMsg("", "Confirm customer to recover?");
			
			if(opt == JOptionPane.OK_OPTION){
				String custNoLatest = custDA.recoverCustomer(getSelectedCustomer());
				
				updateCustRecords();
				
				searchCustomer(custNoLatest);
			}
		}

		private void updateStampView(String stamp) {
			maintainCustUI.getStampP().setStampValue(stamp);			
		}
		
		public void updateCustRecords() {
			ArrayList<Customer> custList = custDA.getCustList();
			custRecordsModel = maintainCustUI.getCustRecordsModel();
			
			maintainCustUI.clearCustRecordsTBL();
			for(Customer cust:custList)
				custRecordsModel.addRow(new Object[]{cust.getCustNo(),
													 cust.getCustName(),
													 cust.getAddress(),
													 cust.getPayTerm()});
			
//			updateStampView(custDA.getStamp());
		}

		private void processSearchCustomer() {
			searchCustomer(maintainCustUI.getSearchTF().getText().trim());
		}

		private void searchCustomer(String searchWord) {
			if(!searchWord.isEmpty()) {
				Integer row = custDA.searchCustomer(searchWord);
				
				if(row >= 0){
//					maintainCustUI.getCustRecordsTBL().setRowSelectionInterval(row, row);
					autoSelectRecord(row);
					
					MouseEvent mouseEv = new MouseEvent(maintainCustUI, 0, 0, 0, 0, 0, 0, false);
					mouseEv.setSource(maintainCustUI.getCustRecordsTBL());
					
					maintainCustHandler.mouseClicked(mouseEv);
				}
			}
		}

		private void autoSelectRecord(Integer row) {
			maintainCustUI.getCustRecordsTBL().requestFocus();
			maintainCustUI.getCustRecordsTBL().changeSelection(row, 0, false, false);			
		}

		private void displayViewTransactions() {
			if(!viewTransHandler.isTransListEmpty)
				maintainCustUI.displayViewTrans();
			else DialogBox.showMsgDialog("", 
										 "Customer has no transactions yet", 
										 JOptionPane.WARNING_MESSAGE);
		}

		private void processAddCustomer() {
			Integer opt = DialogBox.showOKCancelMsg("", "Confirm customer to add?");
			
			if(opt == JOptionPane.OK_OPTION){
				String custNo = maintainCustUI.getCustNumTF().getText().trim(), 
					   custName = maintainCustUI.getCustNameTF().getText().trim(), 
					   address = maintainCustUI.getAddressTF().getText().trim(),
					   payterm = maintainCustUI.getPaytermCB().getSelectedItem().toString().trim();
				
				Customer cust = new Customer(!custNo.isEmpty()? custNo:null, 
										   	 !custName.isEmpty()? custName:null,
										   	 !address.isEmpty()? address:null,
										   	 !payterm.isEmpty()? payterm:null,
											 null,
											 null);
				
				String custNoLatest = custDA.addCustomer(cust);
				
				updateCustRecords();
				
				searchCustomer(custNoLatest);
				
//				setFieldsBlank();
			}
		}

		private void processDeleteCustomer() {
			Integer opt = DialogBox.showOKCancelMsg("", "Confirm customer to delete?");
			
			if(opt == JOptionPane.OK_OPTION){
				String custNoLatest = custDA.deleteCustomer(getSelectedCustomer());
				
				updateCustRecords();
				
				searchCustomer(custNoLatest);
			}
		}

		private void processEditCustomer() {
			Integer opt = DialogBox.showOKCancelMsg("", "Confirm customer to edit?");
			
			if(opt == JOptionPane.OK_OPTION){
				Customer selectedCust = getSelectedCustomer(); 
				
				String[] validFieldChanges = getValidFieldChanges(selectedCust);
				
				Boolean isFieldChange = false;
				for(String field:validFieldChanges) 
					if(field != null) {
						isFieldChange = true;
						break;
					}
				
				if(isFieldChange) {
					String custNoLatest = custDA.editCustomer(selectedCust, validFieldChanges);
					
					updateCustRecords();
					
					searchCustomer(custNoLatest);
					
				} else DialogBox.showMsgDialog("", 
						"There is no change of data in fields\nPlease recheck your inputs", JOptionPane.INFORMATION_MESSAGE);
			}
		}

		private Customer getSelectedCustomer() {
			return custDA.getCustList().get(
					maintainCustUI.getCustRecordsTBL().getSelectedRow());			
		}

		private String[] getValidFieldChanges(Customer cust) {
			String custNoField = maintainCustUI.getCustNumTF().getText().trim(),
				   custNameField = maintainCustUI.getCustNameTF().getText().trim(),
				   addressField = maintainCustUI.getAddressTF().getText().trim(),
				   payTermField = maintainCustUI.getPaytermCB().getSelectedItem().toString().trim();
			
			return new String[]{
				!cust.getCustNo().equals(custNoField)? custNoField:null,
				!cust.getCustName().equals(custNameField)? custNameField:null,
				!cust.getAddress().equals(addressField)? addressField:null,
				!cust.getPayTerm().equals(payTermField)? payTermField:null,	
				};
		}
		

//		MOUSE EVENTS
		
		@Override
		public void mouseClicked(MouseEvent ev) {
			if(ev.getSource() == maintainCustUI.getCustRecordsTBL()){
				enableCustNoTF(false);
				rowSelected();
				
			} else if(ev.getSource() == maintainCustUI){
				if(maintainCustUI.getCustRecordsTBL().hasFocus()){
					enableCustNoTF(true);
					maintainCustUI.getCustRecordsTBL().clearSelection();
					setFieldsBlank();
					enableTableRelatedButtons(false);
					updateStampView("");
				}
			}
		}

		private void setFieldsBlank() {
			setFields("", "", "", "");	
		}

		private void enableCustNoTF(boolean b) {
			maintainCustUI.getCustNumTF().setEditable(b);
		}

		private void rowSelected() {
			Customer cust = getSelectedCustomer();
			
			updateFieldsWithSelectedRow(cust);
			
			viewTransHandler.setTransListEmpty(cust.getListTransactions().isEmpty());
			
			if(!viewTransHandler.isTransListEmpty())
				viewTransHandler.setViewTransUI(cust);
			
			try{
				Boolean isDelete = cust.getStamp().contains("DELETED");
				maintainCustUI.getRecoverB().setEnabled(isDelete);
				maintainCustUI.getDeleteB().setEnabled(!isDelete);
			} catch(Exception e){}
			
			updateStampView(cust.getStamp());
			
			enableTableRelatedButtons(true);
		
		}

		private void updateFieldsWithSelectedRow(Customer cust) {
			setFields(cust.getCustNo(), cust.getCustName(),
					  cust.getAddress(), cust.getPayTerm());
		}

		private void setFields(String custNum, String custName, String address, String payterm) {
			maintainCustUI.getCustNumTF().setText(custNum); 
			maintainCustUI.getCustNameTF().setText(custName); 
			maintainCustUI.getAddressTF().setText(address); 
			maintainCustUI.getPaytermCB().setSelectedItem(payterm);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		public void enableTableRelatedButtons(boolean flag) {
			maintainCustUI.getEditB().setEnabled(flag);
			maintainCustUI.getDeleteB().setEnabled(flag);
			maintainCustUI.getViewTransactionsB().setEnabled(flag);
		}
	}

	
	
	private class SetAdminHandler implements ActionListener, MouseListener {

		public void actionPerformed(ActionEvent ev) {
			Object evo = ev.getSource();
			
			if(evo == setAdminUI.getAddB()) processAddUser();
			else if(evo == setAdminUI.getEditB()) processEditUser();
			else if(evo == setAdminUI.getDeleteB()) processDeleteUser();
			else if(evo == setAdminUI.getSearchB()) processSearchUser();
			else if(evo == setAdminUI.getRecoverB()) processRecoverUser();
		}

		private void processRecoverUser() {
			Integer opt = DialogBox.showOKCancelMsg("", "Confirm user to recover?");
			
			if(opt == JOptionPane.OK_OPTION){
				String userIDLatest = userDA.recoverUser(getSelectedUser());
				
				updateUserRecords();
				
				searchUser(userIDLatest);
			}
		}

		public void updateUserRecords() {
			ArrayList<User> userList = userDA.getUserList();
			userRecordsModel = setAdminUI.getUserRecordsModel();
			
			setAdminUI.clearUserRecordsTBL();
			for(User user:userList)
				userRecordsModel.addRow(new Object[]{user.getUserID(),
													 user.getUserName(),
													 user.getLastName(),
													 user.getFirstName()});
			
			updateStampView("");
		}

		private void updateStampView(String stamp) {
			setAdminUI.getStampP().setStampValue(stamp);			
		}
		
		private void processSearchUser() {
			searchUser(setAdminUI.getSearchTF().getText().trim());
		}

		private void searchUser(String searchWord) {
			if(!searchWord.isEmpty()) {
				Integer row = userDA.searchUser(searchWord);
				
				if(row >= 0){
					setAdminUI.getUserRecordsTBL().setRowSelectionInterval(row, row);
					autoSelectRecord(row);
					
					MouseEvent mouseEv = new MouseEvent(setAdminUI, 0, 0, 0, 0, 0, 0, false);
					mouseEv.setSource(setAdminUI.getUserRecordsTBL());
					
					setAdminHandler.mouseClicked(mouseEv);
				}
			}
		}

		private void autoSelectRecord(Integer row) {
			setAdminUI.getUserRecordsTBL().requestFocus();
			setAdminUI.getUserRecordsTBL().changeSelection(row, 0, false, false);			
		}
		
		private void processAddUser() {
			Integer opt = DialogBox.showOKCancelMsg("", "Confirm user to add?");
			
			if(opt == JOptionPane.OK_OPTION){
				try{
				String userID = setAdminUI.getUserIdTF().getText(),
					   userName = setAdminUI.getUserNameTF().getText(),
					   password = setAdminUI.getPasswordPF().getSelectedText(),
					   lastName = setAdminUI.getLastNameTF().getText(),
					   firstName = setAdminUI.getFirstNameTF().getText();
								
				ArrayList<Module> modList = userID.isEmpty()? new ArrayList<Module>():
																	getModuleList(userID);
				
				User user = new User(!userID.isEmpty()? userID.trim():null,
								   	 !userName.isEmpty()? userName.trim():null,
						   			 !password.isEmpty()? password:null,
								   	 !lastName.isEmpty()? lastName.trim():null,
								   	 !firstName.isEmpty()? firstName.trim():null,
						   			 modList,
									 null);
				
				String userIDLatest = userDA.addUser(user);
				
				updateUserRecords();
				
				searchUser(userIDLatest);

				} catch(Exception e) {}
				
			}
		}
			
		private ArrayList<Module> getModuleList(String userID) {
			ArrayList<Module> moduleList = new ArrayList<Module>();
			
			moduleList.add(getCustomerModule(userID));
			moduleList.addAll(getReportsModule(userID));
			
			return moduleList;
		}

//		}

		private void processDeleteUser() {
			Integer opt = DialogBox.showOKCancelMsg("", "Confirm user to delete?");
			
			if(opt == JOptionPane.OK_OPTION){
				String userIDLatest = userDA.deleteUser(getSelectedUser());
				
				updateUserRecords();
				
				searchUser(userIDLatest);
			}
		}

		private void processEditUser() {
			Integer opt = DialogBox.showOKCancelMsg("", "Confirm user to edit?");
			
			if(opt == JOptionPane.OK_OPTION){
				User selectedUser = getSelectedUser(); 
				
				String[] validFieldChanges = getValidFieldChanges(selectedUser);
				Boolean[] validChkBoxChanges = getValidChkBoxChanges(selectedUser);
				
				Boolean isFieldChange = false;
				for(String field:validFieldChanges) 
					if(field != null) {
						isFieldChange = true;
						break;
					}
				
				Boolean isChkBoxChange = false;
				for(Boolean field:validChkBoxChanges) 
					if(field != null) {
						isChkBoxChange = true;
						break;
					}
				
				if(isFieldChange || isChkBoxChange) {
					String userIDLatest = userDA.editUser(selectedUser, validFieldChanges, validChkBoxChanges);
					updateUserRecords();
					searchUser(userIDLatest);
				} else DialogBox.showMsgDialog("", 
						"There is no change of data in fields\nPlease recheck your inputs", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		
		private Boolean[] getValidChkBoxChanges(User user) {
			ArrayList<Module> modList = user.getListModule();

			Module custMod = null, rpt1Mod = null, rpt2Mod = null, rpt3Mod = null, rpt4Mod = null;
			
			for(Module mod:modList)
				if(mod.getModuleCode().equals(ModuleCodes.MODC001.name())) custMod = mod;
				else if(mod.getModuleCode().equals(ModuleCodes.RPT001.name()))  rpt1Mod = mod;
				else if(mod.getModuleCode().equals(ModuleCodes.RPT002.name()))  rpt2Mod = mod;
				else if(mod.getModuleCode().equals(ModuleCodes.RPT003.name()))  rpt3Mod = mod;
				else if(mod.getModuleCode().equals(ModuleCodes.RPT004.name()))  rpt4Mod = mod;
			
			
			try{
				return new Boolean[]{
					custMod.getCanView() != setAdminUI.getViewChkBx().isSelected()? setAdminUI.getViewChkBx().isSelected():null,
					custMod.getCanAdd() != setAdminUI.getAddChkBx().isSelected()? setAdminUI.getAddChkBx().isSelected():null,
					custMod.getCanEdit() != setAdminUI.getEditChkBx().isSelected()? setAdminUI.getEditChkBx().isSelected():null,
					custMod.getCanDelete() != setAdminUI.getDeleteChkBx().isSelected()? setAdminUI.getDeleteChkBx().isSelected():null,
					rpt1Mod.getCanView() != setAdminUI.getCBSRChkBx().isSelected()? setAdminUI.getCBSRChkBx().isSelected():null,
					rpt2Mod.getCanView() != setAdminUI.getCPRChkBx().isSelected()? setAdminUI.getCPRChkBx().isSelected():null,
					rpt3Mod.getCanView() != setAdminUI.getCSRChkBx().isSelected()? setAdminUI.getCSRChkBx().isSelected():null,
					rpt4Mod.getCanView() != setAdminUI.getTCRChkBx().isSelected()? setAdminUI.getTCRChkBx().isSelected():null};
			} catch(Exception e){
				return null;
			}
		}

		private String[] getValidFieldChanges(User user) {
			String userIDField = setAdminUI.getUserIdTF().getText().trim(),
				   userNameField = setAdminUI.getUserNameTF().getText().trim(),
				   passwordField = setAdminUI.getPasswordPF().getSelectedText(),
				   lastNameField = setAdminUI.getLastNameTF().getText().trim(),
				   firstNameField = setAdminUI.getFirstNameTF().getText().trim();
			
			return new String[]{
				!user.getUserID().equals(userIDField)? userIDField:null,
				!user.getUserName().equals(userNameField)? userNameField:null,
				!user.getPassword().equals(passwordField)? passwordField:null,
				!user.getLastName().equals(lastNameField)? lastNameField:null,
				!user.getFirstName().equals(firstNameField)? firstNameField:null
				};
		}

		private ArrayList<Module> getReportsModule(String userID) {
			ArrayList<Module> modReportList = new ArrayList<Module>(); 
			
			modReportList.add(setRepModulesFields(ModuleCodes.RPT001.name(), userID, 
					setAdminUI.getCBSRChkBx().isSelected()));
			modReportList.add(setRepModulesFields(ModuleCodes.RPT002.name(), userID, 
					setAdminUI.getCPRChkBx().isSelected()));
			modReportList.add(setRepModulesFields(ModuleCodes.RPT003.name(), userID, 
					setAdminUI.getCSRChkBx().isSelected()));
			modReportList.add(setRepModulesFields(ModuleCodes.RPT004.name(), userID, 
					setAdminUI.getTCRChkBx().isSelected()));
			
			return modReportList;
		}

		private Module setRepModulesFields(String moduleCode, String userID, Boolean canView) {
			Module repMod = new Module();
			
			repMod.setModuleCode(moduleCode);
			repMod.setUserID(userID);
			repMod.setCanView(canView);
			
			return repMod;
		}

		private Module getCustomerModule(String userID) {
			if(userID == null) return null;
			
			return new Module(ModuleCodes.MODC001.name(),
						      userID,
						      setAdminUI.getViewChkBx().isSelected(),
						      setAdminUI.getAddChkBx().isSelected(),
						      setAdminUI.getEditChkBx().isSelected(),
						      setAdminUI.getDeleteChkBx().isSelected()); 
		}

		private User getSelectedUser() {
			return userDA.getUserList().get(
					setAdminUI.getUserRecordsTBL().getSelectedRow());			
		}

//		MOUSE EVENTS
		
		@Override
		public void mouseClicked(MouseEvent ev) {
			if(ev.getSource() == setAdminUI.getUserRecordsTBL()){
				enableCustNoTF(false);
				rowSelected();
				
			} else if(ev.getSource() == setAdminUI){
				if(setAdminUI.getUserRecordsTBL().hasFocus()){
					enableCustNoTF(true);
					setAdminUI.getUserRecordsTBL().clearSelection();
					setAdminUI.getUserRecordsTBL().requestFocus(false);
					setFieldsAndChkBoxesBlank();
					enableTableRelatedButtons(false);
					updateStampView("");
					setAdminUI.getErrPasswordL().setText("");
				}
			}
		}
		
		private void enableCustNoTF(boolean b) {
			setAdminUI.getUserIdTF().setEditable(b);
		}
		
		private void setFieldsAndChkBoxesBlank() {
			setFields("", "", "", "", "");
			setCustomerModChkBoxes(false, false, false, false);
			setReportModChkBoxes(false, false, false, false);
		}

		private void setReportModChkBoxes(boolean cbsrFlag, boolean cprFlag, boolean csrFlag, boolean tcrFlag) {
			setAdminUI.getCBSRChkBx().setSelected(cbsrFlag);
			setAdminUI.getCPRChkBx().setSelected(cprFlag);
			setAdminUI.getCSRChkBx().setSelected(csrFlag);
			setAdminUI.getTCRChkBx().setSelected(tcrFlag);
		}

		private void setCustomerModChkBoxes(boolean viewFlag, boolean addFlag, boolean editFlag, boolean deleteFlag) {
			setAdminUI.getViewChkBx().setSelected(viewFlag);
			setAdminUI.getAddChkBx().setSelected(addFlag);
			setAdminUI.getEditChkBx().setSelected(editFlag);
			setAdminUI.getDeleteChkBx().setSelected(deleteFlag);
		}

		private void rowSelected() {
			User user = getSelectedUser();
			
			updateFieldsWithSelectedRow(user);
			
			enableTableRelatedButtons(true);
			
			setAdminUI.getErrPasswordL().setText("Type to reset password");
			
			try{
				Boolean isDelete = user.getStamp().contains("DELETED");
				setAdminUI.getRecoverB().setEnabled(isDelete);
				setAdminUI.getDeleteB().setEnabled(!isDelete);
			} catch(Exception e){}
			
			updateStampView(user.getStamp());
			
		}

		private void updateFieldsWithSelectedRow(User user) {
			setFields(user.getUserID(), user.getUserName(),
					  user.getLastName(), user.getFirstName(), "");

			try{
				List<Module> modList = user.getListModule();
				
				Module custMod = null, rpt1Mod = null, rpt2Mod = null, rpt3Mod = null, rpt4Mod = null;
				
				for(Module mod:modList)
					if(mod.getModuleCode().equals(ModuleCodes.MODC001.name())) custMod = mod;
					else if(mod.getModuleCode().equals(ModuleCodes.RPT001.name()))  rpt1Mod = mod;
					else if(mod.getModuleCode().equals(ModuleCodes.RPT002.name()))  rpt2Mod = mod;
					else if(mod.getModuleCode().equals(ModuleCodes.RPT003.name()))  rpt3Mod = mod;
					else if(mod.getModuleCode().equals(ModuleCodes.RPT004.name()))  rpt4Mod = mod;

				setCustomerModChkBoxes(custMod.getCanView(), custMod.getCanAdd(), 
									   custMod.getCanEdit(), custMod.getCanDelete());
				setReportModChkBoxes(rpt1Mod.getCanView(), rpt2Mod.getCanView(), 
									 rpt3Mod.getCanView(), rpt4Mod.getCanView());
			} catch(Exception ex){}
		}

		private void setFields(String userID, String userName, String lastName, String firstName, String password) {
			setAdminUI.getUserIdTF().setText(userID);
			setAdminUI.getUserNameTF().setText(userName); 
			setAdminUI.getLastNameTF().setText(lastName); 
			setAdminUI.getFirstNameTF().setText(firstName);
			setAdminUI.getPasswordPF().setText(password);
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
		public void enableTableRelatedButtons(boolean flag) {
			setAdminUI.getAddB().setEnabled(!flag);
			setAdminUI.getEditB().setEnabled(flag);
			setAdminUI.getDeleteB().setEnabled(flag);
			setAdminUI.getRecoverB().setEnabled(flag);
		}
	}
	

	private class GenerateReportsHandler implements ListSelectionListener, ActionListener {

		Object[] reportList;

		private GenerateReportsHandler(){
			reportList = reports.getReportsList().toArray();
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			processSelectedReport(generateReportsUI.getReportsLIST().getSelectedIndex());
		}

		public Object[] getReportList() {
			return reportList;
		}
		
		@Override
		public void valueChanged(ListSelectionEvent ev) {
			generateReportsUI.getViewReportB().setEnabled(true);
		}
		
		public void setStatMsg(String msg){
			generateReportsUI.getStatL().setText(msg);
		}

		private void processSelectedReport(Integer selectedIndex) {
			enableMainUI(false);
			setStatMsg("Processing Report...");
			
			String reportName = reportList[selectedIndex].toString();
			
			try {
				if(selectedIndex == 0)
					 processCBSreport(reportName);
				else reports.viewReport(reportName);
			} catch (Exception e) {
				DialogBox.showErrorDialog("Cannot process and generate report."
						+ "\nPossibilities are multiple file-related naming mismatch or report technicalities");
			}
			
			setStatMsg("");
			enableMainUI(true);
		}

		private void enableMainUI(boolean b) {
			mainUI.setEnabled(b);			
		}

		private void processCBSreport(String reportName) throws Exception {
			String stateCode = DialogBox.showInputDialog("", "Enter state code", JOptionPane.PLAIN_MESSAGE);
			
			if(stateCode != null)
				if(stateCode.isEmpty()){
					DialogBox.showErrorDialog("Field is blank.\nPlease repeat with valid input");
					processCBSreport(reportName);
				} else reports.viewParamReport(reportName, stateCode.toUpperCase());			
		}

	}

	public class ViewTransHandler implements ActionListener {
		
		private DefaultTableModel salesDetailsModel;
		List<Sales> transactionsList;
		private Integer salesCursor = 0;
		private Boolean isTransListEmpty;

		public void actionPerformed(ActionEvent ev) {
			verifyNavigateAction(ev.getSource());
		}
		
		private Sales verifyNavigateAction(Object evO) {
			if(evO == viewTransUI.getNextAccB()) 	   updateTransViewPage(getNextSalesDtls());
			else if(evO == viewTransUI.getPrevAccB())  updateTransViewPage(getPrevSalesDtls());
			else if(evO == viewTransUI.getFirstAccB()) updateTransViewPage(getFirstSalesDtls());
			else if(evO == viewTransUI.getLastAccB())  updateTransViewPage(getLastSalesDtls());
			
			return null;
		}

		public Boolean isTransListEmpty() {
			return isTransListEmpty;
		}

		public void setTransListEmpty(Boolean isTransListEmpty) {
			this.isTransListEmpty = isTransListEmpty;
		}

		public List<Sales> getTransactionsList() {
			return transactionsList;
		}

		public Sales getNextSalesDtls(){
			return transactionsList.get(++salesCursor < transactionsList.size()?
					 					salesCursor:(salesCursor=transactionsList.size()-1));
		}
		
		public Sales getPrevSalesDtls(){
			return transactionsList.get(--salesCursor >= 0?
					 					salesCursor:(salesCursor=0));
		}
		
		public Sales getFirstSalesDtls(){
			return transactionsList.get((salesCursor=0));
		}
		
		public Sales getLastSalesDtls(){
			return transactionsList.get((salesCursor=transactionsList.size()-1));
		}

		public void setTransactionsList(List<Sales> transactionsList) {
			this.transactionsList = transactionsList;
		}

		private void setViewTransUI(Customer cust) {
			setTransactionsList(cust.getListTransactions());
			
			updateTransViewPage(getFirstSalesDtls());
		}

		private void updateTransViewPage(Sales sales) {
			viewTransUI.getSalesNumTF().setText(sales.getSalesNo());
			viewTransUI.getSalesDateTF().setText(sales.getSalesDate().toString());
			viewTransUI.getTotalSalesTF().setText(sales.getTotalSalesPrice().toString());
			
			viewTransUI.clearViewTransTBL();
			for(SalesDetail salesDtls:sales.getListSalesDetail())
				viewTransUI.getSalesDetailsRecordsModel().addRow(new Object[]{salesDtls.getProduct().getProdCode(),
													  salesDtls.getProduct().getDescription(),
													  salesDtls.getProduct().getUnit(),
													  salesDtls.getUnitPrice(),
													  salesDtls.getTotalPrice()});
		}
		
		public DefaultTableModel getSalesDetailsModel() {
			return salesDetailsModel;
		}

		public void setSalesDetailsModel(DefaultTableModel salesDetailsModel) {
			this.salesDetailsModel = salesDetailsModel;
		}

	}
	
}
