package test;

import java.sql.SQLException;

import databaseaccessing.DatabaseConnection;

public class DatabaseConnectionTest {
	public static void main(String[] args) {
		DatabaseConnection db = new DatabaseConnection();
		
		try {
			System.out.println(db.getSchema());
			db.query("select * from new_in_town.events where name = 'dinner'");
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
