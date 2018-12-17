package databaseaccessing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

import shared.Interest;
import shared.User;
import shared.UserInfo;


public interface IUserData extends Remote{
	public UserInfo getUserInfo(String username) throws RemoteException;
	boolean existUser(String userName) throws RemoteException;
	String saveUser(UserInfo user)throws RemoteException;
	public ArrayList<User> getUsers(String username) throws RemoteException;
	public void addFriends(String username, User[] users) throws RemoteException , SQLException;
	public ArrayList<Interest> getUsersInterests(String username) throws RemoteException;
	public Vector<Vector<String>> search(String username, ArrayList<String> conditions) throws RemoteException , SQLException;
	public ArrayList<User> getFriends(String username) throws RemoteException, SQLException;
}
