package com.team.juseom.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.juseom.dao.ApplierDao;
import com.team.juseom.dao.ChatDao;
import com.team.juseom.domain.Share;
import com.team.juseom.domain.otoChat;

@Service
public class ShareRunnable implements Runnable{
	
	@Autowired
	private ApplierDao applierDao;
	
	@Autowired
	private ChatDao chatDao;
	
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

}
