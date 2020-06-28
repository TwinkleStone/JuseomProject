package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.Applier;
import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Bidder;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;

//시작
public interface BookDao {
	void insertBook(Book book);
	void insertAuction(Auction auction);
	void insertSale(Sale sale);
	void insertShare(Share share);
	void updateSale(String saleId, String price);
	void updateBook(String bookId, String detail);
	Sale getSale(String saleId);
	Share getShare(String shareId);
	Auction getOneAuction(String auctionId);
	List<Sale> getBookListBySale();
	List<Auction> getBookListByAuction();
	List<Share> getBookListByShare();
	void deleteShare(int shareId);
	
	void updatePresentPrice(Bidder bidder);
	void updateBidNumber(Bidder bidder);
	
	void updatePeopleNumber(Applier applier);
	
	List<Book> searchBookDetail(String keyword, String lowPrice, String highPrice, String tradeType);
	List<Book> searchBookByUserId(String userId);
	Book getOtoChatListBookInfo(String bookId, String userId);
	void updateAuctionWinner(String bidWinner, int auctionId);
	void updateShareWinner(Share share, List<String> winner);
	void updateBookStatus(String bookId);
	String getSalesNumber(int auctionId);
	int getHighBidPrice(String auctionId);

}
