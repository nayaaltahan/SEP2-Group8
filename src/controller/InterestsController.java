package controller;

import java.rmi.RemoteException;
import java.sql.SQLException;

import client.Client;
import model.InterestList;
import shared.Interest;
import view.AddInterestGUI;
import view.MainGUI;
import view.NewsFeedPanel;
import view.StatusView;

public class InterestsController {
	private Client client;
	private AddInterestGUI view;

	public InterestsController(Client client, AddInterestGUI view) {
		this.client = client;
		this.view = view;
		view.setController(this);
		getAllInterests();
	}

	public void getAllInterests() {
		// observer
		InterestList interests = client.getAllInterests();
		System.out.println(interests);
		view.setAllInterests(interests);
		// model maybe
	}

	public void saveInterest(Interest interest) {
		try {
			client.saveInterest(interest);
		} catch (SQLException e) {
			if (e.getMessage().contains("duplicate key value violates unique constraint")) {
				view.showMessage("Interest was already added");
			}
		}
		getAllInterests();
	}

	public void addChosenInterestsIntoUserInterestTable(InterestList selectedInterests) throws RemoteException, SQLException {
		client.addChosenInterestsIntoUserInterestTable(selectedInterests);
	}
	public void getUserInterest() {
		// observer
		InterestList interests;
		interests = client.getUserInterest(); 
		System.out.println(interests);
		view.setUsersInterests(interests);
	}

}
