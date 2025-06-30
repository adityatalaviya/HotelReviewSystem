package com.user.service.service.impl;

import com.user.service.entity.User;
import com.user.service.exceptions.ResourceNotFoundException;
import com.user.service.repo.UserRepo;
import com.user.service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public User createUser(User user) {
        //generate unique user id
        String randomUserId = UUID.randomUUID().toString();
        user.setUserId(randomUserId);
        return userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
    }

    @Override
    public User getUser(String userId) {
        return userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not exist"+userId));
    }

    @Override
    public User updateUser(String userId, User user) {
        User existUser = userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User not exist"));
        existUser.setName(user.getName());
        existUser.setEmail(user.getEmail());
        existUser.setAbout(user.getAbout());
        return userRepo.save(existUser);
    }

    @Override
    public void deleteUser(String userId) {
        userRepo.deleteById(userId);
    }
}
