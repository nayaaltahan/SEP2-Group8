package databaseaccessing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

import shared.Status;

public interface IMessagesData extends Remote{
	
	//parameter could be proxyuser or username 
	//public Message[] getAllPrivateMessages(String username) throws RemoteException;
	public ArrayList<Status>  getAllStatuses(String username) throws RemoteException;
	//public void addPrivateMessage(String from, String to, Message message) throws RemoteException;
	public void addStatus(Status status) throws RemoteException;
}
