package com.cms.controller;

import java.awt.event.*;
import java.sql.Connection;

import com.cms.domains.User;
import com.cms.techServ.*;
import com.cms.ui.LoginUI;

public class LoginController {
	
	private LoginUI loginUI;
	public UserDA userDA;
	private LoginHandler loginHandler;
	private Connection connection;

	public static void main(String[] args){
		new LoginController();
	}
	
	public LoginController(){
		setLoginUI();
	}
	
	private void setLoginUI() {
		//	SHOW LOGIN
		this.connection = new DBConnection().getConnection();
		loginUI = new LoginUI();
		userDA = new UserDA(connection);

		loginHandler = new LoginHandler();
		loginUI.getLoginB().addActionListener(loginHandler);
		loginUI.getPasswordTF().addKeyListener(loginHandler);
	}
	
	private class LoginHandler implements ActionListener, KeyListener{
		public void actionPerformed(ActionEvent loginEV){
			validateUser();
		}

		private void validateUser() {
			User user = userDA.validateUser(loginUI.getUsernameTF().getText(), 
											loginUI.getPasswordTF().getText());
			
			if(user!=null) displayMain(user);
			else reportInvalidInput();			
		}

		private void reportInvalidInput() {
			loginUI.getErrLoginL().setText("Credentials not recognized");
		}

		private void displayMain(User user) {
			loginUI.dispose();
			
			new MainController(user, connection);
		}

//		FOR KEY LISTENER
		
		@Override
		public void keyPressed(KeyEvent ev) {
			if(ev.getKeyCode() == KeyEvent.VK_ENTER){
				validateUser();
			}
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
		}

	}

}
