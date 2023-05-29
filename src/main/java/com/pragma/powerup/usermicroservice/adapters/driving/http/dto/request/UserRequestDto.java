package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    @NotEmpty(message = "The name must not be empty")
    private String name;

    @NotEmpty(message = "The surname must not be empty")
    private String surname;

    @NotEmpty(message = "The dniNumber must not be empty")
    @Column(unique = true, nullable = false, length = 20)
    @Pattern(regexp = "\\d*", message = "The document must be numeric")
    private String dniNumber;

    @NotEmpty(message = "The phone must not be empty")
    @Pattern(regexp = "^\\+?[0-9]{12}$", message = "The phone should only include the + sign and a maximum of 12 numbers")
    private String phone;

    @NotNull(message = "The birth Date must not be empty")
    @Past(message = "Date of birth must be prior to the date entered")
    private LocalDate birthDate;

    @NotEmpty(message = "The email must not be empty")
    @Pattern(regexp="^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$", message= "You must enter a correct email format")
    private String mail;

    @NotEmpty(message = "The password must not be empty")
    private String password;

    @JsonIgnore
    private Long idRole;

}
