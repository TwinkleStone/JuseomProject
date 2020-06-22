package com.team.juseom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.otmChat;
import com.team.juseom.service.JuseomFacade;

@Controller
public class OtmChatController {
	@Value("chatRoom")
	private String CHAT_VIEW;
	
	@Autowired
	private JuseomFacade juseom;
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@ModelAttribute("chatting")
	public otmChat formBackingObject(HttpServletRequest request, SessionStatus status, HttpSession session) 
			throws Exception {
		return new otmChat();
	}
	
	@RequestMapping(value="/chatRoom.do", method=RequestMethod.GET)
	public String chatRoom(
			@RequestParam(value="bookId", required = false) String bookId,
			@RequestParam(value="sellerId", required = false) String sellerId,
			HttpSession session,
			Model model,
			HttpServletRequest request
			) {
		System.out.println(bookId + ", " + sellerId);
		List<otmChat> list = new ArrayList<otmChat>();
		if (juseom.getOtmChatList(bookId).size() == 0) {
			otmChat chat = new otmChat(bookId, sellerId);
			juseom.insertOtmChat(chat);
		}
		list = juseom.getOtmChatList(bookId);
		model.addAttribute("chatList", list);
		session.setAttribute("sellerId", sellerId);
		session.setAttribute("bookId", bookId);
		return CHAT_VIEW;
	}
	
	@RequestMapping(value="/chat.do", method=RequestMethod.POST)
	public String chattingSubmit(
			@ModelAttribute("chatting") otmChat otmChat,
			HttpSession session,
			Model model,
			HttpServletRequest request
			) {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
		String sellerId = session.getAttribute("sellerId").toString();
		String bookId = session.getAttribute("bookId").toString();
		System.out.println(sellerId + ", " + bookId);
		List<otmChat> list = new ArrayList<otmChat>();
		list = juseom.getOtmChatList(bookId);
		if (sellerId.equals(userId)) {
			otmChat.setSellerId(sellerId);
		}
		else {
			otmChat.setBuyerId(userId);
			
		}
		otmChat.setBookId(bookId);
		System.out.println(otmChat.getBookId() + ", " + otmChat.getSellerId() + ", " + otmChat.getChat());
		juseom.insertOtmChat(otmChat);
		
		//model.notify();
		list.add(otmChat);
		System.out.println(list.size());
		model.addAttribute("chatList", list);
		return CHAT_VIEW;
	}
}
