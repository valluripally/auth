package com.stackroute.juggler.service;

import com.stackroute.juggler.model.User;

public interface UserService {
	User save(User user);

	User findByEmail(String email);
	User findByPhoneNumber(Long phoneNumber);

}
