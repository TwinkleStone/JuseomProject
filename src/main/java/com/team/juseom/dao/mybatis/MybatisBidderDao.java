package com.team.juseom.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.team.juseom.dao.BidderDao;
import com.team.juseom.dao.mybatis.mapper.BidderMapper;
import com.team.juseom.domain.Bidder;

@Repository
public class MybatisBidderDao implements BidderDao {
	
	@Autowired
	private BidderMapper bidderMapper;

	@Override
	public List<Bidder> getBidderListByautionId(String auctionId) {
		return bidderMapper.getBidderListByautionId(auctionId);
	}

	@Override
	public void insertBidder(Bidder bidder) {
		bidderMapper.insertBidder(bidder);
	}

}
