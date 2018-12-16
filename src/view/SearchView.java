package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import client.Client;

public class SearchView extends JPanel {
	private final int COLUMN = 6;
	private final List<String> TITLES = Arrays.asList("First Name", "Last Name", "Age", "Nationality", "Gender",
			"Username");
	private Vector<Vector<String>> dataModel = new Vector<Vector<String>>();
	private QueryItem name = new QueryItem("name：", 20);
	private QueryItem gender = new QueryItem("gender：", 5);
	private QueryItem age = new QueryItem("age：", 5);
	private QueryItem nationality = new QueryItem("nationality：", 10);
	private QueryItem interests = new QueryItem("interests", 20);
	private JButton queryBtn = new JButton("search");
	private MyTable table;
	private Client client;

	// public static void main(String[] args) throws ClassNotFoundException,
	// SQLException {
	// Client client = new Client();
	// client.setClient("naya_all");
	// Main frame = new Main(client, "Database Query");
	// }

	public SearchView(Client client) {
		setClient(client);
		Vector<String> titles = new Vector<String>(TITLES);
		dataModel = new Vector<Vector<String>>();
		table = new MyTable(dataModel, titles);
		table.getColumnModel().getColumn(0).setPreferredWidth(35);
		table.getColumnModel().getColumn(1).setPreferredWidth(35);
		table.getColumnModel().getColumn(2).setPreferredWidth(20);
		table.getColumnModel().getColumn(4).setPreferredWidth(20);

		JPanel controlPanel = new JPanel();
		controlPanel.setLayout(new FlowLayout());
		controlPanel.add(name);
		controlPanel.add(gender);
		// controlPanel.add(education);
		controlPanel.add(age);
		// controlPanel.add(work);
		controlPanel.add(interests);
		controlPanel.add(nationality);
		controlPanel.add(queryBtn);

		// controlPanel.setPreferredSize(new Dimension(0, 130));

		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		tablePanel.add(Box.createRigidArea(new Dimension(0, 20)));
		tablePanel.add(table.getTableHeader());
		tablePanel.add(new JScrollPane(table));

		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());

		container.add(tablePanel, BorderLayout.CENTER);

		add(controlPanel, BorderLayout.NORTH);
		add(container, BorderLayout.CENTER);
		this.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.WEST);
		this.add(Box.createRigidArea(new Dimension(20, 0)), BorderLayout.EAST);
		this.add(Box.createRigidArea(new Dimension(0, 20)), BorderLayout.SOUTH);

		setActionListener();

		setMinimumSize(new Dimension(750, 500));
		setVisible(true);
	}

	// public void connectToDB() throws SQLException, ClassNotFoundException {
	// Class.forName("org.postgresql.Driver");
	// final String URL = "jdbc:postgresql://localhost:5432/";
	// conn = DriverManager.getConnection(URL, "postgres", "666");
	// }

	public void showMessage(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	public void setClient(Client client) {
		this.client = client;
	}

	private void setActionListener() {
		queryBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> conditions = new ArrayList<String>();
				if (interests.isSelected() && !interests.getText().isEmpty()) {
					conditions.add(" join new_in_town1.interest_user iu on u.username = iu.username"
							+ " where (interest = '" + interests.getText() + "')");
				}
				if (gender.isSelected() && !gender.getText().isEmpty())
					conditions.add("(gender = '" + gender.getText().toUpperCase().charAt(0) + "')");
				if (name.isSelected() && !name.getText().isEmpty())
					conditions.add("(fname = '" + name.getText() + "')");
				// if (education.isSelected())
				// conditions.add("(education = '" + education.getText() + "')");
				if (age.isSelected() && !age.getText().isEmpty())
					conditions.add("(age(birthdate) = " + age.getText() + ")");
				// if (work.isSelected())
				// conditions.add("(work = '" + work.getText() + "')");
				if (nationality.isSelected() && !nationality.getText().isEmpty())
					conditions.add("(nationality = '" + nationality.getText() + "')");

				dataModel.clear();
				try {
					Vector<Vector<String>> c = client.search(conditions);
					for (int i = 0; i < c.size(); i++) {
						dataModel.add(c.get(i));
					}
					System.out.println(dataModel.toString());
					table.validate();
					table.updateUI();
				} catch (RemoteException e1) {
					e1.printStackTrace();
					showMessage(e1.getMessage());
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					showMessage(e1.getMessage());
				}

			}

		});
	}

}