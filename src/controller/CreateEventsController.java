package controller;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import client.Client;
import shared.Address;
import shared.Event;
import shared.Interest;
import shared.User;
import shared.UserInfo;
import view.CreateEventView;
import view.NewsFeedPanel;

public class CreateEventsController {
	private CreateEventView view;
	private Client client;
	private shared.Event event;
	private String street;
	private String blockNo;
	private String city;
	private Address address;

	public CreateEventsController(Client client, CreateEventView view) {
		this.client = client;
		this.view = view;
		view.setController(this);
		fillUpLists();
	}

	// public boolean eventExists(Event event) {
	// boolean eventExists = client.eventExists(event.getName());
	// if (eventExists) {
	// JOptionPane.showMessageDialog(null, "This event already exists, try another
	// event name.");
	// }
	// return eventExists;
	// }

	public void goToMainGUI() {
		NewsFeedPanel newsFeed = new NewsFeedPanel();
		// needs to fix Newsfeed gui to get getFram
		newsFeed.setVisible(true);
		NewsFeedController newsFeedCtrlr = new NewsFeedController(client, newsFeed);
	}

	public void createEvent(Event event) throws RemoteException, SQLException {
		client.createEvent(event);
	}

	public void fillUpLists() {
		ArrayList<User> friends;
		try {
			view.setCities(client.getCities());
			friends = client.getFriends();
			System.out.println(friends);
			User[] array = new User[friends.size()];
			array = friends.toArray(array);
			view.setAdminsList(array);
			System.out.println(array);
			view.setInviteeList(array);
			Interest[] interests = client.getAllInterests().getArrayOfInterests();
			System.out.println(interests);
			view.setInterestsList(interests);
		} catch (RemoteException e) {
			view.showMessage(e.getMessage());
		} catch (SQLException e) {
			view.showMessage(e.getMessage());
		}

	}

	// public boolean checkEventForm() {
	// StringBuilder msg = new StringBuilder();
	// event = new shared.Event();
	// address = new Address(street, blockNo, city);
	// if (view.eventName.getText().isEmpty()) {
	// msg.append("Event name cannot be empty!!");
	// } else {
	// event.setName(view.eventName.getText());
	// }
	//
	// if ((view.textStreetName.getText().isEmpty()) &&
	// (view.textBlockNo.getText().isEmpty())
	// && (view.cityComboBox.getSelectedObjects().length == 0)) {
	// msg.append("Street,Block number and city cannot be empty!!");
	// } else {
	// address.setStreet(view.textStreetName.getText());
	// address.setBlockNo(view.textBlockNo.getText());
	// address.setCity(view.cityComboBox.getName());
	// }
	// complete checking

}
// IN THE CLIENT CLASS
