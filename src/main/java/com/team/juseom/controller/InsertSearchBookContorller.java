package com.team.juseom.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team.juseom.domain.Book;
import com.team.juseom.service.InsertSearchService;

@Controller
public class InsertSearchBookContorller {
	@ModelAttribute("book")
	public Book formData() {
		return new Book();
	}
	
	//get
	@RequestMapping("/shop/insert/search")
	public String insertBookStep1() {
		return "InsertSearch";
	}
	
	/*@RequestMapping(value="/shop/insertBook/step2", method=RequestMethod.GET)
	public String insertBookStep1ForHand() {
		return "bookInsertHand";
	}*/ //수동으로 입력 차후 추가
	
	//post
	@RequestMapping("/shop/insert/search")
	public String insertBookStep2(
			@RequestParam("name") String name,
			ModelMap model) {
		String clientId = "";
		String clientSecret = "";
		String text = null;
		try {
			text = URLEncoder.encode(name, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("검색어 인코딩 실패",e);
		}

		String apiURL = "https://openapi.naver.com/v1/search/book.xml?query="+ text; // xml 결과

		Map<String, String> requestHeaders = new HashMap<>();
		requestHeaders.put("X-Naver-Client-Id", clientId);
		requestHeaders.put("X-Naver-Client-Secret", clientSecret);
		String responseBody = InsertSearchService.get(apiURL,requestHeaders);
		PagedListHolder<Book> bookList = new PagedListHolder<Book>(InsertSearchService.parse(responseBody));
		bookList.setPageSize(4);
		model.put("searchList", bookList);
		return "InsertSearch";		
  }

}