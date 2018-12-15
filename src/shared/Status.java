package shared;

import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;

public class Status implements Serializable, NewsFeedItem {
	private User sender;
	private String body;
	private MyDate timeSent;

	public Status(User sender, String body) {
		this.sender = sender;
		this.body = body;
	}

	public Status(User sender, String body, MyDate timeSent) {
		this.sender = sender;
		this.body = body;
		this.timeSent = timeSent;
	}

	public User getSender() {
		return sender;
	}

	public String getBody() {
		return body;
	}

	public MyDate getTime() {
		return timeSent;
}

	@Override
	public String getName() {
		return sender.getUserName();
	}

	@Override
	public String getText() {
		return getBody();
	}
}
