package com.team.juseom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team.juseom.domain.Auction;
import com.team.juseom.service.JuseomFacade;

@Controller
public class StartAuctionController {
	//경매를 시작할 때 사용하는 컨트롤러
	
	@Autowired
	private JuseomFacade juseom;
	
//	@RequestMapping("/auction/viewAuctionList.do")
	
}
