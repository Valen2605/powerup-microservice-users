package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapper;

import java.time.LocalDate;

class UserHandlerImplTest {

    private UserHandlerImpl userHandler;

    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IUserRequestMapper userRequestMapper;

    @Mock
    private IUserResponseMapper userResponseMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        userHandler = new UserHandlerImpl(userServicePort, userRequestMapper, userResponseMapper);
    }

    @Test
    public void saveUser_ShouldCallSaveUserInServicePort() {
        // Arrange
        UserRequestDto userRequestDto = new UserRequestDto("Valentina", "Santa ", "12345678",
                "123456", LocalDate.of(1987,5,26), "valentina@mail.com",
                "password123", 1L);

        User user = new User();
        when(userRequestMapper.toUser(userRequestDto)).thenReturn(user);

        // Act
        userHandler.saveOwner(userRequestDto);

        // Assert
        verify(userServicePort, times(1)).saveOwner(user);
    }

}