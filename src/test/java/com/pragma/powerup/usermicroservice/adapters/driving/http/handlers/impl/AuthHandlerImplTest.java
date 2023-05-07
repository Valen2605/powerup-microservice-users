package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.Collections;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.LoginRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.JwtResponseDto;
import com.pragma.powerup.usermicroservice.configuration.security.jwt.JwtProvider;

@ExtendWith(MockitoExtension.class)
class AuthHandlerImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private AuthHandlerImpl authHandler;

    @Test
    public void login_shouldReturnJwtResponseDto_whenValidUserCredentialsProvided() {
        // Arrange
        String token = "tokenTest";
        LoginRequestDto loginRequestDto = new LoginRequestDto("123", "123");
        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(loginRequestDto.getUserDni())
                .password(loginRequestDto.getPassword())
                .authorities(Collections.emptyList())
                .build();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUserDni(), loginRequestDto.getPassword())))
                .thenReturn(authentication);
        when(jwtProvider.generateToken(authentication)).thenReturn(token);

        // Act
        JwtResponseDto result = authHandler.login(loginRequestDto);

        // Assert
        assertEquals(token, result.getToken());
    }

    @Test
    void testRefresh() throws ParseException {
        // Arrange
        String jwtToken = "jwtToken";
        JwtResponseDto jwtResponseDto = new JwtResponseDto(jwtToken);
        String newJwtToken = "newJwtToken";
        JwtResponseDto expectedJwtResponseDto = new JwtResponseDto(newJwtToken);

        Mockito.when(jwtProvider.refreshToken(jwtResponseDto)).thenReturn(newJwtToken);

        // Act
        JwtResponseDto refreshedJwtResponseDto = authHandler.refresh(jwtResponseDto);

        // Assert
        assertNotNull(refreshedJwtResponseDto);
        assertEquals(expectedJwtResponseDto.getToken(), refreshedJwtResponseDto.getToken());
    }

}