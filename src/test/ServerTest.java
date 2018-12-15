package test;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.sql.SQLException;
import java.sql.Timestamp;

import controller.IController;
import databaseaccessing.EditInfoHandler;
import databaseaccessing.EventHandler;
import databaseaccessing.IEditData;
import databaseaccessing.IEventData;
import databaseaccessing.IInterest;
import databaseaccessing.ILoginData;
import databaseaccessing.IMessagesData;
import databaseaccessing.IUserData;
import databaseaccessing.InterestAdapter;
import databaseaccessing.LoginHandler;
import databaseaccessing.MessagesHandler;
import databaseaccessing.UserInfoHandler;

public class ServerTest {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			IMessagesData sMessages = new MessagesHandler();
			IEventData sEvent = new EventHandler(sMessages);
			IEditData sEdit = new EditInfoHandler();
			

			
			Naming.bind("sEvent", sEvent);
			Naming.bind("sMessages", sMessages);
			
			Naming.bind("sEdit", sEdit);
			IUserData sUser = UserInfoHandler.getInstance();	
			ILoginData sLogIn = new LoginHandler();
			IInterest sInterest = new InterestAdapter();
			
			Naming.bind("sUser", sUser);
			Naming.bind("sLogIn", sLogIn);
			Naming.bind("sInterest", sInterest);
			
			System.out.println("Server connected");
			System.out.println(Timestamp.valueOf("2018-1-1 1:1:0"));
			
		} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
