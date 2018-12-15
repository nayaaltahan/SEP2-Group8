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
	private ArrayList<User> users;

	public AddFriendController(Client c, AddFriendView v) {
		this.view = v;
		client = c;
		v.setController(this);
		refresh();

	}

	public void refresh() {
		view.clearLists();
		users = new ArrayList<User>();

		getAllUsers();
		getFriends();

	}

	public void getAllUsers() {
		users = client.getUsers();
		System.out.println(users);
		view.setUsers(users);
		model = new AddFriendModel(users);
	}

	public void getSelectedUserInfo(User user) {
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).equals(user)) {
				view.showSelectedUserInfo(users.get(i).getUserInformation());
			}

		}

	}

	public void getFriends() {
		try {
			users = client.getFriends();
		} catch (RemoteException e) {
			e.getMessage();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.setListOfFriends(users);
	}

	public void saveFriendsToDatabase(User[] users) throws RemoteException, SQLException {

		client.addFriends(users);

	}

}
