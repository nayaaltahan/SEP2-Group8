package controller;

import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import client.Client;
import model.NewsFeedModel;
import model.NewsFeedModel;
import shared.Event;
import shared.Status;
import view.ActivityOnNewsFeedPanel;
import view.NewsFeedPanel;

public class NewsFeedController {
	private NewsFeedPanel view;
	private NewsFeedModel newsFeedModel;
	private Client client;
	
	
	public NewsFeedController(Client client, NewsFeedPanel view) {
		this.client = client;
		this.view = view;
		this.view.setController(this);
		try {
			loadViewAndModel();
		} catch (SQLException e) {
			view.showMessage(e.getMessage());
		}
	}
	
	
	public void loadViewAndModel() throws SQLException {
			newsFeedModel = client.setupNewsFeed();
			System.out.println(newsFeedModel);
			view.setupNewsFeed(getBabyPanelsList());
	}
	
	public ArrayList<ActivityOnNewsFeedPanel> getBabyPanelsList(){
		ArrayList<ActivityOnNewsFeedPanel> listOfActivityPanels = new ArrayList<ActivityOnNewsFeedPanel>();
		for(int i = 0; i < newsFeedModel.size(); i ++) {
			ActivityOnNewsFeedPanel panel = new ActivityOnNewsFeedPanel(newsFeedModel.getItem(i).getName(), newsFeedModel.getItem(i).getText());
			panel.setController(this);
			listOfActivityPanels.add(panel);
			System.out.println("baby panel was added");
		}
		return listOfActivityPanels;
	}


	public String getFullInfo(String text) {
		if(newsFeedModel.getItem(text) instanceof Event) {
			//TODO proxy pattern for event
			Event event = (Event) newsFeedModel.getItem(text);
			return event.toString();
		}
		else if(newsFeedModel.getItem(text) instanceof Status) {
			Status status = (Status) newsFeedModel.getItem(text);
			try {
				return client.getUserInfo(status.getSender()).getAbout(); 
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public Client getClient() {
		return client;
	}
	

}
