/*
 * package com.team.juseom.controller;
 * 
 * import java.io.UnsupportedEncodingException; import java.net.URLEncoder;
 * import java.util.List;
 * 
 * import javax.servlet.http.HttpServletRequest; import
 * javax.servlet.http.HttpSession;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.support.PagedListHolder; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import org.springframework.ui.ModelMap; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.PostMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod; import
 * org.springframework.web.bind.annotation.RequestParam; import
 * org.springframework.web.util.WebUtils;
 * 
 * import com.team.juseom.domain.Book; import
 * com.team.juseom.service.JuseomFacade;
 * 
 * @Controller public class SaleListController { private static final String
 * FORM_VIEW = "UserBookList";
 * 
 * @Autowired private JuseomFacade juseom; public void setJuseom(JuseomFacade
 * juseom) { this.juseom = juseom; }
 * 
 * @RequestMapping(value="/user/saleList.do", method=RequestMethod.GET) public
 * String searchDetail(HttpServletRequest request, HttpSession session, Model
 * model) throws Exception { UserSession userSession = (UserSession)
 * WebUtils.getSessionAttribute(request, "userSession"); String userId =
 * userSession.getUser().getUserId(); System.out.println(userId); List<Book>
 * list = juseom.searchBookByUserId(userId); PagedListHolder<Book> rsltList =
 * new PagedListHolder<Book>(list); model.addAttribute("searchList", rsltList);
 * return FORM_VIEW; } }
 */