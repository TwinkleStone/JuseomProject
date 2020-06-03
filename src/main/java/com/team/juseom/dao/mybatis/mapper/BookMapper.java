package com.team.juseom.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;

public interface BookMapper {

	List<Sale> getBookListBySale();
	List<Auction> getBookListByAuction();
	List<Share> getBookListByShare();
	Book getBook(String bookId);
	Sale getSale(String bookId);
	Share getShare(String bookId);
	
	@Select("SELECT AUCTIONID AS auctionId, " + 
	         "BOOKID AS bookId, " + 
	         "STARTPRICE AS startPrice," + 
	         "PRESENTPRICE AS presentPrice, " + 
	         "SALESNUMBER AS salesNumber, " + 
	         "BIDNUMBER AS bidNumber, " + 
	         "ENDTIME AS endTime" + 
	         "FROM AUCTION " +
	         "WHERE BOOKID = #{bookId}")
	Auction getAuction(String bookId);
	void insertBook(Book book);
	
	 @Insert("INSERT INTO AUCTION(AUCTIONID, BOOKID, STARTPRICE, PRESENTPRICE, SALESNUMBER, BIDNUMBER, ENDTIME) "
	         + "VALUES (#{bookId}, #{startPrice}, #{presentPrice}, #{salesNumber}, #{bidNumber}, #{endTime})")
	void insertAuction(Auction auction);
	void insertSale(Sale sale);
	void insertShare(Share share);
	
}