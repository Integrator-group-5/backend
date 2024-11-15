package com.luxury.wear.service.service.user;

import com.luxury.wear.service.dto.user.UserRequestDto;
import com.luxury.wear.service.dto.user.UserResponseDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {

    // CRUD operations
    UserResponseDto createUser(UserRequestDto userRequestDto);

    UserResponseDto getUserById(Long id);

    List<UserResponseDto> getAllUsers();

    Page<UserResponseDto> getAllUsersPaginated(Pageable pageable);

    UserResponseDto updateUser(Long id, UserRequestDto userRequestDto);

    void deleteUserById(Long id);

    // Utility methods
    UserResponseDto findByEmail(String email);

    void setAdmin(String email);

    void removeAdmin(String email);
}
