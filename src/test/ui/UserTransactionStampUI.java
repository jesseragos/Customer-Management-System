package test.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class UserTransactionStampUI extends JFrame{
	
	private JLabel stampL;
	private JTextField stampTF;

	public UserTransactionStampUI(){
		setPanel();
		
		setComps();
	}
	
	private void setComps() {
		stampL = new JLabel("STAMP: ");
		stampTF = new JTextField(20);
		
		add(stampL, BorderLayout.WEST);
		add(stampTF, BorderLayout.CENTER);
	}

	private void setPanel() {
		this.setSize(100,200);
		this.setVisible(true);
		
		this.setLayout(new BorderLayout());
	}

	public static void main(String[] args){
		new UserTransactionStampUI().revalidate();;
	}

}
