package com.luxury.wear.service.service.user;

import com.luxury.wear.service.dto.user.UserRequestDto;
import com.luxury.wear.service.dto.user.UserResponseDto;
import com.luxury.wear.service.entity.Product;
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

    Page<Product> toggleFavoriteProduct(String email, Long productId, Pageable pageable);

    Page<Product> getFavoriteProducts(String email, Pageable pageable);

    List<Long> getFavoriteProductIds(String email);
}
