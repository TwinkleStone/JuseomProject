package com.team.juseom.controller;

import javax.validation.Valid;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Max;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.team.juseom.domain.Address;
import com.team.juseom.domain.User;

public class MemberCommand {
	@NotEmpty @Email
	private String userId;
	@NotEmpty	
	private String name;
	@NotEmpty @Size(min=6)
	private String password;
	@NotEmpty
	private String confirmPassword;
	@NotEmpty @Pattern(regexp = "01[01679]-\\d{3,4}-\\d{4}")
	private String phone;
	private String commName;
	private String profilePicUrl;
	@Valid
	private Address address;
	
	//@AssertTrue
	//private boolean samePasswordConfirmPassword;
		
	public MemberCommand(User mi) {
		if (mi != null) {
			userId = mi.getUserId();  
			name = mi.getName();  
			password = mi.getPassword();
			confirmPassword = null;
			phone = mi.getPhone();
			address = mi.getAddress();
			commName = mi.getCommName();
			profilePicUrl = mi.getProfilePicUrl();
		}
	}

	public MemberCommand() {}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	@AssertTrue
	public boolean isSamePasswordConfirmPassword() {
		if (password == null || confirmPassword == null)
			return false;
		return password.equals(confirmPassword);
	}

	public boolean hasPassword() {
		return password != null && password.trim().length() > 0;
	}

	@Override
	public String toString() {
		return "MemberCommand [userId=" + userId + ", name=" + name + ", password=" + password + ", confirmPassword=" + confirmPassword
				+ ", phone=" + phone + ", address=" + address + ", commName=" + commName + "profilePicUrl=" + profilePicUrl + "]";
	}

}
