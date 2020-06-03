package com.team.juseom.dao.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.team.juseom.domain.Rate;

public interface RateMapper {

	@Select("SELECT RATEID AS rateId, " 
			+ "RATERID AS raterId, "
			+ "RATEDID AS ratedId, " 
			+ "RATE AS rate"
			+ "DESCRIPTION AS description"
			+ "FROM RATE "
			+ "WHERE RATEDID = #{userId}"
	)
	List<Rate> getRateListByUser(String userId);
	
	
	void insertRate(Rate rate);
	
	@Select("SELECT RATEID as rateId, " 
			+ "RATERID as raterId, "
			+ "RATEDID as ratedId, " 
			+ "RATE as rate"
			+ "DESCRIPTION as description"
			+ "FROM RATE "
			+ "WHERE RATEID = #{rateId}"
	)
	Rate getRate(String rateId);
}


