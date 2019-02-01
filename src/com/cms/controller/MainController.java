package com.cms.controller;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import com.cms.domains.*;
import com.cms.techServ.*;
import com.cms.ui.*;
import com.cms.ui.utils.DialogBox;
import com.cms.utils.AdminChecker;
import com.cms.utils.ModuleCodes;

public class MainController {

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
	
	public MainController(User user, Connection connection) {
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
		
		if(bSet.isEmpty()) {
			mainUI.getWelcomeL().setText("There are no availed options. Please consult with the admin");
			mainUI.getWelcomeL().setFont(new Font("Tahoma", Font.PLAIN, 16));
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
		setViewTransactionsUI();
		setMaintainCustomerUI();
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
		setAdminUI.getSearchTF().addKeyListener(setAdminHandler);
		setAdminUI.getUserRecordsTBL().addMouseListener(setAdminHandler);
		setAdminUI.addMouseListener(setAdminHandler);
		setAdminUI.editableUserFieldsAndChkBoxes(false);
		
		setAdminHandler.updateUserRecords();
		setAdminHandler.prepareFirstUserDisplay();
		setAdminHandler.setNormalMode_SetAdmin();
	}

	private void setMaintainCustomerUI() {
		maintainCustHandler = new MaintainCustomerHandler();
		
		Module custMod = new Module();
		for(Module mod:userMain.getListModule()) 
			if(mod.getModuleCode().equals(ModuleCodes.MODC001.name())){
				custMod = mod;
				break;
			}
		
		Boolean canView = custMod.getCanView() || isAdmin,
				canAdd = custMod.getCanAdd() || isAdmin,
				canEdit = custMod.getCanEdit() || isAdmin,
				canDelete = custMod.getCanDelete() || isAdmin;
		
		isCustModEnabled = false;
		if(canView || canAdd || canEdit || canDelete) isCustModEnabled = true;
		
		Boolean[] priviledges =  new Boolean[]{canView, canAdd, canEdit, canDelete};
		
		JButton[] bSet_MC = maintainCustUI.getBSet();
		for(int i=0; i<priviledges.length; i++)
			if(priviledges[i]){
				if(i!=0) maintainCustUI.addToButtonsPNL(bSet_MC[i]);
				else maintainCustUI.getAvailedBtns().add(bSet_MC[i]);
				bSet_MC[i].addActionListener(maintainCustHandler);
			}
		
		maintainCustUI.getViewTransactionsB().setVisible(canView);
		
		if(isAdmin){
			maintainCustUI.getRecoverB().addActionListener(maintainCustHandler);
			maintainCustUI.addToButtonsPNL(maintainCustUI.getRecoverB());
		} else maintainCustUI.getStampP().setVisible(false);
		
		maintainCustUI.getSearchB().addActionListener(maintainCustHandler);
		maintainCustUI.getSearchTF().addKeyListener(maintainCustHandler);
		maintainCustUI.getCustRecordsTBL().addMouseListener(maintainCustHandler);
		maintainCustUI.editableCustFields(false);
		
		maintainCustHandler.updateCustRecords();
		maintainCustHandler.prepareFirstCustDisplay();
		maintainCustHandler.setNormalMode_MaintainCust();
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

	private class MaintainCustomerHandler implements ActionListener, MouseListener, KeyListener {

		private ActionListener addCustListener = new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Object evo = ev.getSource();

				if(evo == maintainCustUI.getSaveB()) saveAddCustomer();
				else if(evo == maintainCustUI.getCancelB()) cancelAddCustomer();
			}

			private void cancelAddCustomer() {
				setNormalMode_MaintainCust();
				maintainCustUI.getAddB().setEnabled(true);
				removePromptListeners(this);
			}
			
			private void saveAddCustomer() {
				Customer cust = getCustInFieldInfo(); 
				String custNoLatest = custDA.addCustomer(cust);
				
				if(custNoLatest != null){
					updateCustRecords();
					searchCustomer(custNoLatest);
					cancelAddCustomer();
				}
			}
		};
		
		private ActionListener editCustListener = new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Object evo = ev.getSource();
				
				if(evo == maintainCustUI.getSaveB()) saveEditCustomer();
				else if(evo == maintainCustUI.getCancelB()) cancelEditCustomer();
			}

			private void cancelEditCustomer() {
				setNormalMode_MaintainCust();
				maintainCustUI.getEditB().setEnabled(true);
				removePromptListeners(this);
			}

