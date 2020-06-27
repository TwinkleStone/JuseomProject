package com.team.juseom.controller;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.Bidder;
import com.team.juseom.service.BiddingFormValidator;
import com.team.juseom.service.JuseomFacade;

@Controller
public class BiddingController {
	
	@Autowired
	JuseomFacade juseomFacade;
	
	@ModelAttribute("bidder")
	public Bidder viewBiddingForm() {
		Bidder bidder = new Bidder();
		return bidder;
	}

	@GetMapping("/bidding.do")
	public String biddingForm() {
		return "BiddingForm";
	}
	
	@PostMapping("/bidding.do")
	public String insertBidder(
			@ModelAttribute("bidder") Bidder formData,
			BindingResult result,
			HttpServletRequest request,
			Model model) {
		new BiddingFormValidator().validate(formData, result);
		
		if (result.hasErrors()) {
			return "BiddingForm";
		}
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
		
		formData.setUserId(userId);
		
		juseomFacade.insertBidder(formData);
		
		return "redirect:/auction.do";
	}
	
	
	
}