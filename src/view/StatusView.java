package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import shared.Status;
import javax.swing.SwingConstants;

import controller.StatusCtrlr;

import javax.swing.DropMode;

public class StatusView extends JPanel{

	public JTextArea text1;
    private StatusCtrlr ctrlr;
    private StatusButtonListener listener;


	/**
	 * Create the application.
	 */
	public StatusView() {
		super();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setBounds(100, 100, 450, 300);
		
		JLabel lblCreateStatus = new JLabel("Create Status");
		lblCreateStatus.setFont(new Font("Sitka Text", Font.BOLD | Font.ITALIC, 20));
		
		JLabel lblmaxChar = new JLabel("(max 200 char)");
		
		JButton btnSend = new JButton("Send");
		listener = new StatusButtonListener();
		btnSend.addActionListener(listener);
		
		text1 = new JTextArea();
		text1.setToolTipText("");
		text1.setColumns(10);
		text1.setLineWrap(true);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(48, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblCreateStatus, GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
							.addGap(135))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(btnSend)
							.addGap(55))
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblmaxChar)
							.addGap(296))
						.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
							.addComponent(text1, GroupLayout.PREFERRED_SIZE, 354, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(16)
					.addComponent(lblCreateStatus, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
					.addGap(26)
					.addComponent(text1, GroupLayout.PREFERRED_SIZE, 73, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(lblmaxChar)
					.addGap(18)
					.addComponent(btnSend)
					.addContainerGap(34, Short.MAX_VALUE))
		);
		setLayout(groupLayout);
	}
	public void setController(StatusCtrlr ctrlr) {
		this.ctrlr = ctrlr;
	}
	private class StatusButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String body = text1.getText();
			 if(ctrlr.checkStatus(body)) {
				ctrlr.saveStatus(body);
				 JOptionPane.showMessageDialog(null, "Your status created..");
				 
			 }else {
				 JOptionPane.showMessageDialog(null, "your status must not be empty or more than 200 charcters..");
			 }			
		}
		
	}
}
