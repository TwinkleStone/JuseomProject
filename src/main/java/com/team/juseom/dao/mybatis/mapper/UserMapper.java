package com.team.juseom.dao.mybatis.mapper;

import java.util.List;

import com.team.juseom.domain.User;

/**
 * @author Eduardo Macarron
 *
 */
public interface UserMapper {

	User getUserByUserId(String username);

	User getUserByUserIdAndPassword(String username, String password);

	List<String> getUserIdList();
  
	void insertUser(User user);

	void updateUser(User user);

}
