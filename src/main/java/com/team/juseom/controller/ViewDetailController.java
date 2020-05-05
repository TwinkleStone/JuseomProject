package com.team.juseom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;
import com.team.juseom.service.JuseomFacade;

@Controller
public class ViewDetailController {
	private JuseomFacade juseom;
	
	@Autowired
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}

	@RequestMapping("/shop/viewBook.do")
	public String handleRequest(
			@RequestParam("bookId") String bookId,
			@RequestParam("category") String category,
			ModelMap model) throws Exception {
		if(category.equals("sale")) {
			Sale sale = this.juseom.getSale(bookId);
			model.put("sale", sale);
		}else if(category.equals("auction")) {
			Auction auction = this.juseom.getAuction(bookId);
			model.put("auction", auction);
		}else {
			Share share = this.juseom.getShare(bookId);
			model.put("share", share);
		}
		model.put("category", category);
		return "Book";
	}
}
