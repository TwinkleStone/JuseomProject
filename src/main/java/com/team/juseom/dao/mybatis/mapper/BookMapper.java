package com.team.juseom.dao.mybatis.mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.team.juseom.domain.Applier;
import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Bidder;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;

public interface BookMapper {

	List<Sale> getBookListBySale();
	List<Auction> getBookListByAuction();
	List<Share> getBookListByShare();
//	Book getBook(String bookId);
	Sale getSale(String saleId);
	Share getShare(String shareId);
	Auction getOneAuction(String auctionId);
	void insertBook(Book book);
	void insertAuction(Auction auction);
	void insertSale(Sale sale);
	void insertShare(Share share);
	void updateSale(String saleId, String price);
	void updateBook(String bookId, String detail);
	List<Share> getOpenShare();
	
	int updateBidNumber(String auctionId);
	void updatePresentPrice(String auctionId, int bidPrice);

	List<Book> searchBookDetail(@Param("keyword")String keyword, @Param("lowPrice")String lowPrice, @Param("highPrice")String highPrice, @Param("tradeType")String tradeType);
	List<Book> searchBookByUserId(String userId);
	Book getOtoChatListBookInfo(@Param("bookId")String bookId, @Param("userId")String userId);
	void updatePeopleNumber(int applierId);
	void closeAuctionEvent(Date curTime);
	void closeShareEvent(Date curTime);
	void updateAuctionWinner(String bidWinner, int auctionId);
	void updateShareWinner(List<String> winner, int shareId);
	String getSalesNumber(int auctionId);
	void updateBookStatus(String bookId);
	Integer getHighBidPrice(String auctionId);
	int getNowBidNumber(String auctionId);
	void updateMaxAuctionPrice(String auctionId, int highBidPrice);
	String getBidWinner(String auctionId);
}