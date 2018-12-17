package view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListModel;

import controller.CreateEventsController;
import shared.Address;
import shared.Event;
import shared.Interest;
import shared.MyDate;
import shared.User;

import javax.swing.JTextField;

import javax.swing.JScrollPane;

import javax.swing.JButton;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class CreateEventView extends JPanel {

	private JTextField textStreetName;
	private JTextField eventName;
	private JButton btnCreate;
	private JLabel lblIfPrivate;
	private JLabel lblEventName;
	private JLabel lblStreetName;
	private JComboBox<String> cityComboBox;
	private JLabel lblCity;
	private JComboBox<String> isPrivate;
	private JLabel lblDate;
	private JComboBox<Integer> year;
	private JComboBox<String> month;
	private JComboBox<String> day;
	private JLabel lblTime;
	private JComboBox<String> minute;
	private JComboBox<String> hour;

	private DefaultListModel<User> adminsDataModel;
	private JList<User> admins;
	private JScrollPane adminsScroll;

	private DefaultListModel<User> inviteesDataModel;
	private JList<User> invitees;
	private JScrollPane inviteesScroll;

	private DefaultListModel<Interest> categoriesDataModel;
	private JList<Interest> categories;
	private JScrollPane categoriesScroll;

	private JLabel lblAdministrators;
	private JLabel lblInvitees;

	private JLabel lblCategories;

	private CreateEventsController ctrlr;
	private CreatingEventsActionListener listener;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the application.
	 */
	public CreateEventView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 980, 365);

		lblIfPrivate = new JLabel("Private:");

		btnCreate = new JButton("Create!");

		lblEventName = new JLabel("Event name:");

		lblStreetName = new JLabel("Address:");

		textStreetName = new JTextField();
		textStreetName.setColumns(10);

		eventName = new JTextField();
		eventName.setColumns(10);

		cityComboBox = new JComboBox<String>();
		cityComboBox.setMaximumRowCount(50);

		lblCity = new JLabel("City:");

		isPrivate = new JComboBox<String>();
		isPrivate.addItem("YES");
		isPrivate.addItem("NO");

		lblDate = new JLabel("Date:");
		String s = "";

		year = new JComboBox<Integer>();
		year.addItem(2018);
		year.addItem(2019);
		year.addItem(2020);
		year.addItem(2021);

		month = new JComboBox<String>();
		month.setMaximumRowCount(15);
		for (int i = 1; i < 13; i++) {
			if (i < 10) {
				s = "0" + i;
				month.addItem(s);
			} else {
				s = "" + i;
				month.addItem(s);
			}
		}

		day = new JComboBox<String>();
		day.setMaximumRowCount(40);
		for (int i = 1; i < 29; i++) {
			if (i < 10) {
				s = "0" + i;
				day.addItem(s);
			} else {
				s = "" + i;
				day.addItem(s);
			}

		}
		String twentyNine = "29";
		String thirty = "30";
		String thirtyOne = "31";
		day.addItem(twentyNine);
		day.addItem(thirty);
		day.addItem(thirtyOne);

		lblTime = new JLabel("Time:");

		hour = new JComboBox<String>();
		hour.setMaximumRowCount(25);
		for (int i = 0; i < 24; i++) {
			if (i < 10) {
				s = "0" + i;
				hour.addItem(s);
			} else {
				s = "" + i;
				hour.addItem(s);
			}

		}

		minute = new JComboBox<String>();
		minute.addItem("00");
		minute.addItem("15");
		minute.addItem("30");
		minute.addItem("45");

		listener = new CreatingEventsActionListener();
		btnCreate.addActionListener(listener);

		adminsDataModel = new DefaultListModel<User>();
		admins = new JList<User>(adminsDataModel);
		lblAdministrators = new JLabel("Administrators:");
		adminsScroll = new JScrollPane(admins);

		inviteesDataModel = new DefaultListModel<User>();
		invitees = new JList<User>(inviteesDataModel);
		inviteesScroll = new JScrollPane(invitees);

		lblInvitees = new JLabel("Invitees:");

		categoriesDataModel = new DefaultListModel<Interest>();
		categories = new JList<Interest>(categoriesDataModel);
		categoriesScroll = new JScrollPane(categories);

		// categoriesScroll.add(categories);

		lblCategories = new JLabel("Categories:");

		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(19)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblEventName)
						.addComponent(lblDate).addComponent(lblStreetName).addComponent(lblTime).addComponent(lblCity)
						.addComponent(lblIfPrivate))
				.addGap(12)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addComponent(btnCreate)
								.addPreferredGap(ComponentPlacement.RELATED, 100, Short.MAX_VALUE))
						.addComponent(cityComboBox, Alignment.LEADING, 0, 246, Short.MAX_VALUE)
						.addComponent(eventName, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
						.addGroup(groupLayout.createSequentialGroup().addComponent(year, 0, 110, Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(month, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
								.addGap(12)
								.addComponent(day, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.RELATED))
						.addComponent(textStreetName, GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)
						.addGroup(Alignment.LEADING,
								groupLayout.createSequentialGroup()
										.addComponent(hour, GroupLayout.PREFERRED_SIZE, 66, GroupLayout.PREFERRED_SIZE)
										.addGap(18).addComponent(minute, GroupLayout.PREFERRED_SIZE, 62,
												GroupLayout.PREFERRED_SIZE))
						.addComponent(isPrivate, Alignment.LEADING, 0, 246, Short.MAX_VALUE))
				.addGap(53)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.TRAILING)
								.addGroup(
										groupLayout.createSequentialGroup().addComponent(lblAdministrators).addGap(75))
								.addGroup(groupLayout.createSequentialGroup()
										.addComponent(adminsScroll, GroupLayout.PREFERRED_SIZE, 145,
												GroupLayout.PREFERRED_SIZE)
										.addGap(18)))
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup().addComponent(lblInvitees).addGap(125))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(inviteesScroll, GroupLayout.PREFERRED_SIZE, 156,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblCategories)
						.addComponent(categoriesScroll, GroupLayout.PREFERRED_SIZE, 146, GroupLayout.PREFERRED_SIZE))
				.addGap(72)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblAdministrators)
								.addComponent(lblInvitees).addComponent(lblCategories))
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblEventName)
								.addComponent(eventName, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)))
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblDate)
								.addComponent(year, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(day, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(month, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(lblTime)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(hour, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(minute, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE)))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblStreetName)
								.addComponent(textStreetName, GroupLayout.PREFERRED_SIZE, 19,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(
								groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(cityComboBox, GroupLayout.PREFERRED_SIZE, 19,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(lblCity))
						.addGap(18)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE).addComponent(lblIfPrivate)
								.addComponent(isPrivate, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE))
						.addGap(32).addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)

								.addComponent(btnCreate)))
						.addGroup(groupLayout.createSequentialGroup().addPreferredGap(ComponentPlacement.RELATED)
								.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
										.addComponent(categoriesScroll, GroupLayout.PREFERRED_SIZE, 255,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(inviteesScroll, GroupLayout.PREFERRED_SIZE, 256,
												GroupLayout.PREFERRED_SIZE)
										.addComponent(adminsScroll, GroupLayout.PREFERRED_SIZE, 256,
												GroupLayout.PREFERRED_SIZE))))
				.addContainerGap()));
		setLayout(groupLayout);
	}

	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	public void setController(CreateEventsController ctrlr) {
		this.ctrlr = ctrlr;
	}

	public void setCities(String[] strings) {
		cityComboBox.removeAllItems();
		for (int i = 0; i < strings.length; i++) {
			cityComboBox.addItem(strings[i]);
		}
	}

	public void setInviteeList(User[] friendlist) {
		inviteesDataModel.clear();
		for (int i = 0; i < friendlist.length; i++) {
			System.out.println(friendlist[i]);
			inviteesDataModel.addElement(friendlist[i]);
		}
	}

	public void setAdminsList(User[] friendlist) {
		adminsDataModel.clear();
		for (int i = 0; i < friendlist.length; i++) {
			adminsDataModel.addElement(friendlist[i]);
		}
	}

	public void setInterestsList(Interest[] interests) {
		categoriesDataModel.clear();
		for (int i = 0; i < interests.length; i++) {
			System.out.println(interests[i] + "" + interests.length);
			categoriesDataModel.addElement(interests[i]);
		}
	}

	private class CreatingEventsActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String message = "";
			if (e.getSource() == btnCreate) {
				if (eventName.getText().length() == 0 || textStreetName.getText().length() == 0) {
					if (eventName.getText().length() == 0) {
						message += "Event name is empty\n";
					}
					if (textStreetName.getText().length() == 0) {
						message += "Street name is empty\n";
					}
					showMessage(message);
				} else {
					String name = eventName.getText();
					String address = textStreetName.getText();
					MyDate eventTime = new MyDate(00, Integer.parseInt((String) minute.getSelectedItem()),
							Integer.parseInt((String) hour.getSelectedItem()),
							Integer.parseInt((String) day.getSelectedItem()),
							Integer.parseInt((String) month.getSelectedItem()), (Integer) year.getSelectedItem());
					System.out.println(eventTime.toString());
					String city = cityComboBox.getSelectedItem().toString();
					List<User> adminsList = admins.getSelectedValuesList();
					List<User> inviteesList = invitees.getSelectedValuesList();
					List<Interest> interestList = categories.getSelectedValuesList();
					boolean isPrivateBoolean = false;
					if (isPrivate.getSelectedItem().toString().equals("YES")) {
						isPrivateBoolean = true;
					}
					Event event = new Event(name, eventTime, address, city, interestList, adminsList, inviteesList,
							isPrivateBoolean);
					try {
						ctrlr.createEvent(event);
					} catch (RemoteException e1) {
						e1.printStackTrace();	
					} catch (SQLException e1) {
						if(e1.getMessage() != null) {
							if(e1.getMessage().contains("too long")) {
								showMessage("event name or address is too long, make sure they only contain 30 characters");
							}
						}
					}
				}
			}

		}

	}

}