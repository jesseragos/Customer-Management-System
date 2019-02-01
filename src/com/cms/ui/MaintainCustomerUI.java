package com.cms.ui;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.text.JTextComponent;

import com.cms.domains.Customer;
import com.cms.ui.utils.*;
import com.cms.utils.ImageDir;
import com.jgoodies.forms.layout.*;

import java.awt.*;

public class MaintainCustomerUI extends JPanel{

	private static final long serialVersionUID = 1L;
	private static JFrame frame;
	private JTextField custNumTF;
	private JTextField searchTF;
	private JTextField custNameTF;
	private JTextField addressTF;
	private JButton viewTransactionsB;
	private JTable custRecordsTBL;
	private ImagePanel maintainCustomerP;
	private UserTransactionStampPanel stampP;
	private JLabel headingL;
	private JButton searchB;
	private JLabel custNumL;
	private JLabel msgCustNumL;
	private JLabel custNameL;
	private JLabel msgCustNameL;
	private JLabel addressL;
	private JLabel msgAddressL;
	private JLabel payTermL;
	private JLabel msgPayTermL;
	private JScrollPane custRecordsSPane;
	private JDialog viewTransDialog;
	private ViewTransactionUI viewTransUI;
	
	private final int W_VT = 750,
					  H_VT = 540;
	private DefaultTableModel custRecordsModel;
	private JComboBox<String> paytermCB;
	private JButton saveB;
	private JButton cancelB;
	private JPanel buttonsPNL;
	private JButton recoverB;
	private JButton addB;
	private JButton editB;
	private JButton deleteB;
	private JButton[] bSet;
	private ArrayList<JButton> availedBtns;
	private JComponent[] listFields;

	public static void main(String[] args){
		frame = new JFrame();
		frame.getContentPane().add(new MaintainCustomerUI());
		frame.pack();
		frame.setVisible(true);
	}
	
	public MaintainCustomerUI() {
		setMaintainCustomerUI();
		initStampP();
		setPanelAndAddComps();
	}

	private void initStampP() {
		stampP = new UserTransactionStampPanel();
	}

	public UserTransactionStampPanel getStampP() {
		return stampP;
	}

	private void setPanelAndAddComps() {
		this.setLayout(new BorderLayout());
		
		this.add(maintainCustomerP, BorderLayout.CENTER);
		this.add(stampP, BorderLayout.SOUTH);
	}

