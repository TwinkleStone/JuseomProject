package com.team.juseom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team.juseom.domain.Book;
import com.team.juseom.domain.Category;
import com.team.juseom.service.JuseomFacade;

/*
@Controller
@SessionAttributes({"category", "bookList"})
public class ViewCategoryController { 
	private JuseomFacade juseom;

	@Autowired
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@RequestMapping("/shop/viewCategory.do")
	public String handleRequest(
			@RequestParam("categoryId") String categoryId,
			ModelMap model
			) throws Exception {
		Category category = this.juseom.getCategory(categoryId);
		PagedListHolder<Book> bookList = new PagedListHolder<Book>(this.juseom.getBookListByCategory(categoryId));
		bookList.setPageSize(4);
		model.put("category", category);
		model.put("bookList", bookList);
		return "Category";
	}

	@RequestMapping("/shop/viewCategory2.do")
	public String handleRequest2(
			@RequestParam("page") String page,
			@ModelAttribute("category") Category category,
			@ModelAttribute("bookList") PagedListHolder<Book> bookList,
			BindingResult result) throws Exception {
		if (category == null || bookList == null) {
			throw new IllegalStateException("Cannot find pre-loaded category and book list");
		}
		if ("next".equals(page)) { bookList.nextPage(); }
		else if ("previous".equals(page)) { bookList.previousPage(); }
		return "Category";
	}
}*/