package com.team.juseom.dao;

import java.util.List;
import com.team.juseom.domain.Book;

public interface SearchDao {

	public List<Book> getTypeList(String tradeType);
	
	public List<Book> searchBookList(Book book);
}
