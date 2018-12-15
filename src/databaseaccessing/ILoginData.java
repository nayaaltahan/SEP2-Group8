package databaseaccessing;import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ILoginData extends Remote {

		public boolean userInfoIsCorrect(String username, String password) throws SQLException, RemoteException;

}
