package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.configuration.Constants;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserNotCreatedException;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserUnderageException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.Period;

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

        LocalDate yearBirth = user.getBirthDate();

        Period ageCurrent = Period.between(yearBirth, localDate);

        Integer age = ageCurrent.getYears();

        if (age < minimunAge) throw new UserUnderageException();

        if (!user.getRole().getId().equals(Constants.OWNER_ROLE_ID)) throw new UserNotCreatedException();

        if (user.getRole().getId().equals (Constants.OWNER_ROLE_ID) && age >= minimunAge)
            userPersistencePort.saveOwner(user);

    }

    @Override
    public User getOwner(Long id) {
        return userPersistencePort.getOwner(id);
    }
}
