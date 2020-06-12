package com.team.juseom.controller;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;
import com.team.juseom.service.AuctionFormValidator;
import com.team.juseom.service.InsertSearchService;
import com.team.juseom.service.JuseomFacade;
import com.team.juseom.service.SaleFormValidator;
import com.team.juseom.service.ShareFormValidator;

@Controller
@SessionAttributes({"sale", "auction", "share"})
public class InsertBookContorller {
	
	@Autowired
	JuseomFacade juseomFacade;
	
	@ModelAttribute("sale")
	public Sale formData(HttpServletRequest request) {
		Sale s = new Sale();
		Book b = new Book();
		b.setCondition("중");
		s.setBook(b);
		return s;
	}
	
	@ModelAttribute("auction")
	public Auction formData2() {
		Auction a = new Auction();
		Book b = new Book();
		b.setCondition("중");
		a.setBook(b);
		return a;
	}
	
	@ModelAttribute("share")
	public Share formData3() {
		Share s = new Share();
		Book b = new Book();
		b.setCondition("중");
		s.setBook(b);
		return s;
	}
	
	@ModelAttribute("conditionCodes")
	protected List<String> referenceData() throws Exception{
		List<String> conditionCodes = new ArrayList<String>();
		conditionCodes.add("최상");
		conditionCodes.add("상");
		conditionCodes.add("중");
		conditionCodes.add("하");
		return conditionCodes;
	}
	
	@GetMapping("/insert/book.do")
	public String insertBook(
			@RequestParam("isbn") String isbn,
			Model model) {
		String text = null;
		try {
			text = URLEncoder.encode(isbn, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패",e);
		}
		String apiURL = "https://openapi.naver.com/v1/search/book_adv.xml?d_isbn="+ text; // xml 결과

		String responseBody = InsertSearchService.get(apiURL);
		List<BookRegiRequest> bookList = InsertSearchService.parse(responseBody);
		model.addAttribute("bookRegi", bookList.get(0));
		return "InsertBook";
	}
	
	@PostMapping("/insert/sale.do")
	public String insertSale(
			@ModelAttribute("sale") Sale formData, 
			BindingResult result, 
			SessionStatus sessionStatus,
			Model model) {
		new SaleFormValidator().validate(formData, result);	
		if (result.hasErrors()) {
			model.addAttribute("book", formData.getBook());
			model.addAttribute("s_choice", "write");
			return "InsertBook";
		}
		//userId 구현 전까지 임시 코드
		formData.getBook().setUserId("1");
		juseomFacade.insertSale(formData);
		sessionStatus.setComplete();
		return "index";	
	}
	
	@PostMapping("/insert/auction.do")
	public String insertAuction(
			@ModelAttribute("auction") Auction formData, 
			BindingResult result, 
			SessionStatus sessionStatus,
			Model model) {
		new AuctionFormValidator().validate(formData, result);	
		if (result.hasErrors()) {
			model.addAttribute("book", formData.getBook());
			model.addAttribute("a_choice", "write");
			return "InsertBook";
		}
		//userId 구현 전까지 임시 코드
		formData.getBook().setUserId("1");
		juseomFacade.insertAuction(formData);
		sessionStatus.setComplete();
		return "index";	
	}

	@PostMapping("/insert/share.do")
	public String insertShare(
			@ModelAttribute("share") Share formData, 
			BindingResult result, 
			SessionStatus sessionStatus,
			Model model) {
		new ShareFormValidator().validate(formData, result);	
		if (result.hasErrors()) {
			model.addAttribute("book", formData.getBook());
			model.addAttribute("sh_choice", "write");
			return "InsertBook";
		}
		//userId 구현 전까지 임시 코드
		formData.getBook().setUserId("1");
		juseomFacade.insertShare(formData);
		sessionStatus.setComplete();
		return "index";	
	}	
	
}