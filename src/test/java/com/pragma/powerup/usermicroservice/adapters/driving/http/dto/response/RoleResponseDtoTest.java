package com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleResponseDtoTest {

    @Test
    void testGetters() {
        RoleResponseDto roleResponseDto = new RoleResponseDto("ADMIN","ADMIN");
        assertEquals("ADMIN", roleResponseDto.getName());
        assertEquals("ADMIN", roleResponseDto.getDescription());
    }

}