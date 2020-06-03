package com.team.juseom.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.team.juseom.domain.Bidder;

public interface BidderMapper {

	@Select("SELECT BIDDERID AS rateId, " 
			+ "USERID AS userId, "
			+ "AUCTIONID AS auctionId, " 
			+ "BIDNUMBER AS bidNumber"
			+ "BIDPRICE AS bidPrice"
			+ "FROM RATE "
			+ "WHERE RATEDID = #{userId}"
	)
	List<Bidder> getBidderListByautionId(String auctionId);
	
	
	@Insert("INSERT INTO BIDDER(BIDDERID, USERID, AUCTIONID, BIDNUMBER, BIDPRICE)" 
	+ "VALUES (#{bidderId}, #{userId}, #{auctionId}, #{bidNumber}, #{bidPrice})")
	void insertBidder(Bidder bidder);
	
}
