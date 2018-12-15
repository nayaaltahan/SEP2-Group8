package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.AddFriendController;
import shared.User;
import shared.User;
import shared.UserInfo;

public class AddFriendView extends JPanel {

	private JScrollPane allUsersScrollPane;
	private JScrollPane allFriendsScrollPane;

	private JList<User> list;
	private JList<User> listOfFriends;
	private DefaultListModel<User> listModel;
	private DefaultListModel<User> listOfFriendsModel;

	private JButton viewSelectedUserBtn;
	private JButton addFriendBtn;
	private JButton searchBtn;
	private JButton refreshBtn;

	private JTextField searchTxtField;

	private MyActionListener listener;
	private JPanel panel;
	private AddFriendController  ctrlr;

	private SelectionListener listListener;

	public AddFriendView() {

		listener = new MyActionListener();
		listModel = new DefaultListModel<>();
		listOfFriendsModel = new DefaultListModel<>();

		list = new JList<>(listModel);
		list.addListSelectionListener(listListener);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		listOfFriends = new JList<>(listOfFriendsModel);
		listOfFriends.addListSelectionListener(listListener);
		listOfFriends.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		refreshBtn = new JButton("Refresh");
		refreshBtn.addActionListener(listener);
		viewSelectedUserBtn = new JButton("View user");
		viewSelectedUserBtn.addActionListener(listener);
		addFriendBtn = new JButton("Follow");
		addFriendBtn.addActionListener(listener);
		searchBtn = new JButton("Search");
		searchBtn.addActionListener(listener);
		searchTxtField = new JTextField(10);
		allUsersScrollPane = new JScrollPane(list);
		allUsersScrollPane.setPreferredSize(new Dimension(300, 300));
		allUsersScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		allFriendsScrollPane = new JScrollPane(listOfFriends);
		allFriendsScrollPane.setPreferredSize(new Dimension(150, 150));
		add(refreshBtn);
		add(viewSelectedUserBtn);
		add(addFriendBtn);
		add(searchBtn);
		add(searchTxtField);
		add(allUsersScrollPane);
		add(allFriendsScrollPane);
		
	}

	public void setUsers(ArrayList<User> users2) {
		for (int i = 0; i < users2.size(); i++) {
			listModel.addElement(users2.get(i));

		}
	}

	public void setListOfFriends(ArrayList<User> friends) {
		for (int i = 0; i < friends.size(); i++) {
			listOfFriendsModel.addElement(friends.get(i));
			System.out.println(friends.get(i));
		}

	}

	public void setController(AddFriendController ctrlr) {
		this.ctrlr = ctrlr;
	}

	class SelectionListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (e.getSource() == list) {
				if (list.getSelectedValue() instanceof User) {

					User temp = (User) list.getSelectedValue();

				}
			}
		}

	}

	public void showSelectedUserInfo(UserInfo userInfo) {
		
		String name = "About ";
		for (int i = 0; i < listModel.getSize(); i++) {
			if (listModel.getElementAt(i).getUserInformation().equals(userInfo)) {
				name += listModel.getElementAt(i).getUserName();
			}
		}
		JOptionPane.showMessageDialog(null, userInfo.getAbout(), name, JOptionPane.CLOSED_OPTION);
	}

	public void search() {
		String username = searchTxtField.getText();
		boolean invalidUser = true;

		int index = 0;
		for (int i = 0; i < listModel.getSize(); i++) {

			if (username.equals(listModel.getElementAt(i).getUserName())) {
				invalidUser = false;
				index = i;
			}

		}
		if (!invalidUser) {
			list.setSelectedValue(listModel.get(index), true);

		} else {
			JOptionPane.showMessageDialog(null, "No match was found", null, JOptionPane.WARNING_MESSAGE);
		}
	}

	public void follow() {
		List<User> friends = list.getSelectedValuesList();
		String s="";
		User[] friendss = new User[friends.size()];
		for (int i = 0; i < friends.size(); i++) {

			friendss[i] = friends.get(i);
			if(friends.size()>1) {
				s+=", "+friends.get(i).getUserName();

			}
			else {
				s+=friends.get(i).getUserName();

			}
		}
		int choice = JOptionPane.showConfirmDialog(null, "Would you like to follow " + s + "?");
		if (choice == JOptionPane.YES_OPTION) {
			try {
				ctrlr.saveFriendsToDatabase(friendss);
				ctrlr.refresh();
				JOptionPane.showMessageDialog(null, "You are now following "+s, null, JOptionPane.CLOSED_OPTION);
			} catch (RemoteException | SQLException e) {
				if(e.getMessage().contains("already exists")) {
					JOptionPane.showMessageDialog(null, "You're already following this person", "",
						JOptionPane.CLOSED_OPTION);
				}else {
					e.printStackTrace();
				}

			}

		}

	}

	public void clearLists() {
		listOfFriendsModel.clear();
		listModel.clear();
	}

	class MyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == viewSelectedUserBtn) {
				if (list.getSelectedIndices().length > 1) {
					JOptionPane.showMessageDialog(null, "You can only select one person", "Error",
							JOptionPane.ERROR_MESSAGE);
				} else if (list.getSelectedIndices().length == 1)
					showSelectedUserInfo(list.getSelectedValue().getUserInformation());

			}
			if (e.getSource() == addFriendBtn) {

				follow();
			}
			if (e.getSource() == searchBtn) {
				search();

			}
			if (e.getSource() == refreshBtn) {

				ctrlr.refresh();
			}
		}
	}

}
