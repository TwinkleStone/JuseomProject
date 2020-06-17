package com.team.juseom.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.team.juseom.domain.Book;

public class ShareRegi {
	int shareId;
	Book book;
	String shareNumber;
	String endTime;
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
	
	public Date dateFormChange() {
		if(endTime == null)
			return null;
		
		endTime = maTranslation(endTime, 1);
		endTime = maChange(endTime);
		
		SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy KK:mm a");
		try {
			Date to;
			to = fm.parse(endTime);
			
			endTime = fm.format(to);
			endTime = maTranslation(endTime, 2);
			
			return to;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean isEndTimeBeforeNow() {
		if(endTime == null)
			return false;
		
		endTime = maTranslation(endTime, 1);
		endTime = maChange(endTime);
		
		SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy KK:mm a");
		try {
			Date to;
			to = fm.parse(endTime);
			if(to.getHours() == 0) {
				to.setDate(to.getDate() - 1);
			}
			endTime = fm.format(to);
			endTime = maTranslation(endTime, 2);
			return to.before(new Date());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean isRaffleTimeBeforeEndTime() {
		if(endTime == null || raffleTime == null)
			return false;
		
		endTime = maTranslation(endTime, 1);
		endTime = maChange(endTime);
		raffleTime = maTranslation(raffleTime, 1);
		raffleTime = maChange(raffleTime);

		SimpleDateFormat fm = new SimpleDateFormat("MM/dd/yyyy KK:mm a");
		try {
			Date e = fm.parse(endTime);
			Date r = fm.parse(raffleTime);
			
			endTime = fm.format(e);
			endTime = maTranslation(endTime, 2);
			raffleTime = fm.format(r);
			raffleTime = maTranslation(raffleTime, 2);
			
			return r.before(e);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public String maTranslation(String date, int choice) {
		String isMorning = date.substring(date.length()-2, date.length()); //AM, PM 여부 확인
		
		//format을 이용해 Date 타입으로 변환을 위해 고쳐주기
		if(choice == 1) {
			if(isMorning.equals("AM")) {
				date = date.replace(isMorning, "오전");
			}else {
				date = date.replace("PM", "오후");
			}
		}else {
			if(isMorning.equals("오전")) {
				date = date.replace(isMorning, "AM");
			}else {
				date = date.replace("오후", "PM");
			}
		}
		return date;
	}
	
	public String maChange(String date) {
		//12:00 오류로 인해 추가 
		String isMorning = date.substring(date.length()-2, date.length()); //AM, PM 여부 확인
		String hour = date.substring(11,13); //시간 자르기
		if(hour.equals("12")) {
			if(isMorning.equals("오전")) {
				date = date.replace(isMorning, "오후");
				
			}else {
				date = date.replace("오후", "오전");
			}
		}
		return date;
	}
	
}
