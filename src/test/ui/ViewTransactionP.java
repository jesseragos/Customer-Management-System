package test.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ViewTransactionP {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_3;
	private JTextField textField_1;
	private JLabel lblSalesDetails;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton button_3;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewTransactionP window = new ViewTransactionP();
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
	public ViewTransactionP() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 548, 422);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new FormLayout(new ColumnSpec[] {
				FormSpecs.DEFAULT_COLSPEC,
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				ColumnSpec.decode("max(60dlu;default):grow"),
				FormSpecs.RELATED_GAP_COLSPEC,
				FormSpecs.DEFAULT_COLSPEC,},
			new RowSpec[] {
				RowSpec.decode("max(5dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(12dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				FormSpecs.DEFAULT_ROWSPEC,
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(129dlu;default):grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(7dlu;default)"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("default:grow"),
				FormSpecs.RELATED_GAP_ROWSPEC,
				RowSpec.decode("max(9dlu;default)"),}));
		
		JLabel lblUsername = new JLabel("Sales number:");
		frame.getContentPane().add(lblUsername, "3, 3, right, default");
		
		textField = new JTextField();
		frame.getContentPane().add(textField, "5, 3, left, default");
		textField.setColumns(10);
		
		JLabel lblFirstName = new JLabel("Sales date:");
		frame.getContentPane().add(lblFirstName, "7, 3, right, default");
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		frame.getContentPane().add(textField_3, "9, 3, fill, default");
		
		JLabel lblAddress = new JLabel("Total sales:");
		frame.getContentPane().add(lblAddress, "7, 5, right, default");
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		frame.getContentPane().add(textField_1, "9, 5, fill, default");
		
		lblSalesDetails = new JLabel("Sales Details");
		lblSalesDetails.setFont(new Font("Tahoma", Font.ITALIC, 15));
		frame.getContentPane().add(lblSalesDetails, "3, 7");
		
		scrollPane = new JScrollPane();
		frame.getContentPane().add(scrollPane, "3, 9, 7, 1, fill, fill");
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Product Code", "Description", "Unit", "Price", "Quantity"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(91);
		table.getColumnModel().getColumn(0).setMaxWidth(300000000);
		table.getColumnModel().getColumn(1).setPreferredWidth(205);
		scrollPane.setViewportView(table);
		
		button = new JButton("<<");
		frame.getContentPane().add(button, "3, 13");
		
		button_1 = new JButton("<");
		frame.getContentPane().add(button_1, "5, 13");
		
		button_2 = new JButton(">");
		frame.getContentPane().add(button_2, "7, 13");
		
		button_3 = new JButton(">>");
		frame.getContentPane().add(button_3, "9, 13");
	}

}
