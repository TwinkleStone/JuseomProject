package com.team.juseom.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.juseom.dao.BookDao;
import com.team.juseom.dao.mybatis.mapper.BookMapper;
import com.team.juseom.domain.Auction;
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
//	
//	public List<Share> getBookListByShare(){
//		return bookMapper.getBookListByShare();
//	}
//	
//	public Book getBook(String bookId){
//		return bookMapper.getBook(bookId);
//	}
//	
//	public Sale getSale(String bookId){
//		Sale sale = bookMapper.getSale(bookId);
//		sale.setBook(getBook(bookId));
//		return sale;
//	}
//	
//	public Share getShare(String bookId){
//		Share share = bookMapper.getShare(bookId);
//		share.setBook(getBook(bookId));
//		return share;
//	}
//	
//	public Auction getAuction(String bookId){
//		Auction auction = bookMapper.getAuction(bookId);
//		auction.setBook(getBook(bookId));
//		return auction;
//	}
	
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
}
