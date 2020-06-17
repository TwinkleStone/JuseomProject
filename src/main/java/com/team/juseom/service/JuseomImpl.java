package com.team.juseom.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.juseom.dao.BookDao;
import com.team.juseom.dao.EventDao;
import com.team.juseom.dao.RateDao;
import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Book;
import com.team.juseom.domain.Rate;
import com.team.juseom.domain.Sale;
import com.team.juseom.domain.Share;
import com.team.juseom.domain.User;

@Service
@Transactional
public class JuseomImpl implements JuseomFacade {
	@Autowired
	private BookDao bookDao;
	
	@Autowired
	private EventDao eventDao;
	
	@Autowired
	private RateDao rateDao;
	
	@Autowired
	private ThreadPoolTaskScheduler scheduler;
	
	@Override
	public List<Sale> getBookListBySale() {
		return bookDao.getBookListBySale();
	}

	@Override
	public List<Auction> getBookListByAuction() {
		return bookDao.getBookListByAuction();
	}

	@Override
	public List<Share> getBookListByShare() {
		return bookDao.getBookListByShare();
	}

	@Override
	public Sale getSale(String bookId) {
		return bookDao.getSale(bookId);
	}
	
	@Override
	public void insertBook(Book book) {
		bookDao.insertBook(book);
	}

	@Override
	public void insertSale(Sale sale) {
		bookDao.insertBook(sale.getBook());
		bookDao.insertSale(sale);
	}

	@Override
	public void insertAuction(Auction auction, Date closingTime) {
		bookDao.insertBook(auction.getBook());
		bookDao.insertAuction(auction);
		
		Runnable updateTableRunner = new Runnable() {

			@Override
			public void run() {
				Date curTime = new Date();
				eventDao.closeAuctionEvent(curTime);
				System.out.println("updateTableRunner is executed at " + curTime);
			}
		};
		scheduler.schedule(updateTableRunner, closingTime);
		System.out.println("updateTableRunner has been scheduled to execute at " + closingTime);
	}

	@Override
	public void insertShare(Share share) {
		bookDao.insertBook(share.getBook());
		bookDao.insertShare(share);
	}
	
	@Override
	public Auction getOneAuction(String auctionId) {
		return bookDao.getOneAuction(auctionId);
	}
	
	@Override
	public Share getShare(String shareId) {
		return bookDao.getShare(shareId);
	}

	@Override
	public User getUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String userId, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getUserIdList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> searchBookList(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBookListByTradeType(String tradeType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insertRate(Rate rate) {
		rateDao.insertRate(rate);
	}


}
