package com.spring.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.entity.Post;
import com.spring.entity.User;
@Repository
public interface PostRepo extends JpaRepository<User, Serializable>{

	void save(Post post);
	 

	

}
