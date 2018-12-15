package test;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import databaseaccessing.IUserData;
import databaseaccessing.UserInfoHandler;

public class mariaTest {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			IUserData sUser = UserInfoHandler.getInstance();
			Naming.bind("sUser", sUser);
			System.out.println("Server started");
		} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
