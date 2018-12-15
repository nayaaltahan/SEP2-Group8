package test;

import java.awt.EventQueue;
import java.rmi.RemoteException;

import client.Client;
import client.IClient;
import controller.CreateEventsController;
import controller.LoginController;
import controller.NewsFeedController;
import controller.SignUpController;
import controller.StatusCtrlr;
import view.AddInterestGUI;
import view.CreateEventView;
import view.LoginView;
import view.MainGUI;
import view.NewsFeedPanel;
import view.SearchView;
import view.SignUpView;
import view.StatusView;

public class ClientTest {
	public static void main(String[] args) {
		Client client = new Client();
		LoginView view = new LoginView();
		LoginController loginCtrlr = new LoginController(client,view);
		
//		client.setClient("naya_all");
//		NewsFeedPanel panel = new NewsFeedPanel();
//		StatusView statusView = new StatusView();
//		MainGUI view = new MainGUI(panel,statusView);
//		NewsFeedController ctrlr = new NewsFeedController(client, panel);
//		StatusCtrlr sCtrlr = new StatusCtrlr(client, statusView);
		//SearchView view = new SearchView(client, "hi");
		
//		CreateEventView view = new CreateEventView();
//		CreateEventsController ctrlr = new CreateEventsController(client, view);
	}

}