			private void saveEditCustomer() {
				Customer fieldCust = getCustInFieldInfo();
				Customer selectedCust = getSelectedCustomer();
				
				if(selectedCust.getStamp().contains("DELETED"))
					DialogBox.showMsgDialog("", "Cannot modify deleted customer"
							+ "\nFirst recover deleted customer to be edited", JOptionPane.WARNING_MESSAGE);
				else {
					
					String custNoLatest = custDA.editCustomer(selectedCust, fieldCust);
					if(custNoLatest != null){
						updateCustRecords();
						searchCustomer(custNoLatest);
						cancelEditCustomer();
					}
				}
			}
		
		};

		public void actionPerformed(ActionEvent ev) {
			Object evo = ev.getSource();
			
			if(evo == maintainCustUI.getAddB()) processAddCustomer();
			else if(evo == maintainCustUI.getEditB()) processEditCustomer();
			else if(evo == maintainCustUI.getDeleteB()) processDeleteCustomer();
			else if(evo == maintainCustUI.getViewTransactionsB()) displayViewTransactions();
			else if(evo == maintainCustUI.getSearchB()) processSearchCustomer();
			else if(evo == maintainCustUI.getRecoverB()) processRecoverCustomer();
		}
		
		private void removePromptListeners(ActionListener actionListener) {
			maintainCustUI.getSaveB().removeActionListener(actionListener);
			maintainCustUI.getCancelB().removeActionListener(actionListener);
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
			for(int i=0; i<custList.size(); i++){
				Customer cust = custList.get(i);
				custRecordsModel.addRow(new Object[]{cust.getCustNo(),
													 cust.getCustName(),
													 cust.getAddress(),
													 cust.getPayTerm()});
//				if(cust.getStamp().contains("DELETED")) custRecordsModel.setRowColour(i, Color.GRAY);
			}
			
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
					
				}
			}
		}

		public void prepareFirstCustDisplay(){
			try{
				autoSelectRecord(0);
			} catch(Exception ex) {} 
			rowSelected();
		}
		
		private void autoSelectRecord(Integer row) {
			maintainCustUI.getCustRecordsTBL().requestFocus();
			maintainCustUI.getCustRecordsTBL().changeSelection(row, 0, false, false);
			
			MouseEvent mouseEv = new MouseEvent(maintainCustUI, 0, 0, 0, 0, 0, 0, false);
			mouseEv.setSource(maintainCustUI.getCustRecordsTBL());
			
			maintainCustHandler.mouseClicked(mouseEv);
			rowSelected();
		}

		private void displayViewTransactions() {
			if(!viewTransHandler.isTransListEmpty)
				maintainCustUI.displayViewTrans();
			else DialogBox.showMsgDialog("", 
										 "Customer has no transactions yet", 
										 JOptionPane.WARNING_MESSAGE);
		}

		private void processAddCustomer() {
			setFieldsBlank();
			maintainCustUI.editableCustFields(true);
			
			setPromptButtons(addCustListener);
			maintainCustUI.getAddB().setEnabled(false);
			maintainCustUI.setSearchCompsEnabled(false);
			maintainCustUI.getAddB().setVisible(true);
			enableCustNoTF(true);
			
			maintainCustUI.getCustRecordsTBL().setEnabled(false);
		}

		private void setTableButtonsVisibility(boolean flag) {
			ArrayList<JButton> availedBtns = maintainCustUI.getAvailedBtns();
			for(JButton btn:availedBtns)
				btn.setVisible(flag);
		}

		private void visiblePromptButtons(boolean b) {
			maintainCustUI.getSaveB().setVisible(b);
			maintainCustUI.getCancelB().setVisible(b);	
		}

		private Customer getCustInFieldInfo() {
			String custNo = maintainCustUI.getCustNumTF().getText().trim(), 
					   custName = maintainCustUI.getCustNameTF().getText().trim(), 
					   address = maintainCustUI.getAddressTF().getText().trim(),
					   payterm = maintainCustUI.getPaytermCB().getSelectedItem().toString().trim();
			
			return new Customer(!custNo.isEmpty()? custNo:null, 
							   	 !custName.isEmpty()? custName:null,
							   	 !address.isEmpty()? address:null,
							   	 !payterm.isEmpty()? payterm:null,
								 null,
								 null);
		}

		private void processDeleteCustomer() {
			Integer opt = DialogBox.showOKCancelMsg("", "Confirm customer to delete?");
			
			if(opt == JOptionPane.OK_OPTION){
				String custNoLatest = custDA.deleteCustomer(getSelectedCustomer());
				
				updateCustRecords();
				
				if(isAdmin) searchCustomer(custNoLatest);
			}
		}

		private void processEditCustomer() {
			if(maintainCustUI.getCustRecordsTBL().getSelectedRow()<0) 
				prepareFirstCustDisplay();
			
			maintainCustUI.editableCustFields(true);
			maintainCustUI.getCustNumTF().setEditable(false);
			setPromptButtons(editCustListener);
			maintainCustUI.getEditB().setEnabled(false);
			maintainCustUI.getEditB().setVisible(true);
		}

		private void setPromptButtons(ActionListener handler) {
			setTableButtonsVisibility(false);
			
			visiblePromptButtons(true);
			
			maintainCustUI.getSaveB().addActionListener(handler);
			maintainCustUI.getCancelB().addActionListener(handler);
		}

		private Customer getSelectedCustomer() {
			return custDA.getCustList().get(
					maintainCustUI.getCustRecordsTBL().getSelectedRow());			
		}

		public void setNormalMode_MaintainCust() {
			maintainCustUI.editableCustFields(false);
			maintainCustUI.setSearchCompsEnabled(true);
			enableCustNoTF(false);
			visiblePromptButtons(false);
			setTableButtonsVisibility(true);
			maintainCustUI.getCustRecordsTBL().setEnabled(true);
			rowSelected();
		}
		
