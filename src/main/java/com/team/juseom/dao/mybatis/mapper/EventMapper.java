package com.team.juseom.dao.mybatis.mapper;

import java.util.Date;

public interface EventMapper {
	void closeAuctionEvent(Date curTime);
	void closeShareEvent(Date curTime);
}
