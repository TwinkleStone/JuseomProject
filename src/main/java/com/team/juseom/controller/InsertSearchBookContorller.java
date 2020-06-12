package com.team.juseom.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team.juseom.service.InsertSearchService;

@Controller
public class InsertSearchBookContorller {
	
	@GetMapping("/insert/search.do")
	public String insertSearch1() {
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
		List<BookRegiRequest> bookList = InsertSearchService.parse(responseBody);
//		PagedListHolder<Book> bookList = new PagedListHolder<Book>(InsertSearchService.parse(responseBody));
//		bookList.setPageSize(4);
		model.addAttribute("search", name);
		model.addAttribute("searchList", bookList);
		return "InsertSearch";		
	}

}