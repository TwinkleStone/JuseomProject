package com.team.juseom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.Bidder;
import com.team.juseom.service.JuseomFacade;

@Controller
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

	
	@RequestMapping("/bidder/delete.do")
	public String deleteBidder(
			@RequestParam("auctionId") String auctionId,
			@RequestParam("userId") String userId,
			HttpServletResponse response,
			HttpServletRequest request) {
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String sessionUserId = userSession.getUser().getUserId();
		
		if (sessionUserId.equals(userId)) {
			juseom.removeBidder(userId, auctionId);
		} else {
			System.out.println("다른 사람의 입찰은 삭제 불가");
		}
		
		return "redirect:/auction.do";
	}
	
}
