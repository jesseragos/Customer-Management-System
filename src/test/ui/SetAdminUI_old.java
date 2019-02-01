package test.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import com.cms.domains.Customer;
import com.cms.domains.User;
import com.cms.ui.utils.ImagePanel;
import com.cms.ui.utils.Theme;
import com.cms.utils.ImageDir;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class SetAdminUI_old extends JPanel{

	private JTextField userNameTF;
	private JPasswordField passwordPF;
	private JTextField searchTF;
	private JTextField firstNameTF;
	private JTable userRecordsTBL;
	private JButton searchB;
	private JButton addB;
	private JButton editB;
	private JButton deleteB;
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
	private JLabel errUserNameL;
	private JLabel errPasswordL;
	private JLabel firstNameL;
	private JLabel errFirstNameL;
	private JComponent errLastNameL;
	private JLabel custModuleL;
	private JToggleButton custModuleTOGGLEB;
	private JLabel reportL;
	private JToggleButton reportTOGGLEB;
	private JScrollPane userRecordsSPane;
	private ArrayList<User> userList;
	private JButton recoverB;
	private DefaultTableModel userRecordsModel;
	private JTextField userIdTF;
	private JLabel userIdL;
	private JLabel lastNameL;
	private JTextField lastNameTF;
	private JLabel errUserIdL;
	

	public SetAdminUI_old() {
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
				ColumnSpec.decode("max(40dlu;min):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(42dlu;default):grow"),
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
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(4dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(3dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(5dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(9dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(5dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(9dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(5dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(40dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
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
		
		errUserIdL = new JLabel("");
		errUserIdL.setForeground(Color.RED);
		errUserIdL.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12));
		setSetAdminP.add(errUserIdL, "5, 8");
		
		errUserNameL = new JLabel("");
		errUserNameL.setForeground(Color.RED);
		errUserNameL.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12));
		setSetAdminP.add(errUserNameL, "9, 8");
		
		errPasswordL = new JLabel("");
		errPasswordL.setForeground(Color.DARK_GRAY);
		errPasswordL.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12));
		setSetAdminP.add(errPasswordL, "13, 8, 3, 1");
		
		firstNameL = new JLabel("First name:");
		firstNameL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(firstNameL, "3, 10, right, default");
		
		firstNameTF = new JTextField();
		firstNameTF.setColumns(15);
		setSetAdminP.add(firstNameTF, "5, 10, 3, 1, left, default");
		
		lastNameL = new JLabel("Last name:");
		lastNameL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(lastNameL, "9, 10, right, default");
		
		lastNameTF = new JTextField();
		lastNameTF.setColumns(15);
		setSetAdminP.add(lastNameTF, "11, 10, 3, 1, left, default");
		
		errFirstNameL = new JLabel("");
		errFirstNameL.setForeground(Color.RED);
		errFirstNameL.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12));
		setSetAdminP.add(errFirstNameL, "5, 12, 3, 1");
		
		errLastNameL = new JLabel("");
		errLastNameL.setForeground(Color.RED);
		errLastNameL.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12));
		setSetAdminP.add(errLastNameL, "11, 12, 3, 1");
		
		JSeparator separator = new JSeparator();
		setSetAdminP.add(separator, "3, 14, 13, 1");
		
		custModuleL = new JLabel("Customer Module");
		custModuleL.setFont(new Font("Tahoma", Font.ITALIC, 15));
		setSetAdminP.add(custModuleL, "3, 16, 3, 1");
		
		custModuleTOGGLEB = new JToggleButton("Select All");
		custModuleTOGGLEB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		setSetAdminP.add(custModuleTOGGLEB, "15, 16, right, default");
		custModuleTOGGLEB.addActionListener(selectAllHandler);
		
		viewChkBx = new JCheckBox("View");
		viewChkBx.setBackground(Color.WHITE);
		viewChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(viewChkBx, "3, 18");
		
		addChkBx = new JCheckBox("Add");
		addChkBx.setBackground(Color.WHITE);
		addChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(addChkBx, "5, 18");
		
		editChkBx = new JCheckBox("Edit");
		editChkBx.setBackground(Color.WHITE);
		editChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(editChkBx, "7, 18");
		
		deleteChkBx = new JCheckBox("Delete");
		deleteChkBx.setBackground(Color.WHITE);
		deleteChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(deleteChkBx, "9, 18");
		
		separator = new JSeparator();
		setSetAdminP.add(separator, "3, 20, 13, 1");
		
		reportL = new JLabel("Reports");
		reportL.setFont(new Font("Tahoma", Font.ITALIC, 15));
		setSetAdminP.add(reportL, "3, 22, 3, 1");
		
		reportTOGGLEB = new JToggleButton("Select All");
		reportTOGGLEB.setFont(new Font("Tahoma", Font.PLAIN, 10));
		setSetAdminP.add(reportTOGGLEB, "15, 22, right, default");
		reportTOGGLEB.addActionListener(selectAllHandler);
		
		cbsrChkBx = new JCheckBox("Customer By State");
		cbsrChkBx.setBackground(Color.WHITE);
		cbsrChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(cbsrChkBx, "3, 24, 3, 1");
		
		cprChkBx = new JCheckBox("Customer Payment History");
		cprChkBx.setBackground(Color.WHITE);
		cprChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(cprChkBx, "9, 24, 3, 1");
		
		csrChkBx = new JCheckBox("Customer Sales");
		csrChkBx.setBackground(Color.WHITE);
		csrChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(csrChkBx, "3, 26, 3, 1");
		
		tcrChkBx = new JCheckBox("Top Customer");
		tcrChkBx.setBackground(Color.WHITE);
		tcrChkBx.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		setSetAdminP.add(tcrChkBx, "9, 26, 3, 1");
		
		separator = new JSeparator();
		setSetAdminP.add(separator, "3, 28, 13, 1");
		
		userRecordsSPane = new JScrollPane();
		setSetAdminP.add(userRecordsSPane, "3, 30, 13, 1, fill, fill");
		
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
		
		userRecordsModel = (DefaultTableModel) userRecordsTBL.getModel();
		
		addB = new JButton("ADD");
		addB.setFont(new Font("Tahoma", Font.BOLD, 13));
		setSetAdminP.add(addB, "3, 32, default, fill");
		
		editB = new JButton("EDIT");
		editB.setFont(new Font("Tahoma", Font.BOLD, 13));
		setSetAdminP.add(editB, "5, 32, fill, fill");
		
		deleteB = new JButton("DELETE");
		deleteB.setFont(new Font("Tahoma", Font.BOLD, 13));
		setSetAdminP.add(deleteB, "7, 32, default, fill");
		
		recoverB = new JButton("RECOVER");
		recoverB.setFont(new Font("Tahoma", Font.BOLD, 13));
		setSetAdminP.add(recoverB, "9, 32, default, fill");
		
		setCustRecordsTBL();
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

	private class SelectAllHandler implements ActionListener {

		public void actionPerformed(ActionEvent ev) {
			if(ev.getSource() == custModuleTOGGLEB)
				selectCustModuleOptions(custModuleTOGGLEB.isSelected());
			else if(ev.getSource() == reportTOGGLEB)
				selectReportOptions(reportTOGGLEB.isSelected());
				
		}

		private void selectReportOptions(boolean flag) {
			cbsrChkBx.setSelected(flag);
			cprChkBx.setSelected(flag);
			csrChkBx.setSelected(flag);
			tcrChkBx.setSelected(flag);
		}

		private void selectCustModuleOptions(boolean flag) {
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
		return errPasswordL;
	}
}
