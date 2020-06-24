package com.team.juseom.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team.juseom.domain.Applier;
import com.team.juseom.domain.Bidder;
import com.team.juseom.service.JuseomFacade;

@Controller
public class ApplyController {
	
	@Autowired
	JuseomFacade juseomFacade;
	
	@ModelAttribute("applier")
	public Applier viewBiddingForm(
			@RequestParam("shareId") int shareId) {
		Applier applier = new Applier();
		applier.setShareId(shareId);
		return applier;
	}
	
	@RequestMapping("/apply.do")
	public String insertApply(
			@ModelAttribute("applier") Applier applier,
			HttpServletResponse response,
			Model model) throws IOException {
		
		applier.setUserId("test");
		applier.setStatus("lOSE");
		
		int searchResult = juseomFacade.searchApplier(applier);
		
		if (searchResult > 0) {
			/*
			 * response.setContentType("text/html); charset=UTF-8"); PrintWriter out =
			 * response.getWriter(); out.println("<script>alert('권한이 없습니다.');</script>");
			 * out.flush();
			 */
		} else {
			juseomFacade.insertApplier(applier);
		}
		
		return "redirect:/index";
	}
	
	
	

}
