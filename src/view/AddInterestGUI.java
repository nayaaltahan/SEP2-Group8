package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.InterestsController;
import controller.NewsFeedController;
import controller.StatusCtrlr;
import model.InterestList;
import shared.Interest;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class AddInterestGUI extends JPanel{

	private JTextField textField;
	private JScrollPane scrollPane;
	private JButton btnAdd;
	private JButton btnSave;
	private JList<Interest> list;
	private DefaultListModel<Interest> realList;
	private InterestsController ctrlr;
	private InterestButtonListener btnListener;

	/**
	 * Create the application.
	 */
	public AddInterestGUI() {
		initialize();
	}

	public void setController(InterestsController ctrlr) {
		this.ctrlr = ctrlr;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		setBounds(100, 100, 671, 508);

		scrollPane = new JScrollPane();
		realList = new DefaultListModel<Interest>();
		list = new JList<Interest>();
		list.setModel(realList);
		btnListener = new InterestButtonListener();

		textField = new JTextField();
		textField.setColumns(10);

		/////////////////////
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(btnListener);

		//////////////////////////
		btnSave = new JButton("Save");
		btnSave.addActionListener(btnListener);
		// ctrlr.getAllInterests();
		scrollPane.setViewportView(list);
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(24)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 343, GroupLayout.PREFERRED_SIZE)
						.addGap(12)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addGap(57).addComponent(btnSave,
										GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
								.addGroup(groupLayout.createSequentialGroup().addGap(38)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
												.addComponent(btnAdd, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
														GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
												.addComponent(textField, Alignment.TRAILING))))
						.addGap(42)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addGap(24).addComponent(scrollPane,
						GroupLayout.PREFERRED_SIZE, 390, GroupLayout.PREFERRED_SIZE))
				.addGroup(groupLayout.createSequentialGroup().addGap(49)
						.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addGap(35).addComponent(btnAdd).addGap(228).addComponent(btnSave)));
		setLayout(groupLayout);
	}

	public void setAllInterests(InterestList interests) {
		Interest[] array = interests.getArrayOfInterests();
		realList.removeAllElements();
		for (int i = 0; i < array.length; i++) {
			realList.addElement(array[i]);
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
				ctrlr.addChosenInterestsIntoUserInterestTable(
						new InterestList((ArrayList<Interest>) list.getSelectedValuesList()));
				//ctrlr.goToMainView();

			}

		}

	}

}
