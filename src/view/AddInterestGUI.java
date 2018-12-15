package view;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.InterestsController;
import controller.NewsFeedController;
import controller.StatusCtrlr;
import databaseaccessing.IInterest;
import model.InterestList;
import shared.Interest;
import shared.User;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.border.MatteBorder;

public class AddInterestGUI extends JPanel{

	private JTextField textField;
	private JScrollPane scrollPane;
	private JButton btnAdd;
	private JButton btnSave;
	private JList<Interest> list;
	private DefaultListModel<Interest> listModel;
	private InterestsController ctrlr;
	private InterestButtonListener btnListener;

	
	private JList<Interest> userInterestList;
	private DefaultListModel<Interest> userInterestListModel;
	private JScrollPane allUInterestsScrollPane;
	private JList<Interest> list_1;
	private JLabel lblMyInterests;


	/**
	 * Create the application.
	 */
	public AddInterestGUI() {
		setBackground(Color.LIGHT_GRAY);
		initialize();
	}

	public void setController(InterestsController ctrlr) {
		this.ctrlr = ctrlr;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setBounds(100, 100, 907, 508);

		scrollPane = new JScrollPane();
		listModel = new DefaultListModel<Interest>();
		btnListener = new InterestButtonListener();
		
		allUInterestsScrollPane=new JScrollPane();
		userInterestListModel=new DefaultListModel<Interest>();
		list_1 = new JList<Interest>();
		list_1.setModel(userInterestListModel);


		textField = new JTextField();
		textField.setColumns(10);

		/////////////////////
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(btnListener);

		
		
//		allUInterestsScrollPane.setPreferredSize(new Dimension(200, 200));
//		allUInterestsScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		//////////////////////////
		btnSave = new JButton("Save");
		btnSave.addActionListener(btnListener);
	allUInterestsScrollPane.setViewportView(userInterestList);
		
		JLabel lblCantFindAnything = new JLabel("Can't find an interest? Add it to the list");
		lblCantFindAnything.setHorizontalAlignment(SwingConstants.TRAILING);
		lblCantFindAnything.setFont(new Font("Verdana", Font.BOLD, 16));
		
		lblMyInterests = new JLabel("My interests");
		lblMyInterests.setFont(new Font("Monospaced", Font.BOLD, 16));
		lblMyInterests.setForeground(new Color(102, 51, 102));
		
		JLabel lblInterestsAddBy = new JLabel("Interests added by other users");
		lblInterestsAddBy.setForeground(new Color(102, 0, 102));
		lblInterestsAddBy.setFont(new Font("Verdana", Font.BOLD, 16));
		lblInterestsAddBy.setHorizontalAlignment(SwingConstants.TRAILING);
		
	
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(35)
							.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(47)
									.addComponent(lblCantFindAnything))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(56)
									.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addGap(34)
									.addComponent(btnAdd))
								.addGroup(groupLayout.createSequentialGroup()
									.addGap(95)
									.addComponent(btnSave, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(lblInterestsAddBy)))
					.addPreferredGap(ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 191, GroupLayout.PREFERRED_SIZE)
							.addGap(32))
						.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
							.addComponent(lblMyInterests)
							.addGap(68))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(107, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(lblInterestsAddBy)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(lblMyInterests)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 252, GroupLayout.PREFERRED_SIZE)
								.addComponent(list_1, GroupLayout.PREFERRED_SIZE, 261, GroupLayout.PREFERRED_SIZE)))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(28)
							.addComponent(lblCantFindAnything)
							.addGap(18)
							.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnAdd))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnSave)))
					.addGap(106))
		);
		list = new JList<Interest>();
		list.setBorder(new MatteBorder(2, 2, 1, 1, (Color) new Color(64, 64, 64)));
		scrollPane.setViewportView(list);
		list.setModel(listModel);
		setLayout(groupLayout);
	
	}

	public void setAllInterests(InterestList interests) {
		Interest[] array = interests.getArrayOfInterests();
		listModel.removeAllElements();
		for (int i = 0; i < array.length; i++) {
			listModel.addElement(array[i]);
			
			
		}

	}
	public void setUsersInterests(InterestList interests) {
		Interest[] array = interests.getArrayOfInterests();
		for (int i = 0; i < array.length; i++) {
			userInterestListModel.addElement(array[i]);
			System.out.println("users interest"+ interests.get(i));
			
			
		}

	}
	public void showMessage(String s) {
		JOptionPane.showMessageDialog(null, s);
	}

	private class InterestButtonListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btnAdd) {
				ctrlr.saveInterest(new Interest(textField.getText()));
			}
			if (e.getSource() == btnSave) {
				try {
					ctrlr.addChosenInterestsIntoUserInterestTable(
							new InterestList((ArrayList<Interest>) list.getSelectedValuesList()));
				} catch (RemoteException | SQLException e1) {
					if(e1.getMessage().contains("already exists")) {
						JOptionPane.showMessageDialog(null, "You have already added this to your list of interests", "",JOptionPane.CLOSED_OPTION);

					}else {
					e1.printStackTrace();}
				}
				//ctrlr.goToMainView();

			}

		}

	}
}