package com.example.crudproject.service;

import com.example.crudproject.dto.RegisterRequest;
import com.example.crudproject.dto.UserDTO;
import com.example.crudproject.mapper.UserMapper;
import com.example.crudproject.model.User;
import com.example.crudproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<UserDTO> getAllUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public Page<UserDTO> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable)
                .map(UserMapper::toDTO);
    }

    public UserDTO createUser(UserDTO userDTO) {
        User user = UserMapper.toEntity(userDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.toDTO(savedUser);
    }

    public Optional<UserDTO> getUserById(Long id) {
        return userRepository
                .findById(id)
                .map(UserMapper::toDTO);
    }

    public Optional<UserDTO> updateUser(Long id, UserDTO userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setName(userDetails.getName());
                    user.setEmail(userDetails.getEmail());
                    User updatedUser = userRepository.save(user);
                    return UserMapper.toDTO(updatedUser);
                });
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    public void register(RegisterRequest request) {
        // 이메일 중복 체크
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new RuntimeException("Email already in use");
        }

        // 엔티티 생성 및 저장
        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setName(request.getName());
        user.setRole("ROLE_USER");

        userRepository.save(user);
    }
}
