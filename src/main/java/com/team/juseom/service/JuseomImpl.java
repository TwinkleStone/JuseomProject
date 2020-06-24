package com.team.juseom.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.juseom.dao.ApplierDao;
import com.team.juseom.dao.BidderDao;
import com.team.juseom.dao.BookDao;
import com.team.juseom.dao.ChatDao;
import com.team.juseom.dao.EventDao;
import com.team.juseom.dao.RateDao;
import com.team.juseom.dao.UserDao;
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
	private BidderDao bidderDao;
	
	@Autowired
	private ApplierDao applierDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ChatDao chatDao;
	
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
	public void insertAuction(Auction auction) {
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
		scheduler.schedule(updateTableRunner, auction.getEndTime());
		System.out.println("updateTableRunner has been scheduled to execute at " + auction.getEndTime());
	}

	@Override
	public void insertShare(Share share) {
		Runnable updateTableRunner = new Runnable() {	
			// anonymous class 정의
			@Override
			public void run() {   // 스케쥴러에 의해 미래의 특정 시점에 실행될 작업을 정의				
				Date curTime = new Date();
				// 실행 시점의 시각을 전달하여 그 시각 이전의 closing time 값을 갖는 event의 상태를 변경 
				eventDao.closeShareEvent(curTime);	// EVENTS 테이블의 레코드 갱신	
				System.out.println("updateTableRunner is executed at " + curTime);
			}
		};
		
		bookDao.insertBook(share.getBook());
		bookDao.insertShare(share);

		// 스케줄 생성: closingTime에 updateTableRunner.run() 메소드 실행
		scheduler.schedule(updateTableRunner, share.getEndTime());  
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
	public User getUserById(String userId) {
		if (userDao.getUserById(userId) != null) {
			return userDao.getUserById(userId);
		}
		return null;
	}

	@Override
	public User getUserIdPassword(String userId, String password) {
		if (userDao.getUserIdPassword(userId, password) != null) {
			return userDao.getUserIdPassword(userId, password);
		}
		return null;
	}

	@Override
	public void insertUser(User user) {
		userDao.insertUser(user);
	}

	@Override
	public void updateUser(User user) {
		userDao.updateUser(user);
	}

	@Override
	public List<String> getUserIdList() {
		return userDao.getUserIdList();
	}

	@Override
	public List<Book> searchBookList(Book book) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> searchBookDetail(String keyword, String lowPrice, String highPrice, String tradeType) {
		return bookDao.searchBookDetail(keyword, lowPrice, highPrice, tradeType);
	}
	
	public List<Book> searchBookByUserId(String userId) {
		return bookDao.searchBookByUserId(userId);
	}
	
	public List<otmChat> getOtmChatList(String bookId) {
		return chatDao.getOtmChatList(bookId);
	}
	
	public void insertOtmChat(otmChat chat) {
		chatDao.insertotmChat(chat);
	}
	
	@Override
	public List<otoChat> getOtoChatList(String userId) {
		// TODO Auto-generated method stub
		return chatDao.getOtoChatList(userId);
	}

	@Override
	public void insertOtoChat(otoChat chat) {
		chatDao.insertotoChat(chat);
		
	}

	@Override
	public Book getOtoChatListBookInfo(String bookId, String userId) {
		// TODO Auto-generated method stub
		return bookDao.getOtoChatListBookInfo(bookId, userId);
	}

	@Override
	public void insertRate(Rate rate) {
		rateDao.insertRate(rate);
	}

	@Override
	public void insertBidder(Bidder bidder) {
		bidderDao.insertBidder(bidder);
		bookDao.updateBidNumber(bidder);
		bookDao.updatePresentPrice(bidder);
	}

	@Override
	public int getBidderCount(String auctionId) {
		return bidderDao.getBidderCount(auctionId);
	}

	@Override
	public List<Bidder> getBidderListByauctionId(String auctionId) {
		return bidderDao.getBidderListByauctionId(auctionId);
	}

	@Override
	public void insertApplier(Applier applier) {
		applierDao.insertApplier(applier);
		bookDao.updatePeopleNumber(applier);

	}

	@Override
	public int searchApplier(Applier applier) {
		return applierDao.searchApplier(applier);
	}

}
