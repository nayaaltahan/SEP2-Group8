package test;

import java.rmi.RemoteException;

import client.Client;
import shared.ProxyUser;

public class mariaClientTest {
	public static void main(String[] args) {
		Client client = new Client();
		client.setClient("daniela_");
		try {
			System.out.println(client.getUserInfo(new ProxyUser("qwert")).getCity());
			System.out.println(client.getUsers());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
