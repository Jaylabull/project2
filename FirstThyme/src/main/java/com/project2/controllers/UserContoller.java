package com.project2.controllers;

import java.util.LinkedHashMap;

import org.hibernate.usertype.UserVersionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.project2.models.User;
import com.project2.services.UserService;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping(value="/users")
@NoArgsConstructor
@AllArgsConstructor(onConstructor=@__(@Autowired))
public class UserContoller {
	private UserService uServ;
	@PostMapping("/signup")
	public ResponseEntity<String> createUser(@RequestBody LinkedHashMap<String,String>user){
		User u = new User(user.get("firstName"),user.get("lastName"),user.get("email"),user.get("password"));
		if(uServ.createUser(u)) {
			return new ResponseEntity<String>("User was registered",HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Username or email was already taken", HttpStatus.CONFLICT);
		}
	}
	
}
