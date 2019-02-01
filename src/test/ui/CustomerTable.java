package test.ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CustomerTable extends JFrame{
	
	private JTable custTable;
	private JScrollPane custTableScroll;

	public CustomerTable(){
		setPanel();
		
		setCustTable();
	}

	private void setCustTable() {
		DefaultTableModel dtm = new DefaultTableModel();
		
		dtm.setColumnIdentifiers(new String[]{"Name", "Age"});
		
		custTable = new JTable(dtm);
		
		custTable.setPreferredScrollableViewportSize(new Dimension(450,63));
		custTable.setFillsViewportHeight(true);
		
		custTableScroll = new JScrollPane();
		
		custTableScroll.add(custTable);
	}

	private void setPanel() {
		setSize(300,200);
		setVisible(true);
	}
	
	public static void main(String[] args){
		new CustomerTable();
	}

}
