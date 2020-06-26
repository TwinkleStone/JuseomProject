package com.team.juseom.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.Applier;
import com.team.juseom.domain.Share;
import com.team.juseom.service.JuseomFacade;


@Controller
public class ApplyController {
	
	@Autowired
	JuseomFacade juseomFacade;
	
	@RequestMapping(value = "/apply.do", method = RequestMethod.POST, produces = "application/json")         
	@ResponseBody  
	public Share insertApply(@RequestBody Applier applier, 
			HttpServletResponse response,
			HttpServletRequest request,
			Model model) throws IOException {
		
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
		applier.setUserId(userId);
		
		int searchResult = juseomFacade.searchApplier(applier);
		
		if (searchResult > 0) {
			System.out.println("나눔 신청 중복입니다!!");
			response.sendError(HttpServletResponse.SC_NOT_FOUND);
			return null;
		} else {
			System.out.println("올바르게 나눔 신청이 완료되었습니다.");
			juseomFacade.insertApplier(applier);
		}
		
		Share share = juseomFacade.getShare(Integer.toString(applier.getSharedId()));
		
		return share;   // convert order to JSON text in response body
	}
	
	
	

}
