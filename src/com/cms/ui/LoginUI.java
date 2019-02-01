package com.cms.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import com.cms.ui.utils.ImagePanel;
import com.cms.ui.utils.Theme;
import com.cms.utils.ImageDir;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPasswordField;

public class LoginUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField usernameTF;
	private JLabel errLoginL;
	private JLabel usernameL;
	private JLabel passwordL;
	private JButton loginB;
	private JPasswordField passwordPF;
	
	private final String title = "Mobility Inc. | Login";
	private ImagePanel panel;
	private ImageIcon banner = new ImageIcon(ImageDir.BANNER_CMS.dir());
	
	public LoginUI(){
		super("mobility_BG3.png");
		
		setFrame();
		setPanel();
		initialize();
		this.setVisible(true);
	}

	private void setPanel() {
		panel = new ImagePanel();
		panel.setLayout(null);
		panel.setImage(ImageDir.BG3.dir());
	}

	private void initialize() {
		usernameL = new JLabel("Username:");
		usernameL.setFont(new Font("Agency FB", Font.PLAIN, 21));
		usernameL.setBounds(78, 105, 72, 36);
		panel.add(usernameL);
		
		passwordL = new JLabel("Password:");
		passwordL.setFont(new Font("Agency FB", Font.PLAIN, 21));
		passwordL.setBounds(78, 154, 72, 31);
		panel.add(passwordL);
		
		usernameTF = new JTextField();
		usernameTF.setToolTipText("Enter username");
		usernameTF.setBounds(158, 105, 183, 31);
		panel.add(usernameTF);
		usernameTF.setColumns(10);
		
		passwordPF = new JPasswordField();
		passwordPF.setBounds(158, 157, 183, 31);
		panel.add(passwordPF);
		
		loginB = new JButton("Login");
		loginB.setFont(new Font("Century Gothic", Font.BOLD, 15));
		loginB.setBounds(279, 213, 134, 39);
		panel.add(loginB);
		
		errLoginL = new JLabel();
		errLoginL.setForeground(Color.RED.darker());
		errLoginL.setBackground(Color.WHITE);
		errLoginL.setFont(new Font("Century Gothic", Font.BOLD | Font.ITALIC, 14));
		errLoginL.setBounds(12, 213, 200, 16);
		panel.add(errLoginL);
		
		getContentPane().add(panel, BorderLayout.CENTER);
		
		JLabel logoL = new JLabel(banner);
		logoL.setBounds(103, 0, 214, 98);
		panel.add(logoL);
	}
	
	private void setFrame() {
		getContentPane().setLayout(new BorderLayout());
		
		this.setTitle(title);
		this.setSize(450, 310);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		Theme.setLookAndFeel();
	}

	public JTextField getUsernameTF() {
		return usernameTF;
	}

	public void setUsernameTF(JTextField usernameTF) {
		this.usernameTF = usernameTF;
	}

	public JTextField getPasswordTF() {
		return passwordPF;
	}

	public void setPasswordTF(JPasswordField passwordTF) {
		this.passwordPF = passwordTF;
	}

	public JLabel getErrLoginL() {
		return errLoginL;
	}

	public void setErrLoginL(JLabel errLoginL) {
		this.errLoginL = errLoginL;
	}

	public JLabel getUsernameL() {
		return usernameL;
	}

	public void setUsernameL(JLabel usernameL) {
		this.usernameL = usernameL;
	}

	public JLabel getPasswordL() {
		return passwordL;
	}

	public void setPasswordL(JLabel passwordL) {
		this.passwordL = passwordL;
	}

	public JButton getLoginB() {
		return loginB;
	}

	public void setLoginB(JButton loginB) {
		this.loginB = loginB;
	}
}
