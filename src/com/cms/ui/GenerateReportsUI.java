package com.cms.ui;

import com.jgoodies.forms.layout.FormLayout;
import com.cms.ui.utils.ImagePanel;
import com.cms.utils.ImageDir;
import com.cms.utils.ModuleCodes;
import com.jgoodies.forms.layout.*;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.JButton;

public class GenerateReportsUI extends ImagePanel{

//	private final String[] reportList = {"Customer List By State", 
//										 "Customer Payment History",
//										 "Customer Sales History", 
//										 "Top Customer Sales"};

	private static final long serialVersionUID = 1L;
	private JLabel generateReportsL = new JLabel("Generate Reports");
	private JList<Object> reportsLIST;
	private JLabel statL;
	private JButton viewReportB;

	public final static String reportCodeFmt = "RPT";
	
	public GenerateReportsUI() {
		setPanel();
	}

	private void setPanel() {
		this.setImage(ImageDir.BG1.dir());
		this.setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(126dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(43dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(13dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(22dlu;default)"),},
			new RowSpec[] {
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(35dlu;default)"),}));
		
		reportsLIST = new JList<Object>();
		reportsLIST.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		
		generateReportsL.setFont(new Font("Tahoma", Font.PLAIN, 20));
		this.add(generateReportsL, "3, 4, 8, 1");
		this.add(reportsLIST, "5, 8, 3, 1, default, center");
		
		statL = new JLabel();
		add(statL, "4, 10, 2, 1, left, default");
		
		viewReportB = new JButton("View Report");
		viewReportB.setEnabled(false);
		add(viewReportB, "7, 10, 3, 1, right, default");
	}
	
	public JButton getViewReportB() {
		return viewReportB;
	}

	public JList<Object> getReportsLIST() {
		return reportsLIST;
	}

	public JLabel getStatL() {
		return statL;
	}

//	public Object[] getReportList() {
//		ArrayList<String> modReports = new ArrayList<String>();
//		
//		for(ModuleCodes modCodes:ModuleCodes.values())
//			if(modCodes.title().contains(reportCodeFmt))
//				modReports.add(modCodes.title());
//		
//		return modReports.toArray();
//	}

	public void setReportsList(Object[] reportsListData) {
		String[] repTitle = new String[reportsListData.length];
		
		for(int i=0; i<repTitle.length; i++)
			repTitle[i] = ((ModuleCodes) reportsListData[i]).title();
		
		reportsLIST.setListData(repTitle);		
	}
	
}
