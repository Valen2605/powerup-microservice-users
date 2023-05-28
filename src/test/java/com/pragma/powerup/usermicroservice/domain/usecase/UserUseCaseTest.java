package com.pragma.powerup.usermicroservice.domain.usecase;


import com.pragma.powerup.usermicroservice.domain.exceptions.UserUnderageException;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import com.pragma.powerup.usermicroservice.configuration.Constants;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserUseCaseTest {

    @Test
    void saveUser_AgeValid() {
        // Arrange
        LocalDate currentDate = LocalDate.of(2023, 5, 27);
        User user = new User(5L,"María","López","12345678","564458894",
                LocalDate.of(1987, 5, 26),"maria@gmail.com","45889511",
                new Role(Constants.OWNER_ROLE_ID, "ROLE_OWNER","ROLE_OWNER"));;

        IUserPersistencePort userPersistencePortMock = Mockito.mock(IUserPersistencePort.class);
        UserUseCase userUseCase = new UserUseCase(userPersistencePortMock);

        // Act
        userUseCase.saveUser(user);

        // Assert
        int currentYear = currentDate.getYear();
        int yearOfBirth = user.getBirthDate().getYear();
        int age = currentYear - yearOfBirth;

        if (age < 18) {
            assertThrows(UserUnderageException.class, () -> userUseCase.saveUser(user));
        } else {
            verify(userPersistencePortMock).saveUser(user);
        }
    }

}