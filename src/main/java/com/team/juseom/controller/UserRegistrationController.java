package com.team.juseom.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.dao.DataIntegrityViolationException;
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
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.team.juseom.domain.User;
import com.team.juseom.service.ExistUserIdException;
import com.team.juseom.service.JuseomFacade;

@Controller
@SessionAttributes("userForm")
public class UserRegistrationController implements ApplicationContextAware {
	private static final String STEP1_FORM_VIEW = "regForm1";
	private static final String STEP2_FORM_VIEW = "regForm2";
	private static final String STEP3_VIEW = "regConfirm";
	private static final String RESULT_VIEW = "registered";
	
	private WebApplicationContext context;	
	private String uploadDir;
	
	@Override
	public void setApplicationContext(ApplicationContext appContext)
		throws BeansException {
		this.context = (WebApplicationContext) appContext;
		this.uploadDir = context.getServletContext().getRealPath("/upload/");
	}
	
	@Autowired
	private JuseomFacade juseom;
	public void setJuseom(JuseomFacade juseom) {
		this.juseom = juseom;
	}
	
	@ModelAttribute("userForm")
	public UserForm formBackingObject(HttpServletRequest request, SessionStatus status, HttpSession session) 
			throws Exception {
		session.removeAttribute("userSession");
		session.invalidate();
		status.setComplete();
		return new UserForm();
	}
	
	@GetMapping("/user/register/step1.do")		// step1 요청
	public String step1() {
		return STEP1_FORM_VIEW;	// step1 form view로 이동
	}
	
	@RequestMapping(value="/user/register/step2.do", method=RequestMethod.POST)
	public String step2(HttpServletRequest request, HttpSession session,
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result) throws Exception {
		if (result.hasFieldErrors("user.userId") || result.hasFieldErrors("user.password") || 
				result.hasFieldErrors("confirmPassword") || result.hasFieldErrors("user.name") || 
				result.hasFieldErrors("user.phone") || result.hasFieldErrors("user.address1") || 
				result.hasFieldErrors("user.address2") || result.hasFieldErrors("user.address3")) {
			return STEP1_FORM_VIEW;
		}
		if (juseom.getUserById(userForm.getUser().getUserId()) != null) {
			result.rejectValue("user.userId", "UsedEmail",
					"이미 사용중인 아이디입니다.");
			return STEP1_FORM_VIEW;
		}
		if (!userForm.getConfirmPassword().equals(userForm.getUser().getPassword())) {
			result.rejectValue("confirmPassword", "NotMatchPassword",
					"비밀번호가 일치하지 않습니다.");
			return STEP1_FORM_VIEW;
		}
		System.out.println(juseom.getUserById(userForm.getUser().getProfilePicUrl()));
		UserSession userSession = new UserSession(juseom.getUserById(userForm.getUser().getUserId()));
		session.setAttribute("userSession", userSession);
		return STEP2_FORM_VIEW;
	}
	
	@RequestMapping(value="/user/register/profileUpload.do", method=RequestMethod.POST)
	public String submitReport(
			@RequestParam("report") MultipartFile report,
			@ModelAttribute("userForm") UserForm userForm,
			HttpSession session,
			Model model) {
		uploadFile(report);	
		
        String filename = report.getOriginalFilename();
        // 다른 controller에서는 이미 저장된 filename을 데이터베이스로부터 검색해 옴
        // ...
		
		userForm.setReport(report);
		userForm.getUser().setProfilePicUrl(filename);
		
        model.addAttribute("filename", filename);
		return STEP2_FORM_VIEW;
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
	
	@GetMapping("/user/register/step2.do")		// confirm -> step2 이동	
	public String step2FromStep3() {
		return STEP2_FORM_VIEW;	// step2 form view로 이동
	}
	
	@RequestMapping(value="/user/register/confirm.do", method=RequestMethod.POST)
	public String done(HttpSession session, @Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result) throws Exception {
		if (result.hasFieldErrors("user.commName")) {
			return STEP2_FORM_VIEW;
		}
		return STEP3_VIEW;
	}
	
	@RequestMapping(value="/user/register/registered.do", method=RequestMethod.POST)
	public String register(HttpSession session, @Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, SessionStatus status) throws Exception {
		try {
			if (userForm.getReport() == null) {
				userForm.getUser().setProfilePicUrl("Person.jpg");
			}
			juseom.insertUser(userForm.getUser());
		} catch (DataIntegrityViolationException ex) {
			result.rejectValue("user.userId", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return STEP1_FORM_VIEW; 
		}
		status.setComplete();
		return RESULT_VIEW;
	}

}
