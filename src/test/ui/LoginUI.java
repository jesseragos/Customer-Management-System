package test.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class LoginUI {

	private JFrame frame;
	private JTextField tf_Username;
	private JTextField tf_Password;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI window = new LoginUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Login");
		
		JLabel lbl_Username = new JLabel("Username:");
		lbl_Username.setFont(new Font("Agency FB", Font.PLAIN, 21));
		lbl_Username.setBounds(77, 89, 72, 24);
		frame.getContentPane().add(lbl_Username);
		
		JLabel lbl_Password = new JLabel("Password:");
		lbl_Password.setFont(new Font("Agency FB", Font.PLAIN, 21));
		lbl_Password.setBounds(77, 121, 72, 24);
		frame.getContentPane().add(lbl_Password);
		
		tf_Username = new JTextField();
		tf_Username.setToolTipText("Enter username");
		tf_Username.setBounds(157, 93, 183, 22);
		frame.getContentPane().add(tf_Username);
		tf_Username.setColumns(10);
		
		tf_Password = new JTextField();
		tf_Password.setToolTipText("Enter password");
		tf_Password.setColumns(10);
		tf_Password.setBounds(157, 125, 183, 22);
		frame.getContentPane().add(tf_Password);
		
		JButton btn_Login = new JButton("Login");
		btn_Login.setFont(new Font("Century Gothic", Font.BOLD, 15));
		btn_Login.setBounds(274, 201, 134, 39);
		frame.getContentPane().add(btn_Login);
		
		label = new JLabel("<error logs>");
		label.setForeground(Color.RED);
		label.setBounds(12, 213, 92, 16);
		frame.getContentPane().add(label);
	}
}
