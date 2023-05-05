package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserNotCreatedException;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserUnderageException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    @Value("${my.variables.minimum-age}")
    Integer minimunAge;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public void saveOwner(User user) {

        LocalDate localDate = LocalDate.now();
        Integer currentYear = localDate.getYear();
        Integer yearBirth = user.getBirthDate().getYear();
        Integer age = currentYear - yearBirth;

        if (age < minimunAge) throw new UserUnderageException();

        if (user.getRole().getId() != Constants.OWNER_ROLE_ID ) throw new UserNotCreatedException();

        if (user.getRole().getId() == Constants.OWNER_ROLE_ID && age >= minimunAge) userPersistencePort.saveOwner(user);

    }
}
