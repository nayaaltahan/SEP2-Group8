package databaseaccessing;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

public class DatabaseConnection {
	/// maybe it should be a singleton to make sure no data will be lost in case two
	/// things were added to the db in the same time.
	// also consider synchronizing

	private Connection connection = null;
	private final String user = "postgres";
	private final String password = "12345678";
	private final String database = "postgres";
	private final String driver = "org.postgresql.Driver";
	private final String url = "jdbc:postgresql://localhost:5432/";

	public DatabaseConnection() {
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url + database, user, password);
			connection.setSchema("new_in_town");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	public String getSchema() throws SQLException {
		return connection.getSchema();
	}

	private void openDatabase() throws SQLException {
		connection = DriverManager.getConnection(url, user, password);
	}

	private void closeDatabase() throws SQLException {
		connection.close();
	}

	public ArrayList<Object[]> query(String sql, Object... statementElements) throws SQLException {
		openDatabase();

		PreparedStatement statement = null;
		ArrayList<Object[]> list = null;
		ResultSet resultSet = null;
		if (sql != null && statement == null) {
			statement = connection.prepareStatement(sql);
			if (statementElements != null) {
				for (int i = 0; i < statementElements.length; i++)
					statement.setObject(i + 1, statementElements[i]);
			}
		}
		resultSet = statement.executeQuery();
		list = new ArrayList<Object[]>();
		while (resultSet.next()) {
			Object[] row = new Object[resultSet.getMetaData().getColumnCount()];
			for (int i = 0; i < row.length; i++) {
				row[i] = resultSet.getObject(i + 1);
			}
			list.add(row);
		}
		if (resultSet != null)
			resultSet.close();
		if (statement != null)
			statement.close();
		closeDatabase();
		return list;
	}

	public int update(String sql, Object... statementElements) throws SQLException {
		openDatabase();
		PreparedStatement statement = connection.prepareStatement(sql);
		if (statementElements != null) {
			for (int i = 0; i < statementElements.length; i++)
				statement.setObject(i + 1, statementElements[i]);
		}

		int result = statement.executeUpdate();

		closeDatabase();
		return result;
	}

//	public ResultSet getResulset(String sql, Object... statementElements) throws SQLException {
//		openDatabase();
//		PreparedStatement statement = connection.prepareStatement(sql);
//		if (statementElements != null) {
//			for (int i = 0; i < statementElements.length; i++)
//				statement.setObject(i + 1, statementElements[i]);
//		}
//
//		return statement.executeQuery();
//	}

	public Vector<Vector<String>> search(String username, ArrayList<String> conditions) throws SQLException {
		StringBuilder sb = new StringBuilder();
		sb.append("select fname, lname, birthdate, phone, nationality, gender, u.username from new_in_town1.\"user\" u");
		int length = conditions.size();
		if (length != 0 && !conditions.get(0).contains("join"))
			sb.append(" where ");
		for (int i = 0; i < length; i++) {
			sb.append(conditions.get(i));
			if (i != length - 1)
				sb.append(" AND ");
		}
		sb.append(";");

		String queryString = sb.toString();
		System.out.println(queryString);
		Vector<Vector<String>> dataModel = new Vector<Vector<String>>();
		
		Statement stmt;
		stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(queryString);
		Vector<String> record;
		while (rs.next()) {
			record = new Vector<String>();
			for (int i = 0; i < 7; i++) {
				record.add(rs.getString(i + 1));
				System.out.println(rs.getString(i + 1));
			}
			dataModel.add(record);
		}
		return dataModel;
	}

}
