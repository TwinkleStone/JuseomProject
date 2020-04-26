package com.team.juseom.domain;

public class Bidder {
	String bidderId;
	String userId;
	String autionId;
	int bidNumber;
	int bidPrice;
	public String getBidderId() {
		return bidderId;
	}
	public void setBidderId(String bidderId) {
		this.bidderId = bidderId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAutionId() {
		return autionId;
	}
	public void setAutionId(String autionId) {
		this.autionId = autionId;
	}
	public int getBidNumber() {
		return bidNumber;
	}
	public void setBidNumber(int bidNumber) {
		this.bidNumber = bidNumber;
	}
	public int getBidPrice() {
		return bidPrice;
	}
	public void setBidPrice(int bidPrice) {
		this.bidPrice = bidPrice;
	}

}
