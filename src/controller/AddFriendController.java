package controller;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import client.Client;
import client.IClient;
import model.AddFriendModel;
import view.AddFriendView;
import shared.User;

public class AddFriendController {
	private AddFriendModel model;
	private AddFriendView view;
	private Client client;
	//private ArrayList<User> users;

	public AddFriendController(Client c, AddFriendView v) {
		this.view = v;
		client = c;
		v.setController(this);
		refresh();

	}

	public void refresh() {
		view.clearLists();
		//test
		model = new AddFriendModel();

		getAllUsers();
		//getFriends();

	}

	public void getAllUsers() {
		ArrayList <User> users = client.getUsers();
		ArrayList <User> friends = getFriends();
		System.out.println(users);
		view.setUsers(users);
		model = new AddFriendModel(users,friends);
	}

	public void getSelectedUserInfo(User user) {
		for (int i = 0; i < model.usersListSize(); i++) {
			if (model.getUser(i).equals(user)) {
				view.showSelectedUserInfo(model.getUser(i).getUserInformation(), model.getUser(i).getUserInterest());
			}

		}

	}

	public ArrayList<User> getFriends() {
		ArrayList<User> friends = new ArrayList<User>();
		try {
			friends = client.getFriends();
		} catch (RemoteException e) {
			e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.setListOfFriends(friends);
		return friends;
	}

	public void saveFriendsToDatabase(User[] users) throws RemoteException, SQLException {

		client.addFriends(users);

	}

}
