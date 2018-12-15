package shared;

import java.io.Serializable;

public class Message implements Serializable{
	private String body;
	private User from;
	private User to;
	
	public String getBody() {
		return body;
	}

	public User getFrom() {
		return from;
	}

	public User getTo() {
		return to;
	}

	public Message(User from, User to, String body) {
		this.from = from;
		this.to = to;
		this.body = body;
	}
}
