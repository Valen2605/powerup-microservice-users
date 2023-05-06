package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LoginRequestDtoTest {

    @Test
    void testGetters() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("123", "1234");

        assertEquals("123", loginRequestDto.getUserDni());
        assertEquals("1234", loginRequestDto.getPassword());
    }

}