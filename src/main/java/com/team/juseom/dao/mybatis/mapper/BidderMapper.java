package com.team.juseom.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.team.juseom.domain.Bidder;

public interface BidderMapper {

	List<Bidder> getBidderListByautionId(String auctionId);	
	void insertBidder(Bidder bidder);
	
}
