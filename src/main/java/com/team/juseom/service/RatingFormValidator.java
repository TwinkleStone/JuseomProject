package com.team.juseom.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team.juseom.domain.Rate;

public class RatingFormValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Rate.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		Rate rate = (Rate) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required");	
		
		String description = rate.getDescription();
		
		if (description.length() > 50) {
			errors.rejectValue("description", "tooLong");
		}
		
	}

}
