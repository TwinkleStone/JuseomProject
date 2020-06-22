package com.team.juseom.domain;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class otmChat implements Serializable{
	
	String sellerId;
	String bookId;
	String buyerId;
	String chat;
	String chatNum;
	String chatTime;
	
	public otmChat() {
		
	}
	
	public otmChat(String bookId, String sellerId) {
		this.bookId = bookId;
		this.sellerId = sellerId;
	}
	
	public String getSellerId() {
		return sellerId;
	}
	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public String getBuyerId() {
		return buyerId;
	}
	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}
	public String getChat() {
		return chat;
	}
	public void setChat(String chat) {
		this.chat = chat;
	}
	public String getChatNum() {
		return chatNum;
	}
	public void setChatNum(String chatNum) {
		this.chatNum = chatNum;
	}
	public String getChatTime() {
		return chatTime;
	}
	public void setChatTime(String chatTime) {
		this.chatTime = chatTime;
	}
	
}
