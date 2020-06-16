package com.team.juseom.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.team.juseom.domain.User;
import com.team.juseom.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
//	@RequestMapping("/index")
//	public String getSchedule(Model model) {
//		List<User> members = memberService.getMembers();
//		model.addAttribute("members", members);
//		return "member/schedule";
//	}
	
	@RequestMapping("/members/get")
	public String getMember(@RequestParam("id") String memberId, Model model) {
		User mi = memberService.getUser(memberId);
		if (mi == null) {
			return "member/memberNotFound";
		}
		model.addAttribute("member", mi);
		return "member/memberDetail";
	}
	
	@RequestMapping("/members/delete")
	public String deleteMember(@RequestParam("id") String memberId, Model model, HttpSession session) {
		memberService.deleteMember(memberId);
		session.removeAttribute("loginUserId");		
		session.invalidate();
		return "redirect:/index";
	}
}
