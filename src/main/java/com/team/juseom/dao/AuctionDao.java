package com.team.juseom.dao;

import java.util.Date;

import com.team.juseom.domain.Auction;

public interface AuctionDao {
	void insertNewAuction(Auction auction);
	void closeAuction(Date curTime);
}