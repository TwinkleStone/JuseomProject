package com.team.juseom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.team.juseom.domain.Rate;
import com.team.juseom.service.JuseomFacade;

@Controller
public class InsertRatingController {
	
	@Autowired
	private JuseomFacade juseomFacade;
	
	@ModelAttribute("rate")
	public Rate createRateForm(
			@RequestParam("ratedId") String ratedId,
			@RequestParam("raterId") String raterId,
			@RequestParam("bookId") int bookId) {
		Rate rate = new Rate();
		rate.setBookId(bookId);
		rate.setRatedId(ratedId);//별점이 매겨지는 사람
		rate.setRaterId(raterId);//별점을 매기는 사람
		return rate;
	}

	@GetMapping("/rating.do")
	public String rateForm() {
		return "RatingForm";
	}
	
	@PostMapping("/rating.do")
	public String insertRate(
			@ModelAttribute("rate") Rate formData,
			BindingResult result) {
		
		juseomFacade.insertRate(formData);

		return "RatingConfirm";
	}
	
}