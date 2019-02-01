package test.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PasswordFieldResetTest {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordFieldResetTest window = new PasswordFieldResetTest();
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
	public PasswordFieldResetTest() {
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
		
		passwordField = new JPasswordField();
		passwordField.setBounds(94, 87, 228, 22);
		frame.getContentPane().add(passwordField);
		
		JButton btnShowPassword = new JButton("Show password");
		btnShowPassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				passwordField.selectAll();
				textField.setText(passwordField.getSelectedText());
			}
		});
		btnShowPassword.setBounds(159, 118, 163, 25);
		frame.getContentPane().add(btnShowPassword);
		
		textField = new JTextField();
		textField.setBackground(Color.WHITE);
		textField.setEditable(false);
		textField.setBounds(94, 193, 228, 22);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}
}
