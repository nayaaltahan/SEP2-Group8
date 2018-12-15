package shared;

import java.util.ArrayList;


public class ProxyUser implements User{

	private UserInfo info;
	private RealUser realUser;
	private String userName;
	private ArrayList<Interest> interests;
	
	public ProxyUser(String username) {
		this.userName = username;
	}

	@Override
	public UserInfo getUserInformation() {
		// check instead of information alraed is oloaded
		if(realUser == null) {
			realUser =  new RealUser(userName);
		}
		if(info == null) {
			info = realUser.getUserInformation();
		}
		return info;
	}

	@Override
	public String getUserName() {
		return userName;
	}
	
	public String toString() {
		return userName;
	}

	@Override
	public ArrayList<Interest> getUserInterest() {
		if(realUser == null) {
			realUser =  new RealUser(userName);
		}
		if(interests == null) {
			interests = realUser.getUserInterest();
		}
		return interests;
	}
	
}
