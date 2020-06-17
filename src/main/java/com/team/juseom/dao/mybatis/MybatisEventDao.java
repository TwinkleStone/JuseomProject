package com.team.juseom.dao.mybatis;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.team.juseom.dao.EventDao;
import com.team.juseom.dao.mybatis.mapper.EventMapper;

@Repository
public class MybatisEventDao implements EventDao {
	
	@Autowired
	private EventMapper eventMapper;

	@Override
	public void closeAuctionEvent(Date curTime) {
		eventMapper.closeAuctionEvent(curTime);
	}

}
