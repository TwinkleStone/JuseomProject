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
import com.team.juseom.domain.otoChat;
import com.team.juseom.service.JuseomFacade;

@Controller
public class OtoChatController {
	@Value("chatRoomOto")
	private String CHAT_VIEW;
	List<otoChat> list;

	@Autowired
	private JuseomFacade juseom;

	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}

	@ModelAttribute("chatting")
	public otoChat formBackingObject(HttpServletRequest request, SessionStatus status, HttpSession session)
			throws Exception {
		return new otoChat();
	}

	@RequestMapping(value = "/user/chatRoom.do", method = RequestMethod.GET)
	public String chatRoom(@RequestParam(value = "bookId", required = false) String bookId,
			@RequestParam(value = "sellerId", required = false) String sellerId, HttpSession session, Model model,
			HttpServletRequest request) {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
		String chattingRoomId = bookId + "_" + userId;
		System.out.println(chattingRoomId);
	
		//건들면 안됨
		list = juseom.selectOtoChatByChattingRoomId(chattingRoomId);
		
		if (list.size() == 0) {
			otoChat chat = new otoChat(chattingRoomId, bookId, sellerId, userId);
			Date from = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String to = transFormat.format(from);

			chat.setChatTime(to);
			juseom.insertOtoChat(chat);
		}
		

		model.addAttribute("chatList", list);
		session.setAttribute("sellerId", sellerId);
		session.setAttribute("bookId", bookId);
		return CHAT_VIEW;
	}
	
	@RequestMapping(value = "/user/chatRoomSeller.do", method = RequestMethod.GET)
	public String chatRoomSeller(@RequestParam(value = "bookId", required = false) String bookId,
			@RequestParam(value = "buyerId", required = false) String buyerId, HttpSession session, Model model,
			HttpServletRequest request) {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
		String chattingRoomId = bookId + "_" + buyerId;
		System.out.println(chattingRoomId);
	
		//건들면 안됨
		list = juseom.selectOtoChatByChattingRoomId(chattingRoomId);
		
		if (list.size() == 0) {
			otoChat chat = new otoChat(chattingRoomId, bookId, userId, buyerId);
			Date from = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String to = transFormat.format(from);

			chat.setChatTime(to);
			juseom.insertOtoChat(chat);
		}
		

		model.addAttribute("chatList", list);
		session.setAttribute("sellerId", userId);
		session.setAttribute("bookId", bookId);
		session.setAttribute("buyerId", buyerId);
		return CHAT_VIEW;
	}

	@RequestMapping(value = "/user/chat.do", method = RequestMethod.POST)
	public ModelAndView chattingSubmit(

			@ModelAttribute("chatting") otoChat otoChat, HttpSession session, Model model, HttpServletRequest request) {

		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String bookId = session.getAttribute("bookId").toString();
		String sellerId = session.getAttribute("sellerId").toString();
		String userId = userSession.getUser().getUserId();
		String buyerId;
		if (session.getAttribute("buyerId") == null) {
			buyerId = userSession.getUser().getUserId();
		}
		else {
			buyerId = session.getAttribute("buyerId").toString();
		}
		String chattingRoomId = bookId + "_" + buyerId;
		
		//list = juseom.getotoChatList(bookId);
		
		
		otoChat.setSellerId(sellerId);
		otoChat.setSenderId(userId);
		otoChat.setBookId(bookId);
		otoChat.setChattingRoomId(chattingRoomId);
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(from);

		otoChat.setChatTime(to);
		
		list.add(otoChat);

		juseom.insertOtoChat(otoChat);
		
		//새로 get
		//list = juseom.getotoChatList(bookId);
		
		
		//model.addAttribute("chatList", list);
		return new ModelAndView(CHAT_VIEW, "chatList", list);
	}
}
