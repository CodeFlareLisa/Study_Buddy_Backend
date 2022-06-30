package com.Revature.Study_Buddy_Backend;

import com.Revature.Study_Buddy_Backend.Model.User;
import com.Revature.Study_Buddy_Backend.Repository.UserRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserTest {
	@Autowired
	UserRepo userRepo;

	@Test
	void testAddUser(){
		User user = new User();
		user.setEmail("c@gmail.com");
		user.setPasswd("testing");
		user.setName("Castella");
		userRepo.save(user);
	}

	@Test
	void testUserList(){
		List<User> userList = userRepo.findAll();
		assertThat(userList).size().isGreaterThan(0);
		System.out.println("User list: " + userList.size());
	}

	@Test
	void testGetUserId(){
		User user = userRepo.findByuserId(5L);
		assertNotNull(user);
		System.out.println(user);
	}

	@Test
	void testDeleteUser(){
		User user = userRepo.findByuserId(6L);
		userRepo.delete(user);
		System.out.println(user);
	}

	@Test
	void testUpdateUser(){
		User user = userRepo.findByuserId(1L);
		assertNotNull(user, user.toString());
		user.setName("thomas");
		userRepo.save(user);
		System.out.println(user);
	}

	@Test
	void testGetUserWithEmailAndPasswd(){
		String email = "testing@gmail.com";
		String passwd = "7423654";
		User user = userRepo.findUserByEmailAndPasswd(email, passwd);
		assertNotNull(user);
		System.out.println(user);
	}
}
