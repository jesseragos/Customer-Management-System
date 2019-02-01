package com.cms.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class UserTransactionStampPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private JLabel stampL;
	private JTextField stampTF;

	public UserTransactionStampPanel(){
		initialize();
	}
	
	private void initialize() {
		setPanel();
		setDesign();
		setComps();		
	}

	private void setDesign() {
		this.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
	}

	private void setComps() {
		stampL = new JLabel("STAMP: ");
		stampTF = new JTextField(20);
		
//		stampTF.setFont(new Font("Century Gothic", Font.ITALIC, 12));
		stampTF.setBackground(new Color(255,255,225));
		stampTF.setEditable(false);
		
		add(stampL, BorderLayout.WEST);
		add(stampTF, BorderLayout.CENTER);
	}

	private void setPanel() {
		this.setSize(100,200);
		this.setVisible(true);
		this.setBackground(Color.WHITE);
		
		this.setLayout(new BorderLayout());
	}

	public static void main(String[] args){
		new UserTransactionStampPanel().revalidate();;
	}

	public void setStampValue(String stamp) {
		stampTF.setText(stamp);
	}

}
