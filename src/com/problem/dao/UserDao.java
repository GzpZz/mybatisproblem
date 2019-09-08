package com.problem.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.problem.entity.User;

public interface UserDao {
	User findByUserNameAndPassword(@Param("u")User user);
	
	List<User> findAll();
}
