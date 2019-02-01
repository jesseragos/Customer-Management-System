package com.cms.ui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import com.cms.domains.User;
import com.cms.ui.utils.ImagePanel;
import com.cms.utils.ImageDir;
import com.jgoodies.forms.layout.*;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.BoxLayout;

public class SetAdminUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private JTextField userNameTF;
	private JPasswordField passwordPF;
	private JTextField firstNameTF;
	private JTextField userIdTF;
	private JTextField lastNameTF;
	private JTextField searchTF;
	private JTable userRecordsTBL;
	private JButton searchB;
	private JCheckBox viewChkBx;
	private JCheckBox addChkBx;
	private JCheckBox editChkBx;
	private JCheckBox deleteChkBx;
	private JCheckBox cbsrChkBx;
	private JCheckBox cprChkBx;
	private JCheckBox csrChkBx;
	private JCheckBox tcrChkBx;
	private ImagePanel setSetAdminP;
	private UserTransactionStampPanel stampP;
	private SelectAllHandler selectAllHandler;
	private JLabel headingL;
	private JLabel userNameL;
	private JLabel passwordL;
	private JLabel msgUserNameL;
	private JLabel msgPasswordL;
	private JLabel firstNameL;
	private JLabel msgFirstNameL;
	private JLabel msgLastNameL;
	private JToggleButton custModuleTOGGLEB;
	private JToggleButton reportTOGGLEB;
	private JScrollPane userRecordsSPane;
	private ArrayList<User> userList;
	private DefaultTableModel userRecordsModel;
	private JLabel userIdL;
	private JLabel lastNameL;
	private JLabel msgUserIdL;
	private JPanel custModulePNL;
	private JPanel reportModulePNL;
	private JButton saveB;
	private JButton cancelB;

	private JPanel buttonsPNL;
	private JButton recoverB;
	private JButton addB;
	private JButton editB;
	private JButton deleteB;
	private JButton[] bSet;
	private JTextField[] listFields;
	private JCheckBox[] listChkBox;
	private FocusListener passwordHandler;
	private ItemListener readOnlyChkBoxHandler;
	public static final String RESETPW = "<Reset password here>";

	public SetAdminUI() {
		setSetAdminUI();
		initStampP();
		setPanelAndAddComps();
	}

	private void setPanelAndAddComps() {
		this.setLayout(new BorderLayout());
		
		this.add(setSetAdminP, BorderLayout.CENTER);
		this.add(stampP, BorderLayout.SOUTH);
	}

	private void initStampP() {
		stampP = new UserTransactionStampPanel();
	}

	private void setSetAdminUI() {
		setSetAdminP = new ImagePanel();
		setSetAdminP.setImage(ImageDir.BG1.dir());
		
		setSetAdminP.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(34dlu;min):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(35dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(21dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(51dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(19dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(54dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("left:max(22dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(11dlu;default)"),
				RowSpec.decode("max(9dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(12dlu;default)"),
				RowSpec.decode("max(4dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("max(4dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(34dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default):grow"),
				RowSpec.decode("max(6dlu;default)"),}));
		
		initSelectAllHandler();
		
		headingL = new JLabel("Set Admin");
		headingL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		setSetAdminP.add(headingL, "3, 3, 3, 1");
		
		searchTF = new JTextField();
		searchTF.setColumns(10);
		setSetAdminP.add(searchTF, "9, 3, 3, 1, right, default");
		
		searchB = new JButton("Search");
		setSetAdminP.add(searchB, "12, 3, 2, 1, left, default");
		
		userIdL = new JLabel("User ID:");
		userIdL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(userIdL, "3, 6, right, default");
		
		userIdTF = new JTextField();
		userIdTF.setColumns(7);
		setSetAdminP.add(userIdTF, "5, 6, left, default");
		
		userNameL = new JLabel("Username:");
		userNameL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(userNameL, "7, 6, right, default");
		
		userNameTF = new JTextField();
		setSetAdminP.add(userNameTF, "9, 6, left, default");
		userNameTF.setColumns(10);
		
		passwordL = new JLabel("Password:");
		passwordL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(passwordL, "11, 6, right, default");
		
		passwordPF = new JPasswordField();
		passwordPF.setColumns(12);
		setSetAdminP.add(passwordPF, "13, 6, 3, 1, left, default");
		
		msgUserIdL = new JLabel("");
		msgUserIdL.setForeground(Color.GRAY);
		msgUserIdL.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		setSetAdminP.add(msgUserIdL, "5, 7");
		
		msgUserNameL = new JLabel("");
		msgUserNameL.setForeground(Color.GRAY);
		msgUserNameL.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		setSetAdminP.add(msgUserNameL, "9, 7");
		
		msgPasswordL = new JLabel("");
		msgPasswordL.setForeground(Color.GRAY);
		msgPasswordL.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		setSetAdminP.add(msgPasswordL, "13, 7, 3, 1");
		
		firstNameL = new JLabel("First name:");
		firstNameL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(firstNameL, "3, 9, right, default");
		
		firstNameTF = new JTextField();
		firstNameTF.setColumns(15);
		setSetAdminP.add(firstNameTF, "5, 9, 3, 1, left, default");
		
		lastNameL = new JLabel("Last name:");
		lastNameL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(lastNameL, "9, 9, right, default");
		
		lastNameTF = new JTextField();
		lastNameTF.setColumns(15);
		setSetAdminP.add(lastNameTF, "11, 9, 3, 1, left, default");
		
		msgFirstNameL = new JLabel("");
		msgFirstNameL.setForeground(Color.GRAY);
		msgFirstNameL.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		setSetAdminP.add(msgFirstNameL, "5, 10, 3, 1");
		
		msgLastNameL = new JLabel("");
		msgLastNameL.setForeground(Color.GRAY);
		msgLastNameL.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		setSetAdminP.add(msgLastNameL, "11, 10, 3, 1");
		
		custModulePNL = new JPanel();
		custModulePNL.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "Customer Module", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		custModulePNL.setOpaque(false);
		setSetAdminP.add(custModulePNL, "3, 12, 11, 1, fill, fill");
		custModulePNL.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(68dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(55dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		viewChkBx = new JCheckBox("View");
		custModulePNL.add(viewChkBx, "2, 2");
		viewChkBx.setBackground(Color.WHITE);
		viewChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		
		addChkBx = new JCheckBox("Add");
		custModulePNL.add(addChkBx, "4, 2");
		addChkBx.setBackground(Color.WHITE);
		addChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		
		editChkBx = new JCheckBox("Edit");
		custModulePNL.add(editChkBx, "6, 2");
		editChkBx.setBackground(Color.WHITE);
		editChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		
		deleteChkBx = new JCheckBox("Delete");
		custModulePNL.add(deleteChkBx, "8, 2");
		deleteChkBx.setBackground(Color.WHITE);
		deleteChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		
		custModuleTOGGLEB = new JToggleButton("Select All");
		setSetAdminP.add(custModuleTOGGLEB, "15, 12");
		custModuleTOGGLEB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		custModuleTOGGLEB.setOpaque(true);
		custModuleTOGGLEB.addActionListener(selectAllHandler);
		
		reportModulePNL = new JPanel();
		reportModulePNL.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 128)), "Reports Module", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)));
		reportModulePNL.setOpaque(false);
		setSetAdminP.add(reportModulePNL, "3, 14, 11, 1, fill, fill");
		reportModulePNL.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(31dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(58dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,}));
		
		cbsrChkBx = new JCheckBox("Customer By State");
		reportModulePNL.add(cbsrChkBx, "2, 2");
		cbsrChkBx.setBackground(Color.WHITE);
		cbsrChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		
		cprChkBx = new JCheckBox("Customer Payment History");
		reportModulePNL.add(cprChkBx, "4, 2");
		cprChkBx.setBackground(Color.WHITE);
		cprChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		
		csrChkBx = new JCheckBox("Customer Sales");
		reportModulePNL.add(csrChkBx, "2, 4");
		csrChkBx.setBackground(Color.WHITE);
		csrChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		
		tcrChkBx = new JCheckBox("Top Customer");
		reportModulePNL.add(tcrChkBx, "4, 4");
		tcrChkBx.setBackground(Color.WHITE);
		tcrChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		
		saveB = new JButton("Save");
		saveB.setFont(new Font("Tahoma", Font.BOLD, 13));
		saveB.setVisible(false);
		
		reportTOGGLEB = new JToggleButton("Select All");
		setSetAdminP.add(reportTOGGLEB, "15, 14");
		reportTOGGLEB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		reportTOGGLEB.setOpaque(true);
		reportTOGGLEB.addActionListener(selectAllHandler);
		saveB.setOpaque(true);
		setSetAdminP.add(saveB, "3, 16");
		
		cancelB = new JButton("Cancel");
		cancelB.setFont(new Font("Tahoma", Font.BOLD, 13));
		cancelB.setVisible(false);
		cancelB.setOpaque(true);
		setSetAdminP.add(cancelB, "5, 16");
		
		userRecordsSPane = new JScrollPane();
		setSetAdminP.add(userRecordsSPane, "3, 18, 13, 1, fill, fill");
		
		userRecordsTBL = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int data, int columns){
				return false;
			}
		};
		userRecordsTBL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		userRecordsTBL.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "UserName", "Last name", "First name"
			}
		));
		userRecordsTBL.getColumnModel().getColumn(1).setPreferredWidth(139);
		userRecordsTBL.getColumnModel().getColumn(2).setPreferredWidth(114);
		userRecordsTBL.getColumnModel().getColumn(3).setPreferredWidth(133);
		userRecordsSPane.setViewportView(userRecordsTBL);
		
		buttonsPNL = new JPanel();
		setSetAdminP.add(buttonsPNL, "3, 20, 13, 1, fill, fill");
		buttonsPNL.setLayout(new BoxLayout(buttonsPNL, BoxLayout.X_AXIS));

		setButtonsPNL();
		setButtons();
		
		userRecordsModel = (DefaultTableModel) userRecordsTBL.getModel();
		
		setCustRecordsTBL();
		setFieldsToList();
		setChkBoxToList();
		setFGColorToFields();
		setReadOnlyChkBoxHandler();
		initPasswordHandler();
	}

	private void setReadOnlyChkBoxHandler() {
		readOnlyChkBoxHandler = new ItemListener(){
			public void itemStateChanged(ItemEvent ev) {
				for(JCheckBox chkbox:listChkBox)
					if(ev.getSource() == chkbox)
						chkbox.setSelected(chkbox.isSelected());
			}
		};
	}

	private void setPasswordResetMsg() {
		passwordPF.setEchoChar((char) 0);
		passwordPF.setText(RESETPW);
		passwordPF.setForeground(Color.GRAY);
	}

	private void setPasswordHidden() {
		passwordPF.setEchoChar('*');
		passwordPF.setText("");
		passwordPF.setForeground(Color.BLACK);
	}
	
	private void initPasswordHandler() {
		passwordHandler = new FocusListener(){
			public void focusGained(FocusEvent arg0) {
				if(((getPasswordPF().getSelectedText() != null) &&
					(getPasswordPF().getSelectedText().isEmpty() || 
					 getPasswordPF().getSelectedText().equals((RESETPW))))){
					setPasswordHidden();
				}
			}

			public void focusLost(FocusEvent arg0) {
				if(getPasswordPF().getSelectedText() == null ||
					 getPasswordPF().getSelectedText().isEmpty())
						setPasswordResetMsg();
			}
			
		};
		
	}

	public FocusListener getPasswordHandler() {
		return passwordHandler;
	}

	public void preparePasswordHandler(){
		setPasswordResetMsg();
		passwordPF.addFocusListener(passwordHandler);
	}
	
	public void removePasswordHandler(){
		setPasswordHidden();
		passwordPF.removeFocusListener(passwordHandler);
	}

	private void setChkBoxToList() {
		listChkBox = new JCheckBox[]{cbsrChkBx, cprChkBx, csrChkBx, tcrChkBx, viewChkBx, addChkBx, editChkBx, deleteChkBx};
	}

	private void setFieldsToList() {
		listFields = new JTextField[]{userIdTF, userNameTF, passwordPF, firstNameTF, lastNameTF};		
	}

	public JComponent[] getListFields() {
		return listFields;
	}

	private void setFGColorToFields() {
		for(int i=0; i<listFields.length; i++)
			if(i!=0) listFields[i].setBackground(Color.WHITE);
	}

	public void editableUserFieldsAndChkBoxes(Boolean flag){
		for(JTextField fields:listFields)
			fields.setEditable(flag);
		
		for(JCheckBox chkBox:listChkBox)
			if(!flag) chkBox.addItemListener(readOnlyChkBoxHandler);
			else chkBox.removeItemListener(readOnlyChkBoxHandler);
		
		for(JComponent comp:listFields)
			if(userIdTF == comp) msgUserIdL.setText(flag? getMaxCharMsg(5):"");
			else if(userNameTF == comp) msgUserNameL.setText(flag? getMaxCharMsg(15):"");
			else if(lastNameTF == comp) msgLastNameL.setText(flag? getMaxCharMsg(15):"");
			else if(firstNameTF == comp) msgFirstNameL.setText(flag? getMaxCharMsg(15):"");
			else if(passwordPF == comp) msgPasswordL.setText(flag? getMaxCharMsg(20):"");
		
		custModuleTOGGLEB.setVisible(flag);
		reportTOGGLEB.setVisible(flag);
		
		custModuleTOGGLEB.setSelected(false);
		reportTOGGLEB.setSelected(false);
	}
	
	private String getMaxCharMsg(Integer i) {
		return String.format("max %d chars.", i);
	}

	private void setButtonsPNL() {
		FlowLayout fLO = new FlowLayout();
		fLO.setHgap(10);
		fLO.setAlignment(FlowLayout.LEFT);
		buttonsPNL.setOpaque(false);
		buttonsPNL.setLayout(fLO);		
	}
	
	private void setButtons() {
		recoverB = new JButton("RECOVER");
		addB = new JButton("ADD");
		editB = new JButton("EDIT");
		deleteB = new JButton("DELETE");
		
		bSet = new JButton[]{addB, editB, deleteB, recoverB};
		for(int i=0; i<bSet.length; i++){
			bSet[i].setFont(new Font("Tahoma", Font.BOLD, 15));
			bSet[i].setSize(bSet[i].getWidth(), 50);
			buttonsPNL.add(bSet[i]);
		}		
		
	}

	public JButton[] getBSet() {
		return bSet;
	}

	private void setCustRecordsTBL() {
		JTableHeader tblHdr = userRecordsTBL.getTableHeader();
		tblHdr.setBackground(new Color(200, 100, 0));
		tblHdr.setForeground(Color.BLACK);
	}

	public JCheckBox getCBSRChkBx() {
		return cbsrChkBx;
	}

	public JCheckBox getCPRChkBx() {
		return cprChkBx;
	}

	public JCheckBox getCSRChkBx() {
		return csrChkBx;
	}

	public JCheckBox getTCRChkBx() {
		return tcrChkBx;
	}

	public JCheckBox getViewChkBx() {
		return viewChkBx;
	}

	public JCheckBox getAddChkBx() {
		return addChkBx;
	}

	public JCheckBox getEditChkBx() {
		return editChkBx;
	}

	public JCheckBox getDeleteChkBx() {
		return deleteChkBx;
	}

	public void setDeleteChkBx(JCheckBox deleteChkBx) {
		this.deleteChkBx = deleteChkBx;
	}

	private void initSelectAllHandler() {
		selectAllHandler = new SelectAllHandler();		
	}

	public JComponent[] getAllFields(){
		return listFields;
	}
	
	private class SelectAllHandler implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			if(ev.getSource() == custModuleTOGGLEB)
				selectCustModuleOptions(custModuleTOGGLEB.isSelected());
			else if(ev.getSource() == reportTOGGLEB)
				selectReportOptions(reportTOGGLEB.isSelected());
				
		}

		public void selectReportOptions(boolean flag) {
			cbsrChkBx.setSelected(flag);
			cprChkBx.setSelected(flag);
			csrChkBx.setSelected(flag);
			tcrChkBx.setSelected(flag);
		}

		public void selectCustModuleOptions(boolean flag) {
			viewChkBx.setSelected(flag);
			addChkBx.setSelected(flag);
			editChkBx.setSelected(flag);
			deleteChkBx.setSelected(flag);
		}

		
	}

