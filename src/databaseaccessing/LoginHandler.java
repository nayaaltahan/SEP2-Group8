package databaseaccessing;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginHandler implements ILoginData{
private DatabaseConnection db;
	
	public LoginHandler() {
		try {
			UnicastRemoteObject.exportObject(this, 0);
			db = new DatabaseConnection();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@Override
	public boolean userInfoIsCorrect(String username, String password) throws SQLException {
		String sql="select * from new_in_town1.user where username=? and password=?";
		ArrayList<Object[]> usersWithThisInfo = db.query(sql, username, password);
		System.out.println(usersWithThisInfo.size());
		if(usersWithThisInfo.size() == 1) {
			return true;
		}
		else return false;
		
	}
}
