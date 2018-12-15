package view;

import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import com.sun.scenario.effect.AbstractShadow.ShadowMode;

import sun.applet.Main;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.NewsFeedController;

public class ActivityOnNewsFeedPanel extends JPanel {

	private NewsFeedController ctrlr;
	private MyActionListener listener;
	
	public ActivityOnNewsFeedPanel(String name, String text) {
		super();
		//Setting the Button representing Friend or Event name
		JButton btnNewButton = new JButton(name);
		btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
		//getNumberrows for if statements if needed.
		JTextArea txtrStatusOrEvent = new JTextArea();
		txtrStatusOrEvent.setWrapStyleWord(true);
		txtrStatusOrEvent.setLineWrap(true);
		txtrStatusOrEvent.setEditable(false);
		txtrStatusOrEvent.setText(text);
		listener = new MyActionListener();
		btnNewButton.addActionListener(listener);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(txtrStatusOrEvent, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
						.addComponent(btnNewButton, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(btnNewButton, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(txtrStatusOrEvent, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
					.addContainerGap())
		);
		setLayout(groupLayout);
		
		setVisible(true);
		
	}
	
	public void showFullInfo(String info) {
		JOptionPane.showMessageDialog(null, info);
	}
	
	public void setController(NewsFeedController ctrlr) {
		this.ctrlr = ctrlr;
	}
	
	private class MyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton temp = (JButton) e.getSource();
			String fullInfo = ctrlr.getFullInfo(temp.getText());
			showFullInfo(fullInfo);
		}
		
	}
	
	
	
}
