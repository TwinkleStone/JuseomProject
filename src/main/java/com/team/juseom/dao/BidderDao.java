package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.Bidder;

public interface BidderDao {
	List<Bidder> getBidderListByauctionId(String auctionId);
	void insertBidder(Bidder bidder);
	String getBid(int auctionId);
	void removeBidder(String userId, String auctionId);
}