package com.team.juseom.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;
import com.team.juseom.domain.Book;
import com.team.juseom.service.UserFormValidator;
import com.team.juseom.service.JuseomFacade;

@Controller
@RequestMapping({"/shop/newAccount.do","/shop/editAccount.do"})
public class UserFormController {
	@Value("EditAccountForm")
	private String formViewName;
	@Value("index")
	private String successViewName;
	private static final String[] LANGUAGES = {"english", "japanese"};
	
	@Autowired
	private JuseomFacade bookStore;
	public void setPetStore(JuseomFacade bookStore) {
		this.bookStore = bookStore;
	}

	@Autowired
	private UserFormValidator validator;
	public void setValidator(UserFormValidator validator) {
		this.validator = validator;
	}
		
	@ModelAttribute("accountForm")
	public UserForm formBackingObject(HttpServletRequest request) 
			throws Exception {
		UserSession userSession = 
			(UserSession) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession != null) {	// edit an existing account
			return new UserForm(
					bookStore.getUser(userSession.getUser().getUserId()));
		}
		else {	// create a new account
			return new UserForm();
		}
	}

	@ModelAttribute("languages")
	public String[] getLanguages() {
		return LANGUAGES;
	}

	/*@ModelAttribute("categories")
	public List<Category> getCategoryList() {
		return bookStore.getBookListByCategory();
	}*/
	
	@RequestMapping(method = RequestMethod.GET)
	public String showForm() {
		return formViewName;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(
			HttpServletRequest request, HttpSession session,
			@ModelAttribute("accountForm") UserForm userForm,
			BindingResult result) throws Exception {
		
		validator.validate(userForm, result);
		
		if (result.hasErrors()) return formViewName;
		try {
			if (userForm.isNewUser()) {
				bookStore.insertUser(userForm.getUser());
			}
			else {
				bookStore.updateUser(userForm.getUser());
			}
		}
		catch (DataIntegrityViolationException ex) {
			result.rejectValue("account.username", "USER_ID_ALREADY_EXISTS",
					"User ID already exists: choose a different ID.");
			return formViewName; 
		}
		
		UserSession userSession = new UserSession(
				bookStore.getUser(userForm.getUser().getUserId()));
		PagedListHolder<Book> mainList = new PagedListHolder<Book>(
				bookStore.getBookListByTradeType("sale"));
		mainList.setPageSize(4);
		userSession.setMainList(mainList);
		session.setAttribute("userSession", userSession);
		return successViewName;  
	}
}
