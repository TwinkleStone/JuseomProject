package com.team.juseom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.team.juseom.domain.Rate;
import com.team.juseom.service.JuseomFacade;

@Controller
public class InsertRatingController {
	
	@Autowired
	private JuseomFacade juseomFacade;
	
	@ModelAttribute("rate")
	public Rate createRateForm() {
		return new Rate();
	}

	@GetMapping("/rate.do")
	public String rateForm() {
		return "RateForm";
	}
	
	@PostMapping("/rate.do")
	public String insertRate(
			@ModelAttribute("rate") Rate formData,
			BindingResult result,
			SessionStatus sessionStatus,
			Model model) {
		formData.setRatedId("qwerty"); //일단 임시 데이터를 넣어놓음. 추후 userId를 가져올 것.
		formData.setRaterId("test"); // 일단 임시 데이터를 놓어놓음.
		juseomFacade.insertRate(formData);
		
		sessionStatus.setComplete();
		return "TestRating";
	}
	
}