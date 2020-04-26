package com.team.juseom.domain;

public class Auction {
	String auctionId;
	Book book;
	int startPrice;
	int presentPrice;
	int salesNumber;
	int bidNumber;
	String endTime;
	public String getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(String auctionId) {
		this.auctionId = auctionId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(int startPrice) {
		this.startPrice = startPrice;
	}
	public int getPresentPrice() {
		return presentPrice;
	}
	public void setPresentPrice(int presentPrice) {
		this.presentPrice = presentPrice;
	}
	public int getSalesNumber() {
		return salesNumber;
	}
	public void setSalesNumber(int salesNumber) {
		this.salesNumber = salesNumber;
	}
	public int getBidNumber() {
		return bidNumber;
	}
	public void setBidNumber(int bidNumber) {
		this.bidNumber = bidNumber;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
