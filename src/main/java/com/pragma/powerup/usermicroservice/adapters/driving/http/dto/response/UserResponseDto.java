package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;

@AllArgsConstructor
@Getter
public class UserResponseDto {
    private String name;
    private String surname;
    private String dniNumber;
    private String phone;
    private Date birthDate;
    private String mail;
    private String password;
    private Long idRole;
}
