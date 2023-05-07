package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserRequestDtoTest {

    @Test
    void testGetters() {
        UserRequestDto userRequestDto = new UserRequestDto("Valentina", "Santa", "123456",
                "+123456789",LocalDate.of(1987,5,26), "valentina@mail.com",
                "3456", 1L);

        assertEquals("Valentina", userRequestDto.getName());
        assertEquals("Santa", userRequestDto.getSurname());
        assertEquals("123456", userRequestDto.getDniNumber());
        assertEquals("+123456789", userRequestDto.getPhone());
        assertEquals(LocalDate.of(1987,5,26), userRequestDto.getBirthDate());
        assertEquals("valentina@mail.com", userRequestDto.getMail());
        assertEquals("3456", userRequestDto.getPassword());
        assertEquals(1L, userRequestDto.getIdRole());
    }

}