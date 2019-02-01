package test.ui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.Color;

public class MaintainCustomerFormP {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_6;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MaintainCustomerFormP window = new MaintainCustomerFormP();
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
	public MaintainCustomerFormP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 634, 627);
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
				ColumnSpec.decode("max(44dlu;default):grow"),
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
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(6dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(79dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(6dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(20dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(11dlu;default)"),}));
		
		JLabel lblNewLabel = new JLabel("Maintain Customer");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		frame.getContentPane().add(lblNewLabel, "3, 3, 3, 1");
		
		textField_2 = new JTextField();
		frame.getContentPane().add(textField_2, "7, 3, right, default");
		textField_2.setColumns(13);
		
		JButton btnSearch = new JButton("Search");
		frame.getContentPane().add(btnSearch, "8, 3, 2, 1, left, default");
		
		JLabel lblUsername = new JLabel("Customer number:");
		frame.getContentPane().add(lblUsername, "3, 7, right, default");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "5, 7, fill, default");
		textField.setColumns(10);
		
		JLabel label = new JLabel("<error>");
		label.setForeground(Color.RED);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		frame.getContentPane().add(label, "5, 9, 3, 1, default, top");
		
		JLabel lblFirstName = new JLabel("Customer name:");
		frame.getContentPane().add(lblFirstName, "3, 11, right, default");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		frame.getContentPane().add(textField_3, "5, 11, fill, default");
		
		JLabel label_1 = new JLabel("<error>");
		label_1.setForeground(Color.RED);
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		frame.getContentPane().add(label_1, "5, 13, 3, 1");
		
		JLabel lblAddress = new JLabel("Address:");
		frame.getContentPane().add(lblAddress, "3, 15, right, default");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1, "5, 15, 3, 1, fill, default");
		
		JLabel label_2 = new JLabel("<error>");
		label_2.setForeground(Color.RED);
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		frame.getContentPane().add(label_2, "5, 17, 3, 1");
		
		JLabel lblPayterm = new JLabel("Payterm:");
		frame.getContentPane().add(lblPayterm, "3, 19, right, default");
		
		textField_6 = new JTextField();
		textField_6.setColumns(5);
		frame.getContentPane().add(textField_6, "5, 19, left, default");
		
		JLabel label_3 = new JLabel("<error>");
		label_3.setForeground(Color.RED);
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
		frame.getContentPane().add(label_3, "5, 21, 3, 1");
		
		JButton btnAdd = new JButton("ADD");
		frame.getContentPane().add(btnAdd, "3, 29, default, fill");
		
		JButton btnEdit = new JButton("EDIT");
		frame.getContentPane().add(btnEdit, "5, 29, default, fill");
		
		JButton btnDelete = new JButton("DELETE");
		frame.getContentPane().add(btnDelete, "7, 29, default, fill");
		
		JScrollPane scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "3, 25, 7, 1, fill, fill");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Customer #", "Customer name", "Address", "Payterm"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(82);
		table.getColumnModel().getColumn(0).setMaxWidth(300000000);
		table.getColumnModel().getColumn(1).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setMaxWidth(900000000);
		table.getColumnModel().getColumn(2).setPreferredWidth(132);
		table.getColumnModel().getColumn(3).setPreferredWidth(64);
		table.getColumnModel().getColumn(3).setMaxWidth(900000000);
		scrollPane.setViewportView(table);
	}

}
