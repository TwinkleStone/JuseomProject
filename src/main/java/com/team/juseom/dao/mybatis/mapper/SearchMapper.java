package com.team.juseom.dao.mybatis.mapper;

import java.util.List;

import com.team.juseom.domain.Book;

/**
 * @author Eduardo Macarron
 *
 */
public interface SearchMapper {

  List<Book> getTypeList(String tradeType);
  List<Book> searchBookList(Book book);

}
