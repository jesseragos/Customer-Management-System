package test.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class MainMenuUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainMenuUI window = new MainMenuUI();
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
	public MainMenuUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 960, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panel.setBounds(208, 13, 722, 567);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblMenuView = new JLabel("menu view");
		lblMenuView.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuView.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblMenuView.setBounds(291, 214, 157, 64);
		panel.add(lblMenuView);
		
		JLabel lbl_Logout = new JLabel("Logout (username)");
		lbl_Logout.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lbl_Logout.setHorizontalAlignment(SwingConstants.RIGHT);
		lbl_Logout.setBounds(48, 560, 148, 20);
		frame.getContentPane().add(lbl_Logout);
		
		JButton btn_MaintainCustomer = new JButton("Maintain Customer");
		btn_MaintainCustomer.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btn_MaintainCustomer.setBounds(12, 99, 182, 43);
		frame.getContentPane().add(btn_MaintainCustomer);
		
		JButton btn_SetAdmin = new JButton("Set Admin");
		btn_SetAdmin.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btn_SetAdmin.setBounds(12, 172, 182, 43);
		frame.getContentPane().add(btn_SetAdmin);
		
		JButton btn_ViewReports = new JButton("View Reports");
		btn_ViewReports.setFont(new Font("Agency FB", Font.PLAIN, 20));
		btn_ViewReports.setBounds(12, 244, 182, 43);
		frame.getContentPane().add(btn_ViewReports);
	}
}
