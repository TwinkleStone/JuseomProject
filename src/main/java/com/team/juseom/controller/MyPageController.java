package com.team.juseom.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import com.team.juseom.service.JuseomFacade;

@Controller
public class MyPageController implements ApplicationContextAware {
	@Value("myPage")
	private String formViewName;
	
	@RequestMapping(value="/user/mypage.do", method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@Autowired
	private JuseomFacade juseom;
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	private WebApplicationContext context;	
	private String uploadDir;
	
	public void setApplicationContext(ApplicationContext appContext)
		throws BeansException {
		this.context = (WebApplicationContext) appContext;
		this.uploadDir = context.getServletContext().getRealPath("/upload/");
	}
	
	@ModelAttribute("userForm")
	public UserForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		return new UserForm(
				juseom.getUserById(userSession.getUser().getUserId()));
	}
	
	@RequestMapping(value="/user/mypage/profileUpload.do", method=RequestMethod.POST)
	public String submitReport(
			@RequestParam("report") MultipartFile report,
			@ModelAttribute("userForm") UserForm userForm,
			HttpSession session,
			Model model) {
		uploadFile(report);	
		
        String filename = report.getOriginalFilename();
		
		session.setAttribute("filename", filename);
        model.addAttribute("filename", filename);
		return formViewName;
	}
	
	private void uploadFile(MultipartFile report) {
		File file = new File(this.uploadDir + report.getOriginalFilename());
		System.out.println(file.getPath());
		try {
			report.transferTo(file);
		} catch (IllegalStateException | IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/user/mypage/edit.do", method=RequestMethod.POST)
	public String step2(HttpServletRequest request, HttpSession session,
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, Model model) throws Exception {
		try {
			userForm.getUser().setProfilePicUrl(session.getAttribute("filename").toString());
			juseom.updateUser(userForm.getUser());
		} catch (DataIntegrityViolationException ex) {
			return formViewName; 
		}
		System.out.println(userForm.getUser().getProfilePicUrl());
		UserSession userSession = new UserSession(juseom.getUserById(userForm.getUser().getUserId()));
		session.setAttribute("userSession", userSession);
		session.removeAttribute("filename");
		return formViewName;
	}
}
