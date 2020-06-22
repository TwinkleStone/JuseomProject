package com.team.juseom.domain;

import java.io.Serializable;

public class otoChat implements Serializable{
	
	String chattingRoomId;
	String sellerId;
	String bookId;
	String buyerId;
	String chat;
	String chatNum;
	String chatTime;
	String buyerStatus;
	String sellerStatus;
	
	public String getChattingRoomId() {
		return chattingRoomId;
	}
	public void setChattingRoomId(String chattingRoomId) {
		this.chattingRoomId = chattingRoomId;
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
	public String getBuyerStatus() {
		return buyerStatus;
	}
	public void setBuyerStatus(String buyerStatus) {
		this.buyerStatus = buyerStatus;
	}
	public String getSellerStatus() {
		return sellerStatus;
	}
	public void setSellerStatus(String sellerStatus) {
		this.sellerStatus = sellerStatus;
	}

}
