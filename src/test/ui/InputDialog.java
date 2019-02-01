package test.ui;

import javax.swing.JOptionPane;

import com.cms.ui.utils.DialogBox;

public class InputDialog {

	public static void main(String[] args) {
//		String d = JOptionPane.showInputDialog(null, "Enter something:", "Prompt Box", JOptionPane.NO_OPTION);
//		
//		JOptionPane.showMessageDialog(null, (d != null? "Your string is " + d : "String is blank"));
		
		DialogBox.showInputDialog("HI", "hoy ka");
	}

}
