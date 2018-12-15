package databaseaccessing;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class EditInfoHandler implements IEditData{
	private DatabaseConnection db;
	//private static final String INSERT_EDIT_USER = "UPDATE new_in_town.\"user\"  SET Password = '?' + WHERE username = '?';
	//private static final EditInfoHandler INSTANCE = new EditInfoHandler();
	public EditInfoHandler() {
		try {
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db = new DatabaseConnection();
	}
	
	
	


	@Override
	public boolean userInfoIsCorrectEdit(String username, String password, String congirm) throws SQLException{
	String sql="UPDATE new_in_town1.\"user\"  SET Password = ? WHERE username = ?;";
	int usersWithThisInfo = db.update(sql,password, username);
	System.out.println(usersWithThisInfo);
	if(usersWithThisInfo == 1) {
		return true;
	}
	else return false;
	
	}
	}