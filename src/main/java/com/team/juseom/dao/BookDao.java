package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;

//시작
public interface BookDao {
	void insertBook(Book book);
	void insertAuction(Auction auction);
	void insertSale(Sale sale);
	void insertShare(Share share);
//	Book getBook(String bookId);
	Sale getSale(String bookId);
//	Share getShare(String bookId);
//	Auction getAuction(String bookId);
	List<Sale> getBookListBySale();
	List<Auction> getBookListByAuction();
	List<Share> getBookListByShare();
}
