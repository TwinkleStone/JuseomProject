package com.team.juseom.controller;

import java.io.Serializable;

import javax.validation.Valid;

import org.springframework.web.multipart.MultipartFile;

import com.team.juseom.domain.User;
@SuppressWarnings("serial")
public class UserForm implements Serializable {
	@Valid
	private User user;
	private MultipartFile report;
	
	private String confirmPassword;
	
	public UserForm(User user) {
		this.user = user;
	}
	
	public UserForm() {
		this.user = new User();
	}
	
	public User getUser() {
		return user;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public MultipartFile getReport() {
		return report;
	}

	public void setReport(MultipartFile report) {
		this.report = report;
	}	
}
