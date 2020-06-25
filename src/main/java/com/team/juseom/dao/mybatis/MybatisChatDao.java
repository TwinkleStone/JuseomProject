package com.team.juseom.dao.mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.juseom.dao.ChatDao;
import com.team.juseom.dao.mybatis.mapper.ChatMapper;
import com.team.juseom.dao.mybatis.mapper.UserMapper;
import com.team.juseom.domain.otmChat;
import com.team.juseom.domain.otoChat;

@Repository
public class MybatisChatDao implements ChatDao {

	@Autowired
	private ChatMapper chatMapper;
	
	@Override
	public void insertotmChat(otmChat otmChat) {
		chatMapper.insertOtmChat(otmChat);
	}

	@Override
	public void insertotoChat(otoChat otoChat) {
		chatMapper.insertOtoChat(otoChat);
		
	}

	@Override
	public List<otmChat> getOtmChatList(String bookId) {
		if (chatMapper.getOtmChatList(bookId) != null) {
			return chatMapper.getOtmChatList(bookId);
		}
		return null;
	}

	@Override
	public List<otoChat> getOtoChatList(String userId) {
		return chatMapper.getOtoChatList(userId);
	}
	
	public List<otoChat> selectOtmChatByChattingRoomId(String chattingRoomId) {
		return chatMapper.selectOtmChatByChattingRoomId(chattingRoomId);
	}

}