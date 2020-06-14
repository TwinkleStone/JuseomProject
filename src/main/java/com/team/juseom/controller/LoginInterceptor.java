package com.team.juseom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

public class LoginInterceptor implements HandlerInterceptor {
	private final String LOGIN_FORM = "member/loginForm";
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String userId = request.getParameter("id");
		String loginUserId = (String) WebUtils.getSessionAttribute(request, "loginUserId");
		if (loginUserId == null || 
			!loginUserId.equals(userId)) {
			String url = request.getRequestURL().toString(); 
			String query = request.getQueryString();
			ModelAndView modelAndView = new ModelAndView(LOGIN_FORM); 	
			if (query != null) {
				modelAndView.addObject("forwardAction", url+"?"+query);
			}
			else {
				modelAndView.addObject("forwardAction", url);
			}
			modelAndView.addObject("loginCommand", new LoginCommand(userId));

			throw new ModelAndViewDefiningException(modelAndView);
		}
		else {
			return true;
		}
	}
}
