package com.team.juseom.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Share implements Serializable{
	int shareId;
	Book book;
	String shareNumber;
	Date endTime;
	String raffleTime;
	int peopleNumber;
	String status;
	
	public int getShareId() {
		return shareId;
	}
	public void setShareId(int shareId) {
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
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public String getRaffleTime() {
		return raffleTime;
	}
	public void setRaffleTime(String raffleTime) {
		this.raffleTime = raffleTime;
	}
	public int getPeopleNumber() {
		return peopleNumber;
	}
	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
