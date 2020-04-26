package com.team.juseom.domain;

public class Share {
	String shareId;
	Book book;
	String shareNumber;
	String endTime;
	String raffleTime;
	String peopleNumber;
	
	public String getShareId() {
		return shareId;
	}
	public void setShareId(String shareId) {
		this.shareId = shareId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getShareNumber() {
		return shareNumber;
	}
	public void setShareNumber(String shareNumber) {
		this.shareNumber = shareNumber;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getRaffleTime() {
		return raffleTime;
	}
	public void setRaffleTime(String raffleTime) {
		this.raffleTime = raffleTime;
	}
	public String getPeopleNumber() {
		return peopleNumber;
	}
	public void setPeopleNumber(String peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
}
