package com.team.juseom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team.juseom.domain.Auction;
import com.team.juseom.domain.Share;
import com.team.juseom.service.JuseomFacade;

@Controller
@SessionAttributes("shareList")
public class ViewShareController {
private JuseomFacade juseom;
	
	@Autowired
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@RequestMapping("/share.do")
	public String shares(ModelMap model) {
		PagedListHolder<Share> shareList = new PagedListHolder<Share>(this.juseom.getBookListByShare());
		shareList.setPageSize(9);
		model.put("shareList", shareList);
		return "ShareList";	//AuctionList view로 이동
	}
	
	@RequestMapping("/share2.do")
	public String handleRequest(
			@RequestParam("page") String page,
			@ModelAttribute("shareList") PagedListHolder<Share> shareList,
			BindingResult result) throws Exception {
		if (shareList == null) {
			throw new IllegalStateException("Cannot find pre-loaded category and product list");
		}
		if ("next".equals(page)) { shareList.nextPage(); }
		else if ("previous".equals(page)) { shareList.previousPage(); }
		return "ShareList";
	}
}