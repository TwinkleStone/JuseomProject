package com.team.juseom.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Rate implements Serializable {
	int rateId;
	String raterId;
	String ratedId;
	int rate;
	String description;
	int bookId;
	
	public int getRateId() {
		return rateId;
	}
	public void setRateId(int rateId) {
		this.rateId = rateId;
	}
	public String getRaterId() {
		return raterId;
	}
	public void setRaterId(String raterId) {
		this.raterId = raterId;
	}
	public String getRatedId() {
		return ratedId;
	}
	public void setRatedId(String ratedId) {
		this.ratedId = ratedId;
	}
	public int getRate() {
		return rate;
	}
	public void setRate(int rate) {
		this.rate = rate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getBookId() {
		return bookId;
	}
	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

}
