package test.ui;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

public class MainMenuUI_manual extends JFrame{

	private final String[] bNames = {"Maintain Customer", "Set Admin", "Generate Reports"};
	private JButton[] bSet = new JButton[bNames.length];
	
	private JPanel pnl_MMoptionBar;
	private JPanel pnl_MMoptions;
	private JPanel pnl_MMprefs;
	private JPanel pnl_MMview;
	
	public MainMenuUI_manual(){
		setFrame();
		
		setLayout();
		setMenuOptionBar();
		setMenuView();
		
		addComps();
		
		revalidate();
	}
	
	private void setUserPrefs() {
		pnl_MMprefs = new JPanel();
		
		pnl_MMprefs.setLayout(new BoxLayout(pnl_MMprefs, BoxLayout.Y_AXIS));
		
		JLabel logout = new JLabel("Logout"),
			   username = new JLabel("(Jessekiel Ragos)"),
			   help = new JLabel("Help"),
			   about = new JLabel("About");
		
		pnl_MMprefs.add(logout);
		pnl_MMprefs.add(username);
		pnl_MMprefs.add(new JSeparator(JSeparator.HORIZONTAL));
		pnl_MMprefs.add(help);
		pnl_MMprefs.add(new JSeparator(JSeparator.HORIZONTAL));
		pnl_MMprefs.add(about);
		
		pnl_MMprefs.setBorder(new LineBorder(Color.gray, 1));
	}

	private void addComps() {
		add(pnl_MMoptionBar, BorderLayout.WEST);
//		pnl_MMoptions
		add(pnl_MMview, BorderLayout.CENTER);
	}

	private void setLayout() {
		setLayout(new BorderLayout());
	}

	private GridBagConstraints gbc = new GridBagConstraints();
	private void setMenuOptionBar() {
			//	Set panel
			pnl_MMoptionBar = new JPanel();
			pnl_MMoptions = new JPanel();
			setUserPrefs();
			
			pnl_MMoptionBar.setLayout(new BorderLayout());
			
			pnl_MMoptions.setLayout(new GridBagLayout());
//			bPanel.setBackground(Color.DARK_GRAY);
			
			JLabel icon = new JLabel(new ImageIcon("mclicon_icon.png"));
			pnl_MMoptions.add(icon, gbc);
			
			//	Set buttons
			gbc.insets = new Insets(30,10,0,10);
			gbc.gridwidth = 0;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.ipadx = 5;
			gbc.ipady = 30;
			for(int i=0; i<bSet.length; i++) {
				bSet[i] = new JButton(bNames[i]);
				bSet[i].addActionListener(null);
//				bSet[i].setBackground(Color.CYAN);
//				bSet[i].setForeground(Color.BLACK);
				gbc.gridy = i+1;
				pnl_MMoptions.add(bSet[i], gbc);
			}
			
			pnl_MMoptionBar.add(pnl_MMoptions, BorderLayout.NORTH);
			pnl_MMoptionBar.add(pnl_MMprefs, BorderLayout.SOUTH);
			
	}

	private void setMenuView() {
		pnl_MMview = new JPanel();
		
		pnl_MMview.setLayout(new FlowLayout());
		pnl_MMview.add(new JLabel("<main view>"));
		pnl_MMview.setBorder(new LineBorder(Color.gray, 1));
	}

	private void setFrame() {
		setSize(800, 630);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String[] args) {
		new MainMenuUI_manual();
	}

}
