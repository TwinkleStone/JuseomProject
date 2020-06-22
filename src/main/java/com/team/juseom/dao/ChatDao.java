package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.otmChat;
import com.team.juseom.domain.otoChat;

public interface ChatDao {

	  public void insertotmChat(otmChat otmChat) ;
	  
	  public void insertotoChat(otoChat otoChat) ;
	  
	  public List<otmChat> getOtmChatList(String bookId);

	public List<otoChat> getOtoChatList(String userId);
}
