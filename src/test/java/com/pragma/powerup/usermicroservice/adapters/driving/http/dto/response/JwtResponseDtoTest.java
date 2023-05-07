package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JwtResponseDtoTest {

    @Test
    void testGetters() {
        JwtResponseDto jwtResponseDto = new JwtResponseDto("testToken");

        assertEquals("testToken", jwtResponseDto.getToken());
    }

    @Test
    void testSetters() {
        JwtResponseDto jwtResponseDto = new JwtResponseDto();
        jwtResponseDto.setToken("testToken");
        assertEquals("testToken", jwtResponseDto.getToken());
    }

}