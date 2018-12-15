package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.EditController;

public class EditView extends JPanel{

	private EditController ctrlr;

	public JPasswordField getConfirm() {
		return confirm;
	}

	public void setConfirm(JPasswordField confirm) {
		this.confirm = confirm;
	}

	public JPasswordField getPass() {
		return pass;
	}

	private JPasswordField pass;
	private JButton btnLogIn;
	private JLabel lblPassword_1;
	private JLabel lblConfirmPassword;
	private JPasswordField confirm;
	private JPasswordField passConfirm;
	// private LoginGuiButtonListerners btnListener;

	/**
	 * Create the application.
	 */
	public EditView() {
		initialize();
		setBackground(new Color(138, 43, 226));
		setBounds(100, 100, 480, 341);
	}

	public void setController(EditController ctrlr) {
		this.ctrlr = ctrlr;
	}


	public void showMessage(String body) {
		JOptionPane.showMessageDialog(null, body);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		

		btnLogIn = new JButton("UPDATE");
		btnLogIn.setBackground(new Color(192, 192, 192));
		btnLogIn.setFont(new Font("Stencil", Font.ITALIC, 13));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("hi");

				String password = String.valueOf(pass.getPassword());
				String confirm = String.valueOf(passConfirm.getPassword());
				if (!(password.length() == 0 || confirm.length() == 0)) {
					if (password.equals(confirm)) {
						try {
							if (ctrlr.userInfoIsCorrectEdit(password, confirm)) {
								showMessage("your information updated ");
							} else {
								showMessage("info is wrong");
							}

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				} else {
					showMessage("info is missing");
				}
			}
		});

		pass = new JPasswordField();

		lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setForeground(new Color(255, 204, 51));
		lblPassword_1.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));

		confirm = new JPasswordField();
		lblConfirmPassword = new JLabel("Confirm:");
		lblConfirmPassword.setForeground(Color.ORANGE);
		lblConfirmPassword.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));

		passConfirm = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.TRAILING)
					.addGroup(groupLayout.createSequentialGroup()
						.addContainerGap(61, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblConfirmPassword, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(pass, Alignment.LEADING)
							.addComponent(confirm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
						.addGap(122))
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(201)
						.addContainerGap(238, Short.MAX_VALUE))
					.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
						.addGap(178)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
							.addComponent(btnLogIn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							)
						.addContainerGap(203, Short.MAX_VALUE))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(51)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
						.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblConfirmPassword, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
							.addComponent(confirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(27)
						.addComponent(btnLogIn)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addGap(12)
						.addGap(12))
			);
			setLayout(groupLayout);
	}
}