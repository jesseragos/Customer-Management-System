package test.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ActionEvent;

public class PasswordFieldTest {

	private JFrame frame;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PasswordFieldTest window = new PasswordFieldTest();
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
	public PasswordFieldTest() {
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
		passwordField.setText("<reset text>");
		passwordField.setEchoChar((char) 0);
		passwordField.addFocusListener(new FocusListener(){
			@Override
			public void focusGained(FocusEvent arg0) {
				if(((getPasswordField().getSelectedText() != null) &&
					(getPasswordField().getSelectedText().isEmpty() || getPasswordField().getSelectedText().equals(("<reset text>"))))){
					passwordField.setEchoChar('*');
					passwordField.setText("");
				}
			}

			@Override
			public void focusLost(FocusEvent arg0) {
				if((getPasswordField().getSelectedText() == null) ||
					(getPasswordField().getSelectedText().isEmpty())){
					passwordField.setEchoChar((char) 0);
					passwordField.setText("<reset text>");
				}
			}
			
		});
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
		
		textField_1 = new JTextField();
		textField_1.setBounds(12, 13, 116, 22);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
	}

	public JPasswordField getPasswordField() {
		passwordField.selectAll();
		return passwordField;
	}
}
