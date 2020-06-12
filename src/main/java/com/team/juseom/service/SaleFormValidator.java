package com.team.juseom.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team.juseom.domain.Sale;

public class SaleFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Sale.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//Sale 타입으로 변환
		Sale sale = (Sale) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "suggestPrice", "required");	
		
		String price = sale.getSuggestPrice();
		if(!price.matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("suggestPrice", "notPrice");
		}
	}

}
