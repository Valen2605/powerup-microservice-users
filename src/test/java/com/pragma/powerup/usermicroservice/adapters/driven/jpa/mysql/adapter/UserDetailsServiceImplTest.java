package com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.adapter;

import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.RoleEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.entity.UserEntity;
import com.pragma.powerup.usermicroservice.adapters.driven.jpa.mysql.repositories.IUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.*;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)

class UserDetailsServiceImplTest {

    @Mock
    private IUserRepository userRepository;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shouldThrowUsernameNotFoundExceptionWhenUserIsNotFound() {
        // Arrange
        String documentID = "12345678";
        Mockito.when(userRepository.findByDniNumber(documentID)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(documentID));
    }

    @Test
    void shouldThrowUsernameNotFoundExceptionWhenNoUserIsFoundById() {
        // Arrange
        String documentID = "12345678";
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        Mockito.when(userRepository.findByDniNumber(documentID)).thenReturn(Optional.of(userEntity));
        Mockito.when(userRepository.findAllById(userEntity.getId())).thenReturn(Collections.emptyList());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> userDetailsService.loadUserByUsername(documentID));
    }

    @Test
    void shouldReturnCorrectUserAndRoles() {
        // Arrange
        String documentID = "12345678";
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(1L);
        roleEntity.setName("ROLE_ADMIN");
        roleEntity.setDescription("ROLE_ADMIN");
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setRoleEntity(roleEntity);
        Mockito.when(userRepository.findByDniNumber(documentID)).thenReturn(Optional.of(userEntity));
        Mockito.when(userRepository.findAllById(userEntity.getId())).thenReturn(Collections.singletonList(userEntity));

        // Act
        UserDetails userDetails = userDetailsService.loadUserByUsername(documentID);

        // Assert
        assertEquals(userDetails.getUsername(), userEntity.getDniNumber());
        assertEquals(userDetails.getPassword(), userEntity.getPassword());
        assertTrue(userDetails.getAuthorities().contains(new SimpleGrantedAuthority(roleEntity.getName())));
    }

}