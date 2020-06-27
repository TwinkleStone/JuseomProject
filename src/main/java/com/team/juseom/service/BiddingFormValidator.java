package com.team.juseom.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team.juseom.domain.Bidder;


public class BiddingFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Bidder.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		
		Bidder bidder = (Bidder) obj;
	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bidNumber", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "bidPrice", "required");
		
		if (bidder.getBidNumber() == 0) {
			errors.rejectValue("bidNumber", "notZero");
		}
		
		if (bidder.getBidPrice() == 0) {
			errors.rejectValue("bidPrice", "notZero");
		}
		
	}

}
