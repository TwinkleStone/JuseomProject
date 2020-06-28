package com.team.juseom.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.team.juseom.dao.OtoStatusDao;
import com.team.juseom.dao.RateDao;
import com.team.juseom.dao.UserDao;
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
	
	@Autowired
	private ThreadPoolTaskScheduler scheduler2;
	
	@Autowired
	private OtoStatusDao statusDao;
	
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
		Runnable updateTableRunner = new Runnable() {

			@Override
			public void run() {
				Date curTime = new Date();
				
				eventDao.closeAuctionEvent(curTime);
				System.out.println("updateTableRunner is executed at " + curTime);
				
				int auctionId = auction.getAuctionId();
				String bidWinner = bidderDao.getBid(auctionId);
				bookDao.updateAuctionWinner(bidWinner, auctionId);
				
				String sellerId = auction.getBook().getUserId();
				String bookId = Integer.toString(auction.getBook().getBookId());
				List<otoChat> chats = new ArrayList<otoChat>();
				
				String chattingRoomId = bookId + "_" + bidWinner;
				chats.add(new otoChat(chattingRoomId, bookId, sellerId, bidWinner));
				
				Date from = new Date();
				SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String to = transFormat.format(from);
				
				chats.get(0).setChatTime(to);
				chats.get(0).setChat("축하합니다! " + auction.getBook().getName() + " 책 경매에서 낙찰되셨습니다.");
				chatDao.insertotoChat(chats.get(0));
			}
		};
		
		bookDao.insertBook(auction.getBook());
		bookDao.insertAuction(auction);
		
		scheduler.schedule(updateTableRunner, auction.getEndTime());
		System.out.println("updateTableRunner has been scheduled to execute at " + auction.getEndTime());
	}

	@Override
	public void insertShare(Share share) {
		Runnable updateTableRunner = new Runnable() {	

			@Override
			public void run() {		
				Date curTime = new Date();
				eventDao.closeShareEvent(curTime);
				System.out.println("updateTableRunner is executed at " + curTime);
			}
		};
		
		Runnable updateTableRunner2 = new Runnable() {	

			@Override
			public void run() {	
				applierDao.insertWinner(share);
				
				//채팅방만들기
				List<String> userIds = applierDao.getUserIds(share.getShareId()); //당첨자 아이디 리퀘스트 파라미터로 받음
				String sellerId = share.getBook().getUserId(); //상품 판매자 아이디 리퀘스트 파라미터로 받음
				String bookId = Integer.toString(share.getBook().getBookId()); //상품 id 리퀘스트파라미터로 받음 (book 테이블의 bookId)
				List<otoChat> chats = new ArrayList<otoChat>();
				for(int i = 0; i < userIds.size(); i++) {
					String chattingRoomId = bookId + "_" + userIds.get(i);
					chats.add(new otoChat(chattingRoomId, bookId, sellerId, userIds.get(i))); 
					//채팅창 정렬을 위해 시간 추가
					String[] txt = new String[] {"null", "축하합니다! " + share.getBook().getName() + " 책 나눔에 당첨되셨습니다."};
					for(int j = 0; j < txt.length; j++) {
						Date from = new Date();
						SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String to;
						if(j == txt.length - 1) {
							Calendar cal = Calendar.getInstance(); 
							cal.setTime(from);
							cal.add(Calendar.SECOND, 10);
							to = transFormat.format(cal.getTime());
						}else {
							to = transFormat.format(from);
						}
		
						chats.get(i).setChatTime(to);
						chats.get(i).setChat(txt[j]);
						chatDao.insertotoChat(chats.get(i));
					}
				}
			}
		};
		
		bookDao.insertBook(share.getBook());
		bookDao.insertShare(share);

		scheduler.schedule(updateTableRunner, share.getEndTime());  
		scheduler2.schedule(updateTableRunner2, share.getRaffleTime());  
	}
	
	@Override
	public Auction getOneAuction(String auctionId) {
		bookDao.getHighBidPrice(auctionId);
		return bookDao.getOneAuction(auctionId);
		
	}
	
	@Override
	public Share getShare(String shareId) {
		return bookDao.getShare(shareId);
	}
	
	@Override
	public void updateSale(String bookId, String saleId, String price, String detail) {
		bookDao.updateSale(saleId, price);	
		bookDao.updateBook(bookId, detail);
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
	public List<otoChat> selectOtoChatByChattingRoomId(String chattingRoomId) {
		return chatDao.selectOtoChatByChattingRoomId(chattingRoomId);
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

	@Override
	public List<otoChat> getOtoChatListBookId(String userId) {
		return chatDao.getOtoChatListBookId(userId);
	}

	@Override
	public List<Rate> getRateListByUser(String userId) {
		return rateDao.getRateListByUser(userId);
	}

	@Override
	public int searchRate(Rate rate) {
		return rateDao.searchRate(rate);
	}

	@Override
	public Rate getRate(int rateId) {
		return rateDao.getRate(rateId);
	}

	@Override
	public String getSalesNubmer(int auctionId) {
		return bookDao.getSalesNumber(auctionId);
	}
	
	@Override
	public void insertStatus(OtoStatus status) {
		statusDao.insertStatus(status);
		
	}

	@Override
	public void updateSellerStatus(OtoStatus status) {
		statusDao.updateSellerStatus(status);
		
	}

	@Override
	public void updateBuyerStatus(OtoStatus status) {
		statusDao.updateBuyerStatus(status);
	}

	@Override
	public OtoStatus getStatusByChattingRoomId(String chattingRoomId) {
		return statusDao.getStatusByChattingRoomId(chattingRoomId);
		
	}

	@Override
	public void updateBookStatus(String bookId) {
		bookDao.updateBookStatus(bookId);
		
	}

	@Override
	public int getHighBidPrice(String auctionId) {
		return bookDao.getHighBidPrice(auctionId);
	}

}
