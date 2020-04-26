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
	
	public List<Book> getBookListByType(String type){
		return bookMapper.getBookListByType(type);
	}
	
	public Book getBook(String bookId){
		return bookMapper.getBook(bookId);
	}
	
	public Sale getSale(String bookId){
		return bookMapper.getSale(bookId);
	}
	
	public Share getShare(String bookId){
		return bookMapper.getShare(bookId);
	}
	
	public Auction getAuction(String bookId){
		return bookMapper.getAuction(bookId);
	}
	
	public void insertBook(Book book){
		bookMapper.insertBook(book);
	}
	
	public void insertAuction(Auction auction){
		bookMapper.insertAuction(auction);
	}
	
	public void insertSale(Sale sale){
		bookMapper.insertSale(sale);
	}
	
	public void insertShare(Share share){
		bookMapper.insertShare(share);
	}
}
