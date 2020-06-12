package com.team.juseom.dao.mybatis.mapper;

import java.util.Date;

import com.team.juseom.domain.Auction;

public interface AuctionMapper {
	void insertNewAuction(Auction auction);
	void closeAuction(Date curTime);
}
