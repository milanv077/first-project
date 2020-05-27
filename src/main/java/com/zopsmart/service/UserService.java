package com.zopsmart.service;

import com.zopsmart.model.User;
import com.zopsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public void saveUser(User user)
    {
        userRepository.save(user);
    }

    public User findUserByEmail(String name) {
        return userRepository.findUserByEmail(name);
    }

    public User findUserByMobileNumber(String mobileNumber) {
        return userRepository.findUserByMobileNumber(mobileNumber);
    }
}
