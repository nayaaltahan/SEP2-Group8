package view;
import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Font;
import controller.LoginController;

public class LoginView {

	private JFrame frmNewInTown;
	private JTextField user;
	private LoginController ctrlr;
	private JPasswordField pass;
	private JLabel lblUsername;
	private JButton btnLogIn;
	private JLabel lblPassword_1;
	private JButton btnSignUp;
	private JLabel lblOr;
	//private LoginGuiButtonListerners btnListener;

	/**
	 * Create the application.
	 */
	public LoginView() {
		initialize();
		frmNewInTown.setVisible(true);
	}
	
	public void setController(LoginController ctrlr) {
		this.ctrlr = ctrlr;
	}
	
	public JFrame getFrame() {
		return frmNewInTown;
	}
	
	public void showMessage(String body) {
		JOptionPane.showMessageDialog(null, body);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmNewInTown = new JFrame();
		frmNewInTown.getContentPane().setBackground(new Color(138, 43, 226));
		frmNewInTown.setTitle("New in Town");
		frmNewInTown.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\crona\\Desktop\\sep2Logo.png"));
		frmNewInTown.setBounds(100, 100, 480, 341);
		frmNewInTown.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		user = new JTextField();
		user.setColumns(10);
		
		lblUsername = new JLabel("Username:");
		lblUsername.setForeground(new Color(255, 204, 51));
		lblUsername.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
		
		btnLogIn = new JButton("LOG IN");
		btnLogIn.setBackground(new Color(192, 192, 192));
		btnLogIn.setFont(new Font("Stencil", Font.ITALIC, 13));
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("hi");
				String username = user.getText();
				String password = String.valueOf(pass.getPassword());
				if(!(username.length() == 0 || password.length() == 0)) {
					try {
						if(ctrlr.userInfoIsCorrect(username, password)) {
							ctrlr.goToMainView();
						}  else {
							showMessage("Wrong info");
						}
						
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					showMessage("info is missing");
				}
			}
		});
//				String sql="select * from new_in_town.login where username=? and pass=?";
//				//db.query(sql, username, password);
//				try{
////				connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "1994");
//				connection.setSchema("New in Town");
//				prestat=connection.prepareStatement(sql);
//				prestat.setString(1, user.getText());
//				String password = null;
//				password = String.valueOf(pass.getPassword());
//				prestat.setString(2,password);
//				result=prestat.executeQuery();
//				if(result.next())
//				{
//				JOptionPane.showMessageDialog(null,"YOU LOGGED IN");
//				 
//				}
//				else
//				{
//				JOptionPane.showMessageDialog(null, "username OR password is not correct");
//				}
//				}
//				catch(SQLException ex)
//				{
//				JOptionPane.showMessageDialog(null,ex);
//				}
				
		
		pass = new JPasswordField();
		
		lblPassword_1 = new JLabel("Password:");
		lblPassword_1.setForeground(new Color(255, 204, 51));
		lblPassword_1.setFont(new Font("Bookman Old Style", Font.BOLD | Font.ITALIC, 15));
		
		btnSignUp = new JButton("Sign Up");
		btnSignUp.setFont(new Font("Stencil", Font.ITALIC, 13));
		btnSignUp.setBackground(new Color(192, 192, 192));
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("bye");
				frmNewInTown.setVisible(false);
				ctrlr.goToSignUp();
			}
		});
		
		lblOr = new JLabel("OR");
		lblOr.setFont(new Font("Traditional Arabic", Font.ITALIC, 14));
		GroupLayout groupLayout = new GroupLayout(frmNewInTown.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(61, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 95, GroupLayout.PREFERRED_SIZE))
					.addGap(30)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(user, Alignment.LEADING)
						.addComponent(pass, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
					.addGap(122))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(201)
					.addComponent(lblOr)
					.addContainerGap(238, Short.MAX_VALUE))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(178)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(btnLogIn, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSignUp, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addContainerGap(203, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(51)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(user, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblUsername, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 45, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblPassword_1, GroupLayout.PREFERRED_SIZE, 36, GroupLayout.PREFERRED_SIZE)
						.addComponent(pass, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(27)
					.addComponent(btnLogIn)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lblOr)
					.addGap(12)
					.addComponent(btnSignUp, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
					.addGap(12))
		);
		frmNewInTown.getContentPane().setLayout(groupLayout);
	}
	
//	private class LoginGuiButtonListerners implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			if(e.getSource() == btnLogIn) {
//				System.out.println("hi");
//				String username = user.getText();
//				String password = String.valueOf(pass.getPassword());
//				if(!(username.length() == 0 || password.length() == 0)) {
//					try {
//						ctrlr.userInfoIsCorrect(username, password);
//						
//					} catch (SQLException e1) {
//						e1.printStackTrace();
//					}
//				} else {
//					showMessage("info is missing");
//				}
//			} 
//			if(e.getSource() == btnSignUp) {
//				System.out.println("bye");
//
//				ctrlr.goToSignUp();
//			}
//		}
//		
//	}
	
}

