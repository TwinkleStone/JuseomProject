package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.Auction;

public interface AuctionDao {
	List<Auction> getAuctionListByautionid(String autionid);
	//bidder에서 list로 받아오니까 굳이 옥션에서는 필요 없는 것 같기도 함. 일단 보류
	Auction insertAuction(Auction auction);
	Auction getAuction(String auctionId);
}
