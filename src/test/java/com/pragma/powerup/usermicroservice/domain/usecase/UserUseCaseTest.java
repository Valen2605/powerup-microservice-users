package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserUnderageException;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.time.LocalDate;


@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {
    @Mock
    private IUserPersistencePort userPersistencePort;

    private UserUseCase userUseCase;

    @BeforeEach
    void start() {
        MockitoAnnotations.openMocks(this);
        userUseCase = new UserUseCase(userPersistencePort);
    }

    @Test
    void saveUserValidUser() {
        // Arrange
        User user = new User(5L,"María","López","12345678","564458894",
                LocalDate.of(1987, 5, 26),"maria@gmail.com","45889511",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));

        // Act
        userUseCase.saveUser(user);

        // Assert
        verify(userPersistencePort, times(1)).saveUser(user);
    }

    @Test
    void saveUserUnderageUser() {
        // Arrange
        User user = new User(5L,"María","López","12345678","564458894",
                LocalDate.of(2022, 5, 26),"maria@gmail.com","45889511",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));


        user.setBirthDate(LocalDate.of(2022, 5, 26));

        // Act and Assert
        Assertions.assertThrows(UserUnderageException.class, () -> {
            userUseCase.saveUser(user);
        });

        // Verify that userPersistencePort.saveUser() was not called
        verify(userPersistencePort, never()).saveUser(any(User.class));
    }

    @Test
    void getOwnerValidIdOwner() {
        // Arrange
        Long userId = 123L;
        User user = new User(5L,"María","López","12345678","564458894",
                LocalDate.of(1987, 5, 26),"maria@gmail.com","45889511",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));
        when(userPersistencePort.getOwner(userId)).thenReturn(user);

        // Act
        User actualOwner = userUseCase.getOwner(userId);

        // Assert
        Assertions.assertEquals(user, actualOwner);
        verify(userPersistencePort, times(1)).getOwner(userId);
    }

}