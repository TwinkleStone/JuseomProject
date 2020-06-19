package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.Bidder;

public interface BidderDao {
	List<Bidder> getBidderListByautionId(String auctionId);
	void insertBidder(Bidder bidder);
	int getBidderCount(String auctionId);
}