package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RoleResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IRoleHandler;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleRestControllerTest {

    @Mock
    private IRoleHandler roleHandler;

    @InjectMocks
    private RoleRestController roleRestController;

    @Test
    void getRoles() {
        // Arrange
        List<RoleResponseDto> expectedRoles = Arrays.asList(new RoleResponseDto("ADMIN","ADMIN"),
                new RoleResponseDto("OWNER","OWNER"));
        Mockito.when(roleHandler.getAllRoles()).thenReturn(expectedRoles);

        // Act
        ResponseEntity<List<RoleResponseDto>> responseEntity = roleRestController.getAllRoles();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedRoles, responseEntity.getBody());
    }

}