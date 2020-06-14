package com.team.juseom.domain;

public class User {
	
	String userId;
	String password;
	String name;
	String phone;
	String commName;
	String profilePicUrl;
	Address address;
	
	public User(String userId, String password, String name, String phone, String commName, String profilePicUrl,
			Address address) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.commName = commName;
		this.profilePicUrl = profilePicUrl;
		this.address = address;
	}
	
	public User() {
		// TODO Auto-generated constructor stub
	}

	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCommName() {
		return commName;
	}
	public void setCommName(String commName) {
		this.commName = commName;
	}
	public String getProfilePicUrl() {
		return profilePicUrl;
	}
	public void setProfilePicUrl(String profilePicUrl) {
		this.profilePicUrl = profilePicUrl;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}

}
