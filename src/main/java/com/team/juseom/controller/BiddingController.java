package com.team.juseom.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.team.juseom.domain.Bidder;
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
			Model model) {
		
		formData.setUserId("qwerty"); //가데이터
		
		juseomFacade.insertBidder(formData);
		
		return "redirect:/index";
		//return "BidderList";
	}
	
	
	
}