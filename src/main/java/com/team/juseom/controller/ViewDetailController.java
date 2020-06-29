package com.team.juseom.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Bidder;
import com.team.juseom.domain.Rate;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;
import com.team.juseom.domain.User;
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
			Model model,
			HttpServletRequest request) {
		Sale s = juseom.getSale(saleId);
		model.addAttribute("sale", s);	
		User u = juseom.getUserById(s.getBook().getUserId());
		List<Rate> rates = juseom.getRateListByUser(s.getBook().getUserId());
		List<Rate> lately = rates.subList(0, rates.size() < 5? rates.size() : 5);
		String avg = juseom.getAvgRate(s.getBook().getUserId());
		model.addAttribute("sellerName", u.getCommName());
		model.addAttribute("lately", lately);
		model.addAttribute("avg", avg);
		return "SaleDetail"; //상세정보 view로 이동
	}
	
	@RequestMapping("/view/auction.do")
	public String viewAuction(@RequestParam("id") String auctionId,
			Model model) {
		Auction a = juseom.getOneAuction(auctionId);
		
		Integer highBidPrice = juseom.getHighBidPrice(auctionId);
		
		if (highBidPrice == null)
			highBidPrice = Integer.parseInt(a.getStartPrice());

		a.setPresentPrice(highBidPrice);
		
		int bidNumber = juseom.getNowBidNumber(auctionId);
		a.setBidNumber(bidNumber);
		
		Bidder b = new Bidder();
		model.addAttribute("auction", a);
		model.addAttribute("bidder", b);
		User u = juseom.getUserById(a.getBook().getUserId());
		model.addAttribute("sellerName", u.getCommName());
		return "AuctionDetail"; //상세정보 view로 이동
	}
	
	@RequestMapping("/view/share.do")
	public String viewShare(@RequestParam("id") String shareId,
			Model model,
			HttpServletRequest request) {
		Share s = juseom.getShare(shareId);
		model.addAttribute("share", s);
		User u = juseom.getUserById(s.getBook().getUserId());
		model.addAttribute("sellerName", u.getCommName());
		return "ShareDetail"; //상세정보 view로 이동
	}
	
	@RequestMapping("/update/sale.do")
	@ResponseBody 
	public Sale updateSale(@RequestParam("bookId") String bookId,
			@RequestParam("saleId") String saleId,
			@RequestParam("price") String price,
			@RequestParam("detail") String detail,
			HttpServletResponse response) throws IOException {
		System.out.println(price);
		System.out.println(detail);
		if(price == null || price.isEmpty() || !price.matches("-?\\d+(\\.\\d+)?")) {
			response.sendError(404, "가격이 잘못 입력되었습니다.");
			return null;
		}else if(detail.length() > 100) {
			response.sendError(404, "상세설명은 최대 100글자입니다.");
			return null;
		}
		juseom.updateSale(bookId, saleId, price, detail);
		Sale sale = juseom.getSale(saleId);
		return sale;
	}
}
