package com.team.juseom.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import com.team.juseom.domain.otmChat;
import com.team.juseom.service.JuseomFacade;

@Controller
public class OtmChatController {
	@Value("chatRoom")
	private String CHAT_VIEW;
	List<otmChat> list;

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

	@RequestMapping(value = "/chatRoom.do", method = RequestMethod.GET)
	public String chatRoom(@RequestParam(value = "bookId", required = false) String bookId,
			@RequestParam(value = "sellerId", required = false) String sellerId, HttpSession session, Model model,
			HttpServletRequest request) {
		
		//건들면 안됨
		list = juseom.getOtmChatList(bookId);
		
		if (list.size() == 0) {
			otmChat chat = new otmChat(bookId, sellerId);
			Date from = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String to = transFormat.format(from);

			chat.setChatTime(to);
			juseom.insertOtmChat(chat);
		}
		
		model.addAttribute("chatList", list);
		session.setAttribute("sellerId", sellerId);
		session.setAttribute("bookId", bookId);
		return CHAT_VIEW;
	}

	@RequestMapping(value = "/chat.do", method = RequestMethod.POST)
	public ModelAndView chattingSubmit(

			@ModelAttribute("chatting") otmChat otmChat, HttpSession session, Model model, HttpServletRequest request) {

		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
		String sellerId = session.getAttribute("sellerId").toString();
		String bookId = session.getAttribute("bookId").toString();

		//list = juseom.getOtmChatList(bookId);
		
		
		otmChat.setSellerId(sellerId);
		otmChat.setSenderId(userId);
		otmChat.setBookId(bookId);

	
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(from);

		otmChat.setChatTime(to);
		
		list.add(otmChat);

		juseom.insertOtmChat(otmChat);
	
		
		//새로 get
		//list = juseom.getOtmChatList(bookId);
		
		//model.addAttribute("chatList", list);
		return new ModelAndView(CHAT_VIEW, "chatList", list);
	}
}
