package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.Bidder;

//경매에 참여한 사람들의 정보(id와 입찰가)를 저장.
public interface BidderDao {
	List<Bidder> getBidderListByautionId(String auctionId); //각 경매에 대한 전체 경매자들 list를 가져옴
	void insertBidder(Bidder bidder); //경매에 참여한 사람의 정보 insert
}