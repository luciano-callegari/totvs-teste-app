package com.appTeste.application.gateway;

import com.appTeste.application.repository.entity.ContaEntity;
import com.appTeste.application.repository.entity.UserEntity;
import com.appTeste.application.repository.jpa.UserRepositoryJpa;
import com.appTeste.domain.model.Usuario;
import com.appTeste.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserRepositoryGateway implements UserRepository {

    @Autowired
    private UserRepositoryJpa userRepositoryJpa;

    @Override
    public UserDetails loadUserByUserName(String login) {
        return userRepositoryJpa.findByLogin(login);
    }

    @Override
    public Usuario create(Usuario usuario) {
        UserEntity userEntity = UserEntity.fromUsuario(usuario);
        return userRepositoryJpa.save(userEntity).toUsuario();
    }

    @Override
    public Usuario getByLogin(String login) {
        UserEntity userEntity = userRepositoryJpa.getByLogin(login);

        if (userEntity == null) {
            return new Usuario();
        }

        return userEntity.toUsuario();
    }
}
