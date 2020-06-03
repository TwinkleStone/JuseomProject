package com.team.juseom.dao.mybatis.mapper;

import java.util.List;
import com.team.juseom.domain.Bidder;

public interface BidderMapper {
	//mapper xml에서 정의함
	List<Bidder> getBidderListByautionId(String auctionId);
	
	//mapper xml에서 정의함
	void insertBidder(Bidder bidder);
	
}
