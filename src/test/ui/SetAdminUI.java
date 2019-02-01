package test.ui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import java.awt.Color;

public class SetAdminUI {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTable table;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SetAdminUI window = new SetAdminUI();
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
	public SetAdminUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 634, 651);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(70dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(70dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(70dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(70dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(22dlu;default)"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("max(10dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(11dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(5dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(12dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(4dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(13dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(13dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(9dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(75dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(6dlu;default)"),}));
		
		JLabel lblNewLabel = new JLabel("Set Admin");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel, "3, 3");
		
		textField_2 = new JTextField();
		frame.getContentPane().add(textField_2, "7, 3, right, default");
		textField_2.setColumns(13);
		
		JButton btnSearch = new JButton("Search");
		frame.getContentPane().add(btnSearch, "8, 3, 2, 1, left, default");
		
		JLabel lblUsername = new JLabel("Username:");
		frame.getContentPane().add(lblUsername, "3, 7, right, default");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "5, 7, fill, default");
		textField.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password:");
		frame.getContentPane().add(lblPassword, "7, 7, right, default");
		
		passwordField = new JPasswordField();
		frame.getContentPane().add(passwordField, "9, 7, 3, 1, fill, default");
		
		JLabel label = new JLabel("<error>");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		frame.getContentPane().add(label, "5, 9, 3, 1");
		
		JLabel label_1 = new JLabel("<error>");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		frame.getContentPane().add(label_1, "9, 9, 5, 1");
		
		JLabel lblFirstName = new JLabel("First name:");
		frame.getContentPane().add(lblFirstName, "3, 11, right, default");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		frame.getContentPane().add(textField_3, "5, 11, fill, default");
		
		JLabel lblLastName = new JLabel("Last name:");
		frame.getContentPane().add(lblLastName, "7, 11, right, default");
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		frame.getContentPane().add(textField_4, "9, 11, fill, default");
		
		JLabel label_2 = new JLabel("<error>");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		frame.getContentPane().add(label_2, "5, 13, 3, 1");
		
		JLabel label_3 = new JLabel("<error>");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		frame.getContentPane().add(label_3, "9, 13, 5, 1");
		
		JSeparator separator = new JSeparator();
		frame.getContentPane().add(separator, "3, 15, 9, 1");
		
		JLabel lblCustomerModule = new JLabel("Customer Module");
		lblCustomerModule.setFont(new Font("Tahoma", Font.ITALIC, 15));
		frame.getContentPane().add(lblCustomerModule, "3, 17");
		
		JCheckBox chckbxView = new JCheckBox("View");
		frame.getContentPane().add(chckbxView, "3, 19");
		
		JCheckBox chckbxAdd = new JCheckBox("Add");
		frame.getContentPane().add(chckbxAdd, "5, 19");
		
		JCheckBox chckbxEdit = new JCheckBox("Edit");
		frame.getContentPane().add(chckbxEdit, "7, 19");
		
		JCheckBox chckbxDelete = new JCheckBox("Delete");
		frame.getContentPane().add(chckbxDelete, "9, 19");
		
		JSeparator separator_1 = new JSeparator();
		frame.getContentPane().add(separator_1, "3, 21, 9, 1");
		
		JLabel lblReport = new JLabel("Reports");
		lblReport.setFont(new Font("Tahoma", Font.ITALIC, 15));
		frame.getContentPane().add(lblReport, "3, 23");
		
		JCheckBox chckbxCustomerbystatereport = new JCheckBox("Customer By State Report");
		frame.getContentPane().add(chckbxCustomerbystatereport, "3, 25, 3, 1");
		
		JCheckBox chckbxCustomerpaymenthistoryreport = new JCheckBox("Customer Payment History Report");
		frame.getContentPane().add(chckbxCustomerpaymenthistoryreport, "7, 25, 3, 1");
		
		JCheckBox chckbxCustomersalesreport = new JCheckBox("Customer Sales Report");
		frame.getContentPane().add(chckbxCustomersalesreport, "3, 27, 3, 1");
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Top Customer Sales");
		frame.getContentPane().add(chckbxNewCheckBox, "7, 27, 3, 1");
		
		JSeparator separator_2 = new JSeparator();
		frame.getContentPane().add(separator_2, "3, 29, 9, 1");
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "3, 31, 9, 1, fill, fill");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Code", "UserName", "Last name", "First name"
			}
		));
		table.getColumnModel().getColumn(1).setPreferredWidth(139);
		table.getColumnModel().getColumn(2).setPreferredWidth(114);
		table.getColumnModel().getColumn(3).setPreferredWidth(133);
		scrollPane.setViewportView(table);
		
		JButton btnAdd = new JButton("ADD");
		frame.getContentPane().add(btnAdd, "3, 33, default, fill");
		
		JButton btnEdit = new JButton("EDIT");
		frame.getContentPane().add(btnEdit, "5, 33, default, fill");
		
		JButton btnDelete = new JButton("DELETE");
		frame.getContentPane().add(btnDelete, "7, 33, default, fill");
	}

}
