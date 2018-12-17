package controller;

import java.awt.Color;
import java.awt.HeadlessException;
import java.rmi.RemoteException;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JOptionPane;

import client.Client;
import client.IClient;
import databaseaccessing.IUserData;
import shared.User;
import shared.UserInfo;
import view.AddFriendView;
import view.AddInterestGUI;
import view.MainGUI;
import view.NewsFeedPanel;
import view.SignUpView;
import view.StatusView;

public class SignUpController {

	private SignUpView view;
	private Client client;
	private UserInfo user;

	// private static final DateTimeFormatter FORMATTER =
	// DateTimeFormatter.ofPattern("dd-MM-yyyy");

	public SignUpController(Client client, SignUpView registrerGui) {
		this.view = registrerGui;
		this.client = client;
		registrerGui.setController(this);
		fillUpCityCombo();
	}

	private void fillUpCityCombo() {
		try {
			view.setCities(client.getCities());
		} catch (RemoteException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean isValidForm() {

		StringBuilder msj = new StringBuilder();
		user = new UserInfo();

		if (view.getfNameField().getText().isEmpty()) {

			view.getfNameField().setBorder(BorderFactory.createLineBorder(Color.RED));
			msj.append("First Name is empty \n");

		} else {
			user.setFname(view.getfNameField().getText());
			view.getfNameField().setBorder(BorderFactory.createLineBorder(Color.GREEN));

		}

		if (view.getlNameField().getText().isEmpty()) {

			view.getlNameField().setBorder(BorderFactory.createLineBorder(Color.RED));
			msj.append("Last Name is empty \n");

		} else {
			user.setLname(view.getlNameField().getText());
			view.getlNameField().setBorder(BorderFactory.createLineBorder(Color.GREEN));

		}

		if (view.getTxtBirtdate().getText().isEmpty()) {

			view.getTxtBirtdate().setBorder(BorderFactory.createLineBorder(Color.RED));
			msj.append("Birthdate is empty \n");

		} else {
			user.setBirthdate(view.getTxtBirtdate().getText());
			view.getTxtBirtdate().setBorder(BorderFactory.createLineBorder(Color.GREEN));

		}

		if (view.getPhoneField().getText().isEmpty()) {

			view.getPhoneField().setBorder(BorderFactory.createLineBorder(Color.RED));
			msj.append("Phone is empty \n");

		} else {
			try {
				user.setPhone(Integer.parseInt(view.getPhoneField().getText()));
			} catch (NumberFormatException e) {
				view.showMessage("phone should only contain numbers");
			}
			view.getPhoneField().setBorder(BorderFactory.createLineBorder(Color.GREEN));

		}

		if (view.getEmailField().getText().isEmpty()) {

			view.getEmailField().setBorder(BorderFactory.createLineBorder(Color.RED));
			msj.append("Email is empty \n");

		} else {
			user.setEmail(view.getEmailField().getText());
			view.getEmailField().setBorder(BorderFactory.createLineBorder(Color.GREEN));

		}

		if (view.getNationalityField().getText().isEmpty()) {

			view.getNationalityField().setBorder(BorderFactory.createLineBorder(Color.RED));
			msj.append("Nationality is empty \n");

		} else {
			user.setNationality(view.getNationalityField().getText());
			view.getNationalityField().setBorder(BorderFactory.createLineBorder(Color.GREEN));

		}

		if (view.getSelectedCity().length() == 0) {

			view.cityComboBox.setBorder(BorderFactory.createLineBorder(Color.RED));
			msj.append("City is empty \n");

		} else {
			user.setCity(view.getSelectedCity());
			view.cityComboBox.setBorder(BorderFactory.createLineBorder(Color.GREEN));

		}

		if (view.getUserNameField().getText().isEmpty()) {

			view.getUserNameField().setBorder(BorderFactory.createLineBorder(Color.RED));
			msj.append("Username is empty \n");

		} else {
			user.setUsername(view.getUserNameField().getText());
			view.getUserNameField().setBorder(BorderFactory.createLineBorder(Color.GREEN));

		}

		if (String.valueOf(view.getPasswordField().getPassword()).isEmpty()) {

			view.getPasswordField().setBorder(BorderFactory.createLineBorder(Color.RED));
			msj.append("Password is empty \n");

		} else {
			user.setPassword(String.valueOf(view.getPasswordField().getPassword()));
			view.getPasswordField().setBorder(BorderFactory.createLineBorder(Color.GREEN));

		}

		if (String.valueOf(view.getVpasswordField().getPassword()).isEmpty()) {

			view.getVpasswordField().setBorder(BorderFactory.createLineBorder(Color.RED));
			msj.append("Verify Password is empty \n");

		} else {

			if (!String.valueOf(view.getPasswordField().getPassword())
					.equals(String.valueOf(view.getVpasswordField().getPassword()))) {
				msj.append("Password is diferent \n");

				view.getVpasswordField().setBorder(BorderFactory.createLineBorder(Color.RED));
				view.getPasswordField().setBorder(BorderFactory.createLineBorder(Color.RED));

			} else {

				view.getVpasswordField().setBorder(BorderFactory.createLineBorder(Color.GREEN));
			}

		}

		if (!msj.toString().isEmpty())
			JOptionPane.showMessageDialog(view.getFrame(), msj.toString());

		view.setBlackInputs();

		return msj.toString().isEmpty();

	}

	public void saveUser() {

		String gender = "";

		if (view.getRdbtnFemale().isSelected()) {
			gender = "F";
		} else if (view.getRdbtnMale().isSelected()) {
			gender = "M";
		} else if (view.getRdbtnOther().isSelected()) {
			gender = "O";
		}

		user.setGender(gender);

		try {
			if (!existUser()) {
				String returnString = client.saveUser(user);
				if (returnString.equals("correct")) {
					goToMainView();
				} else if (returnString.equals("birth")) {
					view.showMessage("birthdate has to be between 1990-01-01 and 2002-01-01");
				} else if (returnString.equals("number")) {
					view.showMessage("phone should only contain numbers");
				} else if (returnString.equals("date")) {
					view.showMessage("date format is wrong, consider using the following format: yyyy-mm-dd");
				} else if(returnString.equals("too long")){
					view.showMessage("some of the provided info contains too many characters, make sure that none contains more than 20 characters");
				}else {
					view.showMessage(returnString);
				}
			} else {
				view.getUserNameField().setBorder(BorderFactory.createLineBorder(Color.RED));
				JOptionPane.showMessageDialog(view.getFrame(), "Username exist");
				view.setBlackInputs();
			}
		} catch (HeadlessException | RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public boolean existUser() throws RemoteException {

		boolean exist = client.existUser(view.getUserNameField().getText());

//		if (exist) {
//			view.getUserNameField().setBorder(BorderFactory.createLineBorder(Color.RED));
//			JOptionPane.showMessageDialog(view.getFrame(), "Username exist");
//		}
//
//		view.setBlackInputs();

		return exist;
	}

	public void goToMainView() {
		view.getFrame().setVisible(false);
		NewsFeedPanel nfView = new NewsFeedPanel();
		NewsFeedController nfCtrlr = new NewsFeedController(client, nfView);
		StatusView sView = new StatusView();
		StatusCtrlr sCtrl = new StatusCtrlr(client, sView);
		AddFriendView addView = new AddFriendView();
		AddFriendController addCtrlr = new AddFriendController(client, addView);
		MainGUI mainGUI = new MainGUI(client, nfView, sView, addView);
	}

}
