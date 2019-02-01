package test.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.Font;
import java.awt.Insets;

public class GenerateReportsUI_manual extends JPanel{
	
	private final String[] reportlist = {"Report 1", "Report 2", "Report 3", "Report 4"};
	private final JButton[] reportBList = new JButton[reportlist.length]; 
	
	public GenerateReportsUI_manual(){
		setLayout(new GridBagLayout());
		setVisible(true);
		
//		Set buttons
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(3,8,20,0);
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.ipadx = 15;
		gbc.ipady = 30;
		for(int i=0; i<reportBList.length; i++) {
			reportBList[i] = new JButton(reportlist[i]);
//			reportBList[i].addActionListener(this);
//				bSet[i].setBackground(Color.CYAN);
//				bSet[i].setForeground(Color.BLACK);
			gbc.gridy = i;
			add(reportBList[i], gbc);
		}
	}
	
	public static void main(String[] args){
		new GenerateReportsUI_manual();
	}

}
