package shared;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface User extends Serializable {
	public UserInfo getUserInformation();
	public String getUserName();
	public ArrayList<Interest> getUserInterest();
}
