package com.team.juseom.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.User;
import com.team.juseom.service.AuthenticationException;
import com.team.juseom.service.JuseomFacade;

@Controller
@SessionAttributes("userSession")
public class LoginController {
	private final String LOGIN_FORM = "loginForm";
	
	@Autowired
	private JuseomFacade juseom;
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	// 로그인 폼으로 이동
	@RequestMapping("/user/loginForm.do")
	public String loginForm() {
		return LOGIN_FORM;
	}
	
	// 로그인 메소드
		@RequestMapping(value="/user/login.do")
		public ModelAndView handleRequest(HttpServletRequest request,
				@RequestParam("userId") String userId,
				@RequestParam("password") String password,
				@RequestParam(value="loginForwardAction", required=false) String forwardAction,
				Model model) throws Exception {
			System.out.println("forwardAction " + forwardAction);
			System.out.println(userId + ", " + password);
			if (userId == null || userId.equals("") || password == null || password.equals("")) {
				if(forwardAction != null)
					model.addAttribute("loginForwardAction", forwardAction);
				return new ModelAndView(LOGIN_FORM, "message", 
						"Invalid ID or password.  Login failed.");
			}
			User user = juseom.getUserIdPassword(userId, password);
			if (user == null) {
				if(forwardAction != null)
					model.addAttribute("loginForwardAction", forwardAction);
				return new ModelAndView(LOGIN_FORM, "message", 
						"Invalid ID or password.  Login failed.");
			}
			else {
				UserSession userSession = new UserSession(user);
				model.addAttribute("userSession", userSession);
				if (forwardAction != null) 
					return new ModelAndView("redirect:" + forwardAction);
				else 
					return new ModelAndView("redirect:/index");
			}
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
