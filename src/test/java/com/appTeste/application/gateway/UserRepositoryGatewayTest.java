package com.appTeste.application.gateway;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.appTeste.application.repository.entity.UserEntity;
import com.appTeste.application.repository.jpa.UserRepositoryJpa;
import com.appTeste.domain.model.Usuario;
import com.appTeste.domain.model.types.UserRole;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserRepositoryGateway.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserRepositoryGatewayTest {
    @Autowired
    private UserRepositoryGateway userRepositoryGateway;

    @MockBean
    private UserRepositoryJpa userRepositoryJpa;

    /**
     * Method under test: {@link UserRepositoryGateway#loadUserByUserName(String)}
     */
    @Test
    void testLoadUserByUserName() {
        // Arrange
        UserEntity userEntity = new UserEntity("42", "Login", "senha", UserRole.ADMIN);

        when(userRepositoryJpa.findByLogin(Mockito.<String>any())).thenReturn(userEntity);

        // Act
        UserDetails actualLoadUserByUserNameResult = userRepositoryGateway.loadUserByUserName("Login");

        // Assert
        verify(userRepositoryJpa).findByLogin(eq("Login"));
        assertSame(userEntity, actualLoadUserByUserNameResult);
    }

    /**
     * Method under test: {@link UserRepositoryGateway#create(Usuario)}
     */
    @Test
    void testCreate() {
        // Arrange
        when(userRepositoryJpa.save(Mockito.<UserEntity>any())).thenReturn(new UserEntity());

        // Act
        Usuario actualCreateResult = userRepositoryGateway.create(new Usuario("Login", "senha", UserRole.ADMIN));

        // Assert
        verify(userRepositoryJpa).save(isA(UserEntity.class));
        assertNull(actualCreateResult.getRole());
        assertNull(actualCreateResult.getLogin());
        assertNull(actualCreateResult.getPassword());
    }

    /**
     * Method under test: {@link UserRepositoryGateway#create(Usuario)}
     */
    @Test
    void testCreate2() {
        // Arrange
        UserEntity userEntity = mock(UserEntity.class);
        Usuario buildResult = Usuario.builder().login("Login").password("senha").role(UserRole.ADMIN).build();
        when(userEntity.toUsuario()).thenReturn(buildResult);
        when(userRepositoryJpa.save(Mockito.<UserEntity>any())).thenReturn(userEntity);

        // Act
        userRepositoryGateway.create(new Usuario("Login", "senha", UserRole.ADMIN));

        // Assert
        verify(userEntity).toUsuario();
        verify(userRepositoryJpa).save(isA(UserEntity.class));
    }

    /**
     * Method under test: {@link UserRepositoryGateway#create(Usuario)}
     */
    @Test
    void testCreate3() {
        // Arrange
        UserEntity userEntity = mock(UserEntity.class);
        Usuario buildResult = Usuario.builder().login("Login").password("senha").role(UserRole.ADMIN).build();
        when(userEntity.toUsuario()).thenReturn(buildResult);
        when(userRepositoryJpa.save(Mockito.<UserEntity>any())).thenReturn(userEntity);
        Usuario usuario = mock(Usuario.class);
        when(usuario.getRole()).thenReturn(UserRole.ADMIN);
        when(usuario.getLogin()).thenReturn("Login");
        when(usuario.getPassword()).thenReturn("senha");

        // Act
        userRepositoryGateway.create(usuario);

        // Assert
        verify(userEntity).toUsuario();
        verify(usuario).getLogin();
        verify(usuario).getPassword();
        verify(usuario).getRole();
        verify(userRepositoryJpa).save(isA(UserEntity.class));
    }

    /**
     * Method under test: {@link UserRepositoryGateway#getByLogin(String)}
     */
    @Test
    void testGetByLogin() {
        // Arrange
        when(userRepositoryJpa.getByLogin(Mockito.<String>any())).thenReturn(new UserEntity());

        // Act
        Usuario actualByLogin = userRepositoryGateway.getByLogin("Login");

        // Assert
        verify(userRepositoryJpa).getByLogin(eq("Login"));
        assertNull(actualByLogin.getRole());
        assertNull(actualByLogin.getLogin());
        assertNull(actualByLogin.getPassword());
    }

    /**
     * Method under test: {@link UserRepositoryGateway#getByLogin(String)}
     */
    @Test
    void testGetByLogin2() {
        // Arrange
        when(userRepositoryJpa.getByLogin(Mockito.<String>any())).thenReturn(null);

        // Act
        userRepositoryGateway.getByLogin("Login");

        // Assert
        verify(userRepositoryJpa).getByLogin(eq("Login"));
    }

    /**
     * Method under test: {@link UserRepositoryGateway#getByLogin(String)}
     */
    @Test
    void testGetByLogin3() {
        // Arrange
        UserEntity userEntity = mock(UserEntity.class);
        Usuario buildResult = Usuario.builder().login("Login").password("senha").role(UserRole.ADMIN).build();
        when(userEntity.toUsuario()).thenReturn(buildResult);
        when(userRepositoryJpa.getByLogin(Mockito.<String>any())).thenReturn(userEntity);

        // Act
        userRepositoryGateway.getByLogin("Login");

        // Assert
        verify(userEntity).toUsuario();
        verify(userRepositoryJpa).getByLogin(eq("Login"));
    }
}
