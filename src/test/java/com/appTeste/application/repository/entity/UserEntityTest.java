package com.appTeste.application.repository.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.appTeste.domain.model.Usuario;
import com.appTeste.domain.model.types.UserRole;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;

class UserEntityTest {
    /**
     * Method under test: {@link UserEntity#getAuthorities()}
     */
    @Test
    void testGetAuthorities() {
        // Arrange and Act
        Collection<? extends GrantedAuthority> actualAuthorities = (new UserEntity()).getAuthorities();

        // Assert
        assertEquals(1, actualAuthorities.size());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) actualAuthorities).get(0).getAuthority());
    }

    /**
     * Method under test: {@link UserEntity#getAuthorities()}
     */
    @Test
    void testGetAuthorities2() {
        // Arrange
        UserEntity buildResult = UserEntity.builder()
                .id("42")
                .login("Login")
                .password("senha")
                .role(UserRole.ADMIN)
                .build();

        // Act
        Collection<? extends GrantedAuthority> actualAuthorities = buildResult.getAuthorities();

        // Assert
        assertEquals(2, actualAuthorities.size());
        assertEquals("ROLE_ADMIN", ((List<? extends GrantedAuthority>) actualAuthorities).get(0).getAuthority());
        assertEquals("ROLE_USER", ((List<? extends GrantedAuthority>) actualAuthorities).get(1).getAuthority());
    }

    /**
     * Method under test: {@link UserEntity#fromUsuario(Usuario)}
     */
    @Test
    void testFromUsuario() {
        // Arrange and Act
        UserEntity actualFromUsuarioResult = UserEntity.fromUsuario(new Usuario("Login", "senha", UserRole.ADMIN));

        // Assert
        assertEquals("Login", actualFromUsuarioResult.getLogin());
        assertEquals("senha", actualFromUsuarioResult.getPassword());
        assertNull(actualFromUsuarioResult.getId());
        assertEquals(UserRole.ADMIN, actualFromUsuarioResult.getRole());
    }

    /**
     * Method under test: {@link UserEntity#toUsuario()}
     */
    @Test
    void testToUsuario() {
        // Arrange and Act
        Usuario actualToUsuarioResult = (new UserEntity()).toUsuario();

        // Assert
        assertNull(actualToUsuarioResult.getRole());
        assertNull(actualToUsuarioResult.getLogin());
        assertNull(actualToUsuarioResult.getPassword());
    }
    
    @Test
    void testGettersAndSetters() {
        // Arrange
        UserEntity userEntity = new UserEntity();

        // Act
        String actualUsername = userEntity.getUsername();
        boolean actualIsAccountNonExpiredResult = userEntity.isAccountNonExpired();
        boolean actualIsAccountNonLockedResult = userEntity.isAccountNonLocked();
        boolean actualIsCredentialsNonExpiredResult = userEntity.isCredentialsNonExpired();

        // Assert
        assertNull(actualUsername);
        assertTrue(actualIsAccountNonExpiredResult);
        assertTrue(actualIsAccountNonLockedResult);
        assertTrue(actualIsCredentialsNonExpiredResult);
        assertTrue(userEntity.isEnabled());
    }
}
