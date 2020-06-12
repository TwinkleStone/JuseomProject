package com.team.juseom.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.juseom.domain.Sale;
import com.team.juseom.service.JuseomFacade;

@Controller
public class ViewSaleController {
	private JuseomFacade juseom;
	
	@Autowired
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@RequestMapping({"/index.do", "/index"})
	public String sales(ModelMap model) {
//		PagedListHolder<Sale> saleList = new PagedListHolder<Sale>(this.juseom.getBookListBySale());
//		saleList.setPageSize(4);
		List<Sale> saleList = new ArrayList<Sale>(this.juseom.getBookListBySale());
		model.put("saleList", saleList);
		return "index";	//index view로 이동
	}
}