package com.team.juseom.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Sale implements Serializable{
	int saleId;
	Book book;
	String suggestPrice;
	
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getSuggestPrice() {
		return suggestPrice;
	}
	public void setSuggestPrice(String suggestPrice) {
		this.suggestPrice = suggestPrice;
	}
}
