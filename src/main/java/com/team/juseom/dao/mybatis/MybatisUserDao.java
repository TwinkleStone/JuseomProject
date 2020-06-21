package com.team.juseom.dao.mybatis;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.team.juseom.dao.UserDao;
import com.team.juseom.dao.mybatis.mapper.UserMapper;
import com.team.juseom.domain.User;

/**
 * @author Juergen Hoeller
 * @author Colin Sampaleanu
 */
@Repository
public class MybatisUserDao implements UserDao {

	@Autowired
	private UserMapper userMapper;

	public void insertUser(User user) throws DataAccessException {
		userMapper.insertUser(user);
	}

	public void updateUser(User user) throws DataAccessException {
		userMapper.updateUser(user);
	}
 
	public List<String> getUserIdList() throws DataAccessException {
		return userMapper.getUserIdList();
	}

	public User getUserById(String userId) throws DataAccessException {
		if (userMapper.getUserById(userId) != null) {
			return userMapper.getUserById(userId);
		}
		return null;
	}

	public User getUserIdPassword(String userId, String password) throws DataAccessException {
		if (userMapper.getUserByIdPassword(userId, password) != null) {
			return userMapper.getUserByIdPassword(userId, password);
		}
		return null;
	}
}