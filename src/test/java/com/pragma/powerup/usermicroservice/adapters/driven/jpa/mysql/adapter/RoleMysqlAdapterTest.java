package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.exceptions.NoDataFoundException;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.mappers.IRoleEntityMapper;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IRoleRepository;
import com.pragma.powerup.usermicroservice.domain.model.Role;
import org.mockito.Spy;

class RoleMysqlAdapterTest {

    @Mock
    private IRoleRepository roleRepository;

    @Mock
    private IRoleEntityMapper roleEntityMapper;

    @Spy
    @InjectMocks
    private RoleMysqlAdapter roleMysqlAdapter;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldGetAllRolesSuccessfully() {
        // Arrange
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setId(1L);
        roleEntity1.setName("ADMIN");
        roleEntity1.setDescription("ADMIN");

        RoleEntity roleEntity2 = new RoleEntity();
        roleEntity2.setId(2L);
        roleEntity2.setName("OWNER");
        roleEntity2.setDescription("OWNER");

        List<RoleEntity> roleEntityList = Arrays.asList(roleEntity1, roleEntity2);

        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("ADMIN");
        role1.setDescription("ADMIN");

        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("OWNER");
        role2.setDescription("OWNER");

        List<Role> expectedRoleList = Arrays.asList(role1, role2);

        when(roleRepository.findAll()).thenReturn(roleEntityList);
        when(roleEntityMapper.toRoleList(roleEntityList)).thenReturn(expectedRoleList);

        // Act
        List<Role> actualRoleList = roleMysqlAdapter.getAllRoles();

        // Assert
        assertEquals(expectedRoleList, actualRoleList);
    }

    @Test
    void shouldThrowNoDataFoundExceptionWhenNoRolesAreFound() {
        // Arrange
        when(roleRepository.findAll()).thenReturn(Arrays.asList());
        when(roleEntityMapper.toRoleList(Arrays.asList())).thenReturn(Arrays.asList());

        // Act & Assert
        assertThrows(NoDataFoundException.class, () -> roleMysqlAdapter.getAllRoles());
    }

}