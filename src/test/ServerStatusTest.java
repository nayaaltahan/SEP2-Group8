package test;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import databaseaccessing.IMessagesData;
import databaseaccessing.MessagesHandler;

public class ServerStatusTest {
	public static void main(String[] args) {
		try {
			LocateRegistry.createRegistry(1099);
			IMessagesData sMessages = new MessagesHandler();
			Naming.bind("sMessages", sMessages);
			System.out.println("Server started");

		} catch (RemoteException | MalformedURLException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
