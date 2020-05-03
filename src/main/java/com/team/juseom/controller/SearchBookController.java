package com.team.juseom.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team.juseom.domain.Book;
import com.team.juseom.service.BookStoreFacade;



public class SearchBookController {
	private BookStoreFacade book;

	@Autowired
	public void setBook(BookStoreFacade book) {
		this.book = book;
	}

	@RequestMapping("/shop/searchBooks.do")
	public ModelAndView handleRequest(HttpServletRequest request,
			/*@RequestParam(value="keyword", required=false)*/ Book searchBook,
			@RequestParam(value="page", required=false) String page
			) throws Exception {
		if (searchBook != null) {
			/*if (!StringUtils.hasLength(keyword)) {
				return new ModelAndView("Error", "message", "Please enter a keyword to search for, then press the search button.");
			}*/
			PagedListHolder<Book> bookList = new PagedListHolder<Book>(this.book.searchBookList(searchBook));
			bookList.setPageSize(4);
			request.getSession().setAttribute("SearchBooksController_bookList", bookList);
			return new ModelAndView("SearchBooks", "bookList", bookList);
		}
		else {
			@SuppressWarnings("unchecked")
			PagedListHolder<Book> bookList = (PagedListHolder<Book>)request.getSession().getAttribute("SearchBooksController_bookList");
			if (bookList == null) {
				return new ModelAndView("Error", "message", "Your session has timed out. Please start over again.");
			}
			if ("next".equals(page)) {
				bookList.nextPage();
			}
			else if ("previous".equals(page)) {
				bookList.previousPage();
			}
			return new ModelAndView("SearchBooks", "bookList", bookList);
		}
	}
}