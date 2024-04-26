package com.appTeste.domain.usecases.user;

import com.appTeste.domain.exception.ValidationException;
import com.appTeste.domain.model.Usuario;
import com.appTeste.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioCadastroUseCase {

    @Autowired
    private UserRepository userRepository;

    public Usuario execute(Usuario usuario) {
        if (userRepository.getByLogin(usuario.getLogin()).getLogin() != null)
            throw new ValidationException("Usuário indisponivel", "10001");

        String encPassword = new BCryptPasswordEncoder().encode(usuario.getPassword());
        Usuario newUsuario = new Usuario(usuario.getLogin(), encPassword, usuario.getRole());
        return userRepository.create(newUsuario);
    }
}
