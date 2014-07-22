package lich.web.service;

import java.util.List;

import lich.web.model.User;

public interface UserService {

	public List<User> findUsers();
	
	public User findUserById(Long id);
	
	public void saveOrUpdateUser(User user);
	
	public void delete(Long id);
	
}
