package com.luxury.wear.service.service.user;

import com.luxury.wear.service.dto.user.UserRequestDto;
import com.luxury.wear.service.dto.user.UserResponseDto;
import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.exception.EntityAlreadyExistsException;
import com.luxury.wear.service.exception.ResourceNotFoundException;
import com.luxury.wear.service.mapper.UserMapper;
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
    private final UserMapper userMapper;

    private static final String USER_NOT_FOUND_ID = "User not found with Id: ";
    private static final String USER_NOT_FOUND_EMAIL = "User not found with email: ";
    private static final String USER_ALREADY_EXISTS = "User already exists with email: ";

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new EntityAlreadyExistsException(USER_ALREADY_EXISTS + userRequestDto.getEmail());
        }

        User user = userMapper.toEntity(userRequestDto);
        user.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user.setUserRole(UserRole.USER);

        User savedUser = userRepository.save(user);

        return userMapper.toResponseDto(savedUser);
    }

    @Override
    public UserResponseDto getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_ID + id));
    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDto)
                .toList();
    }

    @Override
    public Page<UserResponseDto> getAllUsersPaginated(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(userMapper::toResponseDto);
    }

    @Override
    public UserResponseDto updateUser(Long userId, UserRequestDto userRequestDto) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_ID + userId));

        validateUserUpdateRequest(userRequestDto, existingUser);

        updateExistingUser(existingUser, userRequestDto);

        User updatedUser = userRepository.save(existingUser);

        return userMapper.toResponseDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_ID + id));
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_EMAIL + email));
    }

    @Override
    public void setAdmin(String email) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_EMAIL + email));
        existingUser.setUserRole(UserRole.ADMIN);
        userRepository.save(existingUser);
    }

    @Override
    public void removeAdmin(String email) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(USER_NOT_FOUND_EMAIL + email));
        existingUser.setUserRole(UserRole.USER);
        userRepository.save(existingUser);
    }

    private void updateExistingUser(User existingUser, UserRequestDto newUserData) {
        existingUser.setFirstName(newUserData.getFirstName());
        existingUser.setEmail(newUserData.getEmail());

        if (newUserData.getPassword() != null
                && !newUserData.getPassword().isEmpty()
                && !passwordEncoder.matches(newUserData.getPassword(), existingUser.getPassword())) {
            existingUser.setPassword(passwordEncoder.encode(newUserData.getPassword()));
        }
    }

    private void validateUserUpdateRequest(UserRequestDto userRequestDto, User existingUser) {
        if (userRequestDto.getEmail() != null && !userRequestDto.getEmail().equals(existingUser.getEmail())) {
            if (userRepository.existsByEmail(userRequestDto.getEmail())) {
                throw new EntityAlreadyExistsException("Email is already in use: " + userRequestDto.getEmail());
            }
        }
    }
}
