package com.team.juseom.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.team.juseom.dao.RateDao;
import com.team.juseom.dao.mybatis.mapper.RateMapper;
import com.team.juseom.domain.Rate;

@Repository
public class MybatisRateDao implements RateDao {
	
	@Autowired
	private RateMapper rateMapper;

	@Override
	public List<Rate> getRateListByUser(String userId) {
		return rateMapper.getRateListByUser(userId);
	}

	@Override
	public void insertRate(Rate rate) {
		rateMapper.insertRate(rate);
	}

	@Override
	public Rate getRate(int rateId) {
		return rateMapper.getRate(rateId);
	}

	@Override
	public int searchRate(Rate rate) {
		int bookId = rate.getBookId();
		return rateMapper.searchRate(bookId);
	}

}

