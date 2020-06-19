package com.team.juseom.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.User;
import com.team.juseom.service.AuthenticationException;
import com.team.juseom.service.MemberService;

@Controller
@SessionAttributes("loginUser")
public class LoginController {
	private final String LOGIN_FORM = "loginForm";
	
	@Autowired
	private MemberService memberService;

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 로그인 폼으로 이동
	@RequestMapping("/user/loginForm.do")
	public String loginForm() {
		//model.addAttribute("loginUser", new User());
		return LOGIN_FORM;
	}
	
	// 로그인 메소드
		@RequestMapping(value="/user/login.do", method=RequestMethod.POST)
		public String login(Model model, @ModelAttribute("user") @Valid User user, HttpServletRequest request,
						HttpServletResponse response, HttpSession session, BindingResult bindingResult) throws SQLException, IOException {
			return LOGIN_FORM;
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
	public String submit(@Valid @ModelAttribute("user") LoginCommand loginCommand, 
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
