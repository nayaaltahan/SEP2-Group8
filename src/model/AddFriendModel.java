package model;

import java.io.Serializable;
import java.util.ArrayList;

import org.postgresql.jdbc2.ArrayAssistantRegistry;

import shared.User;

public class AddFriendModel implements Serializable {
	private ArrayList<User> users;
	private ArrayList<User> friends;

	public AddFriendModel() {
		users = new ArrayList<User>();
		friends = new ArrayList<User>();
	}

	public AddFriendModel(ArrayList<User> users, ArrayList<User> friends) {
		this.users = users;
		this.friends = friends;
	}

	public void addUserToUsers(User user) {
		users.add(user);
	}

	public void addUserToFriends(User friend) {
		friends.add(friend);
	}

	public User getUser(int index) {
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
	
	public int usersListSize() {
		return users.size();
	}

}
