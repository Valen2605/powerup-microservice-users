package com.pragma.powerup.usermicroservice.adapters.driving.http.controller;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRestControllerTest {

    @Mock
    private IUserHandler userHandler;

    @InjectMocks
    private UserRestController userRestController;

    private UserRequestDto userRequestDto;

    @BeforeEach
    void setUp() {
        UserRequestDto userRequestDto = new UserRequestDto("Valentina","Santa", "123456",
                "+123456789", LocalDate.of(1987, 5, 26), "valentina@mail.com", "3456",
                1L);
    }

    @Test
    @DisplayName("Given a valid user, when saveOwner is called, then a CREATED response is returned")
    void testSaveOwner() {
        // Arrange
        Mockito.doNothing().when(userHandler).saveOwner(userRequestDto);

        // Act
        ResponseEntity<Map<String, String>> responseEntity = userRestController.saveOwner(userRequestDto);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(Constants.USER_CREATED_MESSAGE, responseEntity.getBody().get(Constants.RESPONSE_MESSAGE_KEY));
    }

}