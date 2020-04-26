package com.team.juseom.domain;

public class Sale {
	String saleId;
	String bookId;
	int suggestPrice;
	
	public String getSaleId() {
		return saleId;
	}
	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
	public String getBookId() {
		return bookId;
	}
	public void setBookId(String bookId) {
		this.bookId = bookId;
	}
	public int getSuggestPrice() {
		return suggestPrice;
	}
	public void setSuggestPrice(int suggestPrice) {
		this.suggestPrice = suggestPrice;
	}
}
