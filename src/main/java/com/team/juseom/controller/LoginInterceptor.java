package com.team.juseom.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.util.WebUtils;

public class LoginInterceptor implements HandlerInterceptor {
	private final String LOGIN_FORM = "loginForm";

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		UserSession userSession = (UserSession)WebUtils.getSessionAttribute(request, "userSession");
		if (userSession == null || userSession.getUser() == null) {
			String url = request.getRequestURL().toString(); 
			String query = request.getQueryString();
			System.out.println(query);
			
			ModelAndView modelAndView = new ModelAndView(LOGIN_FORM);
			
			if (query != null) {
				modelAndView.addObject("loginForwardAction", url+"?"+query);
			}
			else {
				modelAndView.addObject("loginForwardAction", url);
			}
			throw new ModelAndViewDefiningException(modelAndView);
		} else {
			return true;
		}
	}
}
