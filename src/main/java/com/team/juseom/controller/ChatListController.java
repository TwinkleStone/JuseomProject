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
		System.out.println("1번 " + userId);
		//List<Book> list = juseom.searchBookByUserId(userId);
		List<otoChat> list = juseom.getOtoChatList(userId);
		System.out.println(list.size());
		List<Book> bookList = new ArrayList<Book>();
		for (otoChat o : list) {
			bookList.add(juseom.getOtoChatListBookInfo(o.getBookId(), userId));
			System.out.println(o.getBookId()+ ", " + userId);
		}
		//List<Book> list = juseom.getOtoChatListBookInfo(bookId, userId);
		//PagedListHolder<Book> rsltList = new PagedListHolder<Book>(list);
		//model.addAttribute("searchList", rsltList);
		PagedListHolder<Book> rsltList = new PagedListHolder<Book>(bookList);
		System.out.println(rsltList.getPageList().size());
		model.addAttribute("chatList", rsltList);
		return FORM_VIEW;
	}
}
