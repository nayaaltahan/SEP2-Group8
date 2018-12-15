package databaseaccessing;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import shared.Message;
import shared.MyDate;
import shared.ProxyUser;
import shared.Status;
import shared.User;

public class MessagesHandler implements IMessagesData {

	private DatabaseConnection db;
	private static final String INSERT_INTO = "Insert into new_in_town1.Status values(?,?)";

	public MessagesHandler() throws RemoteException {
		UnicastRemoteObject.exportObject(this, 0);
		db = new DatabaseConnection();
	}

	@Override
	public ArrayList<Status> getAllStatuses(String username) {
		// do like view number 5 with order by date created
		ArrayList<Object[]> list;
		ArrayList<Status> statuses = new ArrayList<Status>();
		try {
			list = db.query(
					"SELECT sender , body, time_sent FROM new_in_town1.status s left join new_in_town1.friends on sender = friend_username where user_username = ?;"
					,username);
			int rows = list.size();
			int columns = 3; // maybe 3 including time sent

			for (int i = 0; i < rows; i++) {
				Object[] row = list.get(i);
				User user = new ProxyUser((String) row[0]);
				MyDate timeSent = MyDate.parse(row[2].toString());
				// Timestamp timeSentAsTimeStamp = (Timestamp) row[2];
				// MyDate time_sent = MyDate.parse(timeSentAsTimeStamp.toString());
				Status status = new Status(user, (String) row[1], timeSent);
				statuses.add(status);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return statuses;
	}

	@Override
	public void addStatus(Status status) {
		try {
			db.update(INSERT_INTO, status.getName(), status.getBody());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
