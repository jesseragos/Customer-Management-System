package test.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.cms.ui.utils.ImageFrame;

public class LoginUI_manual extends ImageFrame {

	private static final long serialVersionUID = 1L;
	private JTextField usernameTF;
	private JLabel errLoginL;
	private JLabel usernameL;
	private JLabel passwordL;
	private JButton loginB;
	private JPasswordField passwordPF;
	
	public static void main(String[] args){
		new LoginUI_manual();
	}
	
	public LoginUI_manual(){
		super("mobility_BG3.png");
		setFrame();
		Container pane = this.getContentPane();
		
		
		initialize();
		
		this.setVisible(true);
	}

	private void initialize() {
		usernameL = new JLabel("Username:");
		usernameL.setFont(new Font("Agency FB", Font.PLAIN, 21));
		usernameL.setBounds(77, 89, 72, 24);
		this.add(usernameL);
		
		passwordL = new JLabel("Password:");
		passwordL.setFont(new Font("Agency FB", Font.PLAIN, 21));
		passwordL.setBounds(77, 121, 72, 24);
		this.add(passwordL);
		
		usernameTF = new JTextField();
		usernameTF.setToolTipText("Enter username");
		usernameTF.setBounds(157, 93, 183, 22);
		this.add(usernameTF);
		usernameTF.setColumns(10);
		
		passwordPF = new JPasswordField();
		passwordPF.setBounds(157, 125, 183, 22);
		this.add(passwordPF);
		
		loginB = new JButton("Login");
		loginB.setFont(new Font("Century Gothic", Font.BOLD, 15));
		loginB.setBounds(274, 201, 134, 39);
		this.add(loginB);
		
		errLoginL = new JLabel("<error msg>");
		errLoginL.setFont(new Font("Consolas", Font.BOLD | Font.ITALIC, 12));
		errLoginL.setForeground(Color.RED);
		errLoginL.setBounds(12, 213, 92, 16);
		this.add(errLoginL);
	}
	
	private void setFrame() {
//		setBackgroundImage();
		this.setLayout(null);
		
		this.setSize(450, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setTitle("Login");
		this.setResizable(false);		
	}
	
//	private void setBackgroundImage() {
//		this.setImage("../CASE STUDY - CMS/src/com/cms/images/mobility_BG3.png");		
//	}

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
