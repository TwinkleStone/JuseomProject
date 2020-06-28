package com.team.juseom.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;
import com.team.juseom.domain.otmChat;
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
	
	@RequestMapping(value="/chat.do")
    @ResponseBody
    public String ajax_addComment(@ModelAttribute("otmChat") otmChat otmChat, HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
        String bookId = session.getAttribute("bookId").toString();
        String sellerId = session.getAttribute("sellerId").toString();
        Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(from);
		
		String chat = request.getParameter("comment");
		try{
			otmChat.setSenderId(userId);
			otmChat.setChatTime(to);
			otmChat.setSellerId(sellerId);
			otmChat.setBookId(bookId);
			otmChat.setChat(chat);
        	juseom.insertOtmChat(otmChat);
            
        } catch (Exception e){
            e.printStackTrace();
        }
        return "success";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/chatList.do", produces="application/json; charset=utf8")
    @ResponseBody
    public ResponseEntity ajax_commentList(@ModelAttribute("otmChat") otmChat chat, HttpServletRequest request,
    		HttpSession session) throws Exception{
		List<otmChat> commentVO;
		
        HttpHeaders responseHeaders = new HttpHeaders();
        ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
        String bookId = session.getAttribute("bookId").toString();
        
        // 해당 게시물 댓글
        commentVO = juseom.getOtmChatList(bookId);
        if(commentVO.size() > 0){
            for(int i=1; i<commentVO.size(); i++){
                HashMap hm = new HashMap();
                hm.put("writer", commentVO.get(i).getSenderId());
                hm.put("comment", commentVO.get(i).getChat());
                
                hmlist.add(hm);
            }
            
        }
        
        JSONArray json = new JSONArray(hmlist);        
        return new ResponseEntity(json.toString(), responseHeaders, HttpStatus.CREATED);
        
    }
}
