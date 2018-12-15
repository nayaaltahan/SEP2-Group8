package shared;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

import client.Client;
import databaseaccessing.DatabaseConnection;
import databaseaccessing.UserInfoHandler;

public class RealUser implements User{

	private UserInfoHandler handler;
	private String username;
	
	public RealUser(String username) {
		this.handler = UserInfoHandler.getInstance();
		this.username = username;
	}
	
	@Override
	public UserInfo getUserInformation() {
		try {
			return handler.getUserInfo(username);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getUserName() {
		return username;
	}

	@Override
	public ArrayList<Interest> getUserInterest() {
		try {
			return handler.getUsersInterests(username);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
