package com.team.juseom.service;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.team.juseom.domain.Share;

public class ShareFormValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Share.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object obj, Errors errors) {
		//Share 타입으로 변환
		Share share = (Share) obj;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "raffleTime", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "shareNumber", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "endTime", "required");
		
		String number = share.getShareNumber();
		if(!number.matches("-?\\d+(\\.\\d+)?")) {
			errors.rejectValue("shareNumber", "notNumber");
		}
		String time = share.getEndTime();
		if(!time.matches("^[0,1]?\\d{1}\\/(([0-2]?\\d{1})|([3][0,1]{1}))\\/(([1]{1}[9]{1}[9]{1}\\d{1})|([2-9]{1}\\d{3})) ([1-9]|1[0-2]|0[1-9]){1}(:[0-5][0-9] [aApP][mM]){1}$")) {
			errors.rejectValue("endTime", "notTime");
		}else if(share.isEndTimeBeforeNow()) {
			errors.rejectValue("endTime", "badEndTime");
		}
		String r_time = share.getRaffleTime();
		if(!r_time.matches("^[0,1]?\\d{1}\\/(([0-2]?\\d{1})|([3][0,1]{1}))\\/(([1]{1}[9]{1}[9]{1}\\d{1})|([2-9]{1}\\d{3})) ([1-9]|1[0-2]|0[1-9]){1}(:[0-5][0-9] [aApP][mM]){1}$")) {
			errors.rejectValue("raffleTime", "notTime");
		}else if(share.isRaffleTimeBeforeEndTime()) {
			errors.rejectValue("raffleTime", "badRaffleTime");
		}
	}

}
