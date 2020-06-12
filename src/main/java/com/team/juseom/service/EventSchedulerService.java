package com.team.juseom.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team.juseom.dao.AuctionDao;
import com.team.juseom.domain.Auction;

@Service
@Transactional
public class EventSchedulerService {
	
	@Autowired
	private AuctionDao auctionDao;
	
	@Autowired
	private ThreadPoolTaskScheduler scheduler;
	
	public void eventScheduler(Auction auction) {
		
		Runnable updateTableRunner = new Runnable() {
			
			@Override
			public void run() {
				Date curTime = new Date();
				auctionDao.closeAuction(curTime);
				System.out.println("updateTableRunner is executed at " + curTime);
			}
		};
		
		auctionDao.insertNewAuction(auction);
		
		//scheduler.schedule(updateTableRunner, auction.getEndTime());  
		
		System.out.println("updateTableRunner has been scheduled to execute at " + auction.getEndTime());
	}
}
