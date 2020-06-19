package com.team.juseom.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team.juseom.controller.UserForm;
import com.team.juseom.domain.User;
@Component
public class UserFormValidator {
	
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object obj, Errors errors) {
		UserForm userForm = (UserForm)obj;
		User user = userForm.getUser();
		
		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.id", "EMAIL_REQUIRED", "Email address is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.phone", "PHONE_REQUIRED", "Phone number is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.address1", "ADDRESS1_REQUIRED", "Address (1) is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.address2", "ADDRESS2_REQUIRED", "Address (2) is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.address3", "ADDRESS3_REQUIRED", "Address (3) is required.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.commName", "COMMNAME_REQUIRED", "CommName is required.");
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "user.userId", "USER_ID_REQUIRED", "User ID is required.");
		if (user.getPassword() == null || user.getPassword().length() < 1 ||
				!user.getPassword().equals(userForm.getConfirmPassword())) {
			errors.reject("PASSWORD_MISMATCH",
					"Passwords did not match or were not provided. Matching passwords are required.");
			}
	}
}
