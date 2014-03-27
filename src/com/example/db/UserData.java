package com.example.db;

public class UserData {

	// private variables
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	String name;
	String email;
	String password;

	public String getAccount_status() {
		return account_status;
	}

	public void setAccount_status(String account_status) {
		this.account_status = account_status;
	}

	public String getRole() {
		return Role;
	}

	public void setRole(String role) {
		Role = role;
	}

	String account_status;
	String Role;

	// Empty constructor
	public UserData() {

	}

	// constructor
	public UserData(int id, String name, String email, String password,
			String Role, String Account_status) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.account_status = account_status;
		this.Role = Role;
	}

	// constructor
	public UserData(String name, String email) {
		this.name = name;
		this.email = email;
	}

	// getting ID
	public int getID() {
		return this.id;
	}

	// setting id
	public void setID(int id) {
		this.id = id;
	}

	// getting name

	@Override
	public String toString() {
		return "UserInfo [name=" + name + ", email=" + email + "]";
	}

}
