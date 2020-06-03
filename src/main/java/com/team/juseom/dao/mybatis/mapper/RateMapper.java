package com.team.juseom.dao.mybatis.mapper;

import java.util.List;

import com.team.juseom.domain.Rate;

public interface RateMapper {

	List<Rate> getRateListByUser(String userId);
	void insertRate(Rate rate);
	Rate getRate(String rateId);
}


