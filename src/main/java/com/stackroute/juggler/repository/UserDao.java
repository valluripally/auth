package com.stackroute.juggler.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.juggler.model.User;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
	User save(User user);

	User findByEmail(String email);
	User findByPhoneNumber(Long phoneNumber);
}
