package com.team.juseom.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team.juseom.domain.Rate;
import com.team.juseom.service.JuseomFacade;

@Controller
public class ViewRateController {
	private JuseomFacade juseom;
	
	@Autowired
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@RequestMapping("/rateList.do")
	public String getRateList(
			@RequestParam("ratedId") String ratedId,
			Model model
			) {
		List<Rate> rates = juseom.getRateListByUser(ratedId);
		model.addAttribute("ratedId", ratedId);
		model.addAttribute("ratingList", rates);
		
		return "RatingList";
	}
}
