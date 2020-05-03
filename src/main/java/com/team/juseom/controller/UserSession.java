package com.team.juseom.controller;

import java.io.Serializable;
import org.springframework.beans.support.PagedListHolder;

import com.team.juseom.domain.User;
import com.team.juseom.domain.Book;

/**
 * @author Juergen Hoeller
 * @since 30.11.2003
 */
@SuppressWarnings("serial")
public class UserSession implements Serializable {

	private User user;

	private PagedListHolder<Book> mainList;

	public UserSession(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

	public void setMainList(PagedListHolder<Book> mainList) {
		this.mainList = mainList;
	}

	public PagedListHolder<Book> getMainList() {
		return mainList;
	}
	
	public User test() {
		return user;
	}
}
