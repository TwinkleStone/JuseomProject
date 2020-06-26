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
		List<otoChat> bookIdList = juseom.getOtoChatListBookId(userId);
		//System.out.println(bookIdList.size());
		List<OtoChatList> list = new ArrayList<OtoChatList>();
		for (otoChat o : bookIdList) {
			OtoChatList ch = new OtoChatList();
			String chattRoomId = o.getChattingRoomId();
			String[] roomId = chattRoomId.split("_");
			ch.setBook(juseom.getOtoChatListBookInfo(roomId[0], userId));
			System.out.println(ch.getBook().getName() + ", " + ch.getBook().getBookId());
			ch.setBuyerId(roomId[1]);
			ch.setSellerId(ch.getBook().getUserId());
			list.add(ch);
		}
		PagedListHolder<OtoChatList> rsltList = new PagedListHolder<OtoChatList>(list);
		model.addAttribute("chatList", rsltList);
		return FORM_VIEW;
	}
}
