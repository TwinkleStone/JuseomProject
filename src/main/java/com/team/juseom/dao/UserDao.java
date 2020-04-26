package com.team.juseom.dao;

import java.util.List;

import com.team.juseom.domain.User;

public interface UserDao {
	
	  public User getUser(String userId) ;
	  public User getUser(String userId, String password) ;
	  public void insertUser(User user) ;
	  public void updateUser(User user) ;
	  public List<String> getUserIdList();

}
