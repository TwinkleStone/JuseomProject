package com.team.juseom.dao.mybatis.mapper;

import java.util.List;

import com.team.juseom.domain.otoChat;
import com.team.juseom.domain.otmChat;

/**
 * @author Eduardo Macarron
 *
 */
public interface ChatMapper {
	public void insertOtmChat(otmChat otmChat);

	public void insertOtoChat(otoChat otoChat);

	public List<otmChat> getOtmChatList(String bookId);

	public List<otoChat> getOtoChatList(String userId);
	
	List<otoChat> selectOtoChatByChattingRoomId(String chattingRoomId);

	public List<String> getOtoChatListBookId(String userId);
}
