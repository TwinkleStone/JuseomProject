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
		System.out.println(bookId + ", " + sellerId);
		
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
		
		//System.out.println(list.get(1).getSenderId());
		model.addAttribute("chatList", list);
		session.setAttribute("sellerId", sellerId);
		session.setAttribute("bookId", bookId);
		return CHAT_VIEW;
	}

	/*
	 * @RequestMapping(value = "/chat.do") public ModelAndView
	 * chattingSubmit(HttpServletRequest request, @RequestParam("chat") String
	 * chatting,
	 * 
	 * @RequestParam(value = "forwardAction", required = false) String
	 * forwardAction, HttpSession session, Model model) throws Exception {
	 * UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request,
	 * "userSession"); String userId = userSession.getUser().getUserId(); String
	 * sellerId = session.getAttribute("sellerId").toString(); String bookId =
	 * session.getAttribute("bookId").toString(); otmChat otmChat = new otmChat();
	 * // list = new ArrayList<otmChat>(); list = juseom.getOtmChatList(bookId);
	 * otmChat.setSellerId(sellerId); otmChat.setSenderId(userId);
	 * otmChat.setBookId(bookId);
	 * 
	 * // System.out.println(otmChat.getBookId() + ", " + otmChat.getSellerId() +
	 * ", " // + otmChat.getChat()); Date from = new Date(); SimpleDateFormat
	 * transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String to =
	 * transFormat.format(from);
	 * 
	 * otmChat.setChatTime(to);
	 * 
	 * // model.notify(); list.add(otmChat);
	 * 
	 * juseom.insertOtmChat(otmChat);
	 * 
	 * System.out.println(list.size()); model.addAttribute("chatList", list);
	 * model.addAttribute("userSession", userSession); if (forwardAction != null)
	 * return new ModelAndView("redirect:" + forwardAction); else return new
	 * ModelAndView(CHAT_VIEW); }
	 */

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

		System.out.println(otmChat.getBookId() + ", " + otmChat.getSellerId() + ", " + otmChat.getChat());
		
		Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(from);

		otmChat.setChatTime(to);
		
		System.out.println(list.size() + "곧 리스트를  add함" + otmChat.getChat() + "을 add함");
		list.add(otmChat);

		juseom.insertOtmChat(otmChat);
		System.out.println(list.size());
		
		//새로 get
		//list = juseom.getOtmChatList(bookId);
		
		System.out.println("add하고 get하지 않은 사이즈" + list.size());
		//model.addAttribute("chatList", list);
		return new ModelAndView(CHAT_VIEW, "chatList", list);
	}
}
