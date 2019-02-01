package test.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MaintainCustomerUI extends JPanel{

	public static void main(String[] args) {
		new MaintainCustomerUI();
	}

	private MaintainCustomerFormP custFormP;
	private ViewTransactionP viewTransactP;
	private JTabbedPane tabPane;
	private final String custFormTAG = "Maintain Customer",
				   		 viewTransactTAG = "View Transaction";
	
	public MaintainCustomerUI() {
		setPanel();
		
		initComps();
		addCompsToTab();
	}

	private void setPanel() {
		tabPane = new JTabbedPane();
	}

	private void addCompsToTab() {
		tabPane.add(custFormTAG, custFormP);
		tabPane.add(viewTransactTAG, viewTransactP);
	}

	private void initComps() {
		custFormP = new MaintainCustomerFormP();
		viewTransactP = new ViewTransactionP();
	}
}
