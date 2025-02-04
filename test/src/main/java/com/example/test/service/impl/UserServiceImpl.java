package com.example.test.service.impl;

import com.example.test.controller.exceptions.ContentNotFoundException;
import com.example.test.controller.exceptions.ErrorCode;
import com.example.test.dto.UserDTO;
import com.example.test.enums.UserStatus;
import com.example.test.mappers.UserMapper;
import com.example.test.repository.User;
import com.example.test.repository.UserRepository;
import com.example.test.service.UserService;
import com.example.test.utils.EncodeUtil;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import com.example.test.dto.UserResponseDTO;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Page<UserResponseDTO> getAllUsers(String nickname, String nif, String status, Integer offset, Integer limit, String sortBy) {

        UserStatus userStatus = null;

        Sort.Direction direction = sortBy.startsWith("-") ? Sort.Direction.DESC : Sort.Direction.ASC;
        String property = sortBy.startsWith("-") ? sortBy.substring(1) : sortBy;

        Pageable pageable = PageRequest.of(offset, limit, Sort.by(direction, property));

        if (status != null) {
            try {
                userStatus = UserStatus.valueOf(status);
            } catch (IllegalArgumentException ignored) {}
        }

        Page<User> userPage = userRepository.findAllByFields(nickname, nif, userStatus, pageable);

        List<UserResponseDTO> userResponseDTOS = userPage.getContent().
                stream().
                map(UserMapper::toResponseDTO).
                toList();

        return new PageImpl<>(userResponseDTOS, pageable, userPage.getTotalElements());
    }

    @Override
    public User saveUser(UserDTO userDTO) {
        var user = UserMapper.toEntity(userDTO);
        user.setEntryDate(new Date());
        user.setStatus(UserStatus.ACTIVO);

        String password = userDTO.getPassword();

        user.setPassword(EncodeUtil.encode(password, "bcrypt"));
        return userRepository.save(user);
    }

    @Override
    public UserResponseDTO getUserById(Integer userId) {
        return userRepository.findById(userId).
                map(UserMapper::toResponseDTO).
                orElse(null);
    }

    @Override
    public void deleteUserById(Integer userId) {
        var userOptional = userRepository.findById(userId);

        if (userOptional.isPresent()) {
            userRepository.deleteById(userId);
        } else {
            throw new ContentNotFoundException(ErrorCode.OBJECT_NOT_FOUND);
        }
    }

    @Override
    public void updateUserById(Integer userId, UserDTO userDTO) {
        var userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            var user = userOptional.get();
            user.setName(userDTO.getName());
            user.setSurname1(userDTO.getSurname1());
            user.setSurname2(userDTO.getSurname2());
            user.setEmail(userDTO.getEmail());
            user.setPhoneNumber(userDTO.getPhoneNumber());
            user.setNif(userDTO.getNif());
            user.setNickname(userDTO.getNickname());
            user.setPassword(EncodeUtil.encode(userDTO.getPassword(), "bcrypt"));
            userRepository.save(user);
        } else {
            throw new ContentNotFoundException(ErrorCode.OBJECT_NOT_FOUND);
        }
    }

}
