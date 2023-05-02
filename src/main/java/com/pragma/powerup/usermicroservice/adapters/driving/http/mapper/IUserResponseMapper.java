package com.pragma.powerup.usermicroservice.adapters.driving.http.mapper;

import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.UserResponseDto;
import com.pragma.powerup.usermicroservice.domain.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface IUserResponseMapper {
    @Mapping(source = "user.name", target = "name")
    @Mapping(source = "user.surname", target = "surname")
    @Mapping(source = "user.dniNumber", target = "dniNumber")
    @Mapping(source = "user.phone", target = "phone")
    @Mapping(source = "user.birthDate", target = "birthDate")
    @Mapping(source = "user.mail", target = "mail")
    @Mapping(source = "user.password", target = "password")
    @Mapping(target = "idRole", source = "role.id")
    UserResponseDto toResponse(User user);
    List<UserResponseDto> toResponseList(List<User> userList);
}
