package model;

import java.io.Serializable;
import java.util.ArrayList;

import shared.User;

public class AddFriendModel implements Serializable {
	private ArrayList<User> users;

	public AddFriendModel() {
		users = new ArrayList<User>();
	}

	public AddFriendModel (ArrayList<User> users) {
		this.users=users;
	}
	public void addUser(User user) {
		users.add(user);
	}

	public User getUser(String username) {
		User user = null;
		for (int i = 0; i < users.size(); i++) {
			if (users.get(i).getUserName().equalsIgnoreCase(username)) {
				
			}
		}
		return user;
	}
	public User get(int index) {
		return users.get(index);
	}

	public String toString() {
		String returnStr = "";

		for (int i = 0; i < users.size(); i++) {
			User temp = users.get(i);

			returnStr += temp + "\n";
		}
		return returnStr;
	}

}
