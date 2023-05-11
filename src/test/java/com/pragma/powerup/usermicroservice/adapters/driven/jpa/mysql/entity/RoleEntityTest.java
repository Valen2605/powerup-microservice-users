package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoleEntityTest {
    @Test
    void testGetters() {
        //Arrange
        RoleEntity roleEntity = new RoleEntity(1L, "ADMIN", "ADMIN");

        // Act & Assert
        assertEquals(1L, roleEntity.getId());
        assertEquals("ADMIN", roleEntity.getName());
        assertEquals("ADMIN", roleEntity.getDescription());
    }

    @Test
    void testSetters() {
        //Arrange
        RoleEntity roleEntity = new RoleEntity();

        //Act
        roleEntity.setId(1L);
        roleEntity.setName("ADMIN");
        roleEntity.setDescription("ADMIN");

        //Assert
        assertEquals(1L, roleEntity.getId());
        assertEquals("ADMIN", roleEntity.getName());
        assertEquals("ADMIN", roleEntity.getDescription());

    }

}