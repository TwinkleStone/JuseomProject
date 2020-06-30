package com.team.juseom.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

import com.team.juseom.domain.OtoStatus;
import com.team.juseom.domain.Rate;
import com.team.juseom.service.JuseomFacade;
import com.team.juseom.service.RatingFormValidator;

@Controller
public class InsertRatingController {
	
	@Autowired
	private JuseomFacade juseomFacade;
	
	@ModelAttribute("rate")
	public Rate createRateForm(
			@RequestParam("ratedId") String ratedId,
			@RequestParam("raterId") String raterId,
			@RequestParam("bookId") int bookId) {
		Rate rate = new Rate();
		
		String commName = juseomFacade.getCommnameByUserId(ratedId);
		
		rate.setBookId(bookId);
		rate.setRatedId(commName);//별점이 매겨지는 사람
		rate.setRaterId(raterId);//별점을 매기는 사람

		return rate;
	}


	@GetMapping("/rating.do") 
	public String rateForm() { 
		return "RatingForm"; 
	}
	
	@PostMapping("/rating.do")
	public String insertRate(
			@ModelAttribute("rate") Rate formData,
			HttpServletResponse response,
			BindingResult result, HttpSession session,
			Model model) throws IOException {
		new RatingFormValidator().validate(formData, result);
		
		
		if (result.hasErrors()) {
			return "RatingForm";
		}
		
		int searchRate = juseomFacade.searchRate(formData.getBookId(), formData.getRaterId());
		
		if (searchRate >= 1) {
			System.out.println("이미 별점을 남겼습니다.");
			List<Rate> rates = juseomFacade.getRateListByUser(formData.getRatedId());
			model.addAttribute("ratingList", rates);
			return "RatingList";
		} else {
			juseomFacade.insertRate(formData);
		}
		
		//book status 변경용 코드
		
		String bookId = Integer.toString(formData.getBookId());
		UserSession userSession = (UserSession)session.getAttribute("userSession");
		String userId = userSession.getUser().getUserId();
		String sellerId = session.getAttribute("sellerId").toString();
		String buyerId;
		
		String sellerCommName = juseomFacade.getCommnameByUserId(sellerId);
		String buyerCommName = null;

		if (session.getAttribute("buyerId") == null) {
			buyerId = userSession.getUser().getUserId();
			buyerCommName = juseomFacade.getCommnameByUserId(buyerId);
			model.addAttribute("raterName", sellerCommName);
			model.addAttribute("ratedName", buyerCommName);
		}
		else {
			buyerId = session.getAttribute("buyerId").toString();
			
			buyerCommName = juseomFacade.getCommnameByUserId(buyerId);
			if (sellerId.equals(formData.getRaterId())) {
				model.addAttribute("raterName", sellerCommName);
				model.addAttribute("ratedName", buyerCommName);
			} else {
				model.addAttribute("raterName", buyerCommName);
				model.addAttribute("ratedName", sellerCommName);	
			}
		}
		String chattingRoomId = bookId + "_" + buyerId;
		OtoStatus status = juseomFacade.getStatusByChattingRoomId(chattingRoomId);
		
		if (sellerId.equals(userId)) {
			status.setSellerStatus("CLOSE");
			juseomFacade.updateSellerStatus(status);
		}
		else {
			status.setBuyerStatus("CLOSE");
			juseomFacade.updateBuyerStatus(status);
		}
		
		if (status.getBuyerStatus().equals("CLOSE") && status.getSellerStatus().equals("CLOSE")) {
			juseomFacade.updateBookStatus(bookId);
		}
		
		return "RatingConfirm";

	}
	
}