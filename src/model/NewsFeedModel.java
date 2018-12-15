package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import shared.Event;
import shared.NewsFeedItem;
import shared.Status;

public class NewsFeedModel {
	
	private ArrayList<Status> statuses;
	private ArrayList<Event> events;
	private ArrayList<NewsFeedItem> items;
	
	public NewsFeedModel() {
		statuses = new ArrayList<Status>();
		events = new ArrayList<Event>();
		items = new ArrayList<NewsFeedItem>();
		sortNewsFeed();
	}
	
	public void sortNewsFeed() {
		Collections.sort(items, new Comparator<NewsFeedItem>() {
			public int compare(NewsFeedItem o1, NewsFeedItem o2) {
				if(o1.getTime().isBefore(o2.getTime())) {
					return -1;
				} else return 1;
			}
		});
	}
	
	public void setNewsFeed(ArrayList<Status> statuses, ArrayList<Event> events) {
		this.statuses = statuses;
		this.events = events;
		this.items = new ArrayList<NewsFeedItem>();
		items.addAll(statuses);
		items.addAll(events);
		sortNewsFeed();
	}
	
	public void setNewsFeed(Status[] statuses, Event[] events) {
		for(int i = 0; i < statuses.length ; i++) {
			this.statuses.add(statuses[i]);
		}
		for(int i = 0 ; i < events.length ; i++) {
			this.events.add(events[i]);
		}
		this.items = new ArrayList<NewsFeedItem>();
		items.addAll(this.statuses);
		items.addAll(this.events);
		sortNewsFeed();
	}
	
	
	
	public int size() {
		return items.size();
	}
	
	public String toString() {
		String s = "";
		for(int i = 0 ; i < events.size(); i++) {
			s += events.get(i) + "\n";
		}
		for(int i = 0 ; i < statuses.size(); i++) {
			s += statuses.get(i) + "\n";
		}
		return s;
	}
	
	public NewsFeedItem getItem(int i) {
		return items.get(i);
	}
	
	public NewsFeedItem getItem(String name) {
		for (int i = 0 ; i < items.size() ; i++) {
			if(items.get(i).getName().equals(name)) {
				return items.get(i);
			}
		}
		return null;
	}
	
}
