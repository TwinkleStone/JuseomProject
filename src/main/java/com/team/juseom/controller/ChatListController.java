package com.team.juseom.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.Book;
import com.team.juseom.domain.otoChat;
import com.team.juseom.service.JuseomFacade;

@Controller
public class ChatListController {
private static final String FORM_VIEW = "UserChatList";
	
	@Autowired
	private JuseomFacade juseom;
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@RequestMapping(value="/user/chatList.do", method=RequestMethod.GET)
	public String searchDetail(HttpServletRequest request, HttpSession session,
			Model model) throws Exception {
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
		//List<otoChat> list = juseom.getOtoChatList(userId);
		List<String> bookIdList = juseom.getOtoChatListBookId(userId);
		System.out.println(bookIdList.size());
		List<Book> bookList = new ArrayList<Book>();
		for (String id : bookIdList) {
			bookList.add(juseom.getOtoChatListBookInfo(id, userId));
			System.out.println(id+ ", " + userId);
		}
		PagedListHolder<Book> rsltList = new PagedListHolder<Book>(bookList);
		model.addAttribute("chatList", rsltList);
		return FORM_VIEW;
	}
}
