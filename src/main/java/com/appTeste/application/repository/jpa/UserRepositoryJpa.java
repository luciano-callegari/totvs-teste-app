package com.appTeste.application.repository.jpa;

import com.appTeste.application.repository.entity.UserEntity;
import com.appTeste.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepositoryJpa extends JpaRepository<UserEntity, String> {

    UserDetails findByLogin(String login);

    @Query("SELECT u FROM users u WHERE login = :login")
    Optional<UserEntity> getByLogin(String login);
}
