package com.team.juseom.domain;

public class Sale {
	String saleId;
	Book book;
	int suggestPrice;
	
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getSuggestPrice() {
		return suggestPrice;
	}
	public void setSuggestPrice(int suggestPrice) {
		this.suggestPrice = suggestPrice;
	}
}
