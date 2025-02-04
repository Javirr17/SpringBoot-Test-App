package com.example.test.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotEmpty
    private String name;
    private String surname1;
    private String surname2;
    @Email
    private String email;
    private String phoneNumber;
    @NotEmpty
    private String nif;
    @NotEmpty
    private String nickname;
    @NotEmpty
    private String password;

}
