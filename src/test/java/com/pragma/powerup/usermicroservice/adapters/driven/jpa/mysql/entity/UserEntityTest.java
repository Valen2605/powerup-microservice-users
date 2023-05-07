package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserEntityTest {

    @Test
    void testGetters() {
        //Arrange
        UserEntity userEntity = new UserEntity(1L,"Valentina","Santa","123456",
                                            "123456789", LocalDate.of(1987,5,26),"valentina@email.com",
                                          "password123",new RoleEntity(1L, "ADMIN", "ADMIN"));

        // Act & Assert
        assertEquals(1L, userEntity.getId());
        assertEquals("Valentina", userEntity.getName());
        assertEquals("Santa", userEntity.getSurname());
        assertEquals("123456", userEntity.getDniNumber());
        assertEquals("123456789", userEntity.getPhone());
        assertEquals(LocalDate.of(1987,5,26), userEntity.getBirthDate());
        assertEquals("valentina@email.com", userEntity.getMail());
        assertEquals("password123", userEntity.getPassword());
        assertEquals(1L, userEntity.getRoleEntity().getId());
        assertEquals("ADMIN", userEntity.getRoleEntity().getName());
        assertEquals("ADMIN", userEntity.getRoleEntity().getDescription());
    }

    @Test
    void testSetters() {
        //Arrange
        UserEntity userEntity = new UserEntity();

        //Act
        userEntity.setId(1L);
        userEntity.setName("Valentina");
        userEntity.setSurname("Santa");
        userEntity.setDniNumber("123456");
        userEntity.setPhone("123456789");
        userEntity.setPassword("password123");
        userEntity.setMail("valentina@email.com");
        userEntity.setBirthDate(LocalDate.of(1987,5,26));
        userEntity.setRoleEntity(new RoleEntity(1L, "ADMIN", "ADMIN"));

        //Assert
        assertEquals(1L, userEntity.getId());
        assertEquals("Valentina", userEntity.getName());
        assertEquals("Santa", userEntity.getSurname());
        assertEquals("123456", userEntity.getDniNumber());
        assertEquals("123456789", userEntity.getPhone());
        assertEquals("password123", userEntity.getPassword());
        assertEquals("valentina@email.com", userEntity.getMail());
        assertEquals(LocalDate.of(1987,5,26), userEntity.getBirthDate());
        assertEquals(1L, userEntity.getRoleEntity().getId());
        assertEquals("ADMIN", userEntity.getRoleEntity().getName());
        assertEquals("ADMIN", userEntity.getRoleEntity().getDescription());

    }

}