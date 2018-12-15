package shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Event implements Serializable, NewsFeedItem {
	private String name;
	private MyDate time;
	private String address;
	private String city;
	private ArrayList<Interest> categories;
	private ArrayList<User> admins;
	private ArrayList<User> invitees;
	private boolean isPrivate;

	public Event(String name, MyDate time, String address, String city, List<Interest> categories, List<User> admins,
			List<User> invitees, boolean isPrivate) {
		this.name = name;
		this.time = time;
		this.address = address;
		this.city = city;
		this.categories = new ArrayList<Interest>();
				this.categories.addAll(categories);
		this.admins = new  ArrayList<User>();
		this.admins.addAll(admins);
		this.isPrivate = isPrivate;
		this.invitees = new ArrayList<User>();
		this.invitees.addAll(invitees);
		
	}

	public User[] getAdmins() {
		User[] array = null;
		return admins.toArray(array);
	}

	public int getNumberOfAdmins() {
		return admins.size();
	}

	public int getNumberOfCategories() {
		return categories.size();
	}

	public void setTime(MyDate date) {
		this.time = date;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public MyDate getTime() {
		return time;
	}

	public String getAddress() {
		return address;
	}

	public Interest[] getCategories() {
		return (Interest[]) categories.toArray();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "Event name: " + name + "\nTime of event: " + time + "\nAddress: " + address + "\nCategories: "
				+ getList(categories) + "\nAdmins: " + getList(admins) + "\nInvitees: " + getList(invitees)
				+ "\nPrivate: " + isPrivate;
	}

	@Override
	public String getText() {
		return "Event name: " + name + "\nTime of event: " + time + "\nAddress: " + address + "\nCategories: "
				+ getList(categories);
	}

	public String getList(ArrayList array) {
		String s = "";
		if (array.size() > 0) {
			s = array.get(0).toString();
			for (int i = 1; i < array.size(); i++) {
				s += ", " + array.get(i).toString();
			}
		}
		return s;
	}
	
	public void addAdmin(User user) {
		admins.add(user);
	}
	
	public boolean isPrivate() {
		return isPrivate;
	}
	
	public int getNumberOfInvitees() {
		return invitees.size();
	}
	
	public User[] getInvitees() {
		return (User[]) invitees.toArray();
	}

}
