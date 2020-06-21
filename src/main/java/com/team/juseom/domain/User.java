package com.team.juseom.domain;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("serial")
public class User implements Serializable {
	
	@NotEmpty(message="NotEmpty") @Email
	String userId;
	@NotEmpty(message="NotEmpty") @Size(min=6, max=20, message="Size.password")
	String password;
	@NotEmpty(message="NotEmpty") @Size(max=20)
	String name;
	@NotEmpty(message="NotEmpty") @Pattern(regexp = "01[01679]-\\d{3,4}-\\d{4}", message="Pattern.phone")
	String phone;
	@NotEmpty(message="NotEmpty") @Size(max=20)
	String commName;
	String profilePicUrl;
	@NotEmpty(message="NotEmpty")
	String address1;
	@NotEmpty(message="NotEmpty")
	String address2;
	@NotEmpty(message="NotEmpty")
	String address3;
	
	public User(String userId, String password, String name, String phone, String commName, String profilePicUrl,
			String address1, String address2, String address3) {
		super();
		this.userId = userId;
		this.password = password;
		this.name = name;
		this.phone = phone;
		this.commName = commName;
		this.profilePicUrl = profilePicUrl;
		this.address1 = address1;
		this.address2 = address2;
		this.address3 = address3;
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

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getAddress3() {
		return address3;
	}

	public void setAddress3(String address3) {
		this.address3 = address3;
	}
	
}