	@SuppressWarnings("unchecked")
	private void setMaintainCustomerUI() {
		maintainCustomerP = new ImagePanel();
		maintainCustomerP.setImage(ImageDir.BG1.dir());
		
		maintainCustomerP.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(78dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(80dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(72dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(44dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(11dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(5dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
				RowSpec.decode("max(4dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("max(4dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("max(4dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				RowSpec.decode("max(4dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("fill:max(16dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(79dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default):grow"),
				RowSpec.decode("max(7dlu;default)"),}));
		
		headingL = new JLabel("Maintain Customer");
		headingL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		maintainCustomerP.add(headingL, "3, 3, 3, 1");
		
		searchTF = new JTextField();
		maintainCustomerP.add(searchTF, "9, 3, 2, 1, right, default");
		searchTF.setColumns(10);
		
		searchB = new JButton("Search");
		maintainCustomerP.add(searchB, "11, 3, left, default");
		
		custNumL = new JLabel("Customer no.:");
		custNumL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		maintainCustomerP.add(custNumL, "3, 7, right, default");
		
		custNumTF = new JTextField();
		maintainCustomerP.add(custNumTF, "5, 7, 5, 1, left, default");
		custNumTF.setColumns(10);
		
		msgCustNumL = new JLabel();
		msgCustNumL.setForeground(Color.GRAY);
		msgCustNumL.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		maintainCustomerP.add(msgCustNumL, "5, 8, 5, 1");
		
		custNameL = new JLabel("Customer name:");
		custNameL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		maintainCustomerP.add(custNameL, "3, 10, right, default");
		
		custNameTF = new JTextField();
		custNameTF.setColumns(15);
		maintainCustomerP.add(custNameTF, "5, 10, 5, 1, left, default");
		
		msgCustNameL = new JLabel();
		msgCustNameL.setForeground(Color.GRAY);
		msgCustNameL.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		maintainCustomerP.add(msgCustNameL, "5, 11, 5, 1");
		
		addressL = new JLabel("Address:");
		addressL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		maintainCustomerP.add(addressL, "3, 13, right, default");
		
		addressTF = new JTextField();
		addressTF.setColumns(10);
		maintainCustomerP.add(addressTF, "5, 13, 5, 1, fill, default");
		
		msgAddressL = new JLabel();
		msgAddressL.setForeground(Color.GRAY);
		msgAddressL.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		maintainCustomerP.add(msgAddressL, "5, 14, 5, 1");
		
		payTermL = new JLabel("Payterm:");
		payTermL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		maintainCustomerP.add(payTermL, "3, 16, right, default");
		
		paytermCB = new JComboBox(Customer.payterms);
		paytermCB.setBackground(Color.WHITE);
		maintainCustomerP.add(paytermCB, "5, 16, fill, default");
		
		msgPayTermL = new JLabel();
		msgPayTermL.setForeground(Color.GRAY);
		msgPayTermL.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 12));
		maintainCustomerP.add(msgPayTermL, "5, 17, 5, 1");
		
		saveB = new JButton("Save");
		saveB.setFont(new Font("Tahoma", Font.BOLD, 13));
		saveB.setOpaque(true);
		saveB.setVisible(false);
		maintainCustomerP.add(saveB, "3, 19");


		cancelB = new JButton("Cancel");
		cancelB.setFont(new Font("Tahoma", Font.BOLD, 13));
		cancelB.setOpaque(true);		
		cancelB.setVisible(false);
		maintainCustomerP.add(cancelB, "5, 19");
		
		viewTransactionsB = new JButton("View Transactions");
		viewTransactionsB.setFont(new Font("Tahoma", Font.BOLD, 12));
		viewTransactionsB.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		viewTransactionsB.setBackground(Color.ORANGE.brighter());
//		viewTransactionsB.setOpaque(true);
		maintainCustomerP.add(viewTransactionsB, "9, 19, 3, 1, default, fill");
		
		custRecordsSPane = new JScrollPane();
		maintainCustomerP.add(custRecordsSPane, "3, 21, 9, 1, fill, fill");
		
		custRecordsTBL = new JTable(){
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int data, int columns){
				return false;
			}
		};
		custRecordsTBL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		custRecordsTBL.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"CustNo.", "CustName", "Address", "Payterm"
			}
		));
		custRecordsTBL.getColumnModel().getColumn(0).setPreferredWidth(64);
		custRecordsTBL.getColumnModel().getColumn(0).setMaxWidth(300000000);
		custRecordsTBL.getColumnModel().getColumn(1).setPreferredWidth(100);
		custRecordsTBL.getColumnModel().getColumn(1).setMaxWidth(900000000);
		custRecordsTBL.getColumnModel().getColumn(2).setPreferredWidth(132);
		custRecordsTBL.getColumnModel().getColumn(3).setPreferredWidth(64);
		custRecordsTBL.getColumnModel().getColumn(3).setMaxWidth(900000000);
		custRecordsSPane.setViewportView(custRecordsTBL);
		
		buttonsPNL = new JPanel();
		maintainCustomerP.add(buttonsPNL, "3, 23, 9, 1, fill, fill");
		
		setButtonsPNL();
		setButtons();
		
		custRecordsModel = (DefaultTableModel) custRecordsTBL.getModel();
		
		setCustRecordsTBL();
		initViewTransDialog();
		setViewTransPanel();
		setFieldsToList();
		setFGColorToFields();
	}

	private void setFGColorToFields() {
		for(int i=0; i<listFields.length; i++)
			if(i!=0) ((JComponent) listFields[i]).setBackground(Color.WHITE);
	}

	public void editableCustFields(Boolean flag){
		for(JComponent field:listFields)
			if(field instanceof JComboBox) ((JComboBox<?>) field).setEnabled(flag);
			else ((JTextComponent) field).setEditable(flag);
		
		for(JComponent comp:listFields)
			if(custNumTF == comp) msgCustNumL.setText(flag? getMaxCharMsg(5):"");
			else if(custNameTF == comp) msgCustNameL.setText(flag? getMaxCharMsg(20):"");
			else if(addressTF == comp) msgAddressL.setText(flag? getMaxCharMsg(50):"");
	}

	private String getMaxCharMsg(Integer i) {
		return String.format("max %d chars.", i);
	}

	private void setFieldsToList() {
		listFields = new JComponent[]{custNumTF, custNameTF, addressTF, paytermCB};		
	}

	public JComponent[] getAllFields() {
		return listFields;
	}
	
	private void setButtons() {
		recoverB = new JButton("RECOVER");
		addB = new JButton("ADD");
		editB = new JButton("EDIT");
		deleteB = new JButton("DELETE");
		
		bSet = new JButton[]{viewTransactionsB, addB, editB, deleteB, recoverB};
		for(int i=0; i<bSet.length; i++){
			bSet[i].setFont(new Font("Tahoma", Font.BOLD, i==0? 13:15));
			bSet[i].setSize(bSet[i].getWidth(), 50);
//			buttonsPNL.add(btn);
		}		
		
		availedBtns = new ArrayList<JButton>();
	}

	public ArrayList<JButton> getAvailedBtns() {
		return availedBtns;
	}

	public JButton[] getBSet() {
		return bSet;
	}
	
	public void addToButtonsPNL(JButton btn) {
		availedBtns.add(btn);
		buttonsPNL.add(btn);
	}

	private void setButtonsPNL() {
		FlowLayout fLO = new FlowLayout();
		fLO.setHgap(10);
		fLO.setAlignment(FlowLayout.LEFT);
		buttonsPNL.setOpaque(false);
		buttonsPNL.setLayout(fLO);		
	}

	public JButton getSaveB() {
		return saveB;
	}

	public JButton getCancelB() {
		return cancelB;
	}

	public JButton getRecoverB() {
		return recoverB;
	}

	public JComboBox<String> getPaytermCB() {
		return paytermCB;
	}

	public void setPaytermCB(JComboBox<String> paytermCB) {
		this.paytermCB = paytermCB;
	}

	public DefaultTableModel getCustRecordsModel() {
		return custRecordsModel;
	}

	public void setCustRecordsModel(DefaultTableModel custRecordsModel) {
		this.custRecordsModel = custRecordsModel;
	}

	private void setCustRecordsTBL() {
		JTableHeader tblHdr = custRecordsTBL.getTableHeader();
		tblHdr.setBackground(new Color(200, 100, 0));
		tblHdr.setForeground(Color.BLACK);
	}

	private void initViewTransDialog() {
		viewTransDialog = new JDialog();
	}

	private void setViewTransPanel() {
		viewTransUI = new ViewTransactionUI();
		
		viewTransDialog.getContentPane().add(viewTransUI);		
	}

	public ViewTransactionUI getViewTrans() {
		return viewTransUI;
	}

	private void setViewTransDialog() {
		viewTransDialog.setTitle(viewTransactionsB.getText());
		viewTransDialog.setSize(W_VT, H_VT);
		viewTransDialog.setLocationRelativeTo(null);
		viewTransDialog.setMinimumSize(new Dimension(W_VT-30, H_VT-20));
		viewTransDialog.setModal(true);
	}
	
	public void displayViewTrans() {
		setViewTransDialog();
		updateViewTransPanel();
		viewTransDialog.setVisible(true);
	}

	private void updateViewTransPanel() {
//		viewTrans.getCustTransaction();
	}
	
//		SETTER/GETTER

	public JTextField getCustNumTF() {
		return custNumTF;
	}

	public void setCustNumTF(JTextField custNumTF) {
		this.custNumTF = custNumTF;
	}

	public JTextField getSearchTF() {
		return searchTF;
	}

	public void setSearchTF(JTextField searchTF) {
		this.searchTF = searchTF;
	}

	public JTextField getCustNameTF() {
		return custNameTF;
	}

	public void setCustNameTF(JTextField custNameTF) {
		this.custNameTF = custNameTF;
	}

	public JTextField getAddressTF() {
		return addressTF;
	}

	public void setAddressTF(JTextField addressTF) {
		this.addressTF = addressTF;
	}

	public JButton getViewTransactionsB() {
		return viewTransactionsB;
	}

	public void setViewTransactionsB(JButton viewTransactionsB) {
		this.viewTransactionsB = viewTransactionsB;
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

	public JTable getCustRecordsTBL() {
		return custRecordsTBL;
	}

	public void setCustRecordsTBL(JTable custRecordsTBL) {
		this.custRecordsTBL = custRecordsTBL;
	}

	public JButton getSearchB() {
		return searchB;
	}

	public void setSearchB(JButton searchB) {
		this.searchB = searchB;
	}

	public void clearCustRecordsTBL() {
		try{ for(int i=custRecordsModel.getRowCount()-1; i >= 0; i--)
			custRecordsModel.removeRow(i);
		} catch(ArrayIndexOutOfBoundsException ae){}
	}

	public void setSearchCompsEnabled(Boolean flag) {
		searchTF.setEnabled(flag);
		searchB.setEnabled(flag);
	}

}