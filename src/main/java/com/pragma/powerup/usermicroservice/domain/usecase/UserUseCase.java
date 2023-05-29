package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import com.pragma.powerup.usermicroservice.domain.exceptions.UserUnderageException;
import com.pragma.powerup.usermicroservice.domain.model.User;
import com.pragma.powerup.usermicroservice.domain.spi.IUserPersistencePort;

import java.time.LocalDate;

public class UserUseCase implements IUserServicePort {
    private final IUserPersistencePort userPersistencePort;

    @Value("${my.variables.minimum-age}")
    Integer minimunAge;

    public UserUseCase(IUserPersistencePort userPersistencePort) {
        this.userPersistencePort = userPersistencePort;
    }


    @Override
    public void saveUser(User user) {
        LocalDate localDate = LocalDate.now();
        Integer currentYear = localDate.getYear();
        Integer yearBirth = user.getBirthDate().getYear();
        Integer age = currentYear - yearBirth;

        if (age < 18) throw new UserUnderageException();

        if (age >= 18) userPersistencePort.saveUser(user);
    }


    @Override
    public User getOwner(Long id) {
        return userPersistencePort.getOwner(id);
    }
}
