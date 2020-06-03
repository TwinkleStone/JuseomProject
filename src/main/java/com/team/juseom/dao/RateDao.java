package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.Rate;

public interface RateDao {
	List<Rate> getRateListByUser(String userId);
	void insertRate(Rate rating);
	Rate getRate(String rateId);
}


