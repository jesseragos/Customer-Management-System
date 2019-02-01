package com.cms.ui;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.border.LineBorder;
import com.cms.ui.utils.ImagePanel;
import com.cms.ui.utils.Theme;
import com.cms.utils.ImageDir;

public class MainUI extends JFrame{

	private static final long serialVersionUID = 1L;
							//WIDTH = 810, HEIGHT = 640;
	private static final int WIDTH = 850, HEIGHT = 740;
	private JPanel optionBarP;
	private ImagePanel optionsP;
	private JPanel prefsP;
	private JPanel viewP;
	
	private JLabel logoutL;
	private JLabel userNameL;

	private GridBagConstraints gbc = new GridBagConstraints();
	private CardLayout cardLO;
	private ImagePanel defaultP;
	private JLabel welcomeL = new JLabel("Choose an option to begin...");
	private Font font = new Font("Tahoma", Font.PLAIN, 20);
	private JLabel iconL;
	
	private final String title = "Mobility Inc. | Customer Management";
	private ImageIcon logo;
	public static final Color prefColor = Color.CYAN;
	
	public MainUI(){
		setFrame();
		
		setLayout();
		setOptionBar();
		setView();
		
		addComps();
	}
	
	private void setUserPrefs() {
		prefsP = new JPanel();
		
		prefsP.setBackground(Color.GRAY);
		prefsP.setLayout(new BoxLayout(prefsP, BoxLayout.Y_AXIS));
		
		userNameL = new JLabel();
		logoutL = new JLabel("(Logout)");
		
		setPrefCompsColor();
		
		prefsP.add(userNameL);
		prefsP.add(logoutL);
		
		prefsP.setBorder(new LineBorder(Color.gray, 1));
	}
	
	public JLabel getLogoutL() {
		return logoutL;
	}
	
	private void setPrefCompsColor(){
		userNameL.setForeground(prefColor);
		logoutL.setForeground(prefColor);
	}
	
	public void setUserNameInfo(String username){
		userNameL.setText(username);
	}

	private void addComps() {
		add(optionBarP, BorderLayout.WEST);
		add(viewP, BorderLayout.CENTER);
	}

	private void setLayout() {
		setLayout(new BorderLayout());
	}
	
	private void setOptionBar() {
			//	Set panel
			optionBarP = new JPanel();
			optionBarP.setBackground(Color.WHITE);
			optionBarP.setLayout(new BorderLayout());
			
			optionsP = new ImagePanel();
			optionsP.setImage(ImageDir.BG4.dir());
			optionsP.setLayout(new GridBagLayout());
			
			setUserPrefs();
			
			setImageIconToOptionsBar();
			
			optionBarP.add(optionsP, BorderLayout.CENTER);
			optionBarP.add(prefsP, BorderLayout.SOUTH);
			
	}

	public void setOptionBtns(ArrayList<JButton> bSet){
//		Set buttons
				gbc.insets = new Insets(30,10,0,10);
				gbc.gridwidth = 0;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				gbc.ipadx = 5;
				gbc.ipady = 10;
				for(int i=0; i<bSet.size(); i++) {
					bSet.get(i).setFont(new Font("Agency FB", Font.PLAIN, 21));
					bSet.get(i).addActionListener(null);
					
					gbc.gridy = i+1;
					optionsP.add(bSet.get(i), gbc);
				}
	}
	
	private void setImageIconToOptionsBar() {
		logo = new ImageIcon(ImageDir.LOGOMAINUI.dir());
		iconL = new JLabel(logo);
		optionsP.add(iconL, gbc);		
	}

	private void setView() {
		viewP = new JPanel();
		
		setViewLayout();
		
		defaultP = new ImagePanel();
		defaultP.setImage(ImageDir.BG1.dir());
		defaultP.setLayout(null);
		welcomeL.setFont(font);
		welcomeL.setBounds(45,35,520,24);
		defaultP.add(welcomeL);
		
		viewP.add(defaultP, "Default");
		viewP.setBorder(new LineBorder(Color.gray, 1));
		
		cardLO.show(viewP, "Default");
	}

	private void setViewLayout() {
		cardLO = new CardLayout();
		
		viewP.setLayout(cardLO);
	}
	
	public CardLayout getViewLayout() {
		return cardLO;
	}

	private void setFrame() {
		this.setTitle(title);
		this.setSize(WIDTH, HEIGHT);
		this.setMinimumSize(new Dimension(WIDTH-53, HEIGHT-55));
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(ImageDir.ICON.dir()).getImage());
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		Theme.setLookAndFeel();
	}

//	>> GETTERS/SETTERS <<

	public JPanel getOptionBarP() {
		return optionBarP;
	}

	public void setOptionBarP(ImagePanel optionBarP) {
		this.optionBarP = optionBarP;
	}

	public JPanel getOptionsP() {
		return optionsP;
	}

	public void setOptionsP(ImagePanel optionsP) {
		this.optionsP = optionsP;
	}

	public JPanel getprefsP() {
		return prefsP;
	}

	public void setPrefsP(ImagePanel prefsP) {
		this.prefsP = prefsP;
	}

	public JPanel getViewP() {
		return viewP;
	}

	public void setViewP(JPanel viewP) {
		this.viewP = viewP;
	}
	
	public JLabel getWelcomeL() {
		return welcomeL;
	}
	
}