//		MOUSE EVENTS
		
		@Override
		public void mouseClicked(MouseEvent ev) {
			if(ev.getSource() == maintainCustUI.getCustRecordsTBL() &&
			   maintainCustUI.getCustRecordsTBL().isEnabled()){
				enableCustNoTF(false);
				rowSelected();
			} else maintainCustUI.getViewTransactionsB().setEnabled(false);
		}

		private void setFieldsBlank() {
			setFields("", "", "", "");	
		}

		private void enableCustNoTF(boolean b) {
			maintainCustUI.getCustNumTF().setEditable(b);
		}

		private void rowSelected() {
			Customer cust = getSelectedCustomer();
			
			try{
			updateFieldsWithSelectedRow(cust);
			
			viewTransHandler.setTransListEmpty(cust.getListTransactions().isEmpty());
			
			if(!viewTransHandler.isTransListEmpty())
				viewTransHandler.setViewTransUI(cust);
			
			maintainCustUI.getViewTransactionsB().setEnabled(true);
			
				Boolean isDelete = cust.getStamp().contains("DELETED");
				maintainCustUI.getRecoverB().setEnabled(isDelete);
				maintainCustUI.getDeleteB().setEnabled(!isDelete);
			} catch(Exception e){}
			
			updateStampView(cust.getStamp());
			
			maintainCustUI.getCustRecordsTBL().requestFocus();
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

		@Override
		public void keyPressed(KeyEvent ev) {
			if(ev.getKeyCode() == KeyEvent.VK_ENTER) processSearchCustomer();
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
	private class SetAdminHandler implements ActionListener, MouseListener, KeyListener {

		private ActionListener addUserListener = new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Object evo = ev.getSource();

				if(evo == setAdminUI.getSaveB()) saveAddUser();
				else if(evo == setAdminUI.getCancelB()) cancelAddUser();
			}

			private void cancelAddUser() {
				setNormalMode_SetAdmin();
				setAdminUI.getAddB().setEnabled(true);
				removePromptListeners(this);
			}

			private void saveAddUser() {
				User user = getUserInFieldInfo();
				String userIdLatest = userDA.addUser(user);
				if(userIdLatest != null){
					updateUserRecords();
					searchUser(userIdLatest);
					cancelAddUser();
				}
			}
		};
		
		private ActionListener editUserListener = new ActionListener(){
			public void actionPerformed(ActionEvent ev) {
				Object evo = ev.getSource();
				
				if(evo == setAdminUI.getSaveB()) saveEditUser();
				else if(evo == setAdminUI.getCancelB()) cancelEditUser();
			}

			private void cancelEditUser() {
				setNormalMode_SetAdmin();
				setAdminUI.getEditB().setEnabled(true);
				removePromptListeners(this);
				setAdminUI.removePasswordHandler();
			}

			private void saveEditUser() {
				User fieldUser = getUserInFieldInfo();
				User selectedUser = getSelectedUser();
				
				if(selectedUser.getStamp().contains("DELETED"))
					DialogBox.showMsgDialog("", "Cannot modify deleted user"
							+ "\nFirst recover deleted user to be edited", JOptionPane.WARNING_MESSAGE);
				else {
					
					if(fieldUser.getPassword().equals(SetAdminUI.RESETPW))
						fieldUser.setPassword(selectedUser.getPassword());
					
					String userIdLatest = userDA.editUser(selectedUser, fieldUser);
					if(userIdLatest != null){
						updateUserRecords();
						searchUser(userIdLatest);
						cancelEditUser();
					}
				}
			}
		
		};
		
		public void actionPerformed(ActionEvent ev) {
			Object evo = ev.getSource();
			
			if(evo == setAdminUI.getAddB()) processAddUser();
			else if(evo == setAdminUI.getEditB()) processEditUser();
			else if(evo == setAdminUI.getDeleteB()) processDeleteUser();
			else if(evo == setAdminUI.getSearchB()) processSearchUser();
			else if(evo == setAdminUI.getRecoverB()) processRecoverUser();
		}

		public void prepareFirstUserDisplay() {
			try{
				autoSelectRecord(0);
			} catch (Exception ex) {}
			rowSelected();
		}

		private void removePromptListeners(ActionListener actionListener) {
			setAdminUI.getSaveB().removeActionListener(actionListener);
			setAdminUI.getCancelB().removeActionListener(actionListener);
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
//					setAdminUI.getUserRecordsTBL().setRowSelectionInterval(row, row);
					autoSelectRecord(row);
				}
			}
		}

		private void autoSelectRecord(Integer row) {
			setAdminUI.getUserRecordsTBL().requestFocus();
			setAdminUI.getUserRecordsTBL().changeSelection(row, 0, false, false);

			MouseEvent mouseEv = new MouseEvent(setAdminUI, 0, 0, 0, 0, 0, 0, false);
			mouseEv.setSource(setAdminUI.getUserRecordsTBL());
			
			setAdminHandler.mouseClicked(mouseEv);
			rowSelected();
		}
		
		private void processAddUser() {
			setAdminUI.getPasswordPF().setText("");
			setAdminUI.setPasswordCompsVisible(true);
			
			setAdminUI.setSearchCompsEnabled(false);
			setFieldsAndChkBoxesBlank();
			setAdminUI.editableUserFieldsAndChkBoxes(true);
			enableUserIdTF(true);
			
			setPromptButtons(addUserListener);
			setAdminUI.getAddB().setEnabled(false);
			setAdminUI.getAddB().setVisible(true);
			
			setAdminUI.getUserRecordsTBL().setEnabled(false);
		}
		
		private User getUserInFieldInfo() {
			try{
				String userID = setAdminUI.getUserIdTF().getText(),
					   userName = setAdminUI.getUserNameTF().getText(),
					   password = setAdminUI.getPasswordPF().getSelectedText(),
					   lastName = setAdminUI.getLastNameTF().getText(),
					   firstName = setAdminUI.getFirstNameTF().getText();
								
				ArrayList<Module> modList = userID.isEmpty()? new ArrayList<Module>():getModuleList(userID);
				
				User user = new User(!userID.isEmpty()? userID.trim():null,
								   	 !userName.isEmpty()? userName.trim():null,
						   			 password,
								   	 !lastName.isEmpty()? lastName.trim():null,
								   	 !firstName.isEmpty()? firstName.trim():null,
						   			 modList,
									 null);
				
				return user;
				
				} catch(Exception e) {}
			
			return null;
		}

		private void setPromptButtons(ActionListener handler) {
			setTableButtonsVisibility(false);
			
			visiblePromptButtons(true);
			
			setAdminUI.getSaveB().addActionListener(handler);
			setAdminUI.getCancelB().addActionListener(handler);
		}
			
		private ArrayList<Module> getModuleList(String userID) {
			ArrayList<Module> moduleList = new ArrayList<Module>();
			
			moduleList.add(getCustomerModule(userID));
			moduleList.addAll(getReportsModule(userID));
			
			return moduleList;
		}

		private void processDeleteUser() {
			Integer opt = DialogBox.showOKCancelMsg("", "Confirm user to delete?");
			
			if(opt == JOptionPane.OK_OPTION){
				String userIDLatest = userDA.deleteUser(getSelectedUser());
				
				updateUserRecords();
				
				if(isAdmin) searchUser(userIDLatest);
			}
		}

		private void processEditUser() {
			setAdminUI.preparePasswordHandler();
			setAdminUI.setPasswordCompsVisible(true);
			
			if(setAdminUI.getUserRecordsTBL().getSelectedRow()<0) 
				prepareFirstUserDisplay();
			
			setAdminUI.editableUserFieldsAndChkBoxes(true);
			enableUserIdTF(false);
			setPromptButtons(editUserListener);
			setAdminUI.getEditB().setEnabled(false);
			setAdminUI.getEditB().setVisible(true);
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

		public void setNormalMode_SetAdmin() {
			setAdminUI.setPasswordCompsVisible(false);
			setAdminUI.editableUserFieldsAndChkBoxes(false);
			setAdminUI.setSearchCompsEnabled(true);
			enableUserIdTF(false);
			visiblePromptButtons(false);
			setTableButtonsVisibility(true);
			setAdminUI.getUserRecordsTBL().setEnabled(true);		
			rowSelected();
		}

		private void setTableButtonsVisibility(boolean flag) {
			for(JButton btn:setAdminUI.getBSet())
				btn.setVisible(flag);
		}

		private void visiblePromptButtons(boolean b) {
			setAdminUI.getSaveB().setVisible(b);
			setAdminUI.getCancelB().setVisible(b);	
		}
		
//		MOUSE EVENTS
		
		@Override
		public void mouseClicked(MouseEvent ev) {
			if(ev.getSource() == setAdminUI.getUserRecordsTBL() &&
				 setAdminUI.getUserRecordsTBL().isEnabled()){
				refreshPasswordField();
				enableUserIdTF(false);
				rowSelected();
			}
		}
		
		private void refreshPasswordField() {
			setAdminUI.getPasswordPF().setText("");
			if(!setAdminUI.getEditB().isEnabled()) setAdminUI.getPasswordHandler().focusLost(null);
		}

		private void enableUserIdTF(boolean b) {
			setAdminUI.getUserIdTF().setEditable(b);
		}
		
		private void setFieldsAndChkBoxesBlank() {
			setFields("", "", "", "");
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
			
			try{
				Boolean isDelete = user.getStamp().contains("DELETED");
				setAdminUI.getRecoverB().setEnabled(isDelete);
				setAdminUI.getDeleteB().setEnabled(!isDelete);
			} catch(Exception e){}
			
			updateStampView(user.getStamp());
			
			setAdminUI.getUserRecordsTBL().requestFocus();
		}

		private void updateFieldsWithSelectedRow(User user) {
			setFields(user.getUserID(), user.getUserName(),
					  user.getLastName(), user.getFirstName());

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

		private void setFields(String userID, String userName, String lastName, String firstName) {
			setAdminUI.getUserIdTF().setText(userID);
			setAdminUI.getUserNameTF().setText(userName); 
			setAdminUI.getLastNameTF().setText(lastName); 
			setAdminUI.getFirstNameTF().setText(firstName);
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

		@Override
		public void keyPressed(KeyEvent ev) {
			if(ev.getKeyCode() == KeyEvent.VK_ENTER) processSearchUser();
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
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
			
			ModuleCodes report = (ModuleCodes) reportList[selectedIndex];
			
			try {
				if(report.prompt() != null)
					 processParamReport(report.title(), report.prompt(), report.param());
				else reports.viewReport(report.title());
			} catch (Exception e) {
				e.printStackTrace();
				DialogBox.showErrorDialog("Cannot process and generate report."
						+ "\nPossibilities are multiple file-related naming mismatch or report technicalities");
			}
			
			setStatMsg("");
			enableMainUI(true);
		}

		private void enableMainUI(boolean b) {
			mainUI.setEnabled(b);			
		}

		private void processParamReport(String reportName, String prompt, String param) throws Exception {
			String domain = DialogBox.showInputDialog("", String.format("Enter %s", prompt), JOptionPane.PLAIN_MESSAGE);
			
			if(domain != null) 
				reports.viewParamReport(reportName, domain.toUpperCase(), param);			
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
			viewTransUI.setText_SalesDateTF(sales.getSalesDate());
			viewTransUI.setText_TotalSalesTF(sales.getTotalSalesPrice());
			
			viewTransUI.clearViewTransTBL();
			viewTransUI.initSalesTable(sales);
		}
		
		public DefaultTableModel getSalesDetailsModel() {
			return salesDetailsModel;
		}

		public void setSalesDetailsModel(DefaultTableModel salesDetailsModel) {
			this.salesDetailsModel = salesDetailsModel;
		}

	}
	
}
