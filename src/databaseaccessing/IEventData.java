package databaseaccessing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;

import model.NewsFeedModel;
import shared.Event;

public interface IEventData extends Remote {	
	public ArrayList<Event> getEventsByUsername(String username) throws RemoteException;

	public NewsFeedModel getNewsFeed(String username) throws RemoteException;

	public void addEventToDatabase(Event e) throws RemoteException, SQLException;
	
	public String[] getCities() throws RemoteException, SQLException;
}
