package com.example.test.dto;

import com.example.test.enums.UserStatusDTO;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {

    private Integer id;
    @NotEmpty
    private String name;
    private String surname1;
    private String surname2;
    private String email;
    private String phoneNumber;
    @NotEmpty
    private String nif;
    @NotEmpty
    private String nickname;
    private UserStatusDTO status;

}
