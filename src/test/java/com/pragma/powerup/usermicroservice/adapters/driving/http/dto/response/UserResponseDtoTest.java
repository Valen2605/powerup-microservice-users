package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserResponseDtoTest {

    @Test
    void testGetters() {
        UserResponseDto userResponseDto = new UserResponseDto("Valentina", "Santa", "123456",
                "123456789", LocalDate.of(1987,05,26), "valentina@email.com",
                "password123", 1L);

        assertEquals("Valentina", userResponseDto.getName());
        assertEquals("Santa", userResponseDto.getSurname());
        assertEquals("123456", userResponseDto.getDniNumber());
        assertEquals("123456789", userResponseDto.getPhone());
        assertEquals(LocalDate.of(1987,05,26), userResponseDto.getBirthDate());
        assertEquals("valentina@email.com", userResponseDto.getMail());
        assertEquals("password123", userResponseDto.getPassword());
        assertEquals(1L, userResponseDto.getIdRole());
    }

}