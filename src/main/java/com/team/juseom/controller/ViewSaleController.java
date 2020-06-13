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
	
	@RequestMapping({"/index.do", "/index"})
	public String sales(ModelMap model) {
		PagedListHolder<Sale> saleList = new PagedListHolder<Sale>(this.juseom.getBookListBySale());
		saleList.setPageSize(9);
		model.put("saleList", saleList);
		return "index";	//index view로 이동
	}
	
	@RequestMapping("/index2.do")
	public String handleRequest(
			@RequestParam("page") String page,
			@ModelAttribute("saleList") PagedListHolder<Sale> saleList,
			BindingResult result) throws Exception {
		if (saleList == null) {
			throw new IllegalStateException("Cannot find pre-loaded category and product list");
		}
		if ("next".equals(page)) { saleList.nextPage(); }
		else if ("previous".equals(page)) { saleList.previousPage(); }
		return "index";
	}
}