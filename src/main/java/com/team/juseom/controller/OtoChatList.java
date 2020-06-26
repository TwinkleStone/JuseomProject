package com.team.juseom.controller;

import java.io.Serializable;

import com.team.juseom.domain.Book;

@SuppressWarnings("serial")
public class OtoChatList implements Serializable  {
	private Book book;
	private String sellerId;
	private String buyerId;
	
	public OtoChatList() {
	}
	
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
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
}
