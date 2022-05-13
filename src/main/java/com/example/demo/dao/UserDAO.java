package com.example.demo.dao;

import com.example.demo.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
	User findByUsername(String username);

	User getByUsernameAndPassword(String username, String password);
}