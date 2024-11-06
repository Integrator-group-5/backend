package com.luxury.wear.service.service.user;

import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.exception.EntityAlreadyExistsException;
import com.luxury.wear.service.exception.ResourceNotFoundException;
import com.luxury.wear.service.repository.UserRepository;
import com.luxury.wear.service.roles.UserRole;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final String USER_NOT_FOUND_ID = "User not found with Id: ";
    private static final String USER_NOT_FOUND_EMAIL = "User not found with email: ";
    private static final String USER_NOT_FOUND_USERNAME = "User not found with username: ";
    private static final String USER_ALREADY_EXISTS = "User already exists with email: ";
    private static final String USER_ALREADY_EXISTS_USERNAME = "User already exists with username: ";

    @Override
    public User createUser(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new EntityAlreadyExistsException(USER_ALREADY_EXISTS + user.getEmail());
        }
        if (userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new EntityAlreadyExistsException(USER_ALREADY_EXISTS_USERNAME + user.getUsername());
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUserRole(UserRole.USER);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_ID + id));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Page<User> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public User updateUser(User user) {
        User existingUser = userRepository.findById(user.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_ID + user.getUserId()));

        return updateExistingUser(existingUser, user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_ID + id));
        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_EMAIL + email));
    }

    private User updateExistingUser(User existingUser, User newUserData) {
        existingUser.setFirstName(newUserData.getFirstName());
        existingUser.setUsername(newUserData.getUsername());
        existingUser.setEmail(newUserData.getEmail());
        existingUser.setUserRole(newUserData.getUserRole());
        if (newUserData.getPassword() != null
                && !newUserData.getPassword().isEmpty()
                && !passwordEncoder.matches(newUserData.getPassword(), existingUser.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(newUserData.getPassword()));
        }

        return userRepository.save(existingUser);
    }

    @Override
    public void setAdmin(String email) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_EMAIL + email));
        existingUser.setUserRole(UserRole.ADMIN);
        userRepository.save(existingUser);
    }
}
