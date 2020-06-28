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

import com.team.juseom.domain.OtoStatus;
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
			@RequestParam(value = "sellerId", required = false) String sellerId,
			@RequestParam(value = "buyerId", required = false) String buyerId, HttpSession session, Model model,
			HttpServletRequest request) {
		
		UserSession userSession = 
				(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
		String chattingRoomId = bookId + "_" + buyerId;
	
		//건들면 안됨
		list = juseom.selectOtoChatByChattingRoomId(chattingRoomId);
		
		if (list.size() == 0) {
			otoChat chat = new otoChat(chattingRoomId, bookId, sellerId, userId);
			Date from = new Date();
			SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String to = transFormat.format(from);

			chat.setChatTime(to);
			OtoStatus status = new OtoStatus();
			status.setChattingRoomId(chattingRoomId);
			status.setBuyerId(buyerId);
			status.setSellerId(sellerId);
			status.setBookId(bookId);
			juseom.insertStatus(status);
			juseom.insertOtoChat(chat);
		}
		

		model.addAttribute("chatList", list);
		session.setAttribute("sellerId", sellerId);
		session.setAttribute("bookId", bookId);
		session.setAttribute("buyerId", buyerId);
		return CHAT_VIEW;
	}
	
	@RequestMapping(value="/user/chat.do")
    @ResponseBody
    public String ajax_addComment(@ModelAttribute("otoChat") otoChat otoChat, HttpServletRequest request) throws Exception{
		List<otoChat> commentVO;
		
		HttpSession session = request.getSession();
		UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
		String userId = userSession.getUser().getUserId();
        String buyerId = session.getAttribute("buyerId").toString();
        String bookId = session.getAttribute("bookId").toString();
        String chattingRoomId = bookId + "_" + buyerId;
        Date from = new Date();
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String to = transFormat.format(from);
		
		String chat = request.getParameter("comment");
		try{
			otoChat.setSenderId(userId);
			otoChat.setChattingRoomId(chattingRoomId);
			otoChat.setChatTime(to);
			otoChat.setSellerId(userId);
			otoChat.setBookId(bookId);
			otoChat.setChat(chat);
        	juseom.insertOtoChat(otoChat);
            
        } catch (Exception e){
            e.printStackTrace();
        }
		commentVO = juseom.selectOtoChatByChattingRoomId(chattingRoomId);
		commentVO.add(otoChat);
    	
        return "success";
    }
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/user/chatList.do", produces="application/json; charset=utf8")
    @ResponseBody
    public ResponseEntity ajax_commentList(@ModelAttribute("otoChat") otoChat chat, HttpServletRequest request,
    		HttpSession session) throws Exception{
		List<otoChat> commentVO;
		
        HttpHeaders responseHeaders = new HttpHeaders();
        ArrayList<HashMap> hmlist = new ArrayList<HashMap>();
        UserSession userSession = (UserSession) WebUtils.getSessionAttribute(request, "userSession");
        String userId = userSession.getUser().getUserId();
        String buyerId = session.getAttribute("buyerId").toString();
        String bookId = session.getAttribute("bookId").toString();
        String chattingRoomId = bookId + "_" + buyerId;
        
        // 해당 게시물 댓글
        commentVO = juseom.selectOtoChatByChattingRoomId(chattingRoomId);
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
