package com.team.juseom.service;

import java.util.List;

import com.team.juseom.domain.Applier;
import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Bidder;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.OtoStatus;
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
	void updateSale(String bookId, String saleId, String price, String detail);
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
	String getCommnameByUserId(String userId);
	
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
	List<Bidder> getBidderListByauctionId(String auctionId);
	
	void insertApplier(Applier applier);
	int searchApplier(Applier applier);
	
	List<Rate> getRateListByUser(String userId);
	int searchRate(int bookId, String raterId);
	Rate getRate(int rateId);
	String getAvgRate(String ratedId);
	
	void insertStatus(OtoStatus status);
	void updateSellerStatus(OtoStatus status);
	void updateBuyerStatus(OtoStatus status);
	OtoStatus getStatusByChattingRoomId(String chattingRoomId);
	void updateBookStatus(String bookId);
	String getSalesNubmer(int auctionId);
	Integer getHighBidPrice(String auctionId);
	void removeBidder(String userId, String auctionId);
	int updateBidNumber(String auctionId);
	int getNowBidNumber(String auctionId);
	String getBidWinner(String auctionId);
	String getSellerStatus(String chattingRoomId);
	String getBuyerStatus(String chattingRoomId);
	
	String getShareIdByBookId(String bookId);
	String getAuctionIdByBookId(String bookId);
	String getSaleIdByBookId(String bookId);
}
