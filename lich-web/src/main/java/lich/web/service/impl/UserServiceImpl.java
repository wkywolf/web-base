package lich.web.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lich.maven.dao.CommonDao;
import lich.web.model.User;
import lich.web.service.UserService;
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CommonDao<User> dao;
	
	@Override
	public List<User> findUsers() {
		return dao.findAll(User.class);
	}

	@Override
	public User findUserById(Long id) {
		return dao.getById(User.class, id);
	}

	@Override
	public void saveOrUpdateUser(User user) {
		dao.updateObject(user);
	}

	@Override
	public void delete(Long id) {
		dao.removeObject(User.class, id);
	}

}
