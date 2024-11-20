package com.luxury.wear.service.service.user;

import com.luxury.wear.service.commons.Constants;
import com.luxury.wear.service.dto.user.UserRequestDto;
import com.luxury.wear.service.dto.user.UserResponseDto;
import com.luxury.wear.service.entity.Product;
import com.luxury.wear.service.entity.User;
import com.luxury.wear.service.exception.EntityAlreadyExistsException;
import com.luxury.wear.service.exception.ResourceNotFoundException;
import com.luxury.wear.service.mapper.UserMapper;
import com.luxury.wear.service.repository.UserRepository;
import com.luxury.wear.service.roles.UserRole;
import com.luxury.wear.service.service.product.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    private final ProductService productService;

    @Override
    public UserResponseDto createUser(UserRequestDto userRequestDto) {
        if (userRepository.existsByEmail(userRequestDto.getEmail())) {
            throw new EntityAlreadyExistsException(Constants.ERROR_EMAIL_ALREADY_EXISTS + userRequestDto.getEmail());
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
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_ID + id));
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
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_ID + userId));

        validateUserUpdateRequest(userRequestDto, existingUser);

        updateExistingUser(existingUser, userRequestDto);

        User updatedUser = userRepository.save(existingUser);

        return userMapper.toResponseDto(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_ID + id));
        userRepository.deleteById(id);
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toResponseDto)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_EMAIL + email));
    }

    @Override
    public void setAdmin(String email) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_EMAIL + email));
        existingUser.setUserRole(UserRole.ADMIN);
        userRepository.save(existingUser);
    }

    @Override
    public void removeAdmin(String email) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_EMAIL + email));
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
                throw new EntityAlreadyExistsException(Constants.ERROR_EMAIL_ALREADY_EXISTS + userRequestDto.getEmail());
            }
        }
    }

    @Override
    public Page<Product> toggleFavoriteProduct(String email, Long productId, Pageable pageable) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_EMAIL + email));

        Product existingProduct = productService.GetProductByID(productId);

        // Toggle favorite logic
        List<Product> favoriteProducts = existingUser.getFavoriteProducts();
        if (favoriteProducts.stream().anyMatch(product -> product.getProductId().equals(productId))) {
            favoriteProducts.removeIf(product -> product.getProductId().equals(productId));// Remove the product if it's already a favorite
        } else {
            favoriteProducts.add(existingProduct);// Add the product if it's not already a favorite
        }

        userRepository.save(existingUser);
        return convertListToPage(favoriteProducts, pageable);
    }

    @Override
    public Page<Product> getFavoriteProducts(String email, Pageable pageable) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_EMAIL + email));
        List<Product> favoriteProducts = existingUser.getFavoriteProducts();
        return convertListToPage(favoriteProducts, pageable);
    }

    private Page<Product> convertListToPage(List<Product> list, Pageable pageable) {
        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), list.size());
        return new PageImpl<>(list.subList(start, end), pageable, list.size());
    }

    @Override
    public List<Long> getFavoriteProductIds(String email) {
        User existingUser = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException(Constants.ERROR_USER_NOT_FOUND_EMAIL + email));
        return existingUser.getFavoriteProducts()
                .stream()
                .map(Product::getProductId)
                .toList();
    }
}
