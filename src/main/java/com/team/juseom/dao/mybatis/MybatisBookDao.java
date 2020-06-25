package com.team.juseom.dao.mybatis;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.juseom.dao.BookDao;
import com.team.juseom.dao.mybatis.mapper.BookMapper;
import com.team.juseom.domain.Applier;
import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Bidder;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;

@Repository
public class MybatisBookDao implements BookDao{
	@Autowired
	private BookMapper bookMapper;
	
	public List<Sale> getBookListBySale(){
		return bookMapper.getBookListBySale();
	}
	
	public List<Auction> getBookListByAuction(){
		return bookMapper.getBookListByAuction();
	}
	
	public List<Share> getBookListByShare(){
		return bookMapper.getBookListByShare();
	}
	
//	public Book getBook(String bookId){
//		return bookMapper.getBook(bookId);
//	}
//	
	public Sale getSale(String saleId){
		return bookMapper.getSale(saleId);
	}
	
	public Share getShare(String shareId){
		return bookMapper.getShare(shareId);
	}
	
	public void insertBook(Book book){
		bookMapper.insertBook(book);
	}
	
	public void insertSale(Sale sale){
		bookMapper.insertSale(sale);
	}
//	
//	public void insertShare(Share share){
//		bookMapper.insertShare(share);
//	}

	@Override
	public void insertAuction(Auction auction) {
		bookMapper.insertAuction(auction);
	}

	@Override
	public void insertShare(Share share) {
		bookMapper.insertShare(share);
	}

	@Override
	public Auction getOneAuction(String auctionId) {
		return bookMapper.getOneAuction(auctionId);
	}

	@Override
	public void updateBidNumber(Bidder bidder) {
		int auctionId = bidder.getAuctionId();
		bookMapper.updateBidNumber(auctionId);
		
	}

	@Override
	public void updatePresentPrice(Bidder bidder) {
		int auctionId = bidder.getAuctionId();
		int bidPrice = bidder.getBidPrice();
		bookMapper.updatePresentPrice(auctionId, bidPrice);
		
	}

	@Override
	public List<Book> searchBookDetail(String keyword, String lowPrice, String highPrice, String tradeType) {
		System.out.println("MyBatisBookDao" + keyword + ", " + lowPrice + ", " + highPrice + ", " + tradeType);
		return bookMapper.searchBookDetail(keyword, lowPrice, highPrice, tradeType);
	}
	
	public List<Book> searchBookByUserId(String userId) {
		return bookMapper.searchBookByUserId(userId);
	}

	// 참여중인 채팅리스트 출력을 위한 메소드
	@Override
	public Book getOtoChatListBookInfo(String bookId, String userId) {
		// TODO Auto-generated method stub
		return bookMapper.getOtoChatListBookInfo(bookId, userId);
	}

	@Override
	public void updatePeopleNumber(Applier applier) {
		int applierId = applier.getApplierId();
		bookMapper.updatePeopleNumber(applierId);
		
	}


}
