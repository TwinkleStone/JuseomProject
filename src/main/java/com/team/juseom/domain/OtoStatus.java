package com.team.juseom.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OtoStatus implements Serializable {
	String chattingRoomId;
	String bookId;
	String sellerId;
	String buyerId;
	String sellerStatus;
	String buyerStatus;
	
	public OtoStatus() { }

	public String getChattingRoomId() {
		return chattingRoomId;
	}

	public void setChattingRoomId(String chattingRoomId) {
		this.chattingRoomId = chattingRoomId;
	}

	public String getBookId() {
		return bookId;
	}

	public void setBookId(String bookId) {
		this.bookId = bookId;
	}

	public String getSellerId() {
		return sellerId;
	}

	public void setSellerId(String sellerId) {
		this.sellerId = sellerId;
	}

	public String getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(String buyerId) {
		this.buyerId = buyerId;
	}

	public String getSellerStatus() {
		return sellerStatus;
	}

	public void setSellerStatus(String sellerStatus) {
		this.sellerStatus = sellerStatus;
	}

	public String getBuyerStatus() {
		return buyerStatus;
	}

	public void setBuyerStatus(String buyerStatus) {
		this.buyerStatus = buyerStatus;
	}
}
