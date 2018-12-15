package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.NewsFeedController;
import model.NewsFeedModel;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import java.awt.Button;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class NewsFeedPanel extends JPanel {

	private ArrayList<ActivityOnNewsFeedPanel> babyPanels;
	private JScrollPane scrollPane;
	private JPanel panel;
	private JLabel lblNewsFeed;
	private NewsFeedController ctrlr;
	private JButton refreshButton;
	

	public NewsFeedPanel() {
		lblNewsFeed = new JLabel("News Feed");
		lblNewsFeed.setFont(new Font("Tahoma", Font.PLAIN, 20));
		add(lblNewsFeed);
		scrollPane = new JScrollPane();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setVisible(true);
	}

	public void setupNewsFeed(ArrayList<ActivityOnNewsFeedPanel> babyPanels) {
		remove(scrollPane);
		this.babyPanels = babyPanels;
		panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		for (int i = 0; i < babyPanels.size(); i++) {
			panel.add(this.babyPanels.get(i));
			System.out.println(i);
		}
		scrollPane = new JScrollPane(panel);
		refreshButton = new JButton("Refresh Newsfeed");
		refreshButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				refresh();
			}
		});
		add(refreshButton);
		add(scrollPane);
		this.repaint();
		this.updateUI();
		scrollPane.revalidate();
		// scrollPane.updateUI();
		System.out.println("done");
	}
	
	public void setController(NewsFeedController ctrlr) {
		this.ctrlr = ctrlr;
	}
	
	public NewsFeedController getController() {
		return ctrlr;
	}
	
	public void showMessage(String s) {
		JOptionPane.showMessageDialog(this, s);
	}

	public void refresh() {
		try {
			ctrlr.loadViewAndModel();
		} catch (SQLException e) {//contains
			showMessage(e.getMessage());
		}
	}
	
	
}
