package com.pragma.powerup.usermicroservice.domain.api;

import com.pragma.powerup.usermicroservice.domain.model.User;


public interface IUserServicePort {
    void saveUser(User user);
    User getOwner(Long id);
}
