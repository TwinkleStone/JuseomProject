package com.team.juseom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.team.juseom.service.JuseomFacade;

/*
 * @Controller public class OtoChatController {
 * 
 * @Value("chatRoom") private String CHAT_VIEW;
 * 
 * @Autowired private JuseomFacade juseom; public void setJuseom(JuseomFacade
 * juseom) { this.juseom = juseom; }
 * 
 * @RequestMapping(value="/chatRoom.do", method=RequestMethod.POST) public
 * String chatRoom(
 * 
 * @RequestParam("bookId") String bookId, HttpSession session, Model model,
 * HttpServletRequest request ) {
 * 
 * 
 * 
 * return CHAT_VIEW; } }
 */
