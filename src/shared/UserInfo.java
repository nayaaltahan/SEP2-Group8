package shared;

import java.io.Serializable;

import databaseaccessing.UserInfoHandler;

public class UserInfo implements Serializable{
	private String fname;
	private String lname;
	private String birthdate;
	private int phone;
	private String gender;
	private String nationality;
	private String username;
	private String password;
	private String city;

	public UserInfo(String fname, String lname, String birthdate, int phone, String gender,
			String nationality, String username, String password, String city) {
		super();
		this.fname = fname;
		this.lname = lname;
		this.birthdate = birthdate;
		this.phone = phone;
		this.gender = gender;
		this.nationality = nationality;
		this.username = username;
		this.password = password;
		this.city = city;
	} 
	
	public UserInfo() {
		super();
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}


	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	@Override
	public String toString() {
		return "UserInfo [fname=" + fname + ", lname=" + lname + ", birthdate=" + birthdate + ", phone=" + phone
			 + ", gender=" + gender + ", nationality=" + nationality + ", username=" + username
				+ ", password=" + password + ", city=" + city + "]";
	}

	public String getAbout() {
	
		return fname+" "+lname + " is " + nationality + ", "+ "\n"+fname+" currently lives in " + city;
	}

}
