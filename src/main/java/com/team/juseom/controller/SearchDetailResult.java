package com.team.juseom.controller;

import java.io.Serializable;

import com.team.juseom.domain.Book;

@SuppressWarnings("serial")
public class SearchDetailResult implements Serializable  {
	Book book;
	String tradeType;
	String tradeId;
	
	public SearchDetailResult() {
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public String getTradeType() {
		return tradeType;
	}

	public void setTradeType(String tradeType) {
		this.tradeType = tradeType;
	}

	public String getTradeId() {
		return tradeId;
	}

	public void setTradeId(String tradeId) {
		this.tradeId = tradeId;
	}
}
