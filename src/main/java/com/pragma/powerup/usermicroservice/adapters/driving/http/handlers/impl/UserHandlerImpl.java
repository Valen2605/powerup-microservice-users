package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.request.UserRequestDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IUserHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserRequestMapper;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IUserResponseMapper;
import com.pragma.powerup.usermicroservice.domain.api.IUserServicePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserHandlerImpl implements IUserHandler {

    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @Override
    public UserResponseDto getOwner(Long id) {
        return userResponseMapper.toResponse(userServicePort.getOwner(id));
    }

    @Override
    public void saveOwner(UserRequestDto userRequestDto) {
        userRequestDto.setIdRole(2L);
        userServicePort.saveUser(userRequestMapper.toUser(userRequestDto));
    }

    @Override
    public void saveEmployee(UserRequestDto userRequestDto) {
        userRequestDto.setIdRole(3L);
        userServicePort.saveUser(userRequestMapper.toUser(userRequestDto));
    }
}
