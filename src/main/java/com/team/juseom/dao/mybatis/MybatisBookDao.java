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
	public int updateBidNumber(String auctionId) {
		return bookMapper.updateBidNumber(auctionId);
		
	}

	@Override
	public void updatePresentPrice(String auctionId, int bidPrice) {
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
		int shareId = applier.getSharedId();
		bookMapper.updatePeopleNumber(shareId);
	}

	@Override
	public void updateAuctionWinner(String bidWinner, int auctionId) {
		bookMapper.updateAuctionWinner(bidWinner, auctionId);
	}

	@Override
	public void updateShareWinner(Share share, List<String> winner) {
		int shareId = share.getShareId();
		bookMapper.updateShareWinner(winner, shareId);
	}

	@Override
	public void updateSale(String saleId, String price) {
		bookMapper.updateSale(saleId, price);
	}
	
	@Override
	public void updateBook(String bookId, String detail) {
		bookMapper.updateBook(bookId, detail);
	}

	@Override
	public String getSalesNumber(int auctionId) {
		return bookMapper.getSalesNumber(auctionId);
	}

	@Override
	public void deleteShare(int shareId) {
		bookMapper.deleteShare(shareId);
	}

	@Override
	public void updateBookStatus(String bookId) {
		bookMapper.updateBookStatus(bookId);
		
	}

	@Override
	public int getHighBidPrice(String auctionId) {
		return bookMapper.getHighBidPrice(auctionId);
	}

	@Override
	public int getNowBidNumber(String auctionId) {
		return bookMapper.getNowBidNumber(auctionId);
	}

}
