package com.scm.ContactManager.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.ContactManager.entities.User;
@Repository
public interface UserRepo extends JpaRepository<User, String> {

    // custome methods

    Optional<User> findByEmail(String email);
    Optional<User> findByEmailAndPassword(String email,String password);
    Optional<User> findByEmailToken(String id);

}
