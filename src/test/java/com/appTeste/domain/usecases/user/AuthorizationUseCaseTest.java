package com.appTeste.domain.usecases.user;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.appTeste.application.repository.entity.UserEntity;
import com.appTeste.domain.model.types.UserRole;
import com.appTeste.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthorizationUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AuthorizationUseCaseTest {
    @Autowired
    private AuthorizationUseCase authorizationUseCase;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link AuthorizationUseCase#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        // Arrange
        UserEntity userEntity = new UserEntity("42", "Login", "senha", UserRole.ADMIN);

        when(userRepository.loadUserByUserName(Mockito.<String>any())).thenReturn(userEntity);

        // Act
        UserDetails actualLoadUserByUsernameResult = authorizationUseCase.loadUserByUsername("janedoe");

        // Assert
        verify(userRepository).loadUserByUserName(eq("janedoe"));
        assertSame(userEntity, actualLoadUserByUsernameResult);
    }

    /**
     * Method under test: {@link AuthorizationUseCase#loadUserByUsername(String)}
     */
    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        // Arrange
        when(userRepository.loadUserByUserName(Mockito.<String>any())).thenThrow(new UsernameNotFoundException("Msg"));

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> authorizationUseCase.loadUserByUsername("janedoe"));
        verify(userRepository).loadUserByUserName(eq("janedoe"));
    }
}
