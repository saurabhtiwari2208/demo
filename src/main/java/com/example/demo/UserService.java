package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	@Autowired 
	UserRepository userRepository;
	
	public User getUser(int user_id , String password ){
		return userRepository.getUser(user_id, password);
	}
}
