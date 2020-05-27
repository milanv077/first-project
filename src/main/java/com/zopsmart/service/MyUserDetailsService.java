package com.zopsmart.service;

import com.zopsmart.model.User;
import com.zopsmart.principal.UserPrincipal;
import com.zopsmart.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        User user=userRepository.findUserByEmailOrMobileNumber(s,s);
        if(user==null)
            throw new UsernameNotFoundException("Invalid User");
        return new UserPrincipal(user);


    }

}
