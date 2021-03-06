package com.stackroute.juggler.controller;

import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.juggler.model.Token;
import com.stackroute.juggler.model.User;
import com.stackroute.juggler.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
	public User registerUser(@RequestBody User user) {
		return userService.save(user);
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
		public ResponseEntity<?> login(@RequestBody User login) throws ServletException {

			String jwtToken = "";

			if (login.getEmail() == null || login.getPassword() == null) {
				throw new ServletException("Please fill in username and password");
			}

			String email = login.getEmail();
			String password = login.getPassword();

			User user = userService.findByEmail(email);

			if (user == null) {
				throw new ServletException("User email not found.");
			}

			String pwd = user.getPassword();

			if (!password.equals(pwd)) {
				throw new ServletException("Invalid login. Please check your name and password.");
			}

			jwtToken = Jwts.builder().setSubject(email).claim("roles", "user").setIssuedAt(new Date())
	.signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        Token t =new Token();
		t.setToken(jwtToken);
		return new ResponseEntity<>(t,HttpStatus.CREATED);
	}
}
