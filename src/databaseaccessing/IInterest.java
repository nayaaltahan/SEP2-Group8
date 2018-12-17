package databaseaccessing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

import shared.Interest;
import shared.InterestList;

public interface IInterest extends Remote{
	boolean saveInterest(Interest interest) throws RemoteException , SQLException;
	InterestList getAllInterests() throws RemoteException;
	void addChosenInterestsIntoUserInterestTable(String username, InterestList selectedInterests) throws SQLException,RemoteException;
	InterestList getUserInterest(String username) throws SQLException, RemoteException;
	
}
