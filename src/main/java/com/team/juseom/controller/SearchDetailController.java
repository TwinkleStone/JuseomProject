package com.team.juseom.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;

import com.team.juseom.domain.Book;
import com.team.juseom.service.JuseomFacade;

@Controller
public class SearchDetailController {
	private static final String FORM_VIEW = "searchDetail";
	
	@Autowired
	private JuseomFacade juseom;
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@GetMapping("/search.do")		// step1 요청
	public String step1(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(model.getAttribute("searchList") != null) {
			model.remove("searchList");
		}
		if(session.getAttribute("searchList") != null) {
			session.removeAttribute("searchList");
		}
		return FORM_VIEW;	// step1 form view로 이동
	}
	
	@PostMapping(value="/search/detail.do")
	public String searchDetail(HttpServletRequest request, HttpSession session,
			@RequestParam("keyword") String keyword,
			@RequestParam("lowPrice") String lowPrice,
			@RequestParam("highPrice") String highPrice,
			@RequestParam("tradeType") String tradeType,
			Model model) throws Exception {
		String text = null;
		try {
			text = URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패",e);
		}
		if (lowPrice.equals("")) {
			lowPrice = "0";
		}
		if (highPrice.equals("")) {
			highPrice = "999999999";
		}
		if (tradeType == null) {
			tradeType = "";
		}
		
		List<SearchDetailResult> rslt = new ArrayList<SearchDetailResult>();
		List<Book> list = juseom.searchBookDetail(keyword, lowPrice, highPrice, tradeType);
		for (int i=0; i < list.size(); i++) {
			SearchDetailResult r = new SearchDetailResult();
			r.setTradeType(list.get(i).getTradeType());
			String tradeId;
			String bookId = Integer.toString(list.get(i).getBookId());
			if (r.getTradeType().equals("판매")) {
				tradeId = juseom.getSaleIdByBookId(bookId);
				r.setTradeId(tradeId);
			}
			else if (r.getTradeType().equals("나눔")) {
				tradeId = juseom.getShareIdByBookId(bookId);
				r.setTradeId(tradeId);
			}
			else if (r.getTradeType().equals("경매")){
				tradeId = juseom.getAuctionIdByBookId(bookId);
				r.setTradeId(tradeId);
			}
			
			r.setBook(list.get(i));
			rslt.add(r);
		}
		PagedListHolder<SearchDetailResult> rsltList = new PagedListHolder<SearchDetailResult>(rslt);
		model.addAttribute("keyword", keyword);
		model.addAttribute("lowPrice", lowPrice);
		model.addAttribute("highPrice", highPrice);
		model.addAttribute("tradeType", tradeType);
		model.addAttribute("searchList", rsltList);
		return FORM_VIEW;
	}
}
