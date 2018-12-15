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

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import controller.EditController;

public class EditView extends JPanel {

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

	/**
	 * Create the application.
	 */
	public EditView() {
		initialize();
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
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("hi");

				String password = String.valueOf(pass.getPassword());
				System.out.println(password);
				String confirm1 = String.valueOf(confirm.getPassword());
				System.out.println(confirm1);
				if (password.length() == 0 || confirm1.length() == 0) {
					showMessage("info is missing");
				} else {
					if (password.equals(confirm1)) {
						try {
							if (ctrlr.userInfoIsCorrectEdit(password, confirm1)) {
								showMessage("your information updated ");
							} else {
								showMessage("info is wrong");
							}

						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					} else {
						showMessage("passwords not matching");
					}

				}
			}
		});

		pass = new JPasswordField();

		lblPassword_1 = new JLabel("Password:");

		confirm = new JPasswordField();
		lblConfirmPassword = new JLabel("Confirm:");

		confirm = new JPasswordField();
		GroupLayout groupLayout = new GroupLayout(this);

		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap(61, Short.MAX_VALUE)
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblConfirmPassword, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
				.addGap(30)
				.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(pass, Alignment.LEADING)
						.addComponent(confirm, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
				.addGap(122))
				.addGroup(Alignment.LEADING,
						groupLayout.createSequentialGroup().addGap(201).addContainerGap(238, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup().addGap(178)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false).addComponent(btnLogIn,
								Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						.addContainerGap(203, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addGap(51)
				.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
				.addGroup(
						groupLayout.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblConfirmPassword, GroupLayout.PREFERRED_SIZE, 36,
										GroupLayout.PREFERRED_SIZE)
								.addComponent(confirm, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
				.addGap(27).addComponent(btnLogIn).addPreferredGap(ComponentPlacement.UNRELATED).addGap(12)
				.addGap(12)));
		setLayout(groupLayout);
	}
}