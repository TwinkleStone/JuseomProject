package com.team.juseom.controller;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Bidder;
import com.team.juseom.service.BiddingFormValidator;
import com.team.juseom.service.JuseomFacade;

@Controller
public class BiddingController {
	
	@Autowired
	JuseomFacade juseomFacade;
	
	@RequestMapping(value = "/bidding.do", method = RequestMethod.POST, produces="application/json")
	@ResponseBody
	public Auction insertBidder(@RequestBody Bidder bidder,
			HttpServletResponse response,
			HttpServletRequest request,
			BindingResult result,
			Model model) throws IOException {
			
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
		
		bidder.setUserId(userId);
		
		String salesNumber = juseomFacade.getSalesNubmer(bidder.getAuctionId());
		int startPrice = juseomFacade.getStartPrice(bidder.getAuctionId());
		
		if (bidder.getBidNumber() > Integer.parseInt(salesNumber)) {
			System.out.println("잘못된 bidNumber를 입력했습니다.");
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} else if (bidder.getBidNumber() == 0 || bidder.getBidPrice() == 0) {
			System.out.println("0 입력 불가");
			response.sendError(HttpServletResponse.SC_LENGTH_REQUIRED);
			return null;
		} else if (bidder.getBidPrice() < startPrice) {
			System.out.println("시작가보다 작은 값 입력 불가");
			response.sendError(HttpServletResponse.SC_CONFLICT);
			return null;
		}
		else {
			juseomFacade.insertBidder(bidder);
			System.out.println("입찰에 성공하였습니다.");
		}
		
		Auction auction = juseomFacade.getOneAuction(Integer.toString(bidder.getAuctionId()));
		
		return auction;
		
	}
}