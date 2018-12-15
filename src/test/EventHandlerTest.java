package test;

import java.rmi.RemoteException;
import java.util.ArrayList;

import databaseaccessing.EventHandler;
import shared.Event;

public class EventHandlerTest {
	public static void main(String[] args) {
		EventHandler e = new EventHandler(null);
		ArrayList<Event> events;
		try {
			events = e.getEventsByUsername("naya_all");
			for (int i = 0; i < events.size(); i++) {
				System.out.println(events.get(i));
			}
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
