package com.zopsmart;

import com.zopsmart.model.User;
import com.zopsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String execute(Connection<?> connection) {
        System.out.println("signup === ");
        final User user = new User();
        user.setFirstName(connection.getDisplayName());
        user.setPassword("aaa");
        userRepository.save(user);
        return user.getFirstName();
    }

}