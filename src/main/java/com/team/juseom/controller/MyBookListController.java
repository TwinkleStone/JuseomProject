package com.team.juseom.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.Book;
import com.team.juseom.service.JuseomFacade;

@Controller
public class MyBookListController {
private static final String FORM_VIEW = "UserBookList";
	
	@Autowired
	private JuseomFacade juseom;
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@RequestMapping(value="/user/saleList.do", method=RequestMethod.GET)
	public String searchDetail(HttpServletRequest request, HttpSession session,
			Model model) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
		List<Book> list = juseom.searchBookByUserId(userId);
		List<SearchDetailResult> rslt = new ArrayList<SearchDetailResult>();
		
		for (int i=0; i < list.size(); i++) {
			SearchDetailResult r = new SearchDetailResult();
			r.setTradeType(list.get(i).getTradeType());
			String type = r.getTradeType();
			String tradeId;
			String bookId = Integer.toString(list.get(i).getBookId());
			if (type != null) {
				if (type.equals("판매")) {
					tradeId = juseom.getSaleIdByBookId(bookId);
					r.setTradeId(tradeId);
					System.out.println(r.getTradeType() + ", " + tradeId);
				}
				else if (type.equals("나눔")) {
					tradeId = juseom.getShareIdByBookId(bookId);
					r.setTradeId(tradeId);
					System.out.println(r.getTradeType() + ", " + tradeId);
				}
				else if (type.equals("경매")){
					tradeId = juseom.getAuctionIdByBookId(bookId);
					r.setTradeId(tradeId);
					System.out.println(r.getTradeType() + ", " + tradeId);
				}
			}
			r.setBook(list.get(i));
			rslt.add(r);
		}
		
		
		PagedListHolder<SearchDetailResult> rsltList = new PagedListHolder<SearchDetailResult>(rslt);
		model.addAttribute("searchList", rsltList);
		return FORM_VIEW;
	}
}
