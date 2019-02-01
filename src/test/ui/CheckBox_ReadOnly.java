package test.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class CheckBox_ReadOnly {

	private JFrame frame;
	private ItemListener itemListener;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CheckBox_ReadOnly window = new CheckBox_ReadOnly();
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
	public CheckBox_ReadOnly() {
		setItemListener();
		initialize();
	}

	private void setItemListener() {
		itemListener = new ItemListener(){
			public void itemStateChanged(ItemEvent ev) {
				System.out.println(ev.getSource() instanceof JCheckBox);
			}
			
		};
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JCheckBox chckbxTest = new JCheckBox("Test1");
		chckbxTest.setSelected(true);
		chckbxTest.setBounds(75, 97, 113, 25);
		frame.getContentPane().add(chckbxTest);
		chckbxTest.addItemListener(itemListener);
		
		JCheckBox chckbxTest_1 = new JCheckBox("Test2");
		chckbxTest_1.setBounds(214, 97, 113, 25);
		frame.getContentPane().add(chckbxTest_1);
		chckbxTest_1.addItemListener(itemListener);
		
		JCheckBox chckbxTest_2 = new JCheckBox("Test3");
		chckbxTest_2.setSelected(true);
		chckbxTest_2.setBounds(142, 143, 113, 25);
		frame.getContentPane().add(chckbxTest_2);
		chckbxTest_2.addItemListener(itemListener);
		
		JCheckBox chckbxTest_3 = new JCheckBox("Test4");
		chckbxTest_3.setBounds(40, 173, 113, 25);
		frame.getContentPane().add(chckbxTest_3);
		chckbxTest_3.addItemListener(itemListener);
	}
}
