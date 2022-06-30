package com.Revature.Study_Buddy_Backend.Service;

import com.Revature.Study_Buddy_Backend.Model.User;
import com.Revature.Study_Buddy_Backend.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// hello
@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    public User getUserById(Long userId) {
        return userRepo.findByuserId(userId);
    }

    public void addUser(User user) {
        userRepo.save(user);
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteByUserId(Long userId) {
        userRepo.delete(getUserById(userId));
    }

    public User findUserByEmailAndPasswd(User user) {
        return userRepo.findUserByEmailAndPasswd(user.getEmail(), user.getPasswd());
    }
}