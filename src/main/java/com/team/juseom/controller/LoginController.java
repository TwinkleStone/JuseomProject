package com.team.juseom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.WebUtils;

import com.team.juseom.service.AuthenticationException;
import com.team.juseom.service.MemberService;

@Controller
@RequestMapping("/login")
public class LoginController {
	private final String LOGIN_FORM = "member/loginForm";
	
	@Autowired
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
/*
	@ModelAttribute
	public LoginCommand formBacking() {
		return new LoginCommand();
	}

	@GetMapping
	public String form() {
		return LOGIN_FORM;
	}
*/
	@PostMapping
	public String submit(@Valid @ModelAttribute("loginCommand") LoginCommand loginCommand, 
			BindingResult result, Model model, HttpServletRequest request) {
		if (result.hasErrors()) 
			return LOGIN_FORM;
		try {
			memberService.authenticate(loginCommand);
			WebUtils.setSessionAttribute(request, "loginUserId", loginCommand.getUserId());
			String forwardAction = loginCommand.getForwardAction();
			if (forwardAction != null) 
				return "redirect:" + forwardAction;
			else 
				return "redirect:/index";
		
		} catch (AuthenticationException e) {
			result.reject("invalidIdOrPassword", 
					new Object[] { loginCommand.getUserId() }, null);
			return LOGIN_FORM;
		}
	}

}
