package com.zopsmart.repository;

import com.zopsmart.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    User findUserByEmail(String s);

    User findUserByEmailOrMobileNumber(String email,String mobile);

    User findUserByMobileNumber(String mobileNumber);
}
