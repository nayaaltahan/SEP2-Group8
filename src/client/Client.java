package client;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import databaseaccessing.IEditData;
import databaseaccessing.IEventData;
import databaseaccessing.IInterest;
import databaseaccessing.ILoginData;
import databaseaccessing.IMessagesData;
import databaseaccessing.IUserData;
import model.InterestList;
import model.NewsFeedModel;
import shared.Event;
import shared.Interest;
import shared.ProxyUser;
import shared.Status;
import shared.User;
import shared.UserInfo;

public class Client {

	private IEventData eventData;
	private IUserData userData;
	private IMessagesData messagesData;
	private ILoginData logInData;
	private IInterest interestData;

	private String username;
	private IEditData editData;

	// private IController ctrlr;

	public Client() {
		try {
			eventData = (IEventData) Naming.lookup("rmi://localhost:1099/sEvent");
			userData = (IUserData) Naming.lookup("rmi://localhost:1099/sUser");
			messagesData = (IMessagesData) Naming.lookup("rmi://localhost:1099/sMessages");
			interestData = (IInterest) Naming.lookup("rmi://localhost:1099/sInterest");
			logInData = (ILoginData) Naming.lookup("rmi://localhost:1099/sLogIn");
			editData = (IEditData) Naming.lookup("rmi://localhost:1099/sEdit");
		} catch (NotBoundException | MalformedURLException | RemoteException e) {
			e.printStackTrace();
		}
	}

	public void setClient(String username) {
		this.username = username;
	}

	public NewsFeedModel setupNewsFeed() throws SQLException {
		NewsFeedModel nf = new NewsFeedModel();
		try {
			nf.setNewsFeed(messagesData.getAllStatuses(username), eventData.getEventsByUsername(username));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nf;
	}

	public boolean existUser(String username) {
		try {
			return userData.existUser(username);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean saveUser(UserInfo user)  {
		try {
			boolean temp = userData.saveUser(user);
			if (temp) {
				setClient(user.getUsername());
			}
			return temp;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean userInfoIsCorrect(String username, String password) {
		try {
			return logInData.userInfoIsCorrect(username, password);
		} catch (SQLException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean saveInterest(Interest interest) throws SQLException {
		try {
			return interestData.saveInterest(interest);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public InterestList getAllInterests() {
		try {
			return interestData.getAllInterests();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void addChosenInterestsIntoUserInterestTable(InterestList selectedInterests) throws SQLException, RemoteException {
			interestData.addChosenInterestsIntoUserInterestTable(username, selectedInterests);
	}

	public ArrayList<User> getUsers() {
		try {
			ArrayList<User> list = userData.getUsers(username);
			System.out.println(list);
			return list;
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public UserInfo getUserInfo(User user) throws RemoteException {
		return user.getUserInformation();
	}

	public ArrayList<Interest> getInterestsOfUser(User user) {
		return user.getUserInterest();
	}

	public void addStatus(String body) {
		try {
			messagesData.addStatus(new Status(new ProxyUser(username), body));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public boolean eventExists(String eventName) {
	// try {
	// return eventData.eventExists(eventName);
	// } catch (RemoteException e) {
	// e.printStackTrace();
	// }
	// return false;
	// }

	public void createEvent(Event event) throws RemoteException, SQLException {
		event.addAdmin(new ProxyUser(username));
		eventData.addEventToDatabase(event);
	}

	public Vector<Vector<String>> search(ArrayList<String> conditions) throws RemoteException, SQLException {
		return userData.search(username, conditions);
	}

	public ArrayList<User> getFriends() throws RemoteException, SQLException {
		ArrayList<User> list = userData.getFriends(username);
		System.out.println(list);
		return list;	}

	public boolean userInfoIsCorrectEdit(String password, String confirm) throws SQLException {
		try {
			return editData.userInfoIsCorrectEdit(username, password, confirm);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		return false;
	}

	public String getUsername() {
		return username;
	}

	public void addFriends(User[] friends) throws RemoteException, SQLException {
		userData.addFriends(username, friends);

	}
	
	public String[] getCities() throws RemoteException, SQLException {
		return eventData.getCities();
	}
	
	public InterestList getUserInterest()  {
		try {
			return interestData.getUserInterest(username);
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}return  null;

	}

}
