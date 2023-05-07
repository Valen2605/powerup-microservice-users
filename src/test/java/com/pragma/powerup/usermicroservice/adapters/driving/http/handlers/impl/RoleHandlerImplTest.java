package com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.impl;


import com.pragma.powerup.usermicroservice.adapters.driving.http.dto.response.RoleResponseDto;
import com.pragma.powerup.usermicroservice.adapters.driving.http.handlers.IRoleHandler;
import com.pragma.powerup.usermicroservice.adapters.driving.http.mapper.IRoleResponseMapperImpl;
import com.pragma.powerup.usermicroservice.domain.api.IRoleServicePort;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RoleHandlerImplTest {

    @Mock
    private IRoleServicePort roleServicePort;

    @Mock
    private IRoleResponseMapperImpl roleResponseMapperImpl;

    private IRoleHandler roleHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        roleHandler = new RoleHandlerImpl(roleResponseMapperImpl, roleServicePort);
    }

    @Test
    void getAllRoles_ShouldReturnListOfRoles() {
        // Arrange
        List<Role> roles = new ArrayList<>();
        roles.add(new Role(1L,"ADMIN","ADMIN"));
        roles.add(new Role(2L,"OWNER","OWNER"));

        List<RoleResponseDto> expectedRoles = new ArrayList<>();
        expectedRoles.add(new RoleResponseDto("ADMIN","ADMIN"));
        expectedRoles.add(new RoleResponseDto("OWNER","OWNER"));

        when(roleServicePort.getAllRoles()).thenReturn(roles);
        when(roleResponseMapperImpl.toResponseList(roles)).thenReturn(expectedRoles);

        // Act
        List<RoleResponseDto> actualRoles = roleHandler.getAllRoles();

        // Assert
        assertEquals(expectedRoles.size(), actualRoles.size());
        assertEquals(expectedRoles.get(0).getName(), actualRoles.get(0).getName());
        assertEquals(expectedRoles.get(1).getName(), actualRoles.get(1).getName());
    }

}