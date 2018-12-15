package databaseaccessing;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface IEditData extends Remote{
	
	boolean userInfoIsCorrectEdit(String username, String password, String congirm) throws RemoteException, SQLException;
}