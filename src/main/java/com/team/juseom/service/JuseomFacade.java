package com.team.juseom.service;

import java.util.List;

import com.team.juseom.domain.Applier;
import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Bidder;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.Rate;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;
import com.team.juseom.domain.User;
import com.team.juseom.domain.otmChat;
import com.team.juseom.domain.otoChat;

public interface JuseomFacade {
	void insertBook(Book book);
	void insertSale(Sale sale);
	void insertShare(Share share);
	void insertAuction(Auction auction);
	List<Sale> getBookListBySale();
	List<Auction> getBookListByAuction();
	List<Share> getBookListByShare();
	Sale getSale(String saleId);
	Share getShare(String shareId);
	Auction getOneAuction(String auctionId);
	
	User getUserById(String userId);
	User getUserIdPassword(String userId, String password);
	void insertUser(User user);
	void updateUser(User user);
	List<String> getUserIdList();
	
	List<Book> searchBookDetail(String keyword, String lowPrice, String highPrice, String tradeType);
	List<Book> searchBookByUserId(String userId);
	
	List<Book> searchBookList(Book book);
	
	List<otmChat> getOtmChatList(String bookId);
	void insertOtmChat(otmChat chat);
	
	List<otoChat> getOtoChatList(String userId);
	void insertOtoChat(otoChat chat);
	List<otoChat> getOtoChatListBookId(String userId);
	Book getOtoChatListBookInfo(String bookId, String userId);
	List<otoChat> selectOtoChatByChattingRoomId(String chattingRoomId);
	
	void insertRate(Rate rate);
	
	void insertBidder(Bidder bidder);
	int getBidderCount(String auctionId);
	List<Bidder> getBidderListByauctionId(String auctionId);
	
	void insertApplier(Applier applier);
	int searchApplier(Applier applier);
}
