package com.example.test.service;

import com.example.test.dto.UserDTO;
import com.example.test.dto.UserResponseDTO;
import com.example.test.repository.User;
import org.springframework.data.domain.Page;

public interface UserService {

    Page<UserResponseDTO> getAllUsers(String nickname, String nif, String status, Integer offset, Integer limit, String sortBy);
    User saveUser(UserDTO userDTO);
    UserResponseDTO getUserById(Integer userId);
    void deleteUserById(Integer userId);
    void updateUserById(Integer userId, UserDTO userDTO);

}