//		SETTER/GETTER
	
	public JTextField getUserNameTF() {
		return userNameTF;
	}

	public void setUserNameTF(JTextField userNameTF) {
		this.userNameTF = userNameTF;
	}

	public JPasswordField getPasswordPF() {
		passwordPF.selectAll();
		return passwordPF;
	}

	public void setPasswordPF(JPasswordField passwordPF) {
		this.passwordPF = passwordPF;
	}

	public JTextField getSearchTF() {
		return searchTF;
	}

	public JTextField getFirstNameTF() {
		return firstNameTF;
	}

	public void setFirstNameTF(JTextField firstNameTF) {
		this.firstNameTF = firstNameTF;
	}

	public JTextField getLastNameTF() {
		return lastNameTF;
	}

	public void setLastNameTF(JTextField lastNameTF) {
		this.lastNameTF = lastNameTF;
	}

	public JTable getUserRecordsTBL() {
		return userRecordsTBL;
	}

	public void setUserRecordsTBL(JTable userRecordsTBL) {
		this.userRecordsTBL = userRecordsTBL;
	}

	public JButton getSearchB() {
		return searchB;
	}

	public JButton getAddB() {
		return addB;
	}

	public void setAddB(JButton addB) {
		this.addB = addB;
	}

	public JButton getEditB() {
		return editB;
	}

	public void setEditB(JButton editB) {
		this.editB = editB;
	}

	public JButton getDeleteB() {
		return deleteB;
	}

	public void setDeleteB(JButton deleteB) {
		this.deleteB = deleteB;
	}

	public ArrayList<User> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<User> userList) {
		this.userList = userList;
	}

	public JButton getRecoverB() {
		return recoverB;
	}

	public void setRecoverB(JButton recoverB) {
		this.recoverB = recoverB;
	}

	public DefaultTableModel getUserRecordsModel() {
		return userRecordsModel;
	}

	public void clearUserRecordsTBL() {
		try{ for(int i=userRecordsModel.getRowCount()-1; i >= 0; i--)
			userRecordsModel.removeRow(i);
		} catch(ArrayIndexOutOfBoundsException ae){}
	}

	public UserTransactionStampPanel getStampP() {
		return stampP;
	}
	
	public JTextField getUserIdTF() {
		return userIdTF;
	}
	
	public JLabel getErrPasswordL() {
		return msgPasswordL;
	}

	public JButton getSaveB() {
		return saveB;
	}

	public JButton getCancelB() {
		return cancelB;
	}
	
	public void setPasswordCompsVisible(Boolean flag) {
		passwordL.setVisible(flag);
		passwordPF.setVisible(flag);
	}

	public void setSearchCompsEnabled(Boolean flag) {
		searchTF.setEnabled(flag);
		searchB.setEnabled(flag);		
	}
	
}
