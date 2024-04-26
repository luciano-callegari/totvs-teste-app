package com.appTeste.application.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.appTeste.application.repository.entity.UserEntity;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {TokenService.class})
@ExtendWith(SpringExtension.class)
class TokenServiceTest {
    @Autowired
    private TokenService tokenService;

    /**
     * Method under test: {@link TokenService#gerarToken(UserEntity)}
     */
    @Test
    void testGerarToken() {
        // Arrange
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getLogin()).thenReturn("Login");

        // Act
        tokenService.gerarToken(userEntity);

        // Assert
        verify(userEntity).getLogin();
    }

    /**
     * Method under test: {@link TokenService#gerarToken(UserEntity)}
     */
    @Test
    void testGerarToken2() {
        // Arrange
        UserEntity userEntity = mock(UserEntity.class);
        when(userEntity.getLogin()).thenThrow(new JWTCreationException("An error occurred", new Throwable()));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> tokenService.gerarToken(userEntity));
        verify(userEntity).getLogin();
    }

    /**
     * Method under test: {@link TokenService#validarToken(String)}
     */
    @Test
    void testValidarToken() {
        // Arrange, Act and Assert
        assertEquals("", tokenService.validarToken("ABC123"));
        assertEquals("", tokenService.validarToken(TokenService.AUTH_API));
    }
}
