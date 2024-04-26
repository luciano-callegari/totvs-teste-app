package com.appTeste.domain.repository;

import com.appTeste.domain.model.Usuario;
import org.springframework.security.core.userdetails.UserDetails;

public interface UserRepository {
    UserDetails loadUserByUserName(String login);
    Usuario create(Usuario usuario);
    Usuario getByLogin(String login);
}
