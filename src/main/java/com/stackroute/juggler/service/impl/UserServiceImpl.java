package com.stackroute.juggler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.juggler.model.User;
import com.stackroute.juggler.repository.UserDao;
import com.stackroute.juggler.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;

	public User save(User user) {
		return userDao.save(user);
	}

	public User findByEmail(String email) {
		return userDao.findByEmail(email);
	}

	@Override
	public User findByPhoneNumber(Long phoneNumber) {
		
		return userDao.findByPhoneNumber(phoneNumber);
	}
	
	
}
