package com.team.juseom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Book;
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
			@RequestParam("categoryId") String categoryId,
			ModelMap model) throws Exception {
		if(categoryId == "1") {
			Sale sale = this.juseom.getSale(bookId);
			model.put("book", sale.getBook());
			model.put("detail", sale);
		}else if(categoryId == "2") {
			Share share = this.juseom.getShare(bookId);
			model.put("book", share.getBook());
			model.put("detail", share);
		}else {
			Auction auction = this.juseom.getAuction(bookId);
			model.put("book", auction.getBook());
			model.put("detail", auction);
		}
		return "Book";
	}
}
