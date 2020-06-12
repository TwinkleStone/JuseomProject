package com.team.juseom.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team.juseom.domain.Auction;

public class AuctionFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Auction.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//Auction 타입으로 변환
		Auction auction = (Auction) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "startPrice", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "salesNumber", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "required");
		
		
		String price = auction.getStartPrice();
		if(!price.matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("startPrice", "notPrice");
		}
		String number = auction.getSalesNumber();
		if(!number.matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("salesNumber", "notNumber");
		}
		String time = auction.getEndTime();
		if(!time.matches("^[0,1]?\\d{1}\\/(([0-2]?\\d{1})|([3][0,1]{1}))\\/(([1]{1}[9]{1}[9]{1}\\d{1})|([2-9]{1}\\d{3})) ([1-9]|1[0-2]|0[1-9]){1}(:[0-5][0-9] [aApP][mM]){1}$")) {
			errors.rejectValue("endTime", "notTime");
		}else if(auction.isEndTimeBeforeNow()) {
			errors.rejectValue("endTime", "badEndTime");
		}
	}

}
