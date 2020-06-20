package com.team.juseom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import com.team.juseom.domain.Bidder;
import com.team.juseom.service.JuseomFacade;

@Controller
@SessionAttributes("bidderList")
public class ViewBidderController {
	
	private JuseomFacade juseom;
	
	@Autowired
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@RequestMapping("/bidderList.do")
	public String getBidderList(
			@RequestParam("auctionId") String auctionId,
			Model model) {
		
		List<Bidder> bidders = juseom.getBidderListByauctionId(auctionId);
		model.addAttribute("bidderList", bidders);
					
		return "BidderList";
	}
	
	
}
