package com.team.juseom.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.team.juseom.domain.Sale;
import com.team.juseom.service.JuseomFacade;

@Controller
@SessionAttributes("saleList")
public class ViewSaleController {
	private JuseomFacade juseom;
	
	@Autowired
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@RequestMapping("/shop/viewSale.do")
	public String handleRequest(ModelMap model) throws Exception {
		PagedListHolder<Sale> saleList = new PagedListHolder<Sale>(this.juseom.getBookListBySale());
		saleList.setPageSize(4);
		model.put("saleList", saleList);
		return "Sale";
	} 
}