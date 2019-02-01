package com.cms.ui.utils;

import java.awt.FlowLayout;
import javax.swing.*;

public class DialogBox {
	
	static JDialog dialog;
	
	public static JDialog showMsgDialog(String title, String msg)
	{
		dialog = new JDialog();
		FlowLayout flowLO = new FlowLayout();
		
		dialog.setLayout(flowLO);
		dialog.setTitle(title);
		dialog.setLocationRelativeTo(null);
		dialog.setResizable(false);
		dialog.add(new JLabel(msg));
		dialog.pack();
//		dialog.setVisible(true);
		
		return dialog;
	}
	
	public static void showMsgDialog(String title, String msg, int dIcon){
		JOptionPane.showMessageDialog(null, msg, title, dIcon);
	}
	
	public static void showErrorDialog(String msg)
	{
		showMsgDialog("ERROR", msg, JOptionPane.ERROR_MESSAGE);
	}
	
	public static void showDBErrorDialog(String table)
	{
		showMsgDialog("DATABASE ERROR", "Problem with specified table or DA: " + table, 
				JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
	
	public static int showOKCancelMsg(String title, String msg)
	{
		return JOptionPane.showConfirmDialog(null, msg, title, JOptionPane.OK_CANCEL_OPTION);
	}

	public static String showInputDialog(String title, String prompt, int dIcon){
		return JOptionPane.showInputDialog(null, prompt, title, dIcon);
	}

}
