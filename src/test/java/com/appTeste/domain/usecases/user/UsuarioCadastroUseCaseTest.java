package com.appTeste.domain.usecases.user;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.appTeste.domain.exception.ValidationException;
import com.appTeste.domain.model.Usuario;
import com.appTeste.domain.model.types.UserRole;
import com.appTeste.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UsuarioCadastroUseCase.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UsuarioCadastroUseCaseTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UsuarioCadastroUseCase usuarioCadastroUseCase;

    /**
     * Method under test: {@link UsuarioCadastroUseCase#execute(Usuario)}
     */
    @Test
    void testExecute() {
        // Arrange
        Usuario buildResult = Usuario.builder().login("Login").password("senha").role(UserRole.ADMIN).build();
        when(userRepository.getByLogin(Mockito.<String>any())).thenReturn(buildResult);

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> usuarioCadastroUseCase.execute(new Usuario("Login", "senha", UserRole.ADMIN)));
        verify(userRepository).getByLogin(eq("Login"));
    }

    /**
     * Method under test: {@link UsuarioCadastroUseCase#execute(Usuario)}
     */
    @Test
    void testExecute2() {
        // Arrange
        Usuario.UsuarioBuilder usuarioBuilder = mock(Usuario.UsuarioBuilder.class);
        when(usuarioBuilder.login(Mockito.<String>any())).thenReturn(Usuario.builder());
        Usuario buildResult = usuarioBuilder.login("Login").password("senha").role(UserRole.ADMIN).build();
        Usuario buildResult2 = Usuario.builder().login("Login").password("senha").role(UserRole.ADMIN).build();
        when(userRepository.create(Mockito.<Usuario>any())).thenReturn(buildResult2);
        when(userRepository.getByLogin(Mockito.<String>any())).thenReturn(buildResult);

        // Act
        usuarioCadastroUseCase.execute(new Usuario("Login", "senha", UserRole.ADMIN));

        // Assert
        verify(usuarioBuilder).login(eq("Login"));
        verify(userRepository).create(isA(Usuario.class));
        verify(userRepository).getByLogin(eq("Login"));
    }

    /**
     * Method under test: {@link UsuarioCadastroUseCase#execute(Usuario)}
     */
    @Test
    void testExecute3() {
        // Arrange
        Usuario.UsuarioBuilder usuarioBuilder = mock(Usuario.UsuarioBuilder.class);
        when(usuarioBuilder.login(Mockito.<String>any())).thenReturn(Usuario.builder());
        Usuario buildResult = usuarioBuilder.login("Login").password("senha").role(UserRole.ADMIN).build();
        when(userRepository.create(Mockito.<Usuario>any()))
                .thenThrow(new ValidationException("An error occurred", "An error occurred"));
        when(userRepository.getByLogin(Mockito.<String>any())).thenReturn(buildResult);

        // Act and Assert
        assertThrows(ValidationException.class,
                () -> usuarioCadastroUseCase.execute(new Usuario("Login", "senha", UserRole.ADMIN)));
        verify(usuarioBuilder).login(eq("Login"));
        verify(userRepository).create(isA(Usuario.class));
        verify(userRepository).getByLogin(eq("Login"));
    }
}
