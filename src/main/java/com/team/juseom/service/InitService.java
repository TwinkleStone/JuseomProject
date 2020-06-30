package com.team.juseom.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import com.team.juseom.dao.ApplierDao;
import com.team.juseom.dao.BookDao;
import com.team.juseom.dao.ChatDao;
import com.team.juseom.dao.EventDao;
import com.team.juseom.domain.Share;
import com.team.juseom.domain.otoChat;

@Service
public class InitService implements ApplicationListener<ContextClosedEvent>, InitializingBean, DisposableBean{
	@Autowired
	EventDao eventDao;
	
	@Autowired
	BookDao bookDao;
	
	@Autowired
	private ApplierDao applierDao;
	
	@Autowired
	private ChatDao chatDao;
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	private ThreadPoolTaskScheduler scheduler;
	
	@Autowired
	private ThreadPoolTaskScheduler scheduler2;
	
	@PostConstruct
	public void init() {
		if(context.getDisplayName().contains("juseom-servlet")) {
			// 경매나 나눔 close 작업
			// 나눔 scheduler 재설정 작업
			List<Share> openedShareList = bookDao.getOpenShare();
			System.out.println("opened Share size: " + openedShareList.size());
			Date curTime = new Date();
			// 마감 시간이 현재시간보다 이전일 경우 close 수행
			eventDao.closeShareEvent(curTime);
			for(int i = 0; i < openedShareList.size(); i++) {
				if(openedShareList.get(i).getRaffleTime().compareTo(curTime) == -1) {
					insertChatting(openedShareList.get(i));
				}else {
					System.out.println("scheduler 재 등록");
					if(openedShareList.get(i).getEndTime().compareTo(curTime) != -1) {
						System.out.println("마감시간 재 등록");
						scheduler.schedule(updateTableRunner, openedShareList.get(i).getEndTime());
					}
					((ShareRunnable) updateTableRunner2).setShare(openedShareList.get(i));
					scheduler2.schedule(updateTableRunner2, openedShareList.get(i).getRaffleTime());  
				}
			}
			
			//경매 scheduler 재설정 작업
			eventDao.closeAuctionEvent(curTime);
			System.out.println("resetTableRunner is executed at " + curTime);	
		}
	}
	
	Runnable updateTableRunner2 = new ShareRunnable();
	
	Runnable updateTableRunner = new Runnable() {	
		@Override
		public void run() {		
			Date curTime = new Date();
			eventDao.closeShareEvent(curTime);
			System.out.println("updateTableRunner is executed at " + curTime);
		}
	};
	
	public void insertChatting(Share share) {
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
	
	@Override
	public void destroy() throws Exception {
	}

	@Override
	public void afterPropertiesSet() throws Exception {		
	}

	@Override
	public void onApplicationEvent(ContextClosedEvent event) {	
	}
	
	public class ShareRunnable implements Runnable{
		
		Share share;
		
		public ShareRunnable(Share share) {
			this.share = share;
		}
		
		public ShareRunnable() {
			
		}
		
		public void setShare(Share share) {
			this.share = share;
		}
		
		@Override
		public void run() {	
			System.out.println("share: " + share);
			System.out.println("applierDao: " + applierDao);
			insertChatting(share);
		}
	}
}
