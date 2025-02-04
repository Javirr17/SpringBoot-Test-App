package com.example.test.mappers;

import com.example.test.dto.UserDTO;
import com.example.test.dto.UserResponseDTO;
import com.example.test.enums.UserStatusDTO;
import com.example.test.repository.User;

public class UserMapper {

    // Convierte UserDTO a User
    public static User toEntity(UserDTO userDTO) {
        User user = new User();
        user.setName(userDTO.getName());
        user.setSurname1(userDTO.getSurname1());
        user.setSurname2(userDTO.getSurname2());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setNif(userDTO.getNif());
        user.setNickname(userDTO.getNickname());
        user.setPassword(userDTO.getPassword());
        return user;
    }

    // Convierte User a UserResponseDTO
    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO responseDTO = new UserResponseDTO();
        responseDTO.setId(user.getId());
        responseDTO.setName(user.getName());
        responseDTO.setSurname1(user.getSurname1());
        responseDTO.setSurname2(user.getSurname2());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setPhoneNumber(user.getPhoneNumber());
        responseDTO.setNif(user.getNif());
        responseDTO.setNickname(user.getNickname());

        // Convertir UserStatus a UserStatusDTO
        if (user.getStatus() != null) {
            responseDTO.setStatus(UserStatusDTO.valueOf(user.getStatus().name()));
        }

        return responseDTO;
    }
}
