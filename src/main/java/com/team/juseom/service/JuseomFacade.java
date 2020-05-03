package com.team.juseom.service;

import java.util.List;

import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.Category;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;
import com.team.juseom.domain.User;

public interface JuseomFacade {
	Book getBook(String bookId);
	Sale getSale(String bookId);
	Share getShare(String bookId);
	Auction getAuction(String bookId);
	Category getCategory(String categoryId);
	List<Book> getBookListByCategory(String categoryId);
	
	User getUser(String userId);
	User getUser(String userId, String password);
	void insertUser(User user);
	void updateUser(User user);
	List<String> getUserIdList();
	
	List<Book> searchBookList(Book book);
	List<Book> getBookListByTradeType(String tradeType);
}
