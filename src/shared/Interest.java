package shared;

import java.io.Serializable;

public class Interest implements Serializable{
	private String interest;
	
	public Interest(String name) {
		this.interest = name;
	}

	public String getName() {
		return interest;
	}
	
	public String toString() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
}

