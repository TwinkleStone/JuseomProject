package com.team.juseom.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.team.juseom.service.InsertSearchService;

@Controller
@SessionAttributes("searchList")
public class InsertSearchBookContorller {
	
	@GetMapping("/insert/search.do")
	public String insertSearch1(ModelMap model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(model.getAttribute("searchList") != null) {
			model.remove("searchList");
		}
		if(session.getAttribute("searchList") != null) {
			session.removeAttribute("searchList");
		}
		if(session.getAttribute("sale") != null) {
			session.removeAttribute("sale");
		}
		if(session.getAttribute("auction") != null) {
			session.removeAttribute("auction");
		}
		if(session.getAttribute("share") != null) {
			session.removeAttribute("share");
		}
		return "/InsertSearch";
	}
	
	@PostMapping("/insert/search.do")
	public String insertSearch2(
			@RequestParam("name") String name,
			Model model) {
		String text = null;
		try {
			text = URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패",e);
		}
		String apiURL = "https://openapi.naver.com/v1/search/book_adv.xml?d_titl="+ text; // xml 결과

		String responseBody = InsertSearchService.get(apiURL);
//		List<BookRegiRequest> bookList = InsertSearchService.parse(responseBody);
		PagedListHolder<BookRegiRequest> bookList = new PagedListHolder<BookRegiRequest>(InsertSearchService.parse(responseBody));
		bookList.setPageSize(9);
		model.addAttribute("search", name);
		model.addAttribute("searchList", bookList);
		return "InsertSearch";		
	}
	
	@RequestMapping("/insert/search2.do")
	public String handleRequest(
			@RequestParam("page") String page,
			@ModelAttribute("searchList") PagedListHolder<BookRegiRequest> bookList,
			BindingResult result) throws Exception {
		if (bookList == null) {
			throw new IllegalStateException("Cannot find pre-loaded category and product list");
		}
		if ("next".equals(page)) { bookList.nextPage(); }
		else if ("previous".equals(page)) { bookList.previousPage(); }
		return "InsertSearch";
	}

}