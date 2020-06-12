package com.team.juseom.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.team.juseom.domain.Auction;
import com.team.juseom.service.JuseomFacade;

@Controller
public class AuctionController {
	
//	private JuseomFacade juseom;
//	
//	@Autowired
//	public void setJuseom(JuseomFacade juseom) {
//		this.juseom = juseom;
//	}
//	
//	@RequestMapping("/shop/auction.do")
//	public ModelAndView handleRequest(HttpServletRequest request,
//			@RequestParam("endtime")
//			@DateTimeFormat(pattern="yyyy-MM-dd HH:mm") Date endTime) throws Exception {
//		juseom.eventScheduler(endTime);		
//		return new ModelAndView("Scheuled", "endTime", endTime);
//	}
}
