package databaseaccessing;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Vector;

import shared.User;
import shared.UserInfo;
import shared.RealUser;
import shared.Interest;
import shared.MyDate;
import shared.ProxyUser;

public class UserInfoHandler implements IUserData {
	private DatabaseConnection db;
	private static final UserInfoHandler INSTANCE = new UserInfoHandler();
	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	private static final String SELECT_EXIST_USER = "SELECT * FROM new_in_town1.\"user\" WHERE username = ? ";
	private static final String INSERT_INTO = "INSERT INTO new_in_town1.\"user\"("
			+ " fname, lname, birthdate, phone, gender, nationality,username, password, city)"
			+ " VALUES (?, ?, ?, ?, ?, ?,?, ?, ?)";

	private UserInfoHandler() {
		try {
			UnicastRemoteObject.exportObject(this, 0);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db = new DatabaseConnection();
	}

	// public String[] getUserNames(String city) {
	// db.getAllUsersNamesByCity(city);
	// }

	public UserInfo getUserInfo(String username) throws RemoteException {
		ArrayList<Object[]> list;
		UserInfo info = new UserInfo();
		try {
			list = db.query("SELECT fname, lname, nationality ,city FROM new_in_town1.\"user\" WHERE username = ? ",
					username);
			int rows = list.size(); // should be 1 always
			if (rows == 1) {
				int columns = list.get(0).length;
				// you need to create the realUser with info from the db
				Object[] row = list.get(0);
				String fName = row[0].toString();
				String lName = row[1].toString();
				String nationality = row[2].toString();
				String city = row[3].toString();
				info.setFname(fName);
				info.setLname(lName);
				info.setNationality(nationality);
				info.setCity(city);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return info;

	}

	public static UserInfoHandler getInstance() {
		return INSTANCE;
	}

	@Override
	public boolean existUser(String userName) {
		boolean correct = false;

		try {
			ArrayList<Object[]> list = db.query(SELECT_EXIST_USER, userName);

			if (list.size() >= 1) {
				correct = true;
			}

		} catch (SQLException e) {
			correct = true;
			e.printStackTrace();
		}

		return correct;
	}

	@Override
	public boolean saveUser(UserInfo user) {
		boolean correct = true;
		try {
			db.update(INSERT_INTO, user.getFname(), user.getLname(), // LocalDate.parse(user.getBirthdate(), FORMATTER),
					Date.valueOf(user.getBirthdate()), user.getPhone(), user.getGender(), user.getNationality(),
					user.getUsername(), user.getPassword(), user.getCity());

		} catch (Exception e) {
			correct = false;
			e.printStackTrace();
		}

		return correct;
	}

	@Override
	public ArrayList<User> getUsers(String username) throws RemoteException {
		String sql = "select username from new_in_town1.\"user\" where username <> ? and city = (Select city from new_in_town1.\"user\" where username = ?)";
		ArrayList<Object[]> list;
		ArrayList<User> users = new ArrayList<User>();
		try {
			list = db.query(sql, username, username);
			int rows = list.size();

			for (int i = 0; i < rows; i++) {
				Object[] row = list.get(i);
				ProxyUser pUser = new ProxyUser(row[0].toString());
				users.add(pUser);
			}
			System.out.println(users);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}

	public ArrayList<Interest> getUsersInterests(String username) throws RemoteException {
		String sql = "select interest from new_in_town1.interest_user where username = ? ";
		ArrayList<Interest> interests = new ArrayList<Interest>();
		try {
			ArrayList<Object[]> list = db.query(sql, username);
			for (int i = 0; i < list.size(); i++) {
				Object[] row = list.get(i);
				Interest interest = new Interest(row[0].toString());
				interests.add(interest);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return interests;
	}

	@Override
	public void addFriends(String username, User[] friends) throws SQLException {
		String sql = "INSERT INTO new_in_town1\"user\".friends VALUES(?,?);";
		for (int i = 0; i < friends.length; i++) {

			db.update(sql, username, friends[i].getUserName());

		}

	}

	@Override
	public Vector<Vector<String>> search(String username, ArrayList<String> conditions)
			throws RemoteException, SQLException {
		return db.search(username, conditions);
	}

	public ArrayList<User> getFriends(String username) throws RemoteException, SQLException {
		ArrayList<User> list = new ArrayList<>();
		ProxyUser puser;

		try {
			String sql = "SELECT friend_username FROM new_in_town1.friends WHERE user_username=? ;";
			ArrayList<Object[]> results = db.query(sql, username);
			for (int i = 0; i < results.size(); i++) {
				Object[] row = results.get(i);
				String usernameToReturn = row[0].toString();
				puser = new ProxyUser(usernameToReturn);
				list.add(puser);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(list);
		return list;

	}
}
