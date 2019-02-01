package com.cms.ui;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import com.cms.domains.Sales;
import com.cms.domains.SalesDetail;
import com.cms.ui.utils.ImagePanel;
import com.cms.utils.ImageDir;
import com.jgoodies.forms.layout.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.text.*;
import java.util.Date;

public class ViewTransactionUI extends ImagePanel{

	private static final long serialVersionUID = 1L;
	private JTextField salesNumTF;
	private JTextField salesDateTF;
	private JTextField totalSalesTF;
	private JLabel salesDetailsL;
	private JScrollPane salesDetailsRecordsSPane;
	private JTable salesDetailsRecordsTBL;
	private JButton firstAccB;
	private JButton prevAccB;
	private JButton nextAccB;
	private JButton lastAccB;
	private JLabel salesNumL;
	private JLabel salesDateL;
	private JLabel totalSalesL;
	private DefaultTableModel salesDetailsRecordsModel;
	
	private DecimalFormat currencyDF = new DecimalFormat("$ ###,###,###.00");
	private SimpleDateFormat dateDF = new SimpleDateFormat("dd-MMM-YYYY");

	/**
	 * Create the application.
	 */
	public ViewTransactionUI() {
		setPanel();
		setViewTransactionP();
		disableTFS();
	}

	private void setPanel() {
		setImage(ImageDir.BG2.dir());
	}

	private void disableTFS() {
		salesNumTF.setEditable(false);
		salesDateTF.setEditable(false);
		totalSalesTF.setEditable(false);
		
		salesNumTF.setBackground(Color.WHITE);
		salesDateTF.setBackground(Color.WHITE);
		totalSalesTF.setBackground(Color.WHITE);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void setViewTransactionP() {
		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("max(8dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(5dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(12dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(129dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(7dlu;default)"),
				RowSpec.decode("max(18dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(9dlu;default):grow"),}));
		
		salesNumL = new JLabel("Sales number:");
		salesNumL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		this.add(salesNumL, "3, 5, right, default");
		
		salesNumTF = new JTextField();
		this.add(salesNumTF, "5, 5, left, default");
		salesNumTF.setColumns(10);
		
		salesDateL = new JLabel("Sales date:");
		salesDateL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		this.add(salesDateL, "7, 5, right, default");
		
		salesDateTF = new JTextField();
		salesDateTF.setColumns(10);
		this.add(salesDateTF, "9, 5, fill, default");
		
		totalSalesL = new JLabel("Total sales:");
		totalSalesL.setFont(new Font("Century Gothic", Font.PLAIN, 13));
		this.add(totalSalesL, "7, 7, right, default");
		
		totalSalesTF = new JTextField();
		totalSalesTF.setColumns(10);
		this.add(totalSalesTF, "9, 7, fill, default");
		
		salesDetailsL = new JLabel("Sales Details");
		salesDetailsL.setFont(new Font("Tahoma", Font.ITALIC, 15));
		this.add(salesDetailsL, "3, 9");
		
		salesDetailsRecordsSPane = new JScrollPane();
		salesDetailsRecordsSPane.setEnabled(false);
		this.add(salesDetailsRecordsSPane, "3, 11, 7, 1, fill, fill");
		
		salesDetailsRecordsTBL = new JTable(){
				private static final long serialVersionUID = 1L;

				public boolean isCellEditable(int data, int columns){
					return false;
				}
			};
			
		salesDetailsRecordsTBL.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		salesDetailsRecordsTBL.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Code", "Description", "Unit", "Price", "Quantity"
			}
		));
		salesDetailsRecordsTBL.getColumnModel().getColumn(0).setPreferredWidth(100);
		salesDetailsRecordsTBL.getColumnModel().getColumn(0).setMaxWidth(300000000);
		salesDetailsRecordsTBL.getColumnModel().getColumn(1).setPreferredWidth(105);
		salesDetailsRecordsSPane.setViewportView(salesDetailsRecordsTBL);
		
		salesDetailsRecordsModel = (DefaultTableModel) salesDetailsRecordsTBL.getModel();
		
		firstAccB = new JButton("<<");
		firstAccB.setToolTipText("First");
		firstAccB.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.add(firstAccB, "3, 14, default, fill");
		
		prevAccB = new JButton("<");
		prevAccB.setToolTipText("Previous");
		prevAccB.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.add(prevAccB, "5, 14, default, fill");
		
		nextAccB = new JButton(">");
		nextAccB.setToolTipText("Next");
		nextAccB.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.add(nextAccB, "7, 14, default, fill");
		
		lastAccB = new JButton(">>");
		lastAccB.setToolTipText("Last");
		lastAccB.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.add(lastAccB, "9, 14, default, fill");
	}

	public DefaultTableModel getSalesDetailsRecordsModel() {
		return salesDetailsRecordsModel;
	}
	
	public JButton getFirstAccB() {
		return firstAccB;
	}

	public JButton getPrevAccB() {
		return prevAccB;
	}

	public JButton getNextAccB() {
		return nextAccB;
	}

	public JButton getLastAccB() {
		return lastAccB;
	}
	
	public JTable getSalesDetailsRecordsTBL() {
		return salesDetailsRecordsTBL;
	}

	public void setSalesDetailsRecordsTBL(JTable salesDetailsRecordsTBL) {
		this.salesDetailsRecordsTBL = salesDetailsRecordsTBL;
	}

	public JTextField getSalesNumTF() {
		return salesNumTF;
	}

	public void setSalesNumTF(JTextField salesNumTF) {
		this.salesNumTF = salesNumTF;
	}
	
	public void setText_SalesNumTF(String str) {
		salesNumTF.setText(str);
	}

	public JTextField getSalesDateTF() {
		return salesDateTF;
	}

	public void setSalesDateTF(JTextField salesDateTF) {
		this.salesDateTF = salesDateTF;
	}

	public void setText_SalesDateTF(Date date) {
		salesDateTF.setText(dateDF.format(date));
	}
	
	public JTextField getTotalSalesTF() {
		return totalSalesTF;
	}

	public void setTotalSalesTF(JTextField totalSalesTF) {
		this.totalSalesTF = totalSalesTF;
	}

	public void setText_TotalSalesTF(Double totalSales) {
		totalSalesTF.setText(currencyDF.format(totalSales));
	}
	
	public void clearViewTransTBL(){
		try{ for(int i=salesDetailsRecordsModel.getRowCount()-1; i >= 0; i--)
			salesDetailsRecordsModel.removeRow(i);
		} catch(ArrayIndexOutOfBoundsException ae){}
	}
	
	public void initSalesTable(Sales sales){
		for(SalesDetail salesDtls:sales.getListSalesDetail())
			getSalesDetailsRecordsModel().addRow(new Object[]{salesDtls.getProduct().getProdCode(),
												  salesDtls.getProduct().getDescription(),
												  salesDtls.getProduct().getUnit(),
												  currencyDF.format(salesDtls.getUnitPrice()),
												  salesDtls.getQuantity()});
	}
}
