package test;

import java.rmi.RemoteException;

import databaseaccessing.InterestAdapter;
import model.InterestList;
import shared.Interest;

public class InterestsTest {public static void main(String[] args) {
	InterestAdapter adapter = new InterestAdapter();
	InterestList list = new InterestList();
	Interest a = new Interest("Gaming");
	try {
		list = adapter.getAllInterests();
		adapter.addChosenInterestsIntoUserInterestTable("qwert", list);
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	
	
}