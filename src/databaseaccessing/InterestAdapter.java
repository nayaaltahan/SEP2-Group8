package databaseaccessing;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.SQLException;
import java.util.ArrayList;

import model.InterestList;
import shared.Interest;

public class InterestAdapter implements IInterest {
	private DatabaseConnection db;

	private static final String INSERT_INTO = "INSERT INTO new_in_town1.\"interests\"(" + " interest)  VALUES (?)";
	private static final String GET_ALL_INTERESTS = "SELECT * FROM new_in_town1.\"interests\"";
	private static final String INSERT_INTO_EVENT_USER = "INSERT INTO new_in_town1.interest_user (username, interest) VALUES (?,?)";
	private static final String GET_USER_INTEREST = "SELECT interest FROM new_in_town1.\"interest_user\" WHERE username = ?";

	public InterestAdapter() {
		try {
			UnicastRemoteObject.exportObject(this,0);
			this.db = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public boolean saveInterest(Interest interest) throws RemoteException, SQLException {
			db.update(INSERT_INTO, interest.getName());
			return true;
	}

	@Override
	public InterestList getAllInterests() throws RemoteException {
		try {
			ArrayList<Object[]> list = db.query(GET_ALL_INTERESTS);
			int rows = list.size();
			InterestList interests = new InterestList();
			for (int i = 0; i < rows; i++) {
				interests.add(new Interest(list.get(i)[0].toString()));
			}System.out.println(interests);
			return interests;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void addChosenInterestsIntoUserInterestTable(String username, InterestList selectedInterests)
			throws RemoteException {
		for (int i = 0; i < selectedInterests.size(); i++) {
			try {
				db.update(INSERT_INTO_EVENT_USER, username ,selectedInterests.get(i).getName());
				System.out.println("interests and username are added to userinteresttable");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	@Override
	public InterestList getUserInterest(String username) throws SQLException, RemoteException {
		try {
			ArrayList<Object[]> list = db.query(GET_USER_INTEREST, username);
			int rows = list.size();
			InterestList interests = new InterestList();
			for (int i = 0; i < rows; i++) {
				interests.add(new Interest(list.get(i)[0].toString()));
			}System.out.println(interests);
			return interests;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
