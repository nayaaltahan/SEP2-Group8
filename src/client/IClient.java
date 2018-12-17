package client;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import controller.InterestsController;
import model.NewsFeedModel;
import shared.Interest;
import shared.InterestList;
import shared.User;
import shared.UserInfo;

public interface IClient extends Remote {
	public void setClient(String username) throws RemoteException;
	public UserInfo getUserInfo(User user) throws RemoteException;
	public NewsFeedModel setupNewsFeed() throws RemoteException;
	public boolean existUser(String username) throws RemoteException;
	public boolean saveUser(UserInfo user) throws RemoteException;
	public boolean userInfoIsCorrect(String username, String password) throws RemoteException;
	//public void setController(IController ctrlr) throws RemoteException;
	public boolean saveInterest(Interest interest) throws RemoteException;
	public InterestList getAllInterests() throws RemoteException;
	public void addChosenInterestsIntoUserInterestTable(InterestList selectedInterests) throws RemoteException;
	public void setController(InterestsController interestsController);

}
