package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;

public interface BookDao {
	void insertBook(Book book);
	void insertAuction(Auction auction);
	void insertSale(Sale sale);
	void insertShare(Share share);
	List<Book> getBookListByType(String type);
	Book getBook(String bookId);
	Sale getSale(String bookId);
	Share getShare(String bookId);
	Auction getAuction(String bookId);
}
