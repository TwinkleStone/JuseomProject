package com.team.juseom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("auctionList")
public class ViewAuctionController {
//	private JuseomFacade juseom;
//	
//	@Autowired
//	public void setJuseom(JuseomFacade juseom) {
//		this.juseom = juseom;
//	}
//	
//	@RequestMapping("/shop/viewAuction.do")
//	public String handleRequest(ModelMap model) throws Exception {
//		PagedListHolder<Auction> auctionList = new PagedListHolder<Auction>(this.juseom.getBookListByAuction());
//		auctionList.setPageSize(4);
//		model.put("auctionList", auctionList);
//		return "Auction";
//	} 
}