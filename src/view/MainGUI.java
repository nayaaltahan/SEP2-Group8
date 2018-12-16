package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import client.Client;
import controller.AddFriendController;
import controller.CreateEventsController;
import controller.EditController;
import controller.InterestsController;
import controller.NewsFeedController;

public class MainGUI extends JFrame {

	private NewsFeedPanel newsFeedPanel;
	private StatusView statusView;
	private JPanel homePanel;
	private JTabbedPane tabPane;
	public AddFriendView addFriendsPanel;
	private EditView editView;
	private CreateEventView createEventsPanel;
	private AddInterestGUI addInterestsPanel;
	private SearchView searchView;
	private TabsListener tabsListener;
	private CreateEventsController eventsCtrlr;
	private InterestsController interestsCtrlr;
	private EditController editCtrlr;
	private Client client;
	
	/**
	 * Create the application.
	 */
	public MainGUI(Client client, NewsFeedPanel nfPanel, StatusView sView,
			AddFriendView friendsView) {
		super("Main GUI");
		this.client = client;
		homePanel = new JPanel();
		JPanel addP = new JPanel();
		newsFeedPanel = nfPanel;
		statusView = sView;
		addFriendsPanel = friendsView;
		//addFriendsPanel.setBounds(100, 100, 300, 300);
		//addFriendsPanel.setBounds(100, 100, 300, 400);
		//addP.add(addFriendsPanel);
		homePanel.setLayout(new GridLayout(1, 3));
		homePanel.add(addFriendsPanel);
		homePanel.add(newsFeedPanel);
		homePanel.add(statusView);
		tabPane = new JTabbedPane();
		tabPane.addTab("Home", homePanel);

		this.editView = new EditView();
		editView.setBounds(100, 100, 480, 341);
		JPanel editP = new JPanel();
		editP.add(editView);
		tabPane.addTab("Edit Password", editP);

		createEventsPanel = new CreateEventView();
		createEventsPanel.setBounds(100, 100, 980, 365);
		JPanel cPan = new JPanel();
		cPan.add(createEventsPanel, BorderLayout.CENTER);
		tabPane.addTab("Create Event", cPan);

		this.addInterestsPanel = new AddInterestGUI();
		JPanel intPa = new JPanel();
		intPa.add(addInterestsPanel);
		tabPane.addTab("Add Interests", intPa);
		
		searchView = new SearchView(client);
		tabPane.addTab("Search", searchView);
		
		tabsListener = new TabsListener();
		tabPane.addChangeListener(tabsListener);
		add(tabPane);

		setBounds(100, 100, 1500, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	

	private class TabsListener implements ChangeListener {
		@Override
		public void stateChanged(ChangeEvent e) {
			if(tabPane.getSelectedIndex() == 0) {
				//home
			}
			if(tabPane.getSelectedIndex() == 1) {
				//edit
				if(editCtrlr == null) {
					editCtrlr = new EditController(client, editView);
				}
			}
			if(tabPane.getSelectedIndex() == 2) {
				//create event 
				if(eventsCtrlr == null) {
					eventsCtrlr = new CreateEventsController(client, createEventsPanel);
				}
				eventsCtrlr.fillUpLists();
			}
			if(tabPane.getSelectedIndex() == 3) {
				//add interest
				if(interestsCtrlr == null) {
					interestsCtrlr = new InterestsController(client, addInterestsPanel);
				}
			}
			
		}
	}

}
