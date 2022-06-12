package com.techleads.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techleads.app.model.Users;
import com.techleads.app.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public Users saveUser(Users user) {
		Users savedUser = userRepository.save(user);
		return savedUser;
	}

}
