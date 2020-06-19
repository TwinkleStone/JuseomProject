package com.team.juseom.dao.mybatis.mapper;

import java.util.List;
import java.util.Map;

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

	void updateBidNumber(int auctionId);
	void updatePresentPrice(int auctionId, int bidPrice);

	
}