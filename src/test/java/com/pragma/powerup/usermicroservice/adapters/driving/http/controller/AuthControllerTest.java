package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IAuthHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AuthControllerTest {

    @Mock
    private IAuthHandler authHandler;

    @InjectMocks
    private AuthController authController;

    private LoginRequestDto loginRequestDto;

    @BeforeEach
    void setUp() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("123","1234");
    }

    @Test
    void login() {
        // Arrange
        JwtResponseDto jwtResponseDto = new JwtResponseDto("testToken");
        Mockito.when(authHandler.login(loginRequestDto)).thenReturn(jwtResponseDto);

        // Act
        ResponseEntity<JwtResponseDto> responseEntity = authController.login(loginRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(jwtResponseDto, responseEntity.getBody());
    }

}