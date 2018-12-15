package databaseaccessing;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.TimeZone;

import model.NewsFeedModel;
import shared.Address;
import shared.Event;
import shared.Interest;
import shared.MyDate;
import shared.ProxyUser;
import shared.Status;
import shared.User;

public class EventHandler implements IEventData {

	private DatabaseConnection db;
	private IMessagesData messageHandler;

	public EventHandler(IMessagesData msgHandler) {
		try {
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db = new DatabaseConnection();
		messageHandler = msgHandler;
	}

	@Override
	public void addEventToDatabase(Event e) throws RemoteException, SQLException {
		int rows = db.query("select * from new_in_town1.Address where address = ? and city = ?", e.getAddress(), e.getCity()).size();
		//TODO here the address assigned to another city will be added to the the city where it was added before.
		if(rows == 0) {
			String addressSQL = "INSERT INTO new_in_town1.Address VAlues(?,?)";
			db.update(addressSQL, e.getAddress(),e.getCity());
		}
		String sql = "INSERT INTO new_in_town1.Events Values(?,?,?,?);";
		db.update(sql, e.getName(), Timestamp.valueOf(e.getTime().toString()), e.getAddress(), e.isPrivate());
		addEventCategoriesIntoEventInterestTable(e);
		addEventAdminsIntoAdminsTable(e);
		addEventInviteesIntoInviteesTable(e);
	}

	@Override
	public ArrayList<Event> getEventsByUsername(String username) throws RemoteException {
		ArrayList<Event> arrayToBeReturned = new ArrayList<Event>();
//		try{ArrayList<Object[]> publicEvents = db.query(
//				"select e.name as event_name, e.time, e.is_private, a.street_name, a.block_no, c.name as city_name from new_in_town.address a "
//						+ "right join new_in_town.event e on a.name = e.name "
//						+ "left join new_in_town.city c on a.city = c.name "
//						+ "where is_private = false and a.city = (select u.city from new_in_town.\"user\" u where u.username = ?);",
//				username);
//		ArrayList<Object[]> events = db.query(
//				"select e.name as event_name, e.time, e.is_private, a.street_name, a.block_no, c.name as city_name from new_in_town.address a\r\n"
//						+ "							right join new_in_town.event e on a.name = e.name\r\n"
//						+ "							left join new_in_town.city c on a.city = c.name\r\n"
//						+ "							left join new_in_town.invitees i on a.name = i.name\r\n"
//						+ "							where is_private = true and i.username = ?;",
//				username);
//		events.addAll(publicEvents);
//		int rows = events.size();
//		arrayToBeReturned = new ArrayList<Event>();
//		if (rows >= 1) {
//			int columns = events.get(0).length;
//			for (int i = 0; i < rows; i++) {
//				Object[] row = events.get(i);
//				String eventName = row[0].toString();
//				String sTime = row[1].toString();
//				MyDate time = MyDate.parse(sTime);
//				boolean isPrivate = Boolean.parseBoolean(row[2].toString());
//				String streetName = row[3].toString();
//				String blockNo = row[4].toString();
//				String cityName = row[5].toString();
//				Address address = new Address(streetName, blockNo, cityName);
//				ArrayList<Interest> categories = getEventInterests(eventName);
//				ArrayList<User> admins = getAdmins(eventName);
//				ArrayList<User> invitees = getInvitees(eventName);
//				Event event = new Event(eventName, time, categories, admins, invitees, isPrivate);
//				arrayToBeReturned.add(event);
//			}
//		}}catch (SQLException e) {
//			e.printStackTrace();
//		}
		return arrayToBeReturned;

	}

	private ArrayList<User> getInvitees(String eventName) {
		try {
			ArrayList<Object[]> eventsAndInvitiees = db
					.query("SELECT ei.username FROM new_in_town1.Invitees ei WHERE ei.name = ?;", eventName);
			int rows = eventsAndInvitiees.size();
			int columns = 1;
			ArrayList<User> invitees = new ArrayList<User>();
			for (int i = 0; i < rows; i++) {
				Object[] row = eventsAndInvitiees.get(i);
				invitees.add(new ProxyUser((String) row[0]));
			}
			return invitees;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private ArrayList<User> getAdmins(String eventName) {
		try {
			ArrayList<Object[]> eventsAndAdmins = db
					.query("SELECT ea.username FROM new_in_town1.Admins ea WHERE ea.name = ?;", eventName);
			int rows = eventsAndAdmins.size();
			int columns = 1;
			ArrayList<User> admins = new ArrayList<User>();
			for (int i = 0; i < rows; i++) {
				Object[] row = eventsAndAdmins.get(i);
				admins.add(new ProxyUser((String) row[0]));
			}
			return admins;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private ArrayList<Interest> getEventInterests(String eventName) {
		try {
			ArrayList<Object[]> eventsNameAndInterest = db
					.query("SELECT ei.interest FROM new_in_town1.Event_Interest ei WHERE ei.name = ?;", eventName);
			int rows = eventsNameAndInterest.size();
			int columns = 1;
			ArrayList<Interest> interests = new ArrayList<Interest>();
			for (int i = 0; i < rows; i++) {
				Object[] row = eventsNameAndInterest.get(i);
				interests.add(new Interest((String) row[0]));
			}
			return interests;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	private void addEventCategoriesIntoEventInterestTable(Event e) throws SQLException {
		for (int i = 0; i < e.getNumberOfCategories(); i++) {
			db.update("INSERT INTO new_in_town1.event_interest VALUES(?,?,?,?);", e.getName(),e.getTime(),e.getAddress(), e.getCategories()[i]);
		}
	}

	private void addEventAdminsIntoAdminsTable(Event e) throws SQLException {
		for (int i = 0; i < e.getNumberOfAdmins(); i++) {
			db.update("INSERT INTO new_in_town1.Admins VALUES(?,?,?,?);", e.getName(),e.getTime(),e.getAddress(), e.getAdmins()[i]);
		}
	}

	private void addEventInviteesIntoInviteesTable(Event e) throws SQLException {
		for (int i = 0; i < e.getNumberOfInvitees(); i++) {
			db.update("INSERT INTO new_in_town1.invitees VALUES(?,?,?,?);", e.getName(),e.getTime(),e.getAddress(), e.getInvitees()[i]);
		}
	}

	@Override
	public NewsFeedModel getNewsFeed(String username) throws RemoteException{
		ArrayList<Status> statuses = messageHandler.getAllStatuses(username);
		ArrayList<Event> events = getEventsByUsername(username);
		NewsFeedModel nf = new NewsFeedModel();
		nf.setNewsFeed(statuses, events);
		return nf;
	}

	@Override
	public String[] getCities() throws RemoteException, SQLException {
		ArrayList<Object[]> list = db.query("select * from new_in_town1.city");
		String [] cities = new String[list.size()];
		for(int i = 0 ; i < list.size(); i++) {
			cities[i] = list.get(i)[0].toString();
		}
		return cities;
	}
	
	


}
