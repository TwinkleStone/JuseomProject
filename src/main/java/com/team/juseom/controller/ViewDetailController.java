package com.team.juseom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@RequestMapping("/view/sale.do")
	public String viewSale(@RequestParam("id") String saleId,
			Model model) {
		Sale s = juseom.getSale(saleId);
		model.addAttribute("sale", s);
		return "SaleDetail"; //상세정보 view로 이동
	}
	
	@RequestMapping("/view/auction.do")
	public String viewAuction(@RequestParam("id") String auctionId,
			Model model) {
		Auction a = juseom.getOneAuction(auctionId);
		model.addAttribute("auction", a);
		return "AuctionDetail"; //상세정보 view로 이동
	}
	
	@RequestMapping("/view/share.do")
	public String viewShare(@RequestParam("id") String shareId,
			Model model) {
		Share s = juseom.getShare(shareId);
		model.addAttribute("share", s);
		return "ShareDetail"; //상세정보 view로 이동
	}
}
