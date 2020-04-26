package com.team.juseom.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.team.juseom.dao.SearchDao;
import com.team.juseom.dao.mybatis.mapper.SearchMapper;
import com.team.juseom.domain.Book;

/**
 * @author Juergen Hoeller
 * @author Colin Sampaleanu
 */
@Repository
public class MybatisSearchDao implements SearchDao {

	@Autowired
	private SearchMapper searchMapper;
	
	public List<Book> getTypeList(String tradeType) throws DataAccessException {
		return searchMapper.getTypeList(tradeType);
	}

	public List<Book> searchBookList(Book book) throws DataAccessException {
		return searchMapper.searchBookList(book);
	}
}