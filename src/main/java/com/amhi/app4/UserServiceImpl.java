package com.amhi.app4;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	final static Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public boolean saveUser(User user) {
		logger.info("going to save, now at service level");
		return userDao.saveUser(user);

	}

	@Override
	public List<User> getUsers() {
		return userDao.getUsers();
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public boolean deletetUserById(Long id) {
		return userDao.deletetUserById(id);
	}

	@Override
	public User getUserByEmail(String email) {
		return userDao.getUserByEmail(email);
	}

	@Override
	public User login(String email, String password) {
		return userDao.login(email, password);

	}

}
