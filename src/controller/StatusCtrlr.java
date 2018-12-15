package controller;

import client.Client;
import shared.Status;
import view.StatusView;

public class StatusCtrlr {
	private StatusView view;
	private Client client;

	public StatusCtrlr(Client client, StatusView view) {
		this.view = view;
		this.client = client;
		view.setController(this);
	}

	public boolean checkStatus(String body) {
		if (body.length() == 0) {
			return false;
		} else if (body.length() > 200) {
			return false;
		} else {
			return true;
		}
	}

	public void saveStatus(String body) {
		client.addStatus(body);
	}

}
