package com.cms.ui.utils;

import javax.swing.UIManager;

public class Theme {
	
	public static void setLookAndFeel() {
		try {
            UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaAluOxideLookAndFeel");
		} catch (Exception e) {
//			if ("Nimbus".equals(info.getName())) {
//				UIManager.setLookAndFeel(info.getClassName());
//				break;
//			}
		    // If Nimbus is not available, you can set the GUI to another look and feel.
		}
	}

}
