package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import controller.SignUpController;

import java.awt.Color;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpView {

		private JFrame frame;
		private JTextField fNameField;
		private JTextField lNameField;
		private JTextField txtBirtdate;
		private JTextField phoneField;
		private JTextField nationalityField;

		private JPasswordField passwordField;
		private JPasswordField vpasswordField;

		public JComboBox<String> cityComboBox;

		private JTextField userNameField;

		private JButton btnSignUp;

		private JButton btnSignIn;

		private SignUpController signUpController;
		private LoginView logInGui;

		private JRadioButton rdbtnMale = new JRadioButton("Male");

		private JRadioButton rdbtnFemale = new JRadioButton("Female");

		private JRadioButton rdbtnOther = new JRadioButton("Other");

		private ButtonGroup bg = new ButtonGroup();

		public JFrame getFrame() {
			return frame;
		}
		
		public String getSelectedCity() {
			return cityComboBox.getSelectedItem().toString();

		}

		public void setFrame(JFrame frame) {
			this.frame = frame;
		}

		public JTextField getfNameField() {
			return fNameField;
		}

		public void setfNameField(JTextField fNameField) {
			this.fNameField = fNameField;
		}

		public JTextField getlNameField() {
			return lNameField;
		}

		public void setlNameField(JTextField lNameField) {
			this.lNameField = lNameField;
		}

		public JTextField getTxtBirtdate() {
			return txtBirtdate;
		}

		public void setTxtBirtdate(JTextField txtBirtdate) {
			this.txtBirtdate = txtBirtdate;
		}

		public JTextField getPhoneField() {
			return phoneField;
		}

		public void setPhoneField(JTextField phoneField) {
			this.phoneField = phoneField;
		}

		

		public JTextField getNationalityField() {
			return nationalityField;
		}

		public void setNationalityField(JTextField nationalityField) {
			this.nationalityField = nationalityField;
		}

		public JPasswordField getPasswordField() {
			return passwordField;
		}

		public void setPasswordField(JPasswordField passwordField) {
			this.passwordField = passwordField;
		}

		public JPasswordField getVpasswordField() {
			return vpasswordField;
		}

		public void setVpasswordField(JPasswordField vpasswordField) {
			this.vpasswordField = vpasswordField;
		}


		public JTextField getUserNameField() {
			return userNameField;
		}

		public void setUserNameField(JTextField userNameField) {
			this.userNameField = userNameField;
		}

		public JButton getBtnCreate() {
			return btnSignUp;
		}

		public void setBtnCreate(JButton btnSignUp) {
			this.btnSignUp = btnSignUp;
		}

		public JButton getBtnBack() {
			return btnSignIn;
		}

		public void setBtnBack(JButton btnSignIn) {
			this.btnSignIn = btnSignIn;
		}

		public JRadioButton getRdbtnMale() {
			return rdbtnMale;
		}

		public void setRdbtnMale(JRadioButton rdbtnMale) {
			this.rdbtnMale = rdbtnMale;
		}

		public JRadioButton getRdbtnFemale() {
			return rdbtnFemale;
		}

		public void setRdbtnFemale(JRadioButton rdbtnFemale) {
			this.rdbtnFemale = rdbtnFemale;
		}

		public JRadioButton getRdbtnOther() {
			return rdbtnOther;
		}

		public void setRdbtnOther(JRadioButton rdbtnOther) {
			this.rdbtnOther = rdbtnOther;
		}

		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						new SignUpView(null);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});
		}
		
		public void showMessage(String s) {
			JOptionPane.showMessageDialog(frame, s);
		}

		/**
		 * Create the application.
		 */
		public SignUpView(LoginView logInGui) {
			this.logInGui = logInGui;
			initialize();
		}
		
		public void setController(SignUpController ctrlr) {
			this.signUpController = ctrlr;
		}

		void clearForm() {

			fNameField.setText("");
			lNameField.setText("");
			txtBirtdate.setText("");
			phoneField.setText("");
			nationalityField.setText("");

			passwordField.setText("");
			vpasswordField.setText("");

			userNameField.setText("");

			this.setBlackInputs();

		}
		
		public void setCities(String[] strings) {
			for(int i = 0 ; i < strings.length ; i ++) {
				cityComboBox.addItem(strings[i]);
			}
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frame = new JFrame();
			frame.setBounds(100, 100, 733, 670);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

			JLabel lblFirstName = new JLabel("First Name:");

			fNameField = new JTextField();
			fNameField.setColumns(10);

			JLabel lblLastName = new JLabel("Last Name:");

			lNameField = new JTextField();
			lNameField.setColumns(10);

			JLabel lblBirthdate = new JLabel("Birthdate:");

			txtBirtdate = new JTextField();
			txtBirtdate.setForeground(Color.BLACK);
			txtBirtdate.setText("");
			txtBirtdate.setColumns(10);

			JLabel lblPhone = new JLabel("Phone:");

			phoneField = new JTextField();
			phoneField.setColumns(10);

			JLabel lblGender = new JLabel("Gender:");

			JLabel lblNationality = new JLabel("Nationality:");

			nationalityField = new JTextField();
			nationalityField.setColumns(10);

			

			JLabel lblUsername = new JLabel("Username:");

			JLabel lblPassword = new JLabel("Password:");

			JLabel lblConfirmPassword = new JLabel("Verify Password:");

			passwordField = new JPasswordField();

			vpasswordField = new JPasswordField();

			JLabel lblCity = new JLabel("City:");

			cityComboBox = new JComboBox<String>();
			cityComboBox.setMaximumRowCount(50);

			userNameField = new JTextField();
			userNameField.setColumns(10);

			bg.add(rdbtnFemale);
			bg.add(rdbtnMale);
			bg.add(rdbtnOther);

			rdbtnOther.setSelected(true);

			///////////////////////////////
			btnSignUp = new JButton("Sign Up");
			btnSignUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if (signUpController.isValidForm()) {
						signUpController.saveUser();
					}
				}
			});

			////////////////////////
			btnSignIn = new JButton("Sign in");
			btnSignIn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					frame.setVisible(false);
					logInGui.getFrame().setVisible(true);
					clearForm();
				}
			});

	
			GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
			groupLayout.setHorizontalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(18)
								.addComponent(lblNationality)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(nationalityField, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(21)
								.addComponent(lblPassword)
								.addGap(20)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, 179, GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(lblConfirmPassword)
								.addGap(9)
								.addComponent(vpasswordField, GroupLayout.PREFERRED_SIZE, 176, GroupLayout.PREFERRED_SIZE))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(21)
								.addComponent(btnSignIn)
								.addGap(440)
								.addComponent(btnSignUp))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(22)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblBirthdate)
										.addGap(18)
										.addComponent(txtBirtdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lblPhone))
									.addGroup(groupLayout.createSequentialGroup()
										.addComponent(lblFirstName)
										.addGap(9)
										.addComponent(fNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addGap(24)
										.addComponent(lblLastName)))
								.addGap(21)
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
									.addComponent(lNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
							.addGroup(groupLayout.createSequentialGroup()
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(21)
										.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
											.addComponent(lblUsername)
											.addComponent(lblCity)))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(21)
										.addComponent(lblGender)))
								.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(39)
										.addComponent(rdbtnMale)
										.addGap(18)
										.addComponent(rdbtnFemale)
										.addGap(18)
										.addComponent(rdbtnOther))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(18)
										.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
									.addGroup(groupLayout.createSequentialGroup()
										.addGap(38)
										.addComponent(cityComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGap(802))
			);
			groupLayout.setVerticalGroup(
				groupLayout.createParallelGroup(Alignment.LEADING)
					.addGroup(groupLayout.createSequentialGroup()
						.addGap(30)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(3)
								.addComponent(lblFirstName))
							.addComponent(fNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(6)
								.addComponent(lblLastName))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(3)
								.addComponent(lNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
						.addGap(46)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblBirthdate)
							.addComponent(txtBirtdate, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblPhone)
							.addComponent(phoneField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(36)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(nationalityField, GroupLayout.PREFERRED_SIZE, 24, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblNationality))
						.addGap(36)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(9)
								.addComponent(lblGender))
							.addComponent(rdbtnMale, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(5)
								.addComponent(rdbtnFemale))
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(5)
								.addComponent(rdbtnOther)))
						.addGap(29)
						.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
							.addComponent(cityComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(lblCity))
						.addGap(21)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(3)
								.addComponent(lblUsername))
							.addComponent(userNameField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(25)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(3)
								.addComponent(lblPassword))
							.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addGroup(groupLayout.createSequentialGroup()
								.addGap(3)
								.addComponent(lblConfirmPassword))
							.addComponent(vpasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(75)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(btnSignIn)
							.addComponent(btnSignUp)))
			);
			frame.getContentPane().setLayout(groupLayout);

			setBlackInputs();

		}

		public void setBlackInputs() {
			getfNameField().setBorder(BorderFactory.createLineBorder(Color.BLACK));
			getlNameField().setBorder(BorderFactory.createLineBorder(Color.BLACK));
			getTxtBirtdate().setBorder(BorderFactory.createLineBorder(Color.BLACK));
			getPhoneField().setBorder(BorderFactory.createLineBorder(Color.BLACK));
			getNationalityField().setBorder(BorderFactory.createLineBorder(Color.BLACK));
			cityComboBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			getUserNameField().setBorder(BorderFactory.createLineBorder(Color.BLACK));
			getPasswordField().setBorder(BorderFactory.createLineBorder(Color.BLACK));
			getVpasswordField().setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
	}

