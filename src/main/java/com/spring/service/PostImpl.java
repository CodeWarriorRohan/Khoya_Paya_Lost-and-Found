package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.dao.PostRepo;
import com.spring.entity.Post;
import com.spring.entity.User;

@Service
public class PostImpl {
//implements PostService {

	@Autowired
	private PostRepo postrepo;

//	@Override
//	public User saveUser(User user, String url) {
//
//		String password = passwordEncoder.encode(user.getPassword());
//		user.setPassword(password);
//		user.setRole("ROLE_USER");
//
//		user.setEnable(true);
//
//		User newuser = userRepo.save(user);
//
//		if (newuser != null) {
//			sendEmail(newuser, url);
//		}
//
//		return newuser;
//	}

}
