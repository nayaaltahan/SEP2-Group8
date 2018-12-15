package groupingpackage;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import database.UserInfoHandler;
import mvc.AddFriendController;
import serverSide.IUserData;
import shared.IUser;
import shared.Interest;
import shared.ProxyUser;
import shared.UserInfo;

public class Client implements IClient {
	private IUserData userData;
	private IUser user;
	private AddFriendController ctrlr;
	private String username;

	
	public Client() {
		try {
			UnicastRemoteObject.exportObject(this, 0);
			userData = (IUserData) Naming.lookup("rmi://localhost:1099/m");
		} catch (RemoteException | MalformedURLException | NotBoundException e) {
			e.printStackTrace();
		}

	}

	public void setClient(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	@Override
	public void registerSelf() {
		try {
			userData.registerClient(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	

	@Override
	public ArrayList<IUser> getUsers() {
		try {
			ArrayList<IUser> list = userData.getUsers(username);
			System.out.println(list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public ArrayList<IUser> getUsersFriendList() {
		try {
			ArrayList<IUser> list = userData.getFriends(username);
			System.out.println(list);
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public UserInfo getUserInfo(IUser user) {
		return user.getUserInformation();
	}
	@Override
	public ArrayList<Interest> getInterests(IUser user) {
		return user.getUserInterest();

	}
	@Override
public void addFriends(IUser[] friends) throws RemoteException, SQLException {
		
	
		
			userData.addFriends(username,friends);
		
		
	
}

	

}

