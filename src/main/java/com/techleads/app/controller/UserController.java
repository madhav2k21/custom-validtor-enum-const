package com.techleads.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.techleads.app.model.Route;
import com.techleads.app.model.Users;
import com.techleads.app.repository.RouterRepository;
import com.techleads.app.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RouterRepository routerRepository;

	@PostMapping(value = { "/users" })
	public ResponseEntity<Users> saveUser(@Valid @RequestBody Users user) {

		Users savedUser = userService.saveUser(user);
//		List<Route> routes = user.getRoutes();
//		
//		routes.stream().forEach(r->{
//			r.setUser(savedUser);
//		});
//		routerRepository.saveAll(routes);
		return new ResponseEntity<>(savedUser, HttpStatus.OK);
//		return null;
	}

}
