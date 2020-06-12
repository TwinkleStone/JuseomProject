package com.team.juseom.dao.mybatis;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.team.juseom.dao.AuctionDao;
import com.team.juseom.dao.mybatis.mapper.AuctionMapper;
import com.team.juseom.domain.Auction;

//public class MybatisAuctionDao implements AuctionDao {
//	
//	@Autowired
//	private AuctionMapper auctionMapper;
//
//	@Override
//	public void insertNewAuction(Auction auction) {
//		auctionMapper.insertNewAuction(auction);
//	}
//
//	@Override
//	public void closeAuction(Date curTime) {
//		auctionMapper.closeAuction(curTime);
//	}
//
//}
