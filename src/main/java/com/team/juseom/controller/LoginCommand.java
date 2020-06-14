package com.team.juseom.controller;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginCommand {

	@NotEmpty	
	private String userId;
	@NotEmpty	
	private String password;
	private String forwardAction;
	
	public LoginCommand() {}
	
	public LoginCommand(String userId) {
		this.userId = userId;
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

	public String getForwardAction() {
		return forwardAction;
	}

	public void setForwardAction(String forwardAction) {
		this.forwardAction = forwardAction;
	}

}
