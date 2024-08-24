package com.scm.ContactManager.services;

import java.util.List;
import java.util.Optional;


import com.scm.ContactManager.entities.User;

public interface UserServices {
    User saveUser(User user);
    Optional<User> getUserById(String user);
    Optional<User> updateUser(User user);
    void deleteUser(String id);
    boolean isUserExist(String userId);
    boolean isUserExistByEmail(String email);
    List<User> getAllUsers();
    User getUserByEmail(String email);

}
