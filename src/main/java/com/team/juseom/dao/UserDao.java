package com.team.juseom.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

import com.team.juseom.domain.User;

public interface UserDao {
	
	public User getUserById(String userId) throws DataAccessException;
	public User getUserIdPassword(String userId, String password) throws DataAccessException;
	public void insertUser(User user) throws DataAccessException;
	public void updateUser(User user) throws DataAccessException;
	public List<String> getUserIdList() throws DataAccessException;
	public String getCommnameByUserId(String userId);

}
