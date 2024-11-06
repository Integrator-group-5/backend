package com.luxury.wear.service.service.user;

import com.luxury.wear.service.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    Page<User> getAllUsers(Pageable pageable);

    User updateUser(User user);

    void setAdmin(String email);

    void deleteUserById(Long id);

    User findByEmail(String email);
}
