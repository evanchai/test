package com.ning.app.domain;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by evanchai on 2/4/16.
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String password);
}
