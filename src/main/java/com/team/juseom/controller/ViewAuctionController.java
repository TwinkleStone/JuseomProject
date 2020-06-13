package com.team.juseom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team.juseom.domain.Auction;
import com.team.juseom.service.JuseomFacade;

@Controller
@SessionAttributes("auctionList")
public class ViewAuctionController {
	
	private JuseomFacade juseom;
	
	@Autowired
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@RequestMapping("/auction.do")
	public String auctions(ModelMap model) {
		PagedListHolder<Auction> auctionList = new PagedListHolder<Auction>(this.juseom.getBookListByAuction());
		auctionList.setPageSize(9);
		model.put("auctionList", auctionList);
		return "AuctionList";	//AuctionList view로 이동
	}
	
	@RequestMapping("/auction2.do")
	public String handleRequest(
			@RequestParam("page") String page,
			@ModelAttribute("auctionList") PagedListHolder<Auction> auctionList,
			BindingResult result) throws Exception {
		if (auctionList == null) {
			throw new IllegalStateException("Cannot find pre-loaded category and product list");
		}
		if ("next".equals(page)) { auctionList.nextPage(); }
		else if ("previous".equals(page)) { auctionList.previousPage(); }
		return "AuctionList";
	}
}