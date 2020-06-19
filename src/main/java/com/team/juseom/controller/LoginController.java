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

@Controller
@SessionAttributes("loginUser")
public class LoginController {
	private final String LOGIN_FORM = "loginForm";
	
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

}
