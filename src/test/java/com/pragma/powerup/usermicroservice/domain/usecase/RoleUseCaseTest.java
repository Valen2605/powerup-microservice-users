package com.pragma.powerup.usermicroservice.domain.usecase;

import com.pragma.powerup.usermicroservice.domain.model.Role;
import com.pragma.powerup.usermicroservice.domain.spi.IRolePersistencePort;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RoleUseCaseTest {
    @Mock
    private IRolePersistencePort rolePersistencePort;

    @InjectMocks
    private RoleUseCase roleUseCase;

    @Test
    void givenNoRoles_whenGetAllRoles_thenReturnEmptyList() {
        // Arrange
        Mockito.when(rolePersistencePort.getAllRoles()).thenReturn(Collections.emptyList());

        // Act
        List<Role> roles = roleUseCase.getAllRoles();

        // Assert
        assertTrue(roles.isEmpty());
    }

    @Test
    void givenRolesExist_whenGetAllRoles_thenReturnListOfRoles() {
        // Arrange
        List<Role> expectedRoles = Arrays.asList(
                new Role(1L, "ROLE_ADMIN", "ROLE_ADMIN"),
                new Role(2l, "ROLE_OWNER", "ROLE_OWNER")
        );
        Mockito.when(rolePersistencePort.getAllRoles()).thenReturn(expectedRoles);

        // Act
        List<Role> actualRoles = roleUseCase.getAllRoles();

        // Assert
        assertEquals(expectedRoles.size(), actualRoles.size());
        assertTrue(actualRoles.containsAll(expectedRoles));
    }

}