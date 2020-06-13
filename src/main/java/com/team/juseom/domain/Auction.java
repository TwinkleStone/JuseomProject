package com.team.juseom.domain;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Auction implements Serializable{
	int auctionId;
	Book book;
	String startPrice;
	int presentPrice;
	String salesNumber;
	int bidNumber;
	String endTime;
	public int getAuctionId() {
		return auctionId;
	}
	public void setAuctionId(int auctionId) {
		this.auctionId = auctionId;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public String getStartPrice() {
		return startPrice;
	}
	public void setStartPrice(String startPrice) {
		this.startPrice = startPrice;
	}
	public int getPresentPrice() {
		return presentPrice;
	}
	public void setPresentPrice(int presentPrice) {
		this.presentPrice = presentPrice;
	}
	public String getSalesNumber() {
		return salesNumber;
	}
	public void setSalesNumber(String salesNumber) {
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
	
	public boolean isEndTimeBeforeNow() {
		if(endTime == null)
			return false;
		
		String isMorning = endTime.substring(endTime.length()-2, endTime.length()); //AM, PM 여부 확인
		//format을 이용해 Date 타입으로 변환을 위해 고쳐주기
		if(isMorning.equals("AM")) {
			endTime = endTime.replace(isMorning, "오전");
		}else {
			endTime = endTime.replace("PM", "오후");
		}
		
		//12:00 오류로 인해 추가 
		isMorning = endTime.substring(endTime.length()-2, endTime.length()); //AM, PM 여부 확인
		String hour = endTime.substring(11,13); //시간 자르기
		if(hour.equals("12")) {
			if(isMorning.equals("오전")) {
				endTime = endTime.replace(isMorning, "오후");
			}else {
				endTime = endTime.replace("오후", "오전");
			}
		}

		SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy KK:mm a");
		try {
			Date to;
			to = fm.parse(endTime);
			
			endTime = fm.format(to);
			isMorning = endTime.substring(endTime.length()-2, endTime.length());
			if(isMorning.equals("오전")) {
				endTime = endTime.replace(isMorning, "AM");
			}else {
				endTime = endTime.replace("오후", "PM");
			}
			
			return to.before(new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}

}
