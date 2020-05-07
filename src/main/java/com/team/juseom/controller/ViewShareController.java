package com.team.juseom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

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
	
	@RequestMapping("/shop/viewShare.do")
	public String handleRequest(ModelMap model) throws Exception {
		PagedListHolder<Share> shareList = new PagedListHolder<Share>(this.juseom.getBookListByShare());
		shareList.setPageSize(4);
		model.put("shareList", shareList);
		return "Share";
	} 
}