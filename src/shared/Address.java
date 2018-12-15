package shared;

import java.io.Serializable;

public class Address implements Serializable{
	private String city;
	private String street;
	private String blockNo;
	
	public Address(String street, String blockNo, String city) {
		this.city = city;
		this.street = street;
		this.blockNo = blockNo;
	}


	public String getCity() {
		return city;
	}

	public String getStreet() {
		return street;
	}

	public String getBlockNo() {
		return blockNo;
	}

	@Override
	public String toString() {
		return street + " " + blockNo + ", " + city;
	}
	
	
}
