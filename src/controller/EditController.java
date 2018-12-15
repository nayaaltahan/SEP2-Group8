package controller;

import java.awt.Color;
import java.awt.HeadlessException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import client.Client;
import view.EditView;
import view.LoginView;

public class EditController {

	private Client client;
	private EditView view;

	public EditController(Client client,EditView view) {
		this.client = client;
		this.view = view;
		view.setController(this);
	}
	public void setView(EditView view) {
		this.view = view;
	}

	public boolean userInfoIsCorrectEdit(String password, String confirm) throws SQLException {
		boolean isCorrect = client.userInfoIsCorrectEdit(password, confirm);
		return isCorrect;
	}
	
	
	
}