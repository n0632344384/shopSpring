package com.shop.dao;

import java.util.List;

public interface UserDao {

	// create new user
	User createUser(User user);

	// get all existing users
	List<User> getAllUsers();

	// get user by id
	User getUserById(int id);

	// update user by user and return updated user id 
	int updateUser(User user);

	// delete user by user and return deleted user id
	int deleteUser(User user);
	
	// delete user by id and return if user deleted
	boolean deleteUser(int id);

	// check if login exists and return if login exists
	boolean checkLogin(String login);

	// check if user with this login and pass exists
	boolean checkLoginPass(String login, String pass);

	// get first name by login and return first name
	String getFirstNameByLogin(String login);
	
	// get role by login return role number
	int getRoleByLogin(String login);
}