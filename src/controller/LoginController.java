package controller;

import java.sql.SQLException;

import client.Client;
import view.AddFriendView;
import view.LoginView;
import view.MainGUI;
import view.NewsFeedPanel;
import view.SearchView;
import view.SignUpView;
import view.StatusView;

public class LoginController  {

	private Client client;
	private LoginView view;

	public LoginController(Client client,LoginView view) {
		this.client = client;
		this.view = view;
		view.setController(this);
	}


	public boolean userInfoIsCorrect(String username, String password) throws SQLException {
		boolean isCorrect = client.userInfoIsCorrect(username, password);
		if (isCorrect) {
			client.setClient(username);
		}
		return isCorrect;
	}

	public void goToSignUp() {
		SignUpView signUpView = new SignUpView(view);
		signUpView.getFrame().setVisible(true);
		SignUpController signUpController = new SignUpController(client ,signUpView);
	}
	public void goToMainView() {
		view.getFrame().setVisible(false);
		NewsFeedPanel nfView = new NewsFeedPanel();
		NewsFeedController nfCtrlr = new NewsFeedController(client, nfView);
		StatusView sView = new StatusView();
		StatusCtrlr sCtrl = new StatusCtrlr(client, sView);
		AddFriendView addView = new AddFriendView();
		AddFriendController addCtrlr = new AddFriendController(client, addView);
		MainGUI mainGUI = new MainGUI(client,nfView, sView, addView);
	}	
	
}
